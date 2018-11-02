package io.ffreedom.persistence.json.couchbean;

import java.util.List;

import io.ffreedom.persistence.json.couchbean.base.CouchConnector;
import io.ffreedom.persistence.json.couchbean.base.CouchDocumentEnum;
import io.ffreedom.persistence.json.serializable.JsonSerializationUtil;

public class AppList {

	private List<Integer> appIdList;

	public List<Integer> getAppIdList() {
		return appIdList;
	}

	public AppList setAppIdList(List<Integer> appIdList) {
		this.appIdList = appIdList;
		return this;
	}

	public static void main(String[] args) {

		CouchConnector connector = new CouchConnector();

		String json = connector.getCouchBeanValue(CouchDocumentEnum.APP_LIST);

		List<Integer> jsonToList = JsonSerializationUtil.jsonToList(json, Integer.class);
		
		jsonToList.forEach(id -> {
			System.out.println(id);
		});

	}

}
