package com.ryz.qasystem;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DruidDataSourceAutoConfigure.class})
@MapperScan(basePackages = "com.ryz.qasystem.mapper")
@EnableScheduling
public class QasystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(QasystemApplication.class, args);
    }

}
