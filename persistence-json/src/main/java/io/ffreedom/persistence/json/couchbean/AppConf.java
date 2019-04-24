package io.ffreedom.persistence.json.couchbean;

import java.util.List;

import io.ffreedom.persistence.json.couchbean.base.CouchConnector;
import io.ffreedom.persistence.json.couchbean.base.CouchDocumentEnum;
import io.ffreedom.persistence.json.serializable.JsonSerializationUtil;

public class AppConfig {

	private int id;

	public int getId() {
		return id;
	}

	public AppConfig setId(int id) {
		this.id = id;
		return this;
	}

	public static void main(String[] args) {

		CouchConnector connector = new CouchConnector();

		String json = connector.getCouchBeanValue(CouchDocumentEnum.AppConfig);

		List<AppConfig> jsonToList = JsonSerializationUtil.jsonToList(json, AppConfig.class);

		jsonToList.forEach(appConfig -> {
			System.out.println(appConfig.getId());
		});

	}

}
