package com.Shoots.service;

import com.Shoots.domain.Payment;

import java.util.List;
import java.util.Map;

public interface PaymentService {
    void insertPayment(Payment payment);

    boolean hasPaidForMatch(int idx, int matchIdx);

    int getPlayerCount(int matchIdx);

    List<Map<String, Object>> getPaymentListById(Integer idx);
}
