package io.cygnux.console.controller.base;

import io.mercury.common.log.Log4j2LoggerFactory;
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
public class ControllerResponseHandler implements ResponseBodyAdvice<Object> {

    private static final Logger log = Log4j2LoggerFactory.getLogger(ControllerResponseHandler.class);

    @Override
    public boolean supports(@Nonnull MethodParameter returnType,
                            @Nonnull Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(@Nullable Object body,
                                  @Nonnull MethodParameter returnType,
                                  @Nonnull MediaType selectedContentType,
                                  @Nonnull Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  @Nonnull ServerHttpRequest request,
                                  @Nonnull ServerHttpResponse response) {
        if (body == null) {
            return ResultEnum.OK.newResponse(new Object());
        } else if (body instanceof Boolean b) {
            if (b) {
                return ResultEnum.OK.newResponse();
            } else {
                return ResultEnum.FORBIDDEN.newResponse();
            }
        } else if (body instanceof Integer i) {
            return ResultEnum.CREATED.newResponse(i);
        }
        return ResultEnum.OK.newResponse(body);
    }

}
