package io.cygnux.console.utils;

import io.mercury.common.character.Charsets;
import io.mercury.common.log.Log4j2LoggerFactory;
import io.mercury.common.util.StringSupport;
import io.mercury.serialization.json.JsonParser;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public final class ParamsValidateUtil {

    private static final Logger log = Log4j2LoggerFactory.getLogger(ParamsValidateUtil.class);

    /**
     * 检查参数列表
     *
     * @param objs
     * @return
     */
    public static boolean paramIsNull(Object... objs) {
        for (Object obj : objs) {
            if (obj != null) {
                return false;
            }
        }
        return true;
    }


    public static String getBody(HttpServletRequest request) {
        try {
            return IOUtils.toString(request.getInputStream(), Charsets.UTF8);
        } catch (IOException e) {
            log.error("get body content has IOException -> {}", e.getMessage(), e);
            return null;
        }
    }

    public static <T> T bodyToObject(HttpServletRequest request, Class<T> clazz) {
        var body = getBody(request);
        if (StringSupport.isNullOrEmpty(body)) {
            log.error("body content is null or empty");
            return null;
        }
        return JsonParser.toObject(body, clazz);
    }

    public static <T> List<T> bodyToList(HttpServletRequest request, Class<T> clazz) {
        var body = getBody(request);
        if (StringSupport.isNullOrEmpty(body)) {
            log.error("body content is null or empty");
            return null;
        }
        return JsonParser.toList(body, clazz);
    }


}
