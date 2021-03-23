package com.example.mzaraj.common.error.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FieldErrorResponse {

    private final String objectName;
    private final String field;
    private final String message;
}
