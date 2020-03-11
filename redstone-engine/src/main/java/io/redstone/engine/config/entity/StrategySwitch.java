package io.redstone.config.entity;

import java.util.Set;
import java.util.TreeSet;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

@JSONType(ignores = "key")
public class StrategySwitch implements Comparable<StrategySwitch> {

	@JSONField(name = "ThadId")
	private Integer thadId;

	@JSONField(name = "StrategyID")
	private Integer strategyId;

	@JSONField(name = "InstrumentID")
	private String instrumentId;

	@JSONField(name = "Tradeable")
	private int tradeable;

	public Integer getThadId() {
		return thadId;
	}

	public StrategySwitch setThadId(Integer thadId) {
		this.thadId = thadId;
		return this;
	}

	public Integer getStrategyId() {
		return strategyId;
	}

	public StrategySwitch setStrategyId(Integer strategyId) {
		this.strategyId = strategyId;
		return this;
	}

	public String getInstrumentId() {
		return instrumentId;
	}

	public StrategySwitch setInstrumentId(String instrumentId) {
		this.instrumentId = instrumentId;
		return this;
	}

	public int getTradeable() {
		return tradeable;
	}

	public StrategySwitch setTradeable(int tradeable) {
		this.tradeable = tradeable;
		return this;
	}

	public String getKey() {
		return thadId.toString() + "-" + strategyId.toString() + "-" + instrumentId;
	}

	@Override
	public int compareTo(StrategySwitch o) {
		if (o.thadId.equals(this.thadId)) {
			if (o.strategyId.equals(this.strategyId)) {
				if (o.instrumentId.equals(this.instrumentId)) {
					return 0;
				}
			}
		}
		return 1;
	}

	public static void main(String[] args) {

		StrategySwitch strategySwitch1 = new StrategySwitch().setThadId(1).setStrategyId(1).setInstrumentId("au1706");
		StrategySwitch strategySwitch2 = new StrategySwitch().setThadId(1).setStrategyId(1).setInstrumentId("au1706");
		StrategySwitch strategySwitch3 = new StrategySwitch().setThadId(1).setStrategyId(1).setInstrumentId("au1706");
		StrategySwitch strategySwitch4 = new StrategySwitch().setThadId(1).setStrategyId(1).setInstrumentId("au1706");

		Set<StrategySwitch> set = new TreeSet<>();

		set.add(strategySwitch1);
		set.add(strategySwitch2);
		set.add(strategySwitch3);
		set.add(strategySwitch4);

		for (StrategySwitch strategySwitch : set) {
			System.out.println(strategySwitch.getThadId() + "-" + strategySwitch.getStrategyId() + "-"
					+ strategySwitch.getInstrumentId());
		}

	}
}
