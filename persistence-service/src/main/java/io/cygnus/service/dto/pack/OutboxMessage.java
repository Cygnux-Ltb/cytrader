package io.cygnus.service.dto.pack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.cygnus.service.dto.StrategySwitch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class OutboxMessage<T> {

	private String title;

	private T content;

//	/**
//	 * @param title
//	 * @param content
//	 */
//	public OutboxMessage(OutboxTitle title, T content) {
//		this.title = title.name();
//		this.content = content;
//	}
//
//	public String getTitle() {
//		return title;
//	}
//
//	public OutboxMessage<T> setTitle(String title) {
//		this.title = title;
//		return this;
//	}
//
//	public T getContent() {
//		return content;
//	}
//
//	public OutboxMessage<T> setContent(T content) {
//		this.content = content;
//		return this;
//	}

	public static void main(String[] args) {

		StrategySwitch strategySwitch1 = new StrategySwitch().setCygId(1).setStrategyId(1).setInstrumentId("ni")
				.setTradeable(1);
		StrategySwitch strategySwitch2 = new StrategySwitch().setCygId(1).setStrategyId(1).setInstrumentId("rb")
				.setTradeable(1);
		StrategySwitch strategySwitch3 = new StrategySwitch().setCygId(1).setStrategyId(2).setInstrumentId("TA")
				.setTradeable(1);
		StrategySwitch strategySwitch4 = new StrategySwitch().setCygId(1).setStrategyId(2).setInstrumentId("MA")
				.setTradeable(1);
		StrategySwitch strategySwitch5 = new StrategySwitch().setCygId(1).setStrategyId(2).setInstrumentId("cu")
				.setTradeable(1);
		StrategySwitch strategySwitch6 = new StrategySwitch().setCygId(1).setStrategyId(3).setInstrumentId("p")
				.setTradeable(1);

		List<StrategySwitch> list = new ArrayList<>();

		Map<String, List<StrategySwitch>> map = new HashMap<>();

		map.put("1", list);

		map.get("1").add(strategySwitch1);
		map.get("1").add(strategySwitch2);
		map.get("1").add(strategySwitch3);
		map.get("1").add(strategySwitch4);
		map.get("1").add(strategySwitch5);
		map.get("1").add(strategySwitch6);

		System.out.println(map);

	}

}
