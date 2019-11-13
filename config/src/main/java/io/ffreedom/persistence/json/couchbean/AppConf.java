package io.ffreedom.persistence.json.couchbean;

import java.util.List;

import io.ffreedom.persistence.json.couchbean.base.CouchConnector;
import io.ffreedom.persistence.json.couchbean.base.CouchDocumentEnum;
import io.mercury.persistence.json.JsonParser;

public class AppConf {

	private int id;

	public int getId() {
		return id;
	}

	public AppConf setId(int id) {
		this.id = id;
		return this;
	}

	public static void main(String[] args) {

		String json = CouchConnector.Singleton.getCouchBeanValue(CouchDocumentEnum.AppConf);

		List<AppConf> jsonToList = JsonParser.toList(json, AppConf.class);

		jsonToList.forEach(appConfig -> {
			System.out.println(appConfig.getId());
		});

	}

}
