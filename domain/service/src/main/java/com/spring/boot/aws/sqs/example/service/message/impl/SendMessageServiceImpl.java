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
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.sql.rowset.serial.SerialException;
import java.util.UUID;

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

            return new SendMessageResultDTO().builder()
                    .messageId(result.getMessageId())
                    .awsStatus(String.valueOf(result.getSdkHttpMetadata().getHttpStatusCode()))
                    .build();
        }catch (Exception e){
            throw new ServiceException(e.getMessage(), e, "0000001");
        }
    }
}
