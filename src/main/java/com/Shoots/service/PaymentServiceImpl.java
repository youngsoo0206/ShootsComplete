package com.Shoots.service;

import com.Shoots.domain.Payment;
import com.Shoots.mybatis.mapper.PaymentMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService {

    private PaymentMapper dao;

    public PaymentServiceImpl(PaymentMapper dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public void insertPayment(Payment payment) {
        dao.insertPayment(payment);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean hasPaidForMatch(int idx, int match_idx) {
        return dao.hasPaidForMatch(idx, match_idx);
    }

    @Override
    @Transactional(readOnly = true)
    public int getPlayerCount(int match_idx) {
        return dao.getPlayerCount(match_idx);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> getPaymentListById(Integer idx) {
        return dao.getPaymentListById(idx);
    }

    @Override
    public Payment getPaymentInfoById(Integer idx, int match_idx) {
        return dao.getPaymentInfoById(idx, match_idx);
    }

    @Override
    public void updatePayment(int payment_idx) {
        dao.updatePayment(payment_idx);
    }
}
