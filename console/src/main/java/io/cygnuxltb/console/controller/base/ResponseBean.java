package io.cygnuxltb.console.controller.base;

public record ResponseBean(
        int code,
        String message,
        boolean isArray,
        Object data) {

}
