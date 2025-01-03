package com.Shoots.mybatis.mapper;

import com.Shoots.domain.Payment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentMapper {
    void insertPayment(Payment payment);

    boolean hasPaidForMatch(int idx, int match_idx);

    int getPlayerCount(int match_idx);
}
