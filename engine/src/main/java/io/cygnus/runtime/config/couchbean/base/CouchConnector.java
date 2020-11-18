package io.cygnus.runtime.config.couchbean.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;

import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.common.sys.SysProperties;
import io.mercury.serialization.json.JsonUtil;
import io.mercury.transport.http.HttpRequester;

public final class CouchConnector {

	private static final Logger log = CommonLoggerFactory.getLogger(CouchConnector.class);

	private String couchdbUrl;

	public static final CouchConnector Singleton = new CouchConnector();

	private CouchConnector() {
		Properties properties = new Properties();
		try {
			File configFile = new File(SysProperties.USER_HOME + "/config/couchdb.properties");
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
		AbsCouchBean absCouchBean = JsonUtil.toObject(sendGetRequest(document._database(), document._id()),
				AbsCouchBean.class);
		return absCouchBean.getValue();
	}

	private String sendGetRequest(String _database, String _id) {
		log.info("sendGetRequest() -> _database==[{}], _id==[{}]", _database, _id);
		return HttpRequester.sentGet(couchdbUrl + _database + _id);
	}

	public static void main(String[] args) {

	}

}
