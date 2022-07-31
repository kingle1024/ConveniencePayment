package com.convenience.pay.config;

import com.convenience.pay.service.*;

import java.util.Arrays;
import java.util.HashSet;

public class ApplicationConfig { // application 전체를 관리
    public ConveniencePayService conveniencePayServiceDiscountConvenience(){
        return new ConveniencePayService(
                new HashSet<>(
                    Arrays.asList(new MoneyAdapter(), new CardAdapter())
                ),
                new DiscountByConvenience()
        );
    }
    public ConveniencePayService conveniencePayServiceDiscountPayMethod(){
        return new ConveniencePayService(
                new HashSet<>(
                        Arrays.asList(new MoneyAdapter(), new CardAdapter())
                ),
                new DiscountByPayMethod()
        );
    }
}
