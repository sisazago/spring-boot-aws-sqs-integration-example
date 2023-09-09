package com.spring.boot.aws.sqs.example.application.rest.message.mapper;

import com.spring.boot.aws.sqs.example.application.rest.message.model.request.MessageRequest;
import com.spring.boot.aws.sqs.example.application.rest.message.model.response.MessageResponse;
import com.spring.boot.aws.sqs.example.module.SendMessageDTO;
import com.spring.boot.aws.sqs.example.module.SendMessageResultDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MessageMapper {

    MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);

    SendMessageDTO toDTO(MessageRequest messageRequest);

    MessageResponse toResponse(SendMessageResultDTO sendMessageResultDTO);
}
