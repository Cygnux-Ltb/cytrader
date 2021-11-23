package io.cygnus.console.controller.base;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.mercury.common.character.Charsets;
import io.mercury.common.datetime.pattern.spec.DatePattern;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.common.util.StringSupport;
import io.mercury.serialization.json.JsonParser;

public abstract class BaseController {

	private static final Logger log = CommonLoggerFactory.getLogger(BaseController.class);

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
	protected <T> ResponseEntity<T> badRequest() {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

	/**
	 * Http Status Code: 500 Internal Server Error
	 * 
	 * @return
	 */
	protected <T> ResponseEntity<T> internalServerError() {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	/**
	 * Http Status Code: 200 ok
	 * 
	 * @return
	 */
	protected <T> ResponseEntity<T> ok() {
		return ResponseEntity.ok().build();
	}

	/**
	 * 生成JSON Response
	 * 
	 * @param <T>
	 * @param t
	 * @return
	 */
	protected <T> ResponseEntity<T> responseOf(T t) {
		return t == null ? internalServerError() : ResponseEntity.ok(t);
	}

	private String getBody(HttpServletRequest request) {
		try {
			return IOUtils.toString(request.getInputStream(), Charsets.UTF8);
		} catch (IOException e) {
			log.error("get body content has IOException -> {}", e.getMessage(), e);
			return null;
		}
	}

	protected <T> T bodyToObject(HttpServletRequest request, Class<T> clazz) {
		String body = getBody(request);
		if (StringSupport.isNullOrEmpty(body)) {
			log.error("body content is null or empty");
			return null;
		}
		return JsonParser.toObject(body, clazz);
	}

	protected <T> List<T> bodyToList(HttpServletRequest request, Class<T> clazz) {
		String body = getBody(request);
		if (StringSupport.isNullOrEmpty(body)) {
			log.error("body content is null or empty");
			return null;
		}
		return JsonParser.toList(body, clazz);
	}

}
