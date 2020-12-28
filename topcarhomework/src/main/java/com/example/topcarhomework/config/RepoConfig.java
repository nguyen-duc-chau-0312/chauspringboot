package com.example.topcarhomework.config;

import com.example.topcarhomework.repository.TopCarDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepoConfig {
    @Bean
    public TopCarDao topCarDao() {

        return new TopCarDao("topcar.csv");
    }
}
