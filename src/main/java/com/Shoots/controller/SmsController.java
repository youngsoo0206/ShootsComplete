package com.Shoots.controller;


import com.Shoots.service.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class SmsController {

    private final SmsService smsService;

    @GetMapping("/send-sms/{to}")
    public ResponseEntity<String> sendSms(
            @PathVariable("to") String to
    ){
        ResponseEntity<String> response = smsService.sendSms(to);
        return response;
    }


    @GetMapping("/send-many")
    public ResponseEntity<String> sendSmsMany() {
        ResponseEntity<String> response = smsService.sendMany();
        return response;
    }


}
