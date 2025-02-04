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


delete from PAYMENT where PAYMENT_IDX =
                          99;

insert into PAYMENT (
    MATCH_IDX, SELLER_IDX, BUYER_IDX, PAYMENT_METHOD, PAYMENT_AMOUNT,
    PAYMENT_DATE, PAYMENT_STATUS, MERCHANT_UID, IMP_UID
)
values (27, 1, 4, 'card', 1000,
                   CURRENT_TIMESTAMP, 'paid', 'merchant_27_1', 'imp_111'
       );

select * from PAYMENT where PAYMENT_STATUS='paid' and SELLER_IDX = 1;