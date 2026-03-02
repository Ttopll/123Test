package com.ruoyi.employ;

import com.ruoyi.common.security.annotation.EnableCustomConfig;
import com.ruoyi.common.security.annotation.EnableRyFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableCustomConfig
@EnableRyFeignClients
@SpringBootApplication
public class RuoYiEmployApplication {
    public static void main(String[] args) {
        SpringApplication.run(RuoYiEmployApplication.class, args);
    }
}
