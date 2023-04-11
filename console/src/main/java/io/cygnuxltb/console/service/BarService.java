package io.cygnuxltb.console.service;

import io.cygnuxltb.console.persistence.dao.BarDao;
import io.cygnuxltb.console.persistence.entity.BarEntity;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.List;

import static io.cygnuxltb.console.persistence.util.DaoExecutor.insertOrUpdate;
import static io.cygnuxltb.console.persistence.util.DaoExecutor.select;

@Service
public class BarService {

    @Resource
    private BarDao barDao;

    /**
     * @param instrumentCode String
     * @param tradingDay     int
     * @return List<BarEntity>
     */
    public List<BarEntity> getBars(@Nonnull String instrumentCode, int tradingDay) {
        return getBars(instrumentCode, tradingDay, tradingDay);
    }


    /**
     * @param instrumentCode  String
     * @param startTradingDay int
     * @param endTradingDay   int
     * @return List<BarEntity>
     */
    public List<BarEntity> getBars(@Nonnull String instrumentCode, int startTradingDay, int endTradingDay) {
        return select(() -> barDao.queryBy(instrumentCode, startTradingDay, endTradingDay),
                BarEntity.class);
    }

    /**
     * @param entity BarEntity
     * @return boolean
     */
    public boolean putBar(@Nonnull BarEntity entity) {
        return insertOrUpdate(barDao, entity);
    }

}
