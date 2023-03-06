package io.cygnuxltb.console.controller.base;

public record ResponseModel(
        int code,
        String message,
        boolean isArray,
        Object data) {

}
