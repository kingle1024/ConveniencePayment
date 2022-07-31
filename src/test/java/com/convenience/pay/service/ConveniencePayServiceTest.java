package com.convenience.pay.service;

import com.convenience.pay.config.ApplicationConfig;
import com.convenience.pay.dto.PayMethodType;
import com.convenience.pay.type.ConvenienceType;
import com.convenience.pay.dto.PayRequest;
import com.convenience.pay.dto.PayResponse;
import com.convenience.pay.type.PayCancelResult;
import com.convenience.pay.type.PayResult;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class ConveniencePayServiceTest {
    ApplicationConfig applicationConfig = new ApplicationConfig();
//    ConveniencePayService conveniencePayService = applicationConfig.conveniencePayServiceDiscountPayMethod();
    ConveniencePayService conveniencePayService = new ConveniencePayService(
            new HashSet<>(
                    Arrays.asList(new MoneyAdapter(), new CardAdapter())
            ),
            new DiscountByConvenience()
    );

    @Test
    void pay_success(){
        //given
        PayRequest payRequest = new PayRequest(
                PayMethodType.MONEY, ConvenienceType.G25, 50);
        //when
        PayResponse payResponse = conveniencePayService.pay(payRequest);

        //then
        assertEquals(PayResult.SUCCESS, payResponse.getPayResult());
        assertEquals(35, payResponse.getPaidAmount());
    }

    @Test
    void pay_fail(){
        //given
        PayRequest payRequest = new PayRequest(
                PayMethodType.MONEY, ConvenienceType.G25, 1500_001);

        //when
        PayResponse payResponse = conveniencePayService.pay(payRequest);

        //then
        assertEquals(PayResult.FAIL, payResponse.getPayResult());
        assertEquals(0, payResponse.getPaidAmount());
    }

    @Test
    void payCancel_succes(){
        //given
        PayCancelRequest payCancelRequest = new PayCancelRequest(
                PayMethodType.MONEY, ConvenienceType.G25, 100);
        //when
        PayCancelResponse payCancelResponse = conveniencePayService.payCancel(payCancelRequest);
        //then
        assertEquals(PayCancelResult.PAY_CANCEL_SUCCESS,payCancelResponse.getPayCancelResult());
        assertEquals(100, payCancelResponse.getPayCanceledAmount());
    }
    @Test
    void payCancel_fail(){
        //given
        PayCancelRequest payCancelRequest = new PayCancelRequest(
                PayMethodType.MONEY, ConvenienceType.G25, 99);
        //when
        PayCancelResponse payCancelResponse = conveniencePayService.payCancel(payCancelRequest);
        //then
        assertEquals(PayCancelResult.PAY_CANCEL_FAIL, payCancelResponse.getPayCancelResult());
        assertEquals(0, payCancelResponse.getPayCanceledAmount());
    }

}