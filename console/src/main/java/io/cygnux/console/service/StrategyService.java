package io.cygnux.console.service;

import io.cygnux.repository.dao.StrategyDao;
import io.cygnux.repository.entity.StrategyEntity;
import io.mercury.common.lang.Throws;
import io.mercury.common.log.Log4j2LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static io.cygnux.console.utils.ParamsValidateUtil.checkStrategyId;
import static io.cygnux.console.utils.ParamsValidateUtil.checkStrategyName;
import static io.mercury.common.functional.Functions.exec;
import static io.mercury.common.functional.Functions.execBool;

@Service
public final class StrategyService {

    private static final Logger log = Log4j2LoggerFactory.getLogger(StrategyService.class);

    @Resource
    private StrategyDao dao;

    /**
     * @return
     */
    public List<StrategyEntity> getStrategies() {
        return exec(() -> dao.findAll(), list -> list, e -> {
            log.error("query [StrategyEntity] exception", e);
        });
    }

    /**
     * @param strategyId
     * @return
     */
    public StrategyEntity getStrategy(int strategyId) {
        if (checkStrategyId(strategyId, log, "query [StrategyEntity] param error"))
            Throws.illegalArgument("strategyId");
        return dao.queryByStrategyId(strategyId);
    }

    /**
     * @param strategyName
     * @return
     */
    public List<StrategyEntity> getStrategy(String strategyName) {
        if (checkStrategyName(strategyName, log, "query [StrategyEntity] param error"))
            Throws.illegalArgument("strategyName");
        return dao.queryByStrategyName(strategyName);
    }


    /**
     * @param entity
     * @return
     */
    public boolean putStrategy(StrategyEntity entity) {
        return execBool(() -> dao.save(entity), o -> {
            log.info("save [StrategyEntity] success -> {}", entity);
            return true;
        }, e -> {
            log.error("save [StrategyEntity] failure -> {}", entity, e);
            return false;
        });
    }


}
