package io.cygnus.restful.service.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.cygnus.repository.entity.*;
import io.cygnus.repository.service.StrategyService;
import io.cygnus.restful.service.base.BaseController;
import io.cygnus.restful.service.base.CygRestfulApi;
import io.cygnus.restful.service.resources.executor.StrategyExecutor;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.common.util.StringUtil;

@RestController("/strategy")
public class StrategyController extends BaseController {

	private final Logger log = CommonLoggerFactory.getLogger(getClass());

	@Resource
	private StrategyService service;

	/**
	 * 返回全部Strategy
	 * 
	 * @return
	 */
	@GetMapping
	public ResponseEntity<List<StrategyEntity>> getAllStrategy() {
		return responseOf(service.getStrategys());
	}

	/**
	 * 使用StrategyId作为get params访问Strategy
	 * 
	 * @param strategyId
	 * @return
	 */
	public ResponseEntity<Object> getStrategyById(@RequestParam("strategyId") int strategyId) {
		List<Strategy> strategys = executor.getStrategyById(strategyId);
		return responseOf(strategys);
	}

	/**
	 * 使用StrategyId作为URI访问Param
	 * 
	 * @param strategyId
	 * @return
	 */
	@GetMapping("/{strategyId}/param")
	public ResponseEntity<Object> getParamsByStrategyId(@RequestParam("strategyId") Integer strategyId) {
		List<StrategyParam> strategyParams = executor.getParamsByStrategyId(strategyId);
		return responseOf(strategyParams);
	}

	/**
	 * Put StrategyParam URI is StrategyId
	 * 
	 * @param strategyId
	 * @return
	 */

	@PutMapping("/{strategyId}/param")
	public ResponseEntity<Object> putParamsByStrategyId(@RequestBody HttpServletRequest request) {
		String json = getBody(request);
		log.info("method putParamsByStrategyId recv : {}", json);
		if (StringUtil.isNullOrEmpty(json)) {
			return httpBadRequest();
		}
		StrategyParam strategyParam = toObject(json, StrategyParam.class);
		if (executor.putStrategyParam(strategyParam)) {
			return ok();
		} else {
			return httpInternalServerError();
		}
	}

	/**
	 * 使用StrategyId作为URI访问Param
	 * 
	 * @param strategyId
	 * @return
	 */
	@GetMapping("/{strategyId}/symbol")
	public ResponseEntity<Object> getSymbolByStrategyId(@RequestParam("strategyId") Integer strategyId) {
		List<StrategySymbol> strategySymbols = executor.getSymbolsByStrategyId(strategyId);
		return responseOf(strategySymbols);
	}

}
