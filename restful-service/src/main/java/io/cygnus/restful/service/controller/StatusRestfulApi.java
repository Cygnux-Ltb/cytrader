package io.cygnus.restful.service.resources;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.cygnus.restful.service.base.CygRestfulApi;
import io.cygnus.restful.service.transport.OutboxPublisherGroup;
import io.cygnus.service.dto.StrategySwitch;
import io.cygnus.service.dto.pack.OutboxMessage;
import io.cygnus.service.dto.pack.OutboxTitle;
import io.mercury.common.character.Charsets;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.transport.api.Publisher;

@RestController("/status")
public class StatusRestfulApi extends CygRestfulApi {

	private final Logger log = CommonLoggerFactory.getLogger(getClass());

	private static final ConcurrentHashMap<String, StrategySwitch> strategySwitchMap = new ConcurrentHashMap<>();

	/**
	 * 
	 * @return
	 */
	@GetMapping("/show")
	public ResponseEntity<Object> statusShow() {
		Collection<StrategySwitch> strategySwitchs = strategySwitchMap.values();
		return jsonResponse(strategySwitchs);
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
		List<StrategySwitch> strategySwitchs = jsonToList(json, StrategySwitch.class);
		// 将传入的StrategySwitchs按照CygID分组
		Map<Integer, List<StrategySwitch>> strategySwitchListMap = new HashMap<>();
		for (StrategySwitch strategySwitch : strategySwitchs) {
			Integer thadId = strategySwitch.getCygId();
			strategySwitch.setCygId(null);
			if (strategySwitchListMap.containsKey(thadId)) {
				strategySwitchListMap.get(thadId).add(strategySwitch);
			} else {
				List<StrategySwitch> strategySwitchsForThadId = new ArrayList<>();
				strategySwitchsForThadId.add(strategySwitch);
				strategySwitchListMap.put(thadId, strategySwitchsForThadId);
			}
		}
		// 按照ThadID分别发送策略开关
		for (Integer cygId : strategySwitchListMap.keySet()) {
			Publisher<byte[]> publisher = OutboxPublisherGroup.INSTANCE.acquireMember(cygId);
			String msg = outboxMessageToJson(
					new OutboxMessage<>(OutboxTitle.StrategySwitch.name(), strategySwitchListMap.get(cygId)));
			log.info("StrategySwitchs : {}", msg);
			publisher.publish(msg.getBytes(Charsets.UTF8));
		}
		return httpOk();
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
		StrategySwitch strategySwitch = jsonToObj(json, StrategySwitch.class);
		strategySwitch.setCygId(cygId);
		strategySwitchMap.put(strategySwitch.getKey(), strategySwitch);
		return httpOk();
	}

}
