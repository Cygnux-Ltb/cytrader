package io.cygnus.console.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.cygnus.console.controller.base.BaseController;
import io.cygnus.console.service.PnlService;
import io.cygnus.repository.entity.CygPnlDaily;
import io.cygnus.repository.entity.CygPnlDailySettlement;

@RestController("/pnl")
public class PnlRestfulApi extends BaseController {

	@Resource
	private PnlService service;

	/**
	 * 查询PNL
	 * 
	 * @param strategyId
	 * @param tradingDay
	 * @return
	 */
	@GetMapping
	public ResponseEntity<Object> getPnlDailys(@RequestParam("strategyId") int strategyId,
			@RequestParam("tradingDay") int tradingDay) {
		if (checkParamIsNull(tradingDay))
			return badRequest();
		return responseOf(service.getPnlDailys(strategyId, tradingDay));
	}

	/**
	 * Put PnlDaily
	 * 
	 * @param request
	 * @return
	 */
	@PutMapping
	public ResponseEntity<Object> putPnlDailys(@RequestBody HttpServletRequest request) {
		var pnlDaily = bodyToObject(request, CygPnlDaily.class);
		return pnlDaily == null ? badRequest() : service.putPnlDaily(pnlDaily) ? ok() : internalServerError();
	}

	/**
	 * 查询结算PNL
	 * 
	 * @param strategyId
	 * @param tradingDay
	 * @return
	 */
	@GetMapping("/settlement")
	public ResponseEntity<List<CygPnlDailySettlement>> getPnlSettlementDailys(
			@RequestParam("strategyId") int strategyId, @RequestParam("tradingDay") int tradingDay) {
		if (checkParamIsNull(tradingDay))
			return badRequest();
		return responseOf(service.getPnlDailySettlements(strategyId, tradingDay));
	}

}
