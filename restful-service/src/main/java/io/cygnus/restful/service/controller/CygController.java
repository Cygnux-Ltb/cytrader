package io.cygnus.restful.service.controller;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.cygnus.repository.entity.CygInfoEntity;
import io.cygnus.repository.service.CygInfoService;
import io.cygnus.restful.service.base.BaseController;
import io.cygnus.service.dto.InitFinish;
import io.mercury.common.annotation.GetCache;

@RestController("/cyg_info")
public class CygController extends BaseController {

	/**
	 * 执行具体操作的executor
	 */
	@Resource
	private CygInfoService service;

	/**
	 * Get All cygInfo
	 * 
	 * @return
	 */
	@GetMapping
	@GetCache
	public ResponseEntity<Object> getAllCygInfo() {
		List<CygInfoEntity> cygInfoList = service.getAll();
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
	public ResponseEntity<CygInfoEntity> getcygInfoById(@PathParam("cygId") int cygId) {
		CygInfoEntity entity = service.get(cygId);
		return responseOf(entity);
	}

}
