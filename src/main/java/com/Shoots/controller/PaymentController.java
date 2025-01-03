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
        logger.info("Payment Add Method >>>>>>>>>>>>>>>>>>> payment : " + payment.toString());

        payment.setPayment_method("card");
        payment.setPayment_status("SUCCESS");

        paymentService.insertPayment(payment);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "결제 데이터가 서버에 저장되었습니다.");

        return ResponseEntity.ok(response);
    }
}
