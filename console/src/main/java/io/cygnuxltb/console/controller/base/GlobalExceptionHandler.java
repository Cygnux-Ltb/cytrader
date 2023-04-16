package io.cygnuxltb.console.controller.base;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseBean handleException(Exception e) {
        if (e instanceof NullPointerException)
            return ResponseStatus.NOT_FOUND
                    .response()
                    .setMessage(e.getMessage());
        if (e instanceof IllegalArgumentException)
            return ResponseStatus.BAD_REQUEST
                    .response()
                    .setMessage(e.getMessage());
        return ResponseStatus.INTERNAL_ERROR
                .response();
    }

}