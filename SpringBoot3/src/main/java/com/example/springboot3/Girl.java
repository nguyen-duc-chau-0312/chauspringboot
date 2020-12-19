package com.example.springboot3;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class Girl {
    @PostConstruct
    public void postConstruct(){
        System.out.println("\t Doi tuong girl sau khi khoi tao xong se chay ham nay");
    }

    @PreDestroy
    public void preDestroy(){
        System.out.println("\t Doi tuong girl truoc khi bi destroy se chay ham nay");
    }
}
