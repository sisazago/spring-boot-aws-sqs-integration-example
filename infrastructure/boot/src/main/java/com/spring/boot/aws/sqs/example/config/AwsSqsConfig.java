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

    @Value("${config.aws.region.static}")
    private String awsRegion;

    @Value("${config.aws.credentials.access-key}")
    private String awsAccessKey;

    @Value("${config.aws.credentials.secret-key}")
    private String awsSecretKey;

    @Value("${sqs.instance.local}")
    private Boolean localInstance;

    @Value("${sqs.queue.url}")
    private String sqsQueueUrl;

    @Bean
    public AmazonSQS amazonSQS(){
        // Validate if you are running the sqs services locally and if this is the case use your local configuration.
        if(localInstance != null && localInstance.equals(Boolean.TRUE)){
            return AmazonSQSClient.builder()
                    .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(sqsQueueUrl, awsRegion))
                    .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKey, awsSecretKey)))
                    .build();
        }else{
            return AmazonSQSClient.builder()
                    .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(sqsQueueUrl, awsRegion))
                    .withCredentials(new InstanceProfileCredentialsProvider(false))
                    .build();
        }
    }

}
