package io.cygnux.console.persistence.dao.util;

import io.mercury.common.lang.Throws;
import io.mercury.common.log.Log4j2LoggerFactory;
import io.mercury.serialization.json.JsonWrapper;
import org.slf4j.Logger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.function.Supplier;

import static io.mercury.common.functional.Functions.exec;
import static io.mercury.common.functional.Functions.execBool;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;

public final class DaoExecutor {

    private static final Logger log = Log4j2LoggerFactory.getLogger(DaoExecutor.class);

    public static <T> List<T> select(Supplier<List<T>> query, Class<T> type) {
        return exec(query,
                result -> {
                    if (isEmpty(result))
                        log.warn("query [{}] return 0 row", type.getSimpleName());
                    else if (result.size() > 4)
                        log.info("query [{}] return {} row", type.getSimpleName(), result.size());
                    else
                        log.info("query [{}], result -> {}", type.getSimpleName(), JsonWrapper.toJson(result));
                    return result;
                },
                e -> log.error("query all [{}], an exception occurred", type.getSimpleName(), e));
    }

    public static <T> boolean insertOrUpdate(JpaRepository<T, Long> repository, T entity) {
        return execBool(() -> {
            if (entity == null)
                Throws.illegalArgument("entity");
            return repository.saveAndFlush(entity);
        }, o -> {
            log.info("insert [{}] success, entity -> {}", entity.getClass().getSimpleName(), entity);
            return true;
        }, e -> {
            log.error("insert [{}] failure, entity -> {}", entity.getClass().getSimpleName(), entity, e);
            return false;
        });
    }

}
