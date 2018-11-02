package io.ffreedom.persistence.json.couchbean.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;

import io.ffreedom.common.charset.Charsets;
import io.ffreedom.common.env.SysProperty;
import io.ffreedom.common.log.LoggerFactory;
import io.ffreedom.persistence.json.serializable.JsonSerializationUtil;

public final class CouchConnector {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private final PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();

	private final CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connectionManager).build();

	private String couchdbUrl;

	public CouchConnector() {
		Properties properties = new Properties();
		try {
			File configFile = new File(SysProperty.USER_HOME + "/config/couchdb.properties");
			InputStream inputStream = null;
			if (configFile.exists()) {
				inputStream = new FileInputStream(configFile);
			} else {
				inputStream = CouchConnector.class.getResourceAsStream("couchdb.properties");
			}
			properties.load(inputStream);
			this.couchdbUrl = properties.getProperty("couchdb_url");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		File file = new File(SysProperty.USER_HOME + "/config/couchdb.properties");

		if (!file.exists()) {
			System.out.println("9999");
		}
		System.err.println(Long.MAX_VALUE);

	}

	/**
	 * 发送Get请求获取数据
	 * 
	 * @param resultType
	 * @param uri
	 * @param params
	 * @return
	 */
	public String getCouchBeanValue(CouchDocument document) {
		AbsCouchBean absCouchBean = JsonSerializationUtil
				.jsonToObj(sendGetRequest(document._database(), document._id()), AbsCouchBean.class);
		return absCouchBean.getValue();
	}

	private String sendGetRequest(String _database, String _id) {
		try {
			logger.info("sendGetRequest() -> _database==[{}], _id==[{}]", _database, _id);
			HttpGet httpGet = new HttpGet(couchdbUrl + _database + _id);
			CloseableHttpResponse response = httpClient.execute(httpGet);
			try {
				int statusCode = response.getStatusLine().getStatusCode();
				if (statusCode > 307) {
					throw new RuntimeException(
							"Exception -> Request URI: [" + httpGet.getURI() + "] return status code " + statusCode);
				}
				return EntityUtils.toString(response.getEntity(), Charsets.UTF8);
			} finally {
				response.close();
			}
		} catch (Exception e) {
			throw new RuntimeException("Exception -> " + e.getMessage());
		}
	}

}
