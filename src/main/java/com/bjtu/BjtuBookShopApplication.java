package com.bjtu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.bjtu.mapper")
@SpringBootApplication
public class BjtuBookShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(BjtuBookShopApplication.class, args);
    }

}
