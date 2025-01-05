package com.Shoots.service;

import com.Shoots.domain.Payment;

public interface PaymentService {
    void insertPayment(Payment payment);

    boolean hasPaidForMatch(int idx, int matchIdx);

    int getPlayerCount(int matchIdx);
}
