package io.cygnux.console.service.base;

import org.slf4j.Logger;

import io.mercury.common.util.StringSupport;

public abstract class BaseService {

	protected final boolean checkStrategyId(int strategyId, Logger log, String msg) {
		if (strategyId < 0) {
			log.error("{}, strategyId=={}", msg, strategyId);
			return true;
		}
		return false;
	}

	protected final boolean checkStrategyName(String strategyName, Logger log, String msg) {
		if (StringSupport.isNullOrEmpty(strategyName)) {
			log.error("{}, strategyName=={}", msg, strategyName);
			return true;
		}
		return false;
	}

	protected final boolean checkInvestorId(String investorId, Logger log, String msg) {
		if (StringSupport.isNullOrEmpty(investorId)) {
			log.error("{}, investorId=={}", msg, investorId);
			return true;
		}
		return false;
	}

	protected final boolean checkInstrumentCode(String instrumentCode, Logger log, String msg) {
		if (StringSupport.isNullOrEmpty(instrumentCode)) {
			log.error("{}, instrumentCode=={}", msg, instrumentCode);
			return true;
		}
		return false;
	}

	protected final boolean checkTradingDay(int tradingDay, Logger log, String msg) {
		if (tradingDay <= 0) {
			log.error("{}, tradingDay=={}", msg, tradingDay);
			return true;
		}
		return false;
	}

	protected final boolean checkTradingDay(int startTradingDay, int endTradingDay, Logger log, String msg) {
		if (startTradingDay <= 0 || endTradingDay < startTradingDay) {
			log.error("{}, startTradingDay=={}, endTradingDay=={}", msg, startTradingDay, endTradingDay);
			return true;
		}
		return false;
	}

	protected final boolean checkOrdSysId(long ordSysId, Logger log, String msg) {
		if (ordSysId <= 0) {
			log.error("{}, ordSysId=={}", msg, ordSysId);
			return true;
		}
		return false;
	}

}
