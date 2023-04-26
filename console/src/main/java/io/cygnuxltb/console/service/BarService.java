package io.cygnuxltb.console.service;

import io.cygnuxltb.console.persistence.dao.BarDao;
import io.cygnuxltb.console.persistence.entity.BarEntity;
import io.cygnuxltb.console.service.bean.OutboundConverter;
import io.cygnuxltb.protocol.http.outbound.BarM1DTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;

import static io.cygnuxltb.console.persistence.util.DaoExecutor.insertOrUpdate;
import static io.cygnuxltb.console.persistence.util.DaoExecutor.select;

@Service
public class BarService {


    @Resource
    private BarDao dao;

    /**
     * @param instrumentCode String
     * @param tradingDay     int
     * @return List<BarEntity>
     */
    public List<BarM1DTO> getBars(@Nonnull String instrumentCode, int tradingDay) {
        return getBars(instrumentCode, tradingDay, tradingDay);
    }


    /**
     * @param instrumentCode  String
     * @param startTradingDay int
     * @param endTradingDay   int
     * @return List<BarEntity>
     */
    public List<BarM1DTO> getBars(@Nonnull String instrumentCode, int startTradingDay, int endTradingDay) {
        return select(BarEntity.class,
                () -> dao.queryBy(instrumentCode, startTradingDay, endTradingDay))
                .stream()
                .map(OutboundConverter::toBarDTO)
                .collect(Collectors.toList());
    }

    /**
     * @param entity BarEntity
     * @return boolean
     */
    public boolean putBar(@Nonnull BarEntity entity) {
        return insertOrUpdate(dao, entity);
    }

}
