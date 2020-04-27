package io.redstone.engine.config.entity.pack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

import io.redstone.engine.config.entity.StrategySwitch;

@JSONType(orders = { "Title", "Content" })
public class OutboxMessage<T> {

	@JSONField(name = "Title")
	private String title;

	@JSONField(name = "Content")
	private T content;

	public OutboxMessage() {
	}

	/**
	 * @param title
	 * @param content
	 */
	public OutboxMessage(OutboxTitle title, T content) {
		super();
		this.title = title.name();
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public OutboxMessage<T> setTitle(String title) {
		this.title = title;
		return this;
	}

	public T getContent() {
		return content;
	}

	public OutboxMessage<T> setContent(T content) {
		this.content = content;
		return this;
	}

	public static void main(String[] args) {

		StrategySwitch strategySwitch1 = new StrategySwitch().setThadId(1).setStrategyId(1).setInstrumentId("ni")
				.setTradeable(1);
		StrategySwitch strategySwitch2 = new StrategySwitch().setThadId(1).setStrategyId(1).setInstrumentId("rb")
				.setTradeable(1);
		StrategySwitch strategySwitch3 = new StrategySwitch().setThadId(1).setStrategyId(2).setInstrumentId("TA")
				.setTradeable(1);
		StrategySwitch strategySwitch4 = new StrategySwitch().setThadId(1).setStrategyId(2).setInstrumentId("MA")
				.setTradeable(1);
		StrategySwitch strategySwitch5 = new StrategySwitch().setThadId(1).setStrategyId(2).setInstrumentId("cu")
				.setTradeable(1);
		StrategySwitch strategySwitch6 = new StrategySwitch().setThadId(1).setStrategyId(3).setInstrumentId("p")
				.setTradeable(1);
		
		List<StrategySwitch> list = new ArrayList<>();
		
		Map<String,List<StrategySwitch>> map = new HashMap<>();
		
		map.put("1", list);
		
		map.get("1").add(strategySwitch1);
		map.get("1").add(strategySwitch2);
		map.get("1").add(strategySwitch3);
		map.get("1").add(strategySwitch4);
		map.get("1").add(strategySwitch5);
		map.get("1").add(strategySwitch6);
		
		Object json = JSON.toJSON(map.get("1"));
		
		System.out.println(json);
		

	}

}
