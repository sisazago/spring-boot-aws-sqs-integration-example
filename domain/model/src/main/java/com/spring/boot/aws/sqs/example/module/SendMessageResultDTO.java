package com.spring.boot.aws.sqs.example.module;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SendMessageResultDTO {

    private String messageId;

    private String awsStatus;
}
