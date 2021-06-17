package io.cygnus.service.dto;

import java.util.Set;
import java.util.TreeSet;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
public class StrategySwitch implements Comparable<StrategySwitch> {

	@Getter
	@Setter
	private Integer cygId;

	@Getter
	@Setter
	private Integer strategyId;

	@Getter
	@Setter
	private String instrumentId;

	@Getter
	@Setter
	private int tradeable;

	public String getKey() {
		return cygId.toString() + "-" + strategyId.toString() + "-" + instrumentId;
	}

	@Override
	public int compareTo(StrategySwitch o) {
		if (o.cygId.equals(this.cygId)) {
			if (o.strategyId.equals(this.strategyId)) {
				if (o.instrumentId.equals(this.instrumentId)) {
					return 0;
				}
			}
		}
		return 1;
	}

	public static void main(String[] args) {

		StrategySwitch strategySwitch1 = new StrategySwitch().setCygId(1).setStrategyId(1).setInstrumentId("au1706");
		StrategySwitch strategySwitch2 = new StrategySwitch().setCygId(1).setStrategyId(1).setInstrumentId("au1706");
		StrategySwitch strategySwitch3 = new StrategySwitch().setCygId(1).setStrategyId(1).setInstrumentId("au1706");
		StrategySwitch strategySwitch4 = new StrategySwitch().setCygId(1).setStrategyId(1).setInstrumentId("au1706");

		Set<StrategySwitch> set = new TreeSet<>();

		set.add(strategySwitch1);
		set.add(strategySwitch2);
		set.add(strategySwitch3);
		set.add(strategySwitch4);

		for (StrategySwitch strategySwitch : set) {
			System.out.println(strategySwitch.getCygId() + "-" + strategySwitch.getStrategyId() + "-"
					+ strategySwitch.getInstrumentId());
		}

	}
}
