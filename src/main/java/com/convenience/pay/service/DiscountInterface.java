package com.convenience.pay.service;

import com.convenience.pay.dto.PayRequest;

public interface DiscountInterface {
    Integer getDiscountedAmount(PayRequest payRequest);
}
