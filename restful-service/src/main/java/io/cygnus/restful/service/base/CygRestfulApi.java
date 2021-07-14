package io.cygnus.restful.service.base;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.cygnus.service.dto.pack.OutboxMessage;
import io.mercury.common.character.Charsets;
import io.mercury.common.datetime.pattern.spec.DatePattern;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.serialization.json.JsonParser;
import io.mercury.serialization.json.JsonWrapper;

public abstract class CygRestfulApi {

	private static final Logger log = CommonLoggerFactory.getLogger(CygRestfulApi.class);

	/**
	 * 转换String到Date
	 * 
	 * @param tradingDay
	 * @return
	 */
	protected Date changeTradingDay(String tradingDay) {
		try {
			return DatePattern.YYYYMMDD.newSimpleDateFormat().parse(tradingDay);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 检查参数列表
	 * 
	 * @param objs
	 * @return
	 */
	protected boolean checkParamIsNull(Object... objs) {
		for (Object obj : objs) {
			if (obj != null) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Http Status Code: 400 Bad Request
	 * 
	 * @return
	 */
	protected <T> ResponseEntity<T> httpBadRequest() {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

	/**
	 * Http Status Code: 500 Internal Server Error
	 * 
	 * @return
	 */
	protected <T> ResponseEntity<T> httpInternalServerError() {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	/**
	 * Http Status Code: 200 Ok
	 * 
	 * @return
	 */
	protected <T> ResponseEntity<T> httpOk() {
		return ResponseEntity.ok().build();
	}

	/**
	 * 生成JSON Response
	 * 
	 * @param entity
	 * @return
	 */
	protected <T> ResponseEntity<T> jsonResponse(T object) {
		if (object == null) {
			return httpInternalServerError();
		}
		return ResponseEntity.ok(object);
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	protected String getBody(HttpServletRequest request) {
		try {
			// String json = IOUtils.toString(request.getInputStream(),
			// CharsetCode.UTF8);
			// System.out.println(json);
			return IOUtils.toString(request.getInputStream(), Charsets.UTF8);
		} catch (IOException e) {
			log.error("Get body has exception, {}", e.getMessage(), e);
			return null;
		}
	}

	/**
	 * 
	 * @param <T>
	 * @param json
	 * @param clazz
	 * @return
	 */
	protected <T> T jsonToObj(String json, Class<T> clazz) {
		return JsonParser.toObject(json, clazz);
	}

	/**
	 * 
	 * @param <T>
	 * @param json
	 * @param clazz
	 * @return
	 */
	protected <T> List<T> jsonToList(String json, Class<T> clazz) {
		return JsonParser.toList(json, clazz);
	}

	/**
	 * 
	 * @param outboxMessage
	 * @return
	 */
	protected String outboxMessageToJson(OutboxMessage<?> outboxMessage) {
		return JsonWrapper.toJson(outboxMessage);
	}

}
