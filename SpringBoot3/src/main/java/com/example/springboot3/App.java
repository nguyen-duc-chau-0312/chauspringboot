package com.example.springboot3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.SQLOutput;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        System.out.println("> Truoc khi Ioc Container duoc khoi tao");
        ApplicationContext context = SpringApplication.run(App.class, args);
        System.out.println("> Sau khi Ioc Container duoc khoi tao");

        //Khi chay xong, luc nay con text chua cac Bean duoc danh dau Component
        Girl girl = context.getBean(Girl.class);

        System.out.println("Truoc khi Ioc Container destroy  Girl ");
        ((ConfigurableApplicationContext)context).getBeanFactory().destroyBean(girl);
        System.out.println("Sau khi Ioc Container destroy Girl");
    }
}
