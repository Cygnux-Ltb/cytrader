package io.cygnus.restful.service.resources;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.cygnus.restful.service.base.CygRestfulApi;
import io.cygnus.restful.service.resources.executor.SymbolExecutor;
import io.cygnus.service.entity.SymbolInfo;
import io.cygnus.service.entity.SymbolTradingFee;
import io.cygnus.service.entity.SymbolTradingPeriod;
import io.cygnus.service.entity.TradeableInstrument;
import io.mercury.common.util.StringUtil;

@RestController("/symbol")
public class SymbolRestfulApi extends CygRestfulApi {

	/**
	 * 执行具体操作的executor
	 */
	private SymbolExecutor executor = new SymbolExecutor();

	/**
	 * 返回全部Symbol
	 * 
	 * @return
	 */
	@GetMapping
	public ResponseEntity<Object> getAllSymbolInfo() {
		List<SymbolInfo> allSymbolInfo = executor.getAllSymbolInfo();
		return jsonResponse(allSymbolInfo);
	}

	/**
	 * Get SymbolInfo for [symbol]
	 * 
	 * @param symbol
	 * @return
	 */
	@GetMapping("/{symbol}")
	public ResponseEntity<Object> getSymbolInfoByName(@RequestParam("symbol") String symbol) {
		List<SymbolInfo> symbolInfoByName = executor.getSymbolInfoByName(symbol);
		return jsonResponse(symbolInfoByName);
	}

	/**
	 * Get [SymbolTradingFee] for [symbol]
	 * 
	 * @param symbol
	 * @return
	 */
	@GetMapping("/{symbol}/trading_fee")
	public ResponseEntity<Object> getSymbolTradingFeeByName(@RequestParam("symbol") String symbol) {
		List<SymbolTradingFee> symbolTradingFeeByName = executor.getSymbolTradingFeeByName(symbol);
		return jsonResponse(symbolTradingFeeByName);
	}

	/**
	 * Put [SymbolTradingFee] for [symbol]
	 * 
	 * @param symbol
	 * @param request
	 * @return
	 */
	@PutMapping("/{symbol}/trading_fee")
	public ResponseEntity<Object> putSymbolTradingFeeByName(@RequestParam("symbol") String symbol,
			@RequestBody HttpServletRequest request) {
		String json = getBody(request);
		if (StringUtil.isNullOrEmpty(json)) {
			return httpBadRequest();
		}
		SymbolTradingFee symbolTradingFee = jsonToObj(json, SymbolTradingFee.class);
		if (executor.putSymbolTradingFeeByName(symbol, symbolTradingFee)) {
			return httpOk();
		} else {
			return httpInternalServerError();
		}
	}

	/**
	 * Get [SymbolTradingPeriod] for [symbol]
	 * 
	 * @param symbol
	 * @return
	 */
	@GetMapping("/{symbol}/trading_period")
	public ResponseEntity<Object> getSymbolTradingPeriodByName(@RequestParam("symbol") String symbol) {
		List<SymbolTradingPeriod> symbolTradingPeriodByName = executor.getSymbolTradingPeriodByName(symbol);
		return jsonResponse(symbolTradingPeriodByName);
	}

	/**
	 * Put [SymbolTradingPeriod] for [symbol]
	 * 
	 * @param symbol
	 * @param request
	 * @return
	 */
	@PutMapping("/{symbol}/trading_period")
	public ResponseEntity<Object> putSymbolTradingPeriodByName(@RequestParam("symbol") String symbol,
			@RequestBody HttpServletRequest request) {
		String json = getBody(request);
		if (json == null || json.equals("")) {
			return httpBadRequest();
		}
		SymbolTradingPeriod symbolTradingPeriod = jsonToObj(json, SymbolTradingPeriod.class);
		if (executor.putSymbolTradingPeriodByName(symbol, symbolTradingPeriod)) {
			return httpOk();
		} else {
			return httpInternalServerError();
		}
	}

	/**
	 * Get [TradeableInstrument] for [symbol] and [tradingDay]
	 * 
	 * @param symbol
	 * @param tradingDay
	 * @return
	 */

	@GetMapping("/{symbol}/tradeable/{tradingDay}")
	public ResponseEntity<Object> getTradeables(@RequestParam("symbol") String symbol,
			@RequestParam("tradingDay") String tradingDay) {
		Date dateTradingDay = changeTradingDay(tradingDay);
		if (dateTradingDay == null) {
			return httpBadRequest();
		}
		List<TradeableInstrument> tradeables = executor.getTradeables(symbol, dateTradingDay);
		return jsonResponse(tradeables);
	}

}
