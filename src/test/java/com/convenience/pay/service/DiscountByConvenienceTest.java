package com.convenience.pay.service;

import com.convenience.pay.dto.PayMethodType;
import com.convenience.pay.dto.PayRequest;
import com.convenience.pay.type.ConvenienceType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiscountByConvenienceTest {
    DiscountByConvenience discountByConvenience = new DiscountByConvenience();
    @Test
    void discount(){
        //given
        PayRequest payRequestG25 =
                new PayRequest(PayMethodType.CARD, ConvenienceType.G25, 1000 );
        PayRequest payRequestGU =
                new PayRequest(PayMethodType.CARD, ConvenienceType.GU, 1000 );
        PayRequest payRequestSEVEN =
                new PayRequest(PayMethodType.CARD, ConvenienceType.SEVEN, 1000 );

        //then
        Integer disCountedAmountG25 =
                discountByConvenience.getDiscountedAmount(payRequestG25);
        Integer disCountedAmountGU =
                discountByConvenience.getDiscountedAmount(payRequestGU);
        Integer disCountedAmountSEVEN =
                discountByConvenience.getDiscountedAmount(payRequestSEVEN);

        //when
        assertEquals(disCountedAmountG25, 800);
        assertEquals(disCountedAmountGU, 900);
        assertEquals(disCountedAmountSEVEN, 1000);
    }
}