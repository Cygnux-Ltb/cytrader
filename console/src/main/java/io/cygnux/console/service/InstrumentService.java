package io.cygnux.console.service;

import io.cygnux.repository.dao.InstrumentDao;
import io.cygnux.repository.dao.InstrumentSettlementDao;
import io.cygnux.repository.entity.InstrumentEntity;
import io.cygnux.repository.entity.InstrumentSettlementEntity;
import io.mercury.common.log.Log4j2LoggerFactory;
import io.mercury.serialization.json.JsonWrapper;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.Resource;
import java.util.List;

import static io.mercury.common.functional.Functions.exec;
import static io.mercury.common.functional.Functions.execBool;

@Service
public final class InstrumentService {

    private static final Logger log = Log4j2LoggerFactory.getLogger(InstrumentService.class);

    @Resource
    private InstrumentDao dao;

    @Resource
    private InstrumentSettlementDao settlementDao;

    /**
     * @param instrumentCode String
     * @return List<TInstrument>
     */
    public List<InstrumentEntity> getInstrument(@Nonnull String instrumentCode) {
        return exec(() -> dao.query(instrumentCode), list -> {
            if (CollectionUtils.isEmpty(list))
                log.warn("query [TInstrument] return 0 row, instrumentCode=={}", instrumentCode);
            else
                log.info("query [TInstrument] where instrumentCode=={}, result -> {}", instrumentCode,
                        JsonWrapper.toJson(list));
            return list;
        }, e -> {
            log.error("query [TInstrument] exception, instrumentCode=={}", instrumentCode, e);
        });
    }

    /**
     * @param instrumentCode String
     * @param tradingDay     int
     * @return List<TInstrumentSettlement>
     */
    public List<InstrumentSettlementEntity> getInstrumentSettlement(@Nonnull String instrumentCode, int tradingDay) {
        return exec(() -> settlementDao.queryByInstrumentCodeAndTradingDay(instrumentCode, tradingDay),
                list -> {
                    if (CollectionUtils.isEmpty(list))
                        log.warn("query [TInstrumentSettlement] return 0 row, instrumentCode=={}, tradingDay=={}",
                                instrumentCode, tradingDay);
                    else
                        log.info("query [TInstrumentSettlement] where instrumentCode=={} and tradingDay=={}, result -> {}",
                                instrumentCode, tradingDay, JsonWrapper.toPrettyJsonHasNulls(list));
                    return list;
                }, e -> {
                    log.error("query [TInstrumentSettlement] exception, instrumentCode=={}, tradingDay=={}",
                            instrumentCode, tradingDay, e);
                });
    }

    /**
     * @param instrument TInstrument
     * @return boolean
     */
    public boolean putInstrument(@Nonnull InstrumentEntity instrument) {
        return execBool(() -> dao.save(instrument), o -> {
            log.info("save [TInstrument] success, instrument -> {}", instrument);
            return true;
        }, e -> {
            log.error("save [TInstrument] failure, instrument -> {}", instrument, e);
            return false;
        });
    }

    /**
     * @param instrumentSettlement TInstrumentSettlement
     * @return
     */
    public boolean putInstrumentStatic(@Nonnull InstrumentSettlementEntity instrumentSettlement) {
        return execBool(() -> settlementDao.save(instrumentSettlement), o -> {
            log.info("save [TInstrumentSettlement] success -> {}", instrumentSettlement);
            return true;
        }, e -> {
            log.error("save [TInstrumentSettlement] failure -> {}", instrumentSettlement, e);
            return false;
        });
    }

}
