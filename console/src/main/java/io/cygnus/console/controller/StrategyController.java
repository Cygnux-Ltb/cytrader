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

import io.cygnus.repository.entity.CygStrategy;
import io.cygnus.repository.entity.CygStrategyParam;
import io.cygnus.restful.service.StrategyService;
import io.cygnus.restful.service.base.BaseController;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.common.util.StringSupport;

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
	public ResponseEntity<List<CygStrategy>> getStrategys() {
		return responseOf(service.getStrategys());
	}

	/**
	 * 使用StrategyId作为get params访问Strategy
	 * 
	 * @param strategyId
	 * @return
	 */
	public ResponseEntity<CygStrategy> getStrategyById(@RequestParam("strategyId") int strategyId) {
		CygStrategy strategy = service.getStrategy(strategyId);
		return responseOf(strategy);
	}

	/**
	 * 使用StrategyId作为URI访问Param
	 * 
	 * @param strategyId
	 * @return
	 */
	@GetMapping("/param")
	public ResponseEntity<Object> getParamsByStrategyId(@RequestParam("strategyId") int strategyId) {
		List<CygStrategyParam> strategyParams = service.getStrategyParams(strategyId);
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
		if (StringSupport.isNullOrEmpty(json)) {
			return badRequest();
		}
		CygStrategyParam entity = toObject(json, CygStrategyParam.class);
		return (service.putStrategyParam(entity)) ? ok() : internalServerError();
	}

}
