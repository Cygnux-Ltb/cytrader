package io.cygnuxltb.console.controller;

import io.cygnuxltb.console.controller.base.ResponseStatus;
import io.cygnuxltb.console.controller.util.ControllerUtil;
import io.cygnuxltb.protocol.http.inbound.StrategySwitch;
import io.cygnuxltb.protocol.http.pack.OutboxMessage;
import io.cygnuxltb.protocol.http.pack.OutboxTitle;
import io.mercury.common.collections.MutableMaps;
import io.mercury.common.http.MimeType;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.serialization.json.JsonWrapper;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
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

/**
 * 交易系统状态服务接口
 */
@RestController
@RequestMapping(path = "/status", produces = MimeType.APPLICATION_JSON_UTF8)
public final class StatusController {

    private static final Logger log = Log4j2LoggerFactory.getLogger(StatusController.class);

    private final ConcurrentMap<Integer, StrategySwitch> StrategySwitchMap = MutableMaps.newConcurrentHashMap();

    /**
     * 获取全部策略状态
     *
     * @return Collection<StrategySwitch>
     */
    @GetMapping
    public Collection<StrategySwitch> allStrategySwitch() {
        return StrategySwitchMap.values();
    }

    /**
     * 发送状态指令
     *
     * @param request HttpServletRequest
     * @return ResponseEntity<?>
     */
    @PutMapping(path = "/command", consumes = APPLICATION_JSON_UTF8)
    public ResponseStatus statusCommand(@RequestBody HttpServletRequest request) {
        var strategySwitchList = ControllerUtil.bodyToList(request, StrategySwitch.class);
        if (strategySwitchList == null) {
            return ResponseStatus.BAD_REQUEST;
        }
        // 将传入的StrategySwitch按照sysId分组
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
        // 按照sysId分别发送策略开关
        for (Integer sysId : strategySwitchListMap.keySet()) {
            //Publisher<String, String> publisher = CommandDispatcher.GROUP_INSTANCE.getMember(cygId);
            String msg = JsonWrapper
                    .toJson(new OutboxMessage<>()
                            .setTitle(OutboxTitle.StrategySwitch.name())
                            .setContent(strategySwitchListMap.get(sysId)));
            log.info("StrategySwitch : {}", msg);
            //publisher.publish(msg);
        }
        return ResponseStatus.OK;
    }

    /**
     * 更新状态
     *
     * @param productId int
     * @param request   HttpServletRequest
     * @return ResponseEntity<?>
     */
    @PutMapping("/update")
    public ResponseStatus statusUpdate(@RequestParam("productId") int productId,
                                       @RequestBody HttpServletRequest request) {
        StrategySwitch strategySwitch = ControllerUtil.bodyToObject(request, StrategySwitch.class);
        Objects.requireNonNull(strategySwitch, "Input StrategySwitch is null");
        strategySwitch.setProductId(productId);
        StrategySwitchMap.put(productId, strategySwitch);
        return ResponseStatus.OK;
    }

}