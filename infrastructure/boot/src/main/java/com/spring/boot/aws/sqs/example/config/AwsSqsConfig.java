package com.spring.boot.aws.sqs.example.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsSqsConfig {

    @Value("${sqs.queue.region}")
    private String region;

    @Value("${config.aws.credentials.access-key}")
    private String awsAccessKey;

    @Value("${config.aws.credentials.secret-key}")
    private String awsSecretKey;

    @Value("${sqs.instance.local}")
    private Boolean localInstance;

    @Value("${sqs.queue.url}")
    private String awsSqsEndpoint;

    @Bean
    public AmazonSQS amazonSQSS() {
        if(localInstance != null && localInstance) {
            return AmazonSQSClient.builder()
                    .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(awsSqsEndpoint, region))
                    .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKey, awsSecretKey)))
                    .build();
        }else{
            return AmazonSQSClient.builder()
                    .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(awsSqsEndpoint, region))
                    .withCredentials(new InstanceProfileCredentialsProvider(false))
                    .build();
        }
    }

}
