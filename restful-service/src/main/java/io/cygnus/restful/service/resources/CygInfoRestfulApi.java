package io.cygnus.restful.service.resources;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.cygnus.restful.service.base.CygRestfulApi;
import io.cygnus.restful.service.resources.executor.CygInfoExecutor;
import io.cygnus.service.dto.InitFinish;
import io.cygnus.service.entity.CygInfo;
import io.cygnus.service.entity.CygMqConfig;
import io.cygnus.service.entity.CygStrategy;
import io.mercury.common.annotation.cache.GetCache;

@RestController("/cyg_info")
public class CygInfoRestfulApi extends CygRestfulApi {

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
	public ResponseEntity<Object> getAllcygInfo() {
		List<CygInfo> thadInfos = executor.getAllcygInfo();
		return jsonResponse(thadInfos);
	}

	private static ConcurrentHashMap<Integer, InitFinish> thadInfoInitFinishCacheMap = new ConcurrentHashMap<>();

	
	@PutMapping("/initialized")
	public ResponseEntity<Object> putInitFinish(@RequestBody HttpServletRequest request) {
		String json = getBody(request);
		if (checkParamIsNull(json)) {
			return httpBadRequest();
		}
		InitFinish initFinish = jsonToObj(json, InitFinish.class);
		thadInfoInitFinishCacheMap.put(initFinish.getCygId(), initFinish);
		return httpOk();
	}

	@GetMapping("/{cygId}")
	public ResponseEntity<Object> getcygInfoById(@PathParam("cygId") Integer cygId) {
		List<CygInfo> thadInfoById = executor.getCygInfoById(cygId);
		return jsonResponse(thadInfoById);
	}

	@GetMapping("/{cygId}/strategy")
	public ResponseEntity<Object> getcygStrategyById(@PathParam("cygId") Integer cygId) {
		List<CygStrategy> thadStrategys = executor.getCygStrategyById(cygId);
		return jsonResponse(thadStrategys);
	}

	@GetMapping("/{cygId}/mq")
	public ResponseEntity<Object> getcygMqConfigById(@PathParam("cygId") Integer cygId) {
		List<CygMqConfig> thadMqConfigs = executor.getCygMqConfigById(cygId);
		return jsonResponse(thadMqConfigs);
	}

}
