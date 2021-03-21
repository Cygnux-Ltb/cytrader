package io.cygnus.restful.service.resources.executor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;

import io.cygnus.db.dao.SymbolDao;
import io.cygnus.restful.service.base.BaseExecutor;
import io.cygnus.service.entity.SymbolInfo;
import io.cygnus.service.entity.SymbolTradingFee;
import io.cygnus.service.entity.SymbolTradingPeriod;
import io.cygnus.service.entity.TradeableInstrument;
import io.mercury.common.concurrent.list.CacheList;
import io.mercury.common.concurrent.map.GuavaCacheMap;
import io.mercury.common.log.CommonLoggerFactory;

public class SymbolExecutor extends BaseExecutor {

	private static final Logger log = CommonLoggerFactory.getLogger(SymbolExecutor.class);

	/**
	 * All SymbolInfo Cache
	 */
	private static final CacheList<SymbolInfo> AllSymbolInfoCache = new CacheList<>(() -> {
		SymbolDao symbolDao = new SymbolDao();
		return symbolDao.getAllSymbolInfo();
	});

	public List<SymbolInfo> getAllSymbolInfo() {
		return AllSymbolInfoCache.get();
	}

	/**
	 * SymbolInfo Cache by symbolName
	 */
	private static final GuavaCacheMap<String, List<SymbolInfo>> SymbolInfoCacheMap = GuavaCacheMap.newBuilder()
			.buildWith((symbol) -> {
				SymbolDao symbolDao = new SymbolDao();
				return symbolDao.getSymbolInfoByName(symbol);
			});

	public List<SymbolInfo> getSymbolInfoByName(String symbol) {
		return SymbolInfoCacheMap.getOptional(symbol).get();
	}

	/**
	 * SymbolTradingFee Cache by symbolName
	 */
	private static final GuavaCacheMap<String, List<SymbolTradingFee>> SymbolTradingFeeCacheMap = GuavaCacheMap
			.newBuilder().buildWith((symbol) -> {
				SymbolDao symbolDao = new SymbolDao();
				return symbolDao.getSymbolTradingFeeByName(symbol);
			});

	public List<SymbolTradingFee> getSymbolTradingFeeByName(String symbol) {
		return SymbolTradingFeeCacheMap.getOptional(symbol).get();
	}

	public boolean putSymbolTradingFeeByName(String symbol, SymbolTradingFee symbolTradingFee) {
		SymbolDao symbolDao = new SymbolDao();
		return symbolDao.putSymbolTradingFeeByName(symbol, symbolTradingFee);
	}

	/**
	 * SymbolTradingPeriod Cache by symbolName
	 */
	private static final GuavaCacheMap<String, List<SymbolTradingPeriod>> SymbolTradingPeriodCacheMap = GuavaCacheMap
			.newBuilder().buildWith((symbol) -> {
				SymbolDao symbolDao = new SymbolDao();
				return symbolDao.getSymbolTradingPeriodByName(symbol);
			});

	public List<SymbolTradingPeriod> getSymbolTradingPeriodByName(String symbol) {
		return SymbolTradingPeriodCacheMap.getOptional(symbol).get();
	}

	public boolean putSymbolTradingPeriodByName(String symbol, SymbolTradingPeriod symbolTradingPeriod) {
		SymbolDao symbolDao = new SymbolDao();
		return symbolDao.putSymbolTradingPeriodByName(symbol, symbolTradingPeriod);
	}

	/**
	 * 
	 * @param symbol
	 * @param tradingDay
	 * @return
	 */
	public List<TradeableInstrument> getTradeables(String symbol, Date tradingDay) {
		SymbolDao symbolDao = new SymbolDao();
		List<TradeableInstrument> instrumentByTradingDay = symbolDao.getTradeableInstrument(symbol, tradingDay);
		List<TradeableInstrument> instrumentByTradingDayAndSymbol = new ArrayList<>();
		for (TradeableInstrument instrument : instrumentByTradingDay) {
			if (instrument.getSymbol().equals(symbol)) {
				instrumentByTradingDayAndSymbol.add(instrument);
			}
		}
		return instrumentByTradingDayAndSymbol;
	}

	public boolean putTradeables(String symbol, Date tradingDay, String json) {
		return false;
	}

}
