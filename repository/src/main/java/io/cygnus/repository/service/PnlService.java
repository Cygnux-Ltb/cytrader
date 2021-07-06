package io.cygnus.repository.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import io.cygnus.repository.dao.PnlDailyDao;
import io.cygnus.repository.dao.PnlSettlementDailyDao;
import io.cygnus.repository.entity.PnlDailyEntity;
import io.cygnus.repository.entity.PnlSettlementDailyEntity;

@Service
public final class PnlService {

	@Resource
	private PnlDailyDao pnlDailyDao;

	@Resource
	private PnlSettlementDailyDao pnlSettlementDailyDao;

	public List<PnlDailyEntity> getPnlDailys(int strategyId, int tradingDay) {
		List<PnlDailyEntity> list = pnlDailyDao.queryByStrategyIdAndTradingDay(strategyId, tradingDay);
		if (list == null)
			return new ArrayList<>();
		return list;
	}

	public List<PnlSettlementDailyEntity> getPnlSettlementDailys(int strategyId, int tradingDay) {
		List<PnlSettlementDailyEntity> list = pnlSettlementDailyDao.queryByStrategyIdAndTradingDay(strategyId,
				tradingDay);
		if (list == null)
			return new ArrayList<>();
		return list;
	}

	public void putPnlDailys(PnlDailyEntity pnlDaily) {

	}

	public void putPnlSettlementDailys(PnlDailyEntity pnlDaily) {

	}

}
