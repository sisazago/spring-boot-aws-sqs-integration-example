package com.spring.boot.aws.sqs.example.service.message;

import com.spring.boot.aws.sqs.example.module.SendMessageDTO;
import com.spring.boot.aws.sqs.example.module.SendMessageResultDTO;

public interface SendMessageService {

    /**
     * This method is created to send a message to the sqs queue service and return the information related to the response
     * from aws.
     * @param message the message
     * @return {@link SendMessageResultDTO}
     * */
    SendMessageResultDTO sendMessage(SendMessageDTO messageDTO);
}
