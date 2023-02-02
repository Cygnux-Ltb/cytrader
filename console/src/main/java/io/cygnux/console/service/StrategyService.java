package io.cygnux.console.service;

import io.cygnux.console.persistence.dao.StrategyDao;
import io.cygnux.console.persistence.entity.StrategyEntity;
import io.cygnux.console.persistence.util.DaoExecutor;
import io.mercury.common.lang.Throws;
import io.mercury.common.log.Log4j2LoggerFactory;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.util.List;

import static io.cygnux.console.controller.util.RequestUtil.checkStrategyId;
import static io.cygnux.console.controller.util.RequestUtil.checkStrategyName;
import static io.cygnux.console.persistence.util.DaoExecutor.select;

@Service
public final class StrategyService {

    private static final Logger log = Log4j2LoggerFactory.getLogger(StrategyService.class);

    @Resource
    private StrategyDao strategyDao;

    /**
     * @return List<StrategyEntity>
     */
    public List<StrategyEntity> getAllStrategy() {
        return select(() -> strategyDao.findAll(), StrategyEntity.class);
    }

    /**
     * @param strategyId int
     * @return StrategyEntity
     */
    @Nullable
    public StrategyEntity getStrategy(int strategyId) {
        if (checkStrategyId(strategyId, log, "query [StrategyEntity] param error"))
            Throws.illegalArgument("strategyId");
        StrategyEntity entity = strategyDao.queryByStrategyId(strategyId);
        if (entity == null)
            log.error("entity == null where strategyId -> {}", strategyId);
        return entity;
    }

    /**
     * @param strategyName String
     * @return StrategyEntity
     */
    @Nullable
    public StrategyEntity getStrategy(String strategyName) {
        if (checkStrategyName(strategyName, log, "query [StrategyEntity] param error"))
            Throws.illegalArgument("strategyName");
        StrategyEntity entity = strategyDao.queryByStrategyName(strategyName);
        if (entity == null)
            log.error("entity == null where strategyName -> {}", strategyName);
        return entity;
    }


    /**
     * @param entity StrategyEntity
     * @return boolean
     */
    public boolean putStrategy(StrategyEntity entity) {
        return DaoExecutor.insertOrUpdate(strategyDao, entity);
    }


}
