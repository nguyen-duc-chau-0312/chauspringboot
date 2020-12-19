package com.example.springboot4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(App.class, args);

        //Lay Bean Girl Service
        GirlService girlSerivce = context.getBean(GirlService.class);
        //Lay Random 1 co gai tu tang service
        Girl girl = girlSerivce.getRandomGirl();
        System.out.println(girl);
    }
}
