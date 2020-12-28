package com.example.topcar.config;

import com.example.topcar.repository.TopCarDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepoConfig {
    @Bean
    public TopCarDao topCarDao() {

        return new TopCarDao("topcar.csv");
    }
}
