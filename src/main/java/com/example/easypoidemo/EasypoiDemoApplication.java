package com.example.easypoidemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.easypoidemo.mapper")
public class EasypoiDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasypoiDemoApplication.class, args);
    }

}
