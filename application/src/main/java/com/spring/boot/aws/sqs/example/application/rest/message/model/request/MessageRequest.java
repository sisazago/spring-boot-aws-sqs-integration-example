package com.spring.boot.aws.sqs.example.application.rest.message.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequest {

    @NotNull(message = "Message cannot be null")
    private String message;

    private LocalDate date;

}
