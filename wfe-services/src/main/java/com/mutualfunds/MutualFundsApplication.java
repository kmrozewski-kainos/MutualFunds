package com.mutualfunds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.mutualfunds")
public class MutualFundsApplication {

    public static void main(String[] args) {
        new SpringApplication(MutualFundsApplication.class).run(args);
    }
}
