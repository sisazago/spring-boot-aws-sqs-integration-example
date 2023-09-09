package com.spring.boot.aws.sqs.example.application.rest.message.controller;

import com.spring.boot.aws.sqs.example.application.rest.message.model.request.MessageRequest;
import com.spring.boot.aws.sqs.example.application.rest.message.model.response.MessageResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface SendMessageController {


    @ApiOperation(value = "Send message to AWS SQS")
    @ApiResponses({
            @ApiResponse(code = 200, response = MessageResponse.class, message = "OK"),
            @ApiResponse(code = 422, message = "The information is not complete"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    ResponseEntity<MessageResponse> sendMessage(MessageRequest message, BindingResult result);
}
