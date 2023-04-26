package io.cygnuxltb.console.controller.base;

import io.mercury.common.log4j2.Log4j2LoggerFactory;
import org.slf4j.Logger;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@RestControllerAdvice
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {

    private static final Logger log = Log4j2LoggerFactory.getLogger(GlobalResponseHandler.class);

    @Override
    public boolean supports(@Nonnull MethodParameter parameter,
                            @Nonnull Class<? extends HttpMessageConverter<?>> type) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(@Nullable Object returnObj,
                                  @Nonnull MethodParameter parameter,
                                  @Nonnull MediaType selectedContentType,
                                  @Nonnull Class<? extends HttpMessageConverter<?>> type,
                                  @Nonnull ServerHttpRequest request,
                                  @Nonnull ServerHttpResponse response) {
        switch (returnObj) {
            case null -> {
                return ResponseStatus.NOT_FOUND.response();
            }
            case ResponseBean resp -> {
                return resp;
            }
            case ResponseStatus status -> {
                return status.response();
            }
            case Boolean bool -> {
                return bool
                        ? ResponseStatus.OK.response()
                        : ResponseStatus.FORBIDDEN.response();
            }
            case Integer i -> {
                return i < 0
                        ? ResponseStatus.CREATED.responseOf(i)
                        : ResponseStatus.FORBIDDEN.response();
            }
            default -> {
                return ResponseStatus.OK.responseOf(returnObj);
            }
        }
    }


}
