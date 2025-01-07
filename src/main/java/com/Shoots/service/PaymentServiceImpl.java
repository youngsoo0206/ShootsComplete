package com.Shoots.service;

import com.Shoots.domain.Payment;
import com.Shoots.mybatis.mapper.PaymentMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService {

    private PaymentMapper dao;

    public PaymentServiceImpl(PaymentMapper dao) {
        this.dao = dao;
    }

    @Override
    public void insertPayment(Payment payment) {
        dao.insertPayment(payment);
    }

    @Override
    public boolean hasPaidForMatch(int idx, int match_idx) {
        return dao.hasPaidForMatch(idx, match_idx);
    }

    @Override
    public int getPlayerCount(int match_idx) {
        return dao.getPlayerCount(match_idx);
    }

    @Override
    public List<Map<String, Object>> getPaymentListById(Integer idx) {
        return dao.getPaymentListById(idx);
    }
}
