CREATE TABLE PAYMENT (
     PAYMENT_IDX INT AUTO_INCREMENT PRIMARY KEY,
     MATCH_IDX INT NOT NULL,
     SELLER_IDX INT NOT NULL,
     BUYER_IDX INT NOT NULL,
     PAYMENT_METHOD VARCHAR(10) NOT NULL,
     PAYMENT_AMOUNT DECIMAL(10, 2) NOT NULL,
     PAYMENT_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
     PAYMENT_STATUS VARCHAR(10) NOT NULL,
     MERCHANT_UID VARCHAR(30) NOT NULL,
     IMP_UID VARCHAR(30) NOT NULL
);

#                          FOREIGN KEY (match_idx) REFERENCES match_post(match_idx),  -- 외래 키 제약
#                          FOREIGN KEY (buyer_idx) REFERENCES regular_user(idx),  -- 외래 키 제약
#                          FOREIGN KEY (seller_idx) REFERENCES business_user(business_idx)  -- 외래 키 제약

select * from PAYMENT;
delete from PAYMENT;
drop table PAYMENT;
