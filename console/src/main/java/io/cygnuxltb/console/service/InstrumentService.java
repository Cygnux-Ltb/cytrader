package io.cygnuxltb.console.service;

import io.cygnuxltb.console.persistence.dao.InstrumentDao;
import io.cygnuxltb.console.persistence.dao.InstrumentSettlementDao;
import io.cygnuxltb.console.persistence.entity.InstrumentEntity;
import io.cygnuxltb.console.persistence.entity.InstrumentSettlementEntity;
import io.cygnuxltb.console.service.bean.OutboundConverter;
import io.cygnuxltb.protocol.http.inbound.InstrumentPrice;
import io.cygnuxltb.protocol.http.outbound.InstrumentDTO;
import io.cygnuxltb.protocol.http.outbound.InstrumentSettlementDTO;
import io.mercury.common.collections.MutableMaps;
import jakarta.annotation.Resource;
import org.eclipse.collections.api.map.ConcurrentMutableMap;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;

import static io.cygnuxltb.console.persistence.util.DaoExecutor.insertOrUpdate;
import static io.cygnuxltb.console.persistence.util.DaoExecutor.select;
import static java.util.Arrays.stream;

@Service
public final class InstrumentService {

    @Resource
    private InstrumentDao dao;

    @Resource
    private InstrumentSettlementDao settlementDao;

    // LastPrices Cache
    private final ConcurrentMutableMap<String, InstrumentPrice> lastPriceMap = MutableMaps.newConcurrentHashMap();

    private InstrumentPrice getInstrumentPrice(String instrumentCode) {
        return lastPriceMap.putIfAbsent(instrumentCode, new InstrumentPrice(instrumentCode));
    }

    /**
     * @param instrumentCode String
     * @return List<InstrumentEntity>
     */
    public List<InstrumentDTO> getInstrument(@Nonnull String instrumentCode) {
        return select(InstrumentEntity.class,
                () -> dao.queryBy(instrumentCode))
                .stream()
                .map(OutboundConverter::toInstrumentDTO)
                .collect(Collectors.toList());
    }

    /**
     * @param tradingDay     int
     * @param instrumentCode String
     * @return List<InstrumentSettlementEntity>
     */
    public List<InstrumentSettlementDTO> getInstrumentSettlement(int tradingDay,
                                                                 @Nonnull String instrumentCode) {
        return select(InstrumentSettlementEntity.class,
                () -> settlementDao
                        .queryByInstrumentCodeAndTradingDay(instrumentCode, tradingDay))
                .stream()
                .map(OutboundConverter::toInstrumentSettlementDTO)
                .collect(Collectors.toList());
    }

    /**
     * @param instrumentCodes String[]
     * @return List<InstrumentPrice>
     */
    public List<InstrumentPrice> getLastPrice(String... instrumentCodes) {
        return stream(instrumentCodes)
                .map(this::getInstrumentPrice)
                .collect(Collectors.toList());
    }


    /**
     * @param entity InstrumentEntity
     * @return boolean
     */
    public boolean putInstrument(@Nonnull InstrumentEntity entity) {
        return insertOrUpdate(dao, entity);
    }

    /**
     * @param entity InstrumentSettlementEntity
     * @return boolean
     */
    public boolean putInstrumentStatic(@Nonnull InstrumentSettlementEntity entity) {
        return insertOrUpdate(settlementDao, entity);
    }

    /**
     * @param instrumentCode String
     * @param price          double
     * @return boolean
     */
    public boolean putLastPrice(@Nonnull String instrumentCode, double price) {
        try {
            getInstrumentPrice(instrumentCode).setLastPrice(price);
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

}
