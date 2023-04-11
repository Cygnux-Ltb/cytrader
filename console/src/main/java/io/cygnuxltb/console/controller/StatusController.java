package io.cygnuxltb.console.controller;

import io.cygnuxltb.console.controller.base.ServiceException;
import io.cygnuxltb.console.controller.util.RequestUtil;
import io.cygnuxltb.console.controller.util.ResponseUtil;
import io.cygnuxltb.protocol.http.dto.inbound.StrategySwitch;
import io.cygnuxltb.protocol.http.dto.pack.OutboxMessage;
import io.cygnuxltb.protocol.http.dto.pack.OutboxTitle;
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
        var strategySwitchList = RequestUtil.bodyToList(request, StrategySwitch.class);
        if (strategySwitchList == null) {
            return ResponseUtil.badRequest();
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
        for (Integer sysId : strategySwitchListMap.keySet()) {
            //Publisher<String, String> publisher = CommandDispatcher.GROUP_INSTANCE.getMember(cygId);
            String msg = JsonWrapper
                    .toJson(new OutboxMessage<>()
                            .setTitle(OutboxTitle.StrategySwitch.name())
                            .setContent(strategySwitchListMap.get(sysId)));
            log.info("StrategySwitch : {}", msg);
            //publisher.publish(msg);
        }
        return ResponseUtil.ok();
    }

    /**
     * @param productId int
     * @param request   HttpServletRequest
     * @return ResponseEntity<?>
     */
    @PutMapping("/update")
    public ResponseEntity<?> statusUpdate(@RequestParam("productId") int productId,
                                          @RequestBody HttpServletRequest request) {
        StrategySwitch strategySwitch = RequestUtil.bodyToObject(request, StrategySwitch.class);
        Objects.requireNonNull(strategySwitch, "Input StrategySwitch is null");
        strategySwitch.setProductId(productId);
        StrategySwitchMap.put(productId, strategySwitch);
        return ResponseUtil.ok();
    }

}