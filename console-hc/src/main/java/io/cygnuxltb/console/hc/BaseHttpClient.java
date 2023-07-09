package io.cygnuxltb.console.hc;

import io.mercury.common.http.JreHttpClient;
import io.mercury.common.http.PathParams;
import io.mercury.common.http.PathParams.PathParam;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.common.util.StringSupport;
import io.mercury.serialization.json.JsonParser;
import io.mercury.serialization.json.JsonWrapper;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

class BaseHttpClient {

    protected final Logger log = Log4j2LoggerFactory.getLogger(getClass());

    protected <T> List<T> sendGetRequest(Class<T> type, String uri, PathParam... params) {
        try {
            var response = JreHttpClient.GET(PathParams.with(params).toFullUri(uri));
            int statusCode = response.statusCode();
            if (statusCode > 307)
                throw new RuntimeException(
                        "GET request uri: [" + uri + "] return status code: [" + statusCode + "]");
            String body = response.body();
            return StringSupport.isNullOrEmpty(body)
                    ? new ArrayList<>()
                    : JsonParser.toList(body, type);
        } catch (Exception e) {
            log.error("catch exception message -> {}", e.getMessage(), e);
            throw new RuntimeException("Exception Message -> " + e.getMessage(), e);
        }
    }

    protected boolean sendPutRequest(Object body, String uri, PathParam... params) {
        if (body == null)
            throw new IllegalArgumentException("Put request body does not allowed null or empty string.");
        try {
            var response = JreHttpClient.PUT(PathParams.with(params).toFullUri(uri),
                    JsonWrapper.toJson(body));
            int statusCode = response.statusCode();
            if (statusCode > 307) {
                log.error("PUT request uri: [{}] -> {}", uri, statusCode);
                return false;
            }
            return true;
        } catch (Exception e) {
            log.error("catch exception message -> {}", e.getMessage(), e);
            throw new RuntimeException("Exception Message -> " + e.getMessage(), e);
        }
    }

}
