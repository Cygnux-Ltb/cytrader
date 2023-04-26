package io.cygnuxltb.console.service;

import io.cygnuxltb.console.persistence.dao.PnlDao;
import io.cygnuxltb.console.persistence.dao.PnlSettlementDao;
import io.cygnuxltb.console.persistence.entity.PnlEntity;
import io.cygnuxltb.console.persistence.entity.PnlSettlementEntity;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

import static io.cygnuxltb.console.persistence.util.DaoExecutor.insertOrUpdate;
import static io.cygnuxltb.console.persistence.util.DaoExecutor.select;

@Service
public final class PnlService {

    @Resource
    private PnlDao dao;

    @Resource
    private PnlSettlementDao settlementDao;

    /**
     * @param strategyId int
     * @param tradingDay int
     * @return List<PnlEntity>
     */
    public List<PnlEntity> getPnl(int strategyId, int tradingDay) {
        return select(PnlEntity.class,
                () -> dao.queryByStrategyIdAndTradingDay(strategyId, tradingDay));
    }

    /**
     * @param strategyId int
     * @param tradingDay int
     * @return List<PnlSettlementEntity>
     */
    public List<PnlSettlementEntity> getPnlSettlement(int strategyId, int tradingDay) {
        return select(PnlSettlementEntity.class,
                () -> settlementDao.queryByStrategyIdAndTradingDay(strategyId, tradingDay));
    }

    /**
     * @param entity PnlEntity
     * @return boolean
     */
    public boolean putPnl(PnlEntity entity) {
        return insertOrUpdate(dao, entity);
    }

    /**
     * @param entity PnlSettlementEntity
     * @return boolean
     */
    public boolean putPnlSettlement(PnlSettlementEntity entity) {
        return insertOrUpdate(settlementDao, entity);
    }

}
