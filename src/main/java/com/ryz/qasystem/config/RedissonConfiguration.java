package com.ryz.qasystem.config;


import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfiguration {
    @Bean
    public Redisson redisson(){
        //单机模式
        Config config = new Config();
        config.useSingleServer().setAddress("redis://121.196.158.139:6379").setDatabase(0);
        return (Redisson) Redisson.create(config);
    }
}
