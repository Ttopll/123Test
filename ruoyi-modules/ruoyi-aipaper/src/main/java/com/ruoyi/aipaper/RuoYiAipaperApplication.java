package com.ruoyi.aipaper;

import com.ruoyi.common.security.annotation.EnableCustomConfig;
import com.ruoyi.common.security.annotation.EnableRyFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableCustomConfig
@EnableRyFeignClients
@SpringBootApplication
public class RuoYiAipaperApplication {
    public static void main(String[] args) {
        SpringApplication.run(RuoYiAipaperApplication.class, args);
    }
}
