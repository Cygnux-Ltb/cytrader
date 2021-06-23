package io.cygnus.restful.service.resources;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.cygnus.persistence.entity.Strategy;
import io.cygnus.persistence.entity.StrategyParam;
import io.cygnus.persistence.entity.StrategySymbol;
import io.cygnus.restful.service.base.CygRestfulApi;
import io.cygnus.restful.service.resources.executor.StrategyExecutor;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.common.util.StringUtil;

@RestController("/strategy")
public class StrategyRestfulApi extends CygRestfulApi {

	private final Logger log = CommonLoggerFactory.getLogger(getClass());

	private StrategyExecutor executor = new StrategyExecutor();

	/**
	 * 返回全部Strategy
	 * 
	 * @return
	 */
	@GetMapping
	public ResponseEntity<Object> getAllStrategy() {
		List<Strategy> strategys = executor.getAllStrategy();
		return jsonResponse(strategys);
	}

	/**
	 * 使用StrategyId作为URI访问Strategy
	 * 
	 * @param strategyId
	 * @return
	 */

	@GetMapping("/{strategyId}")
	public ResponseEntity<Object> getStrategyById(@RequestParam("strategyId") Integer strategyId) {
		List<Strategy> strategys = executor.getStrategyById(strategyId);
		return jsonResponse(strategys);
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
		return jsonResponse(strategyParams);
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
		StrategyParam strategyParam = jsonToObj(json, StrategyParam.class);
		if (executor.putStrategyParam(strategyParam)) {
			return httpOk();
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
		return jsonResponse(strategySymbols);
	}

}
