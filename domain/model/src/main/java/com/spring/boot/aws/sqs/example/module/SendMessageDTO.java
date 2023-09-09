package com.spring.boot.aws.sqs.example.module;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SendMessageDTO {

    private String message;

    private LocalDate date;
}
