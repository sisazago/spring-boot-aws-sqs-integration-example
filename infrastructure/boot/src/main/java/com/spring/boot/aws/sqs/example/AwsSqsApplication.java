package com.spring.boot.aws.sqs.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.spring.boot.aws.sqs.example"})
public class AwsSqsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AwsSqsApplication.class, args);
    }
}
