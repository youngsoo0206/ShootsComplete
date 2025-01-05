package com.Shoots.service;

import com.Shoots.domain.Payment;
import com.Shoots.mybatis.mapper.MatchMapper;
import com.Shoots.mybatis.mapper.PaymentMapper;
import org.springframework.stereotype.Service;

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
}
