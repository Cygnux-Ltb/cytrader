package io.cygnux.console.controller;

import static io.cygnux.console.dto.pack.OutboxTitle.StrategySwitch;
import static io.cygnux.console.utils.ResponseUtil.*;
import static io.cygnux.console.utils.ParamsValidateUtil.bodyToList;
import static io.cygnux.console.utils.ParamsValidateUtil.bodyToObject;

import java.util.*;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.http.HttpServletRequest;

import io.cygnux.console.dto.StrategySwitch;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.cygnux.console.dto.pack.OutboxMessage;
import io.cygnux.console.transport.CommandDispatcher;
import io.mercury.common.collections.MutableMaps;
import io.mercury.common.log.Log4j2LoggerFactory;
import io.mercury.serialization.json.JsonWrapper;
import io.mercury.transport.api.Publisher;

@RestController("/status")
public final class StatusController {

    private static final Logger log = Log4j2LoggerFactory.getLogger(StatusController.class);

    private static final ConcurrentMap<Integer, StrategySwitch> StrategySwitchMap = MutableMaps.newConcurrentHashMap();

    /**
     * @return
     */
    @GetMapping
    public ResponseEntity<Collection<StrategySwitch>> allStrategySwitch() {
        return responseOf(StrategySwitchMap.values());
    }

    /**
     * @param request
     * @return
     */
    @PutMapping("/command")
    public ResponseEntity<Object> statusCommand(@RequestBody HttpServletRequest request) {
        var strategySwitchList = bodyToList(request, StrategySwitch.class);
        if (strategySwitchList == null) {
            return badRequest();
        }
        // 将传入的StrategySwitch按照CygID分组
        Map<Integer, List<StrategySwitch>> strategySwitchListMap = new HashMap<>();
        for (StrategySwitch strategySwitch : strategySwitchList) {
            int cygId = strategySwitch.getProductId();
            if (strategySwitchListMap.containsKey(cygId)) {
                strategySwitchListMap.get(cygId).add(strategySwitch);
            } else {
                List<StrategySwitch> strategySwitchForProdId = new ArrayList<>();
                strategySwitchForProdId.add(strategySwitch);
                strategySwitchListMap.put(cygId, strategySwitchForProdId);
            }
        }
        // 按照CydId分别发送策略开关
        for (Integer cygId : strategySwitchListMap.keySet()) {
            //Publisher<String, String> publisher = CommandDispatcher.GROUP_INSTANCE.getMember(cygId);
            String msg = JsonWrapper
                    .toJson(new OutboxMessage<>(StrategySwitch.name(), strategySwitchListMap.get(cygId)));

            log.info("StrategySwitchs : {}", msg);
            //publisher.publish(msg);
        }
        return ok();
    }

    /**
     * @param productId
     * @param request
     * @return
     */
    @PutMapping("/update")
    public ResponseEntity<Object> statusUpdate(@RequestParam("productId") int productId,
                                               @RequestBody HttpServletRequest request) {
        StrategySwitch strategySwitch = bodyToObject(request, StrategySwitch.class);

        Objects.requireNonNull(strategySwitch, "Input StrategySwitch is null");
        strategySwitch.setProductId(productId);
        StrategySwitchMap.put(productId, strategySwitch);
        return ok();
    }

}