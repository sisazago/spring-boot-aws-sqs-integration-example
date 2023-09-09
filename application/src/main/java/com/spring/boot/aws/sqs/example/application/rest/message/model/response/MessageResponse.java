package com.spring.boot.aws.sqs.example.application.rest.message.model.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageResponse {

    private String messageId;

    private String awsStatus;
}
