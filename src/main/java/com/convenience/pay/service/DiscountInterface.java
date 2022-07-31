package com.convenience.pay.service;

import com.convenience.pay.dto.PayRequest;
import org.springframework.stereotype.Component;

@Component
public interface DiscountInterface {
    Integer getDiscountedAmount(PayRequest payRequest);
}
