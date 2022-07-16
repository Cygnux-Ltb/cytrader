package io.cygnux.console.service;

import io.cygnux.repository.dao.BarDao;
import io.cygnux.repository.entities.TBar;
import io.mercury.common.log.Log4j2LoggerFactory;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.Resource;
import java.util.List;

import static io.mercury.common.functional.Functions.exec;
import static io.mercury.common.functional.Functions.execBool;

@Service
public class BarService {

    private static final Logger log = Log4j2LoggerFactory.getLogger(BarService.class);

    @Resource
    private BarDao dao;

    /**
     * @param instrumentCode String
     * @param tradingDay     int
     * @return List<TBar>
     */
    public List<TBar> getBars(@Nonnull String instrumentCode, int tradingDay) {
        return exec(() -> dao.queryByInstrumentCodeAndTradingDay(instrumentCode, tradingDay),
                list -> {
                    if (CollectionUtils.isEmpty(list))
                        log.warn("query [BarEntity] return 0 row, instrumentCode=={}, tradingDay=={}", instrumentCode,
                                tradingDay);
                    else
                        log.info("query [BarEntity] where instrumentCode=={}, tradingDay=={}, result row -> {}", instrumentCode,
                                tradingDay, list.size());
                    return list;
                }, e -> log.error("query [BarEntity] exception, instrumentCode=={}, tradingDay=={}", instrumentCode, tradingDay, e));
    }

    /**
     * @param bar TBar
     * @return boolean
     */
    public boolean putBar(@Nonnull TBar bar) {
        return execBool(() -> dao.save(bar), o -> {
            log.info("save [BarEntity] success -> {}", bar);
            return true;
        }, e -> {
            log.error("save [BarEntity] failure -> {}", bar, e);
            return false;
        });
    }

}
