package io.ffreedom.persistence.json.serializable;

import java.util.List;

import com.alibaba.fastjson.JSON;

public class JsonSerializationUtil {

	public static String objToJson(Object obj) {
		return JSON.toJSONString(obj);
	}

	public static <T> T jsonToObj(String json, Class<T> clazz) {
		return JSON.parseObject(json, clazz);
	}

	public static <T> List<T> jsonToList(String json, Class<T> clazz) {
		return JSON.parseArray(json, clazz);
	}

}
