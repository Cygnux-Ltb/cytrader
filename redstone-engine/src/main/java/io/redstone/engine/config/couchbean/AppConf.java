package io.redstone.engine.config.couchbean;

import java.util.List;

import io.mercury.serialization.json.JsonUtil;
import io.redstone.engine.config.couchbean.base.CouchConnector;
import io.redstone.engine.config.couchbean.base.CouchDocumentEnum;

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

		List<AppConf> jsonToList = JsonUtil.toList(json, AppConf.class);

		jsonToList.forEach(appConfig -> {
			System.out.println(appConfig.getId());
		});

	}

}
