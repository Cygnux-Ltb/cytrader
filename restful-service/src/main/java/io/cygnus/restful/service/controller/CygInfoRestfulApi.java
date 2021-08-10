package io.cygnus.restful.service.controller;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.cygnus.repository.entity.CygInfoEntity;
import io.cygnus.restful.service.base.BaseController;
import io.cygnus.restful.service.resources.executor.CygInfoExecutor;
import io.cygnus.service.dto.InitFinish;
import io.mercury.common.annotation.cache.GetCache;

@RestController("/cyg_info")
public class CygInfoRestfulApi extends BaseController {

	/**
	 * 执行具体操作的executor
	 */
	private CygInfoExecutor executor = new CygInfoExecutor();

	/**
	 * Get All cygInfo
	 * 
	 * @return
	 */
	@GetMapping
	@GetCache
	public ResponseEntity<Object> getAllCygInfo() {
		List<CygInfoEntity> cygInfoList = executor.getAllcygInfo();
		return responseOf(cygInfoList);
	}

	private static ConcurrentHashMap<Integer, InitFinish> cygInfoInitFinishCacheMap = new ConcurrentHashMap<>();

	/**
	 * 
	 * @param request
	 * @return
	 */
	@PutMapping("/initialized")
	public ResponseEntity<Object> putInitFinish(@RequestBody HttpServletRequest request) {
		String json = getBody(request);
		if (checkParamIsNull(json)) {
			return badRequest();
		}
		InitFinish initFinish = toObject(json, InitFinish.class);
		cygInfoInitFinishCacheMap.put(initFinish.getCygId(), initFinish);
		return ok();

	}

	/**
	 * 
	 * @param cygId
	 * @return
	 */
	@GetMapping("/{cygId}")
	public ResponseEntity<Object> getcygInfoById(@PathParam("cygId") Integer cygId) {
		List<CygInfo> thadInfoById = executor.getCygInfoById(cygId);
		return responseOf(thadInfoById);
	}

	/**
	 * 
	 * @param cygId
	 * @return
	 */
	@GetMapping("/{cygId}/strategy")
	public ResponseEntity<Object> getcygStrategyById(@PathParam("cygId") Integer cygId) {
		List<CygStrategy> thadStrategys = executor.getCygStrategyById(cygId);
		return responseOf(thadStrategys);
	}

	/**
	 * 
	 * @param cygId
	 * @return
	 */
	@GetMapping("/{cygId}/mq")
	public ResponseEntity<Object> getcygMqConfigById(@PathParam("cygId") Integer cygId) {
		List<CygMqConfig> thadMqConfigs = executor.getCygMqConfigById(cygId);
		return responseOf(thadMqConfigs);
	}

}
