package com.group4.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@MapperScan("com.group4.server.mapper")
//@EnableScheduling
public class StartApplication {
    public static void main(String[] args){
        SpringApplication.run(StartApplication.class);
    }

}

