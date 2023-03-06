package io.cygnuxltb.console.service;

import io.cygnuxltb.console.persistence.dao.InstrumentDao;
import io.cygnuxltb.console.persistence.dao.InstrumentSettlementDao;
import io.cygnuxltb.console.persistence.entity.InstrumentEntity;
import io.cygnuxltb.console.persistence.entity.InstrumentSettlementEntity;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.List;

import static io.cygnuxltb.console.persistence.util.DaoExecutor.insertOrUpdate;
import static io.cygnuxltb.console.persistence.util.DaoExecutor.select;

@Service
public final class InstrumentService {

    @Resource
    private InstrumentDao instrumentDao;

    @Resource
    private InstrumentSettlementDao instrumentSettlementDao;

    /**
     * @param instrumentCode String
     * @return List<InstrumentEntity>
     */
    public List<InstrumentEntity> getInstrument(@Nonnull String instrumentCode) {
        return select(() -> instrumentDao.queryBy(instrumentCode),
                InstrumentEntity.class);
    }

    /**
     * @param instrumentCode String
     * @param tradingDay     int
     * @return List<InstrumentSettlementEntity>
     */
    public List<InstrumentSettlementEntity> getInstrumentSettlement(@Nonnull String instrumentCode, int tradingDay) {
        return select(() -> instrumentSettlementDao.queryByInstrumentCodeAndTradingDay(instrumentCode, tradingDay),
                InstrumentSettlementEntity.class);
    }

    /**
     * @param entity InstrumentEntity
     * @return boolean
     */
    public boolean putInstrument(@Nonnull InstrumentEntity entity) {
        return insertOrUpdate(instrumentDao, entity);
    }

    /**
     * @param entity InstrumentSettlementEntity
     * @return boolean
     */
    public boolean putInstrumentStatic(@Nonnull InstrumentSettlementEntity entity) {
        return insertOrUpdate(instrumentSettlementDao, entity);
    }

}
