package io.cygnus.restful.service;

import static io.mercury.common.functional.Functions.exec;
import static io.mercury.common.functional.Functions.execBool;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import io.cygnus.repository.dao.PnlDailyDao;
import io.cygnus.repository.dao.PnlDailySettlementDao;
import io.cygnus.repository.entity.CygPnlDaily;
import io.cygnus.repository.entity.CygPnlDailySettlement;
import io.mercury.common.log.CommonLoggerFactory;

@Service
public final class PnlService {

	private final Logger log = CommonLoggerFactory.getLogger(PnlService.class);

	@Resource
	private PnlDailyDao dao;

	@Resource
	private PnlDailySettlementDao settlementDao;

	/**
	 * 
	 * @param strategyId
	 * @param tradingDay
	 * @return
	 */
	public List<CygPnlDaily> getPnlDailys(int strategyId, int tradingDay) {
		return exec(() -> dao.queryByStrategyIdAndTradingDay(strategyId, tradingDay), list -> {
			if (CollectionUtils.isEmpty(list))
				log.warn("query [PnlDailyEntity] return 0 row, strategyId=={}, tradingDay=={}", strategyId, tradingDay);
			else
				log.info("query [PnlDailyEntity] where strategyId=={}, tradingDay=={}, result row -> {}", strategyId,
						tradingDay, list.size());
			return list;
		}, e -> {
			log.error("query [PnlDailyEntity] exception, strategyId=={}, tradingDay=={}", strategyId, tradingDay, e);
		});

	}

	/**
	 * 
	 * @param strategyId
	 * @param tradingDay
	 * @return
	 */
	public List<CygPnlDailySettlement> getPnlDailySettlements(int strategyId, int tradingDay) {
		return exec(() -> settlementDao.queryByStrategyIdAndTradingDay(strategyId, tradingDay), list -> {
			if (CollectionUtils.isEmpty(list))
				log.warn("query [PnlDailySettlementEntity] return 0 row, strategyId=={}, tradingDay=={}", strategyId,
						tradingDay);
			else
				log.info("query [PnlDailySettlementEntity] where strategyId=={}, tradingDay=={}, result row -> {}",
						strategyId, tradingDay, list.size());
			return list;
		}, e -> {
			log.error("query [PnlDailySettlementEntity] exception, strategyId=={}, tradingDay=={}", strategyId,
					tradingDay, e);
		});
	}

	/**
	 * 
	 * @param pnlDaily
	 * @return
	 */
	public boolean putPnlDaily(CygPnlDaily pnlDaily) {
		return execBool(() -> dao.save(pnlDaily), o -> {
			log.info("save [PnlDailyEntity] success -> {}", pnlDaily);
			return true;
		}, e -> {
			log.error("save [PnlDailyEntity] failure -> {}", pnlDaily, e);
			return false;
		});
	}

	/**
	 * 
	 * @param entity
	 * @return
	 */
	public boolean putPnlDailySettlement(CygPnlDailySettlement entity) {
		return execBool(() -> settlementDao.save(entity), o -> {
			log.info("save [PnlDailySettlementEntity] success -> {}", entity);
			return true;
		}, e -> {
			log.error("save [PnlDailySettlementEntity] failure -> {}", entity, e);
			return false;
		});
	}

}
