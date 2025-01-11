package com.Shoots.controller;

import com.Shoots.domain.Payment;
import com.Shoots.service.PaymentHistoryService;
import com.Shoots.service.PaymentHistoryServiceImpl;
import com.Shoots.service.PaymentService;
import lombok.AllArgsConstructor;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;


@Controller
@RequestMapping("/refund")
@AllArgsConstructor
public class RefundController {

    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
    private final PaymentHistoryService paymentHistoryService;
    private PaymentService paymentService;

    @PostMapping("/refundProcess")
    public ResponseEntity<?> refundProcess(@RequestBody Payment payment) {
        try {
            HttpClient client = HttpClient.newHttpClient();

            String jsonRequestBody = "{"
                    + "\"imp_key\": \"4374671001417615\","
                    + "\"imp_secret\": \"ukuBMPxlLnuayHqeO6MTTUy82qMDLGHAzpQUsoLJyPsK8xUkqw5JzIewNqgI7BlCJ8NFNcRckg9YPpFE\""
                    + "}";

            HttpRequest requestToken = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.iamport.kr/users/getToken"))
                    .header("Content-Type", "application/json")
                    .method("POST", HttpRequest.BodyPublishers.ofString(jsonRequestBody, StandardCharsets.UTF_8))
                    .build();

            HttpResponse<String> responseToken = client.send(requestToken, HttpResponse.BodyHandlers.ofString());

            if (responseToken.statusCode() == 200) {
                String accessToken = extractAccessToken(responseToken.body());

                String jsonRefundRequest = "{"
                        + "\"imp_uid\": \"" + payment.getImp_uid() + "\","
                        + "\"merchant_uid\": \"" + payment.getMerchant_uid() + "\","
                        + "\"refund_amount\": " + payment.getPayment_amount()
                        + "}";

                HttpRequest requestRefund = HttpRequest.newBuilder()
                        .uri(URI.create("https://api.iamport.kr/payments/cancel"))
                        .header("Content-Type", "application/json")
                        .header("Authorization", "Bearer " + accessToken)  // Bearer 토큰 사용
                        .method("POST", HttpRequest.BodyPublishers.ofString(jsonRefundRequest, StandardCharsets.UTF_8))
                        .build();

                HttpResponse<String> responseRefund = client.send(requestRefund, HttpResponse.BodyHandlers.ofString());

                if (responseRefund.statusCode() == 200) {
                    logger.info(">>>>>>>>>> Refund Process Success");
                    paymentService.updatePayment(payment.getPayment_idx());

                    paymentHistoryService.insertHistory(payment);

                    JSONObject response = new JSONObject();
                    response.put("success", true);
                    response.put("message", "Refund success");


                    return ResponseEntity.ok(response.toString());
                } else {
                    logger.error(">>>>>>>>>> Refund failed : " + responseRefund.body());
                    return new ResponseEntity<>("{\"message\": \"" + responseRefund.body() + "\"}", HttpStatus.INTERNAL_SERVER_ERROR);
                }

            }else {
                logger.error("Failed to get access token: " + responseToken.body());

                return new ResponseEntity<>("Failed to get access token", HttpStatus.UNAUTHORIZED);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    private String extractAccessToken(String responseBody) throws JSONException {
        JSONObject jsonResponse = new JSONObject(responseBody);
        return jsonResponse.getJSONObject("response").getString("access_token");
    }
}
