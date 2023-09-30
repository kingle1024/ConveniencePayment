package com.convenience.pay.config;

import com.convenience.pay.PayApplication;
import com.convenience.pay.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
@ComponentScan(basePackageClasses = PayApplication.class)
@Configuration // 기본적으로 스프링부트에서 포함해준다.
public class ApplicationConfig { // application 전체를 관리
    @Autowired
    private ApplicationContext applicationConxt;
    public void getResource() throws IOException{
        Resource resource = applicationConxt.getResource("myTemplate.txt");
        System.out.println(resource.contentLength() +"");
    }
}
