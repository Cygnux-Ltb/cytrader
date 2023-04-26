package io.cygnuxltb.console.persistence.util;

import io.mercury.common.lang.Throws;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
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

    /**
     * @param func Supplier<List<T>>
     * @param type Class<T>
     * @return List<T>
     */
    public static <T> List<T> select(Class<T> type, Supplier<List<T>> func) {
        return exec(func,
                result -> {
                    if (isEmpty(result))
                        log.warn("query [{}] return 0 row", type.getSimpleName());
                    else if (result.size() > 4)
                        log.info("query [{}] return {} row", type.getSimpleName(), result.size());
                    else
                        log.info("query [{}] return {} row, result -> {}", type.getSimpleName(),
                                result.size(), JsonWrapper.toJson(result));
                    return result;
                },
                e -> log.error("query [{}], an exception occurred", type.getSimpleName(), e));
    }

    /**
     * @param repository JpaRepository<T, Long>
     * @param entity     T
     * @return boolean
     */
    public static <T> boolean insertOrUpdate(JpaRepository<T, Long> repository, T entity) {
        return execBool(
                () -> {
                    if (entity == null)
                        Throws.illegalArgument("entity");
                    return repository.saveAndFlush(entity);
                },
                o -> {
                    log.info("insert or update [{}] success, entity -> {}",
                            entity.getClass().getSimpleName(), entity);
                    return true;
                },
                e -> {
                    log.error("insert or update [{}] failure, entity -> {}",
                            entity.getClass().getSimpleName(), entity, e);
                    return false;
                });
    }

}
