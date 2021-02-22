package io.cygnus.restful.client.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPut;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.slf4j.Logger;

import io.mercury.common.character.Charsets;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.common.sys.SysProperties;
import io.mercury.serialization.json.JsonParser;
import io.mercury.serialization.json.JsonWrapper;

public abstract class BaseApiClient {

	private final Logger log = CommonLoggerFactory.getLogger(getClass());

	private final PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();

	private final CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connectionManager).build();

	private String baseUrl;

	protected BaseApiClient() {
		Properties properties = new Properties();
		try {
			if (SysProperties.USER_HOME == null) {
				properties.load(BaseApiClient.class.getResourceAsStream("restful.properties"));
			} else {
				FileInputStream fileInputStream = new FileInputStream(
						new File(SysProperties.USER_HOME + "/config/restful.properties"));
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
	 * @param resultType
	 * @param uri
	 * @param params
	 * @return
	 */
	protected <T> List<T> getResultSet(Class<T> resultType, String uri, PathParam... params) {
		return analysisJson(sendGetRequest(replaceParam(uri, params)), resultType);
	}

	private String sendGetRequest(String uri) {
		try {
			HttpGet httpGet = new HttpGet(baseUrl + uri);
			CloseableHttpResponse response = httpClient.execute(httpGet);
			try {
				int statusCode = response.getCode();
				if (statusCode > 307) {
					throw new RuntimeException(
							"Exception -> Request URI: [" + httpGet.getUri() + "] return status code " + statusCode);
				}
				return EntityUtils.toString(response.getEntity(), Charsets.UTF8);
			} finally {
				response.close();
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
	 * put JSON格式数据到服务器
	 * 
	 * @param jsonBody
	 * @param uri
	 * @param params
	 * @return
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
			if (statusCode > 307) {
				return false;
			}
			return true;
		} catch (Exception e) {
			throw new RuntimeException("Exception -> " + e.getMessage());
		}
	}

	private String replaceParam(String uri, PathParam... params) {
		for (PathParam param : params) {
			uri = uri.replace(param.getName(), param.getValue());
		}
		return uri;
	}

	public static void main(String[] args) {

	}

}
