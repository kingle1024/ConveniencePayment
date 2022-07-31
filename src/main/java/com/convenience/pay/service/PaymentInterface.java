package com.convenience.pay.service;

import com.convenience.pay.dto.PayMethodType;
import com.convenience.pay.type.CancelPaymentResult;
import com.convenience.pay.type.PaymentResult;

public interface PaymentInterface {
    PayMethodType getPayMethodType();
    PaymentResult payment(Integer payAmount);
    CancelPaymentResult cancelPayment(Integer cancelAmount);
}
