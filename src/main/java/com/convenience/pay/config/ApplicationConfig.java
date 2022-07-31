package com.convenience.pay.config;

import com.convenience.pay.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashSet;
@Configuration
public class ApplicationConfig { // application 전체를 관리
    @Bean // Indicates that a method produces a bean to be managed by the Spring container.
    public ConveniencePayService conveniencePayService(){
        return new ConveniencePayService(
                new HashSet<>(
                    Arrays.asList(moneyAdapter(), cardAdapter())
                ),
                discountByConvenience()
        );
    }
    @Bean
    public CardAdapter cardAdapter() {
        return new CardAdapter();
    }
    @Bean
    public MoneyAdapter moneyAdapter() {
        return new MoneyAdapter();
    }

    @Bean
    public DiscountByConvenience discountByConvenience() {
        return new DiscountByConvenience();
    }

    public ConveniencePayService conveniencePayServiceDiscountPayMethod(){
        return new ConveniencePayService(
                new HashSet<>(
                        Arrays.asList(moneyAdapter(), cardAdapter())
                ),
                new DiscountByPayMethod()
        );
    }
}
