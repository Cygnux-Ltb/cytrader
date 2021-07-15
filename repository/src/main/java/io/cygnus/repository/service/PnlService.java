package io.cygnus.repository.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import io.cygnus.repository.dao.PnlDailyDao;
import io.cygnus.repository.dao.PnlSettlementDailyDao;
import io.cygnus.repository.entity.PnlDailyEntity;
import io.cygnus.repository.entity.PnlSettlementDailyEntity;
import io.mercury.common.log.CommonLoggerFactory;

@Service
public final class PnlService {

	@Resource
	private PnlDailyDao pnlDailyDao;

	@Resource
	private PnlSettlementDailyDao pnlSettlementDailyDao;

	private static final Logger log = CommonLoggerFactory.getLogger(PnlService.class);

	/**
	 * 
	 * @param strategyId
	 * @param tradingDay
	 * @return
	 */
	public List<PnlDailyEntity> getPnlDailys(int strategyId, int tradingDay) {
		List<PnlDailyEntity> list = pnlDailyDao.queryByStrategyIdAndTradingDay(strategyId, tradingDay);
		if (list == null)
			return new ArrayList<>();
		return list;
	}

	/**
	 * 
	 * @param strategyId
	 * @param tradingDay
	 * @return
	 */
	public List<PnlSettlementDailyEntity> getPnlSettlementDailys(int strategyId, int tradingDay) {
		List<PnlSettlementDailyEntity> list = pnlSettlementDailyDao.queryByStrategyIdAndTradingDay(strategyId,
				tradingDay);
		if (list == null)
			return new ArrayList<>();
		return list;
	}

	/**
	 * 
	 * @param pnlDaily
	 * @return
	 */
	public boolean putPnlDaily(PnlDailyEntity pnlDaily) {
		try {
			pnlDailyDao.save(pnlDaily);
			log.info("save PnlDaily entity success -> {}", pnlDaily);
			return true;
		} catch (Exception e) {
			log.error("save PnlDaily entity failure -> {}, message -> {}", pnlDaily, e.getMessage(), e);
			return false;
		}
	}

	/**
	 * 
	 * @param pnlSettlementDailyEntity
	 * @return
	 */
	public boolean putPnlSettlementDaily(PnlSettlementDailyEntity pnlSettlementDailyEntity) {
		try {
			pnlSettlementDailyDao.save(pnlSettlementDailyEntity);
			log.info("save PnlSettlementDaily entity success -> {}", pnlSettlementDailyEntity);
			return true;
		} catch (Exception e) {
			log.error("save PnlSettlementDaily entity failure -> {}, message -> {}", pnlSettlementDailyEntity,
					e.getMessage(), e);
			return false;
		}
	}

}
