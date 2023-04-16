package io.cygnuxltb.console.controller.base;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class ResponseBean {

    private int code;
    private String message;
    private boolean isArray;
    private Object data;

}
