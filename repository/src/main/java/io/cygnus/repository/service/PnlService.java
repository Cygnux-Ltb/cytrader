package io.cygnus.repository.service;

import static io.mercury.common.functional.Functions.booleanFun;
import static io.mercury.common.functional.Functions.listFun;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import io.cygnus.repository.dao.PnlDailyDao;
import io.cygnus.repository.dao.PnlDailySettlementDao;
import io.cygnus.repository.entity.PnlDailyEntity;
import io.cygnus.repository.entity.PnlDailySettlementEntity;
import io.mercury.common.log.CommonLoggerFactory;

@Service
public final class PnlService {

	@Resource
	private PnlDailyDao dao;

	@Resource
	private PnlDailySettlementDao settlementDao;

	private static final Logger log = CommonLoggerFactory.getLogger(PnlService.class);

	/**
	 * 
	 * @param strategyId
	 * @param tradingDay
	 * @return
	 */
	public List<PnlDailyEntity> getPnlDailys(int strategyId, int tradingDay) {
		return listFun(() -> dao.queryByStrategyIdAndTradingDay(strategyId, tradingDay), list -> {
			if (CollectionUtils.isEmpty(list))
				log.warn("query [PnlDailyEntity] return 0 row, strategyId=={}, tradingDay=={}", strategyId, tradingDay);
			else
				log.info("query [PnlDailyEntity] where strategyId=={}, tradingDay=={}, result row -> {}", strategyId,
						tradingDay, list.size());
			return list;
		}, e -> {
			log.error("query [PnlDailyEntity] exception, strategyId=={}, tradingDay=={}, e.getMessage() -> {}",
					strategyId, tradingDay, e.getMessage(), e);
		});

	}

	/**
	 * 
	 * @param strategyId
	 * @param tradingDay
	 * @return
	 */
	public List<PnlDailySettlementEntity> getPnlDailySettlements(int strategyId, int tradingDay) {
		return listFun(() -> settlementDao.queryByStrategyIdAndTradingDay(strategyId, tradingDay), list -> {
			if (CollectionUtils.isEmpty(list))
				log.warn("query [PnlDailySettlementEntity] return 0 row, strategyId=={}, tradingDay=={}", strategyId,
						tradingDay);
			else
				log.info("query [PnlDailySettlementEntity] where strategyId=={}, tradingDay=={}, result row -> {}",
						strategyId, tradingDay, list.size());
			return list;
		}, e -> {
			log.error(
					"query [PnlDailySettlementEntity] exception, strategyId=={}, tradingDay=={}, e.getMessage() -> {}",
					strategyId, tradingDay, e.getMessage(), e);
		});
	}

	/**
	 * 
	 * @param pnlDaily
	 * @return
	 */
	public boolean putPnlDaily(PnlDailyEntity pnlDaily) {
		return booleanFun(() -> dao.save(pnlDaily), o -> {
			log.info("save [PnlDailyEntity] success -> {}", pnlDaily);
			return true;
		}, e -> {
			log.error("save [PnlDailyEntity] failure -> {}, message -> {}", pnlDaily, e.getMessage(), e);
			return false;
		});
	}

	/**
	 * 
	 * @param entity
	 * @return
	 */
	public boolean putPnlDailySettlement(PnlDailySettlementEntity entity) {
		return booleanFun(() -> settlementDao.save(entity), o -> {
			log.info("save [PnlDailySettlementEntity] success -> {}", entity);
			return true;
		}, e -> {
			log.error("save [PnlDailySettlementEntity] failure -> {}, message -> {}", entity, e.getMessage(), e);
			return false;
		});
	}

}
