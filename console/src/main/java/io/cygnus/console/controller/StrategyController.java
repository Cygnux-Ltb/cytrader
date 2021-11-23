package io.cygnus.console.controller;

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

import io.cygnus.console.controller.base.BaseController;
import io.cygnus.console.service.StrategyService;
import io.cygnus.repository.entity.CygStrategy;
import io.cygnus.repository.entity.CygStrategyParam;
import io.mercury.common.log.CommonLoggerFactory;

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
		CygStrategyParam param = bodyToObject(request, CygStrategyParam.class);
		log.info("putParamsByStrategyId recv : {}", param);
		return param == null ? badRequest() : service.putStrategyParam(param) ? ok() : internalServerError();
	}

}
