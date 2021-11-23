package io.cygnus.restful.service.controller;

import static java.util.Arrays.stream;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.cygnus.repository.entity.CygInstrumentStatic;
import io.cygnus.restful.service.InstrumentService;
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
	@GetMapping("/static_info")
	public ResponseEntity<List<CygInstrumentStatic>> getSettlementPrice(
			@RequestParam("instrumentCode") String instrumentCode, @RequestParam("tradingDay") int tradingDay) {
		if (checkParamIsNull(instrumentCode, tradingDay)) {
			return badRequest();
		}
		List<CygInstrumentStatic> instrumentSettlements = service.getInstrumentSettlement(instrumentCode, tradingDay);
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
		List<LastPrice> lastPrices = stream(instrumentCodes.split(",")).map(instrumentCode -> lastPriceMap
				.putIfAbsent(instrumentCode, new LastPrice().setInstrumentCode(instrumentCode)))
				.collect(Collectors.toList());

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
