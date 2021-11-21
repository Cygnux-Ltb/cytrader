package io.cygnus.restful.service.controller;

import static io.cygnus.service.dto.pack.OutboxTitle.StrategySwitch;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.cygnus.restful.service.base.BaseController;
import io.cygnus.restful.service.transport.OutboxPublisherGroup;
import io.cygnus.service.dto.StrategySwitch;
import io.cygnus.service.dto.pack.OutboxMessage;
import io.mercury.common.collections.MutableMaps;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.serialization.json.JsonWrapper;
import io.mercury.transport.api.Publisher;

@RestController("/status")
public class StatusController extends BaseController {

	private static final Logger log = CommonLoggerFactory.getLogger(StatusController.class);

	private static final ConcurrentMap<Integer, StrategySwitch> StrategySwitchMap = MutableMaps.newConcurrentHashMap();

	/**
	 * 
	 * @return
	 */
	@GetMapping
	public ResponseEntity<Collection<StrategySwitch>> allStrategySwitch() {
		return responseOf(StrategySwitchMap.values());
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	@PutMapping("/command")
	public ResponseEntity<Object> statusCommand(@RequestBody HttpServletRequest request) {
		String json = getBody(request);
		log.info("method statusCommand recv : {}", json);
		List<StrategySwitch> strategySwitchs = toList(json, StrategySwitch.class);
		// 将传入的StrategySwitchs按照CygID分组
		Map<Integer, List<StrategySwitch>> strategySwitchListMap = new HashMap<>();
		for (StrategySwitch strategySwitch : strategySwitchs) {
			int cygId = strategySwitch.getCygId();
			if (strategySwitchListMap.containsKey(cygId)) {
				strategySwitchListMap.get(cygId).add(strategySwitch);
			} else {
				List<StrategySwitch> strategySwitchsForThadId = new ArrayList<>();
				strategySwitchsForThadId.add(strategySwitch);
				strategySwitchListMap.put(cygId, strategySwitchsForThadId);
			}
		}
		// 按照CydId分别发送策略开关
		for (Integer cygId : strategySwitchListMap.keySet()) {
			Publisher<String> publisher = OutboxPublisherGroup.GROUP_INSTANCE.getMember(cygId);
			String msg = JsonWrapper
					.toJson(new OutboxMessage<>(StrategySwitch.name(), strategySwitchListMap.get(cygId)));

			log.info("StrategySwitchs : {}", msg);
			publisher.publish(msg);
		}
		return ok();
	}

	/**
	 * 
	 * @param cygId
	 * @param request
	 * @return
	 */
	@PutMapping("/update")
	public ResponseEntity<Object> statusUpdate(@RequestParam("cygId") int cygId,
			@RequestBody HttpServletRequest request) {
		String json = getBody(request);
		log.info("method statusUpdate recv : {}", json);
		StrategySwitch strategySwitch = toObject(json, StrategySwitch.class);
		strategySwitch.setCygId(cygId);
		StrategySwitchMap.put(cygId, strategySwitch);
		return ok();
	}

}