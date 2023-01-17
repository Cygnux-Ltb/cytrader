package io.cygnux.console.service;

import io.cygnux.console.persistence.dao.BarDao;
import io.cygnux.console.persistence.entity.BarEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import jakarta.annotation.Resource;
import java.util.List;

import static io.cygnux.console.persistence.util.DaoExecutor.insertOrUpdate;
import static io.cygnux.console.persistence.util.DaoExecutor.select;

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
        return select(() -> barDao.query(instrumentCode, startTradingDay, endTradingDay), BarEntity.class);
    }

    /**
     * @param entity BarEntity
     * @return boolean
     */
    public boolean putBar(@Nonnull BarEntity entity) {
        return insertOrUpdate(barDao, entity);
    }

}
