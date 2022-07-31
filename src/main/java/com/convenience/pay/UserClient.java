package com.convenience.pay;

import com.convenience.pay.config.ApplicationConfig;
import com.convenience.pay.dto.PayMethodType;
import com.convenience.pay.dto.PayRequest;
import com.convenience.pay.dto.PayResponse;
import com.convenience.pay.service.ConveniencePayService;
import com.convenience.pay.service.PayCancelRequest;
import com.convenience.pay.service.PayCancelResponse;
import com.convenience.pay.type.ConvenienceType;
import com.convenience.pay.type.PayResult;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserClient {
    public static void main(String[] args) {
        // 사용자 -> 편결이 -> 머니
//        ApplicationContext applicationContext =
//                new AnnotationConfigApplicationContext(ApplicationConfig.class);
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring-config.xml");
        ConveniencePayService conveniencePayService =
                applicationContext.getBean("conveniencePayService",
                ConveniencePayService.class);

        // G25, 결제 1000원
        Integer payAmount = 50;
        PayRequest payRequest = new PayRequest(
                PayMethodType.CARD, ConvenienceType.G25, payAmount);
        PayResponse payResponse = conveniencePayService.pay(payRequest);
        System.out.println(payResponse);

        // G25, 취소 500원
        Integer payCancelAmount = 400;
        PayCancelRequest payCancelRequest = new PayCancelRequest(
                PayMethodType.MONEY, ConvenienceType.G25, payCancelAmount);
        PayCancelResponse payCancelResponse = conveniencePayService.payCancel(payCancelRequest);
        System.out.println(payCancelResponse);
    }
}
