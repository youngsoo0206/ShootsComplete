package com.Shoots.controller;

import com.Shoots.domain.Payment;
import com.Shoots.service.IamportService;
import com.Shoots.service.PaymentHistoryService;
import com.Shoots.service.PaymentService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;


@Controller
@AllArgsConstructor
@RequestMapping("/payment")
public class PaymentController {

    private PaymentService paymentService;
    private PaymentHistoryService paymentHistoryService;
    private IamportService iamportService;
    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @Transactional
    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> paymentAdd(@RequestBody Payment payment) {

        Map<String, Object> response = new HashMap<>();
        logger.info("결제 정보 payment : " + payment.toString());

        try {

            String paymentDetails = iamportService.getPaymentDetails(payment.getImp_uid());

            if (paymentDetails != null && paymentDetails.contains("\"status\":\"paid\"")) {
                logger.info("이미 결제된 정보 : " + payment.getImp_uid());

                payment.setPayment_status("paid");
                insertPaymentHistoryWithoutTransaction(payment);
                paymentService.insertPayment(payment);

                response.put("success", true);
                response.put("message", "이미 결제된 상태입니다.");
                response.put("data", paymentDetails);

                return ResponseEntity.ok(response);
            }

            if (paymentDetails != null && paymentDetails.contains("\"status\":\"failed\"")) {
                logger.info("결제 중 실패 : " + payment.getImp_uid());

                payment.setPayment_status("failed");
                insertPaymentHistoryWithoutTransaction(payment);

                response.put("success", false);
                response.put("message", "결제 중 실패");
                response.put("data", paymentDetails);

                return ResponseEntity.ok(response);
            }


            payment.setPayment_method("card");
            payment.setPayment_status("paid");

            insertPaymentHistoryWithoutTransaction(payment);
            paymentService.insertPayment(payment);

            logger.info("결제 성공 : buyer = " + payment.getBuyer_idx() + ", Merchant_uid = " + payment.getMerchant_uid());

            response.put("success", true);
            response.put("message", "결제 성공");
            response.put("data", payment);

            return ResponseEntity.ok(response);

        } catch (Exception e) {

            logger.error("결제 실패 : buyer = " + payment.getBuyer_idx() + ", Merchant_uid = " + payment.getMerchant_uid() + ", error: " + e.getMessage());

            response.put("success", false);
            response.put("message", "결제 처리 중 오류가 발생했습니다.");
            response.put("error", e.getMessage());

            payment.setPayment_status("fail");

            return ResponseEntity.status(500).body(response);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected void insertPaymentHistoryWithoutTransaction(Payment payment) {
        paymentHistoryService.insertHistory(payment);
    }

    @PostMapping("/checkPayment")
    public ResponseEntity<?> verifyPayment(@RequestBody Map<String, String> request) {
        String impUid = request.get("imp_uid");

        String paymentDetails = iamportService.getPaymentDetails(impUid);

        if (paymentDetails != null) {

            JSONObject jsonResponse = new JSONObject(paymentDetails);
            JSONObject response = jsonResponse.getJSONObject("response");
            String paymentStatus = response.getString("status");

            logger.info("결제 조회 성공 checkPayment : payment details = " + paymentDetails);
            if ("paid".equals(paymentStatus)) {
                logger.info("결제 조회 성공 : imp_uid = " + impUid + " 결제 상태 : paid");
                return ResponseEntity.ok(response);
            } else if ("cancelled".equals(paymentStatus)) {
                logger.info("결제 조회 성공 : imp_uid = " + impUid + " 결제 상태 : cancelled");
                return ResponseEntity.ok(response);
            } else {
                logger.error("결제 상태 오류 : imp_uid = " + impUid + " 결제 상태 : " + paymentStatus);
                return ResponseEntity.status(500).body("결제 상태 오류");
            }
        } else {
            logger.error("결제 조회 실패 imp_uid: " + impUid);
            return ResponseEntity.status(500).body("결제 조회 실패");
        }
    }
}
