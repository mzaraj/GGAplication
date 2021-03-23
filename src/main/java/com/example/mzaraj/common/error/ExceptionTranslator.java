package com.example.mzaraj.common.error;


import com.example.mzaraj.common.error.response.ErrorResponse;
import com.example.mzaraj.common.exception.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.example.mzaraj.common.error.ErrorConstance.ERR_OBJECT_NOT_FOUND;
import static java.util.Objects.nonNull;
import static org.springframework.http.HttpStatus.NOT_FOUND;


@Slf4j
@ControllerAdvice
public class ExceptionTranslator {

    private static final String NO_MESSAGE_AVAILABLE = "(no message available)";
    private static final String UNSPECIFIED = "(unspecified)";

    private String getMessage(Exception ex) {
        return nonNull(ex.getMessage()) ? ex.getMessage() : NO_MESSAGE_AVAILABLE;
    }

    private String getCause(Exception ex) {
        return nonNull(ex.getCause()) ? String.valueOf(ex.getCause()) : UNSPECIFIED;
    }

    protected void logError(String method, Exception ex) {

        log.error("{}({}) with cause: {} and exception: {}", method, ex.getClass(), getCause(ex), getMessage(ex));
        log.error("Exception ", ex);
    }


    @ResponseBody
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<?> objectNotFoundException(ObjectNotFoundException ex) {
        logError("objectNotFoundError", ex);
        return new ResponseEntity<>(new ErrorResponse(ERR_OBJECT_NOT_FOUND, ex.getLocalizedMessage()),NOT_FOUND);
    }
}
