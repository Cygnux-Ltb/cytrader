package io.cygnus.engine.config.couchbean.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;

import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.common.sys.SysProperties;
import io.mercury.serialization.json.JsonParser;
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
			log.error("", e);
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
	public String getCouchBeanValue(String database, CouchDocument document) {
		AbsCouchBean absCouchBean = JsonParser.toObject(sendGetRequest(database, document.documentId()),
				AbsCouchBean.class);
		return absCouchBean.getValue();
	}

	/**
	 * 
	 * @param database
	 * @param documentId
	 * @return
	 */
	private String sendGetRequest(String database, String documentId) {
		log.info("sendGetRequest() -> database==[{}], documentId==[{}]", database, documentId);
		try {
			return HttpRequester.sentGet(couchdbUrl + "/" + database + "/" + documentId);
		} catch (Exception e) {
			log.error("sendGetRequest() -> database==[{}], documentId==[{}]", database, documentId, e);
			return "";
		}
	}

}
