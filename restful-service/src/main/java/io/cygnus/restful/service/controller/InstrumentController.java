package io.cygnus.restful.service.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.cygnus.repository.entity.InstrumentSettlementEntity;
import io.cygnus.repository.service.InstrumentService;
import io.cygnus.restful.service.base.BaseController;
import io.cygnus.service.dto.LastPrice;
import io.mercury.common.annotation.GetCache;
import io.mercury.common.util.StringSupport;

@RestController("/instrument")
public class InstrumentController extends BaseController {

	@Resource
	private InstrumentService service;

	/**
	 * Get SettlementPrice
	 * 
	 * @param instrumentId
	 * @param tradingDay
	 * @return
	 */
	@GetMapping("/settlement_price")
	public ResponseEntity<List<InstrumentSettlementEntity>> getSettlementPrice(
			@RequestParam("instrumentCode") String instrumentCode, @RequestParam("tradingDay") int tradingDay) {
		if (checkParamIsNull(instrumentCode, tradingDay)) {
			return badRequest();
		}
		List<InstrumentSettlementEntity> instrumentSettlements = service.getInstrumentSettlement(instrumentCode,
				tradingDay);
		return responseOf(instrumentSettlements);
	}

	// LastPrices Cache
	private static final ConcurrentHashMap<String, LastPrice> lastPriceMap = new ConcurrentHashMap<>();

	/**
	 * Get LastPrices
	 * 
	 * @param instrumentsStr
	 * @return
	 */
	@GetMapping("/last_price")
	@GetCache
	public ResponseEntity<Object> getLastPrice(@RequestParam("instrumentCodes") String instrumentCodes) {
		if (StringSupport.nonEmpty(instrumentCodes)) {
			return badRequest();
		}
		List<LastPrice> lastPrices = new ArrayList<>();
		String[] instrumentCodeArray = instrumentCodes.split(",");
		for (String instrumentCode : instrumentCodeArray) {
			final LastPrice lastPrice = lastPriceMap.putIfAbsent(instrumentCode,
					new LastPrice().setInstrumentCode(instrumentCode));
			// TODO 修改最新价格进入队列
			lastPrices.add(lastPrice);
		}
		return responseOf(lastPrices);
	}

	/**
	 * Put LastPrice
	 * 
	 * @param request
	 * @return
	 */
	@PutMapping("/last_price")
	public ResponseEntity<Object> putLastPrice(@RequestBody HttpServletRequest request) {
		String json = getBody(request);
		if (json == null) {
			return badRequest();
		}
		final LastPrice lastPrice = toObject(json, LastPrice.class);
		lastPriceMap.put(lastPrice.getInstrumentCode(), lastPrice);
		return ok();
	}

}
