package io.cygnux.console.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import io.cygnux.console.service.StrategyService;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.cygnux.console.controller.base.BaseController;
import io.cygnux.repository.entities.internal.InStrategy;
import io.cygnux.repository.entities.internal.InStrategyParam;
import io.mercury.common.log.Log4j2LoggerFactory;

@RestController("/strategy")
public class StrategyController extends BaseController {

	private final Logger log = Log4j2LoggerFactory.getLogger(getClass());

	@Resource
	private StrategyService service;

	/**
	 * 返回全部Strategy
	 * 
	 * @return
	 */
	@GetMapping
	public ResponseEntity<List<InStrategy>> getStrategys() {
		return responseOf(service.getStrategys());
	}

	/**
	 * 使用StrategyId作为get params访问Strategy
	 * 
	 * @param strategyId
	 * @return
	 */
	public ResponseEntity<InStrategy> getStrategyById(@RequestParam("strategyId") int strategyId) {
		InStrategy strategy = service.getStrategy(strategyId);
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
		List<InStrategyParam> strategyParams = service.getStrategyParams(strategyId);
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
		InStrategyParam param = bodyToObject(request, InStrategyParam.class);
		log.info("putParamsByStrategyId recv : {}", param);
		return param == null ? badRequest() : service.putStrategyParam(param) ? ok() : internalServerError();
	}

}
