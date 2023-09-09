package com.spring.boot.aws.sqs.example.application.rest.message.controller.impl;


import com.spring.boot.aws.sqs.example.application.rest.message.controller.SendMessageController;
import com.spring.boot.aws.sqs.example.application.rest.message.mapper.MessageMapper;
import com.spring.boot.aws.sqs.example.application.rest.message.model.request.MessageRequest;
import com.spring.boot.aws.sqs.example.application.rest.message.model.response.MessageResponse;
import com.spring.boot.aws.sqs.example.module.SendMessageResultDTO;
import com.spring.boot.aws.sqs.example.module.ex.controller.RestException;
import com.spring.boot.aws.sqs.example.service.message.SendMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/message")
public class SendMessageControllerImpl implements SendMessageController {

    private final SendMessageService sendMessageService;

    private final MessageMapper mapper;

    @Override
    @PostMapping
    public ResponseEntity<MessageResponse> sendMessage(@RequestBody MessageRequest message, BindingResult result) {
        if(!result.hasErrors()){
            SendMessageResultDTO resultDTO = sendMessageService.sendMessage(mapper.toDTO(message));
            return ResponseEntity.ok(mapper.toResponse(resultDTO));
        }
        throw new RestException("The information is not complete", HttpStatus.UNPROCESSABLE_ENTITY, "10006");
    }
}
