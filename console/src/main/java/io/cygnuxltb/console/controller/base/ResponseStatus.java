package io.cygnuxltb.console.controller.base;

import java.util.Collection;

public enum ResponseStatus {

    /* 成功状态码 */
    OK(200, "请求成功"),

    CREATED(201, "已创建"),

    UPDATED(204, "已更新"),


    /* 错误状态码 */
    BAD_REQUEST(400, "客户端请求错误"),

    UNAUTHORIZED(401, "客户端未认证"),

    FORBIDDEN(403, "客户端请求被拒绝"),

    NOT_FOUND(404, "请求的资源不存在"),

    INTERNAL_ERROR(500, "服务器内部错误"),

    /* 业务状态码 */
    USER_NOT_EXIST_ERROR(10001, "用户不存在"),

    ;

    private final int code;

    private final String message;

    ResponseStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseBean response() {
        return new ResponseBean()
                .setCode(code)
                .setMessage(message)
                .setArray(false);
    }

    public ResponseBean responseOf(Object data) {
        return new ResponseBean()
                .setCode(code)
                .setMessage(message)
                .setArray(data instanceof Collection)
                .setData(data);
    }

}
