package com.convenience.pay.service;

import com.convenience.pay.dto.PayMethodType;
import com.convenience.pay.dto.PayRequest;
import com.convenience.pay.type.ConvenienceType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiscountByPayMethodTest {
    DiscountByPayMethod discountByPayMethod = new DiscountByPayMethod();

    @Test
    void discountSuccess(){
        //given
        PayRequest payRequestMoney = new PayRequest(
                PayMethodType.MONEY, ConvenienceType.G25,1000
        );
        PayRequest payRequestCard = new PayRequest(
                PayMethodType.CARD, ConvenienceType.GU, 1000
        );
        //when
        Integer disCountedAmountMoney =
                discountByPayMethod.getDiscountedAmount(payRequestMoney);
        Integer disCountedAmountCard =
                discountByPayMethod.getDiscountedAmount(payRequestCard);

        //then
        assertEquals(disCountedAmountMoney, 700);
        assertEquals(disCountedAmountCard, 1000);
    }
}