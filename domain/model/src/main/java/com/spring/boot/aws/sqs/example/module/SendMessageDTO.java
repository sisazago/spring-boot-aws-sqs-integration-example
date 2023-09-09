package com.spring.boot.aws.sqs.example.module;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SendMessageDTO {

    private String message;

    private Date date;
}
