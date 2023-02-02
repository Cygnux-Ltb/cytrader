package io.cygnux.console.controller.base;

public record ResponseModel(
        int code,
        String message,
        boolean isArray,
        Object data) {

}
