package com.ryz.qasystem;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DruidDataSourceAutoConfigure.class})
public class QasystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(QasystemApplication.class, args);
    }

}
