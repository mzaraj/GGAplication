package com.example.mzaraj.common.error.response;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Getter
@RequiredArgsConstructor
public class ErrorResponse {

    private final String message;
    private final String description;
    private List<FieldErrorResponse> fieldErrors;

    public ErrorResponse(String message) {
        this(message, null);
    }

    public void add(String objectName, String field, String message) {
        if (isNull(fieldErrors)) {
            fieldErrors = new ArrayList<>();
        }
        fieldErrors.add(new FieldErrorResponse(objectName, field, message));
    }
}
