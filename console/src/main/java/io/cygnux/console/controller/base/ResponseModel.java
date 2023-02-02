package io.cygnux.console.controller.base;

import java.util.Collection;

public record ResponseModel(
        int code,
        String message,
        boolean isArray,
        Object data) {

}
