package com.leovegas.apiwallet.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class ExceptionResponse {

    private Date timestamp;

    private String message;

    private List<String> details;
}
