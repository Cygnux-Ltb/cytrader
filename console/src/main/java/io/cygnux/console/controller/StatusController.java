package io.cygnux.console.controller;

import io.cygnux.console.controller.base.ServiceException;
import io.cygnux.console.service.dto.StrategySwitch;
import io.cygnux.console.service.dto.pack.OutboxMessage;
import io.mercury.common.collections.MutableMaps;
import io.mercury.common.log.Log4j2LoggerFactory;
import io.mercury.serialization.json.JsonWrapper;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentMap;

import static io.cygnux.console.controller.util.RequestUtil.bodyToList;
import static io.cygnux.console.controller.util.RequestUtil.bodyToObject;
import static io.cygnux.console.controller.util.ResponseUtil.badRequest;
import static io.cygnux.console.controller.util.ResponseUtil.ok;
import static io.cygnux.console.service.dto.pack.OutboxTitle.StrategySwitch;
import static io.mercury.common.http.MimeType.APPLICATION_JSON_UTF8;

@RestController
@RequestMapping(path = "/status")
public final class StatusController {

    private static final Logger log = Log4j2LoggerFactory.getLogger(StatusController.class);

    private static final ConcurrentMap<Integer, StrategySwitch> StrategySwitchMap = MutableMaps.newConcurrentHashMap();

    /**
     * @return ResponseEntity<Collection < StrategySwitch>>
     */
    @ExceptionHandler(ServiceException.class)
    @GetMapping
    public Collection<StrategySwitch> allStrategySwitch() {
        return StrategySwitchMap.values();
    }

    /**
     * @param request HttpServletRequest
     * @return ResponseEntity<?>
     */
    @ExceptionHandler(ServiceException.class)
    @PutMapping(path = "/command", consumes = APPLICATION_JSON_UTF8)
    public ResponseEntity<?> statusCommand(@RequestBody HttpServletRequest request) {
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

            log.info("StrategySwitch : {}", msg);
            //publisher.publish(msg);
        }
        return ok();
    }

    /**
     * @param productId int
     * @param request   HttpServletRequest
     * @return ResponseEntity<?>
     */
    @PutMapping("/update")
    public ResponseEntity<?> statusUpdate(@RequestParam("productId") int productId,
                                          @RequestBody HttpServletRequest request) {
        StrategySwitch strategySwitch = bodyToObject(request, StrategySwitch.class);
        Objects.requireNonNull(strategySwitch, "Input StrategySwitch is null");
        strategySwitch.setProductId(productId);
        StrategySwitchMap.put(productId, strategySwitch);
        return ok();
    }

}