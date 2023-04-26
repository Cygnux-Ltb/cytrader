package io.cygnuxltb.console.service;

import io.cygnuxltb.console.persistence.dao.StrategyDao;
import io.cygnuxltb.console.persistence.entity.StrategyEntity;
import io.cygnuxltb.console.persistence.util.DaoExecutor;
import io.mercury.common.lang.Throws;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.util.List;

import static io.cygnuxltb.console.controller.util.ControllerUtil.illegalStrategyId;
import static io.cygnuxltb.console.controller.util.ControllerUtil.illegalStrategyName;
import static io.cygnuxltb.console.persistence.util.DaoExecutor.select;

@Service
public final class StrategyService {

    private static final Logger log = Log4j2LoggerFactory.getLogger(StrategyService.class);

    @Resource
    private StrategyDao strategyDao;

    /**
     * @return List<StrategyEntity>
     */
    public List<StrategyEntity> getAllStrategy() {
        return select(StrategyEntity.class,
                () -> strategyDao.findAll());
    }

    /**
     * @param strategyId int
     * @return StrategyEntity
     */
    @Nullable
    public StrategyEntity getStrategy(int strategyId) {
        if (illegalStrategyId(strategyId, log))
            Throws.illegalArgument("strategyId");
        StrategyEntity entity = strategyDao.queryByStrategyId(strategyId);
        if (entity == null)
            log.warn("entity == null where strategyId -> {}", strategyId);
        return entity;
    }

    /**
     * @param strategyName String
     * @return StrategyEntity
     */
    @Nullable
    public StrategyEntity getStrategy(String strategyName) {
        if (illegalStrategyName(strategyName, log))
            Throws.illegalArgument("strategyName");
        StrategyEntity entity = strategyDao.queryByStrategyName(strategyName);
        if (entity == null)
            log.warn("entity == null where strategyName -> {}", strategyName);
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
