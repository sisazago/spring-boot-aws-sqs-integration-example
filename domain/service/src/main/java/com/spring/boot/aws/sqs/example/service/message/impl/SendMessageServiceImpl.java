package com.spring.boot.aws.sqs.example.service.message.impl;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.boot.aws.sqs.example.module.SendMessageDTO;
import com.spring.boot.aws.sqs.example.module.SendMessageResultDTO;
import com.spring.boot.aws.sqs.example.module.ex.service.ServiceException;
import com.spring.boot.aws.sqs.example.service.message.SendMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class SendMessageServiceImpl implements SendMessageService {

    private final AmazonSQS amazonSQS;

    @Value("${sqs.queue.url}")
    private String sqsServiceUrl;

    @Value("${sqs.message.group}")
    private String sqsGroupName;


    @Override
    public SendMessageResultDTO sendMessage(SendMessageDTO messageDTO) {
        log.info("[Start][SendMessageServiceImpl][sendMessage]input:{}", messageDTO);

        try{
            ObjectMapper objectMapper = new ObjectMapper();
            SendMessageRequest sendMessageRequest;
            if(StringUtils.isEmpty(sqsGroupName)){
                sendMessageRequest= new SendMessageRequest().withQueueUrl(sqsServiceUrl)
                        .withMessageBody(objectMapper.writeValueAsString(messageDTO));
            }else{
                sendMessageRequest = new SendMessageRequest().withQueueUrl(sqsServiceUrl)
                        .withMessageBody(objectMapper.writeValueAsString(messageDTO))
                        .withMessageGroupId(sqsGroupName)
                        .withMessageDeduplicationId(UUID.randomUUID().toString());
            }

            SendMessageResult result = amazonSQS.sendMessage(sendMessageRequest);

            log.info("[End][SendMessageServiceImpl][sendMessage]output: [messageId:{}]", result.getMessageId());
            return new SendMessageResultDTO().builder()
                    .messageId(result.getMessageId())
                    .awsStatus(String.valueOf(HttpStatus.SC_OK))
                    .build();
        }catch (Exception e){
            log.error("[Error][SendMessageServiceImpl][sendMessage]", e);
            throw new ServiceException(e.getMessage(), e, "0000001");
        }
    }
}
