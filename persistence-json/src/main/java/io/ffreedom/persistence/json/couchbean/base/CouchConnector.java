package io.ffreedom.persistence.json.couchbean.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;

import io.ffreedom.common.env.SystemPropertys;
import io.ffreedom.common.log.CommonLoggerFactory;
import io.ffreedom.persistence.json.serializable.JsonSerializationUtil;
import io.ffreedom.transport.http.HttpRequester;

public final class CouchConnector {

	private Logger logger = CommonLoggerFactory.getLogger(getClass());

	private String couchdbUrl;

	public static final CouchConnector Singleton = new CouchConnector();

	private CouchConnector() {
		Properties properties = new Properties();
		try {
			File configFile = new File(SystemPropertys.USER_HOME + "/config/couchdb.properties");
			InputStream inputStream = null;
			if (configFile.exists())
				inputStream = new FileInputStream(configFile);
			else
				inputStream = CouchConnector.class.getResourceAsStream("couchdb.properties");
			properties.load(inputStream);
			this.couchdbUrl = properties.getProperty("couchdb_url");
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
	public String getCouchBeanValue(CouchDocument document) {
		AbsCouchBean absCouchBean = JsonSerializationUtil
				.jsonToObj(sendGetRequest(document._database(), document._id()), AbsCouchBean.class);
		return absCouchBean.getValue();
	}

	private String sendGetRequest(String _database, String _id) {
		logger.info("sendGetRequest() -> _database==[{}], _id==[{}]", _database, _id);
		return HttpRequester.INSTANCE.httpGet(couchdbUrl + _database + _id);
	}

	public static void main(String[] args) {

	}

}
