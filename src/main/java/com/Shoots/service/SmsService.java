package com.Shoots.service;

import org.springframework.http.ResponseEntity;

public interface SmsService {
    ResponseEntity<String> sendSms();

    ResponseEntity<String> sendSms(String to);

    //MultipleDetailMessageSentResponse sendSmsMany(ArrayList<Message> messageList, boolean b, boolean b1);

    ResponseEntity<String> sendMany();
}
