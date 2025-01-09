package com.Shoots.controller;

import com.Shoots.domain.Payment;
import com.Shoots.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    private PaymentService paymentService;
    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> paymentAdd(@RequestBody Payment payment) {

        Map<String, Object> response = new HashMap<>();

        try {
            payment.setPayment_method("card");
            payment.setPayment_status("SUCCESS");

            paymentService.insertPayment(payment);

            logger.info(">>>>>>>>>> Payment success : buyer = " + payment.getBuyer_idx() + ", Merchant_uid = " + payment.getMerchant_uid());

            response.put("success", true);
            response.put("message", "결제 성공");

            return ResponseEntity.ok(response);

        } catch (Exception e) {

            logger.error(">>>>>>>>>> Payment failed : buyer = " + payment.getBuyer_idx() + ", Merchant_uid = " + payment.getMerchant_uid() + ", error: " + e.getMessage());

            response.put("success", false);
            response.put("message", "결제 처리 중 오류가 발생했습니다.");

            return ResponseEntity.status(500).body(response);
        }
    }
}
