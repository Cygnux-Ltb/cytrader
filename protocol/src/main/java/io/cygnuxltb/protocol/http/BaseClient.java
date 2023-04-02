package io.cygnux.client.base;

import io.mercury.common.character.Charsets;
import io.mercury.common.log.Log4j2LoggerFactory;
import io.mercury.common.sys.SysProperties;
import io.mercury.serialization.json.JsonParser;
import io.mercury.serialization.json.JsonWrapper;
import io.mercury.transport.http.param.PathParam;

import org.slf4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public abstract class BaseClient {

    protected final Logger log = Log4j2LoggerFactory.getLogger(getClass());

    private final PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();

    private final CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(manager).build();

    private String baseUrl;

    protected BaseClient() {
        Properties properties = new Properties();
        try {
            if (SysProperties.USER_HOME == null) {
                properties.load(BaseClient.class.getResourceAsStream("restful.properties"));
            } else {
                FileInputStream fileInputStream = new FileInputStream(
                        SysProperties.USER_HOME + "/config/restful.properties");
                properties.load(fileInputStream);
            }
            this.baseUrl = properties.getProperty("baseUrl");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送Get请求获取数据
     *
     * @param resultType Class<T>
     * @param uri        String
     * @param params     PathParam[]
     * @return List<T>
     */
    protected <T> List<T> getResultSet(Class<T> resultType, String uri, PathParam... params) {
        return analysisJson(sendGetRequest(replaceParam(uri, params)), resultType);
    }

    private String sendGetRequest(String uri) {
        try {
            HttpGet httpGet = new HttpGet(baseUrl + uri);
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                int statusCode = response.getCode();
                if (statusCode > 307) {
                    throw new RuntimeException(
                            "Exception -> Request URI: [" + httpGet.getUri() + "] return status code " + statusCode);
                }
                return EntityUtils.toString(response.getEntity(), Charsets.UTF8);
            }
        } catch (Exception e) {
            log.error("Error Message -> {}", e.getMessage(), e);
            throw new RuntimeException("Exception -> " + e.getMessage());
        }
    }

    private <T> List<T> analysisJson(String json, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        if (json != null && !json.equals("")) {
            list = JsonParser.toList(json, clazz);
        }
        return list;
    }

    /**
     * Put JSON格式数据到服务器
     *
     * @param body   Object
     * @param uri    String
     * @param params PathParam[]
     * @return boolean
     */
    protected boolean putBody(Object body, String uri, PathParam... params) {
        return sendPutRequest(toJsonString(body), replaceParam(uri, params));
    }

    private String toJsonString(Object obj) {
        return JsonWrapper.toJson(obj);
    }

    private boolean sendPutRequest(String jsonBody, String uri) {
        if (jsonBody == null || jsonBody.equals("")) {
            throw new IllegalArgumentException("Put request body does not allowed null or empty string.");
        }
        StringEntity entity = new StringEntity(jsonBody, ContentType.APPLICATION_JSON);
        HttpPut httpPut = new HttpPut(baseUrl + uri);
        httpPut.setEntity(entity);
        try (CloseableHttpResponse response = httpClient.execute(httpPut)) {
            int statusCode = response.getCode();
            return statusCode <= 307;
        } catch (Exception e) {
            throw new RuntimeException("Exception -> " + e.getMessage());
        }
    }

    private String replaceParam(String uri, PathParam... params) {
        for (PathParam param : params) {
            uri = uri.replace(param.getName(), param.getValue().toString());
        }
        return uri;
    }

}
