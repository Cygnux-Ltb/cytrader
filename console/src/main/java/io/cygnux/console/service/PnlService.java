package io.cygnux.console.service;

import static io.mercury.common.functional.Functions.exec;
import static io.mercury.common.functional.Functions.execBool;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import io.cygnux.repository.dao.PnlDailyDao;
import io.cygnux.repository.dao.PnlDailySettlementDao;
import io.cygnux.repository.entity.PnlEntity;
import io.cygnux.repository.entity.PnlSettlementEntity;
import io.mercury.common.log.Log4j2LoggerFactory;

@Service
public final class PnlService {

    private static final Logger log = Log4j2LoggerFactory.getLogger(PnlService.class);

    @Resource
    private PnlDailyDao dao;

    @Resource
    private PnlDailySettlementDao settlementDao;

    /**
     * @param strategyId int
     * @param tradingDay int
     * @return List<StPnl>
     */
    public List<PnlEntity> getPnl(int strategyId, int tradingDay) {
        return exec(() -> dao.queryByStrategyIdAndTradingDay(strategyId, tradingDay), list -> {
            if (CollectionUtils.isEmpty(list))
                log.warn("query [PnlDailyEntity] return 0 row, strategyId=={}, tradingDay=={}", strategyId, tradingDay);
            else
                log.info("query [PnlDailyEntity] where strategyId=={}, tradingDay=={}, result row -> {}", strategyId,
                        tradingDay, list.size());
            return list;
        }, e -> log.error("query [PnlDailyEntity] exception, strategyId=={}, tradingDay=={}", strategyId, tradingDay, e));

    }

    /**
     * @param strategyId int
     * @param tradingDay int
     * @return List<StPnlSettlement>
     */
    public List<PnlSettlementEntity> getPnlSettlement(int strategyId, int tradingDay) {
        return exec(() -> settlementDao.queryByStrategyIdAndTradingDay(strategyId, tradingDay), list -> {
            if (CollectionUtils.isEmpty(list))
                log.warn("query [PnlDailySettlementEntity] return 0 row, strategyId=={}, tradingDay=={}", strategyId,
                        tradingDay);
            else
                log.info("query [PnlDailySettlementEntity] where strategyId=={}, tradingDay=={}, result row -> {}",
                        strategyId, tradingDay, list.size());
            return list;
        }, e -> log.error("query [PnlDailySettlementEntity] exception, strategyId=={}, tradingDay=={}", strategyId,
                tradingDay, e));
    }

    /**
     * @param pnl StPnl
     * @return
     */
    public boolean putPnl(PnlEntity pnl) {
        return execBool(() -> dao.save(pnl), o -> {
            log.info("save [PnlDailyEntity] success -> {}", pnl);
            return true;
        }, e -> {
            log.error("save [PnlDailyEntity] failure -> {}", pnl, e);
            return false;
        });
    }

    /**
     * @param pnlSettlement StPnlSettlement
     * @return
     */
    public boolean putPnlSettlement(PnlSettlementEntity pnlSettlement) {
        return execBool(() -> settlementDao.save(pnlSettlement), o -> {
            log.info("save [PnlDailySettlementEntity] success -> {}", pnlSettlement);
            return true;
        }, e -> {
            log.error("save [PnlDailySettlementEntity] failure -> {}", pnlSettlement, e);
            return false;
        });
    }

}
