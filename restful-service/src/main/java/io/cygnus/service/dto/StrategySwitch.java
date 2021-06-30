package io.cygnus.service.dto;

import java.util.Set;
import java.util.TreeSet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class StrategySwitch implements Comparable<StrategySwitch> {

	private int cygId;

	private int strategyId;

	private String instrumentCode;

	private boolean tradeable;

	public String getKey() {
		return cygId + "-" + strategyId + "-" + instrumentCode;
	}

	@Override
	public int compareTo(StrategySwitch o) {
		if (o.cygId == this.cygId) {
			if (o.strategyId == this.strategyId) {
				if (o.instrumentCode.equals(this.instrumentCode)) {
					return 0;
				}
			}
		}
		return 1;
	}

	public static void main(String[] args) {

		StrategySwitch strategySwitch1 = new StrategySwitch().setCygId(1).setStrategyId(1).setInstrumentCode("au1706");
		StrategySwitch strategySwitch2 = new StrategySwitch().setCygId(1).setStrategyId(1).setInstrumentCode("au1706");
		StrategySwitch strategySwitch3 = new StrategySwitch().setCygId(1).setStrategyId(1).setInstrumentCode("au1706");
		StrategySwitch strategySwitch4 = new StrategySwitch().setCygId(1).setStrategyId(1).setInstrumentCode("au1706");

		Set<StrategySwitch> set = new TreeSet<>();

		set.add(strategySwitch1);
		set.add(strategySwitch2);
		set.add(strategySwitch3);
		set.add(strategySwitch4);

		for (StrategySwitch strategySwitch : set) {
			System.out.println(strategySwitch.getCygId() + "-" + strategySwitch.getStrategyId() + "-"
					+ strategySwitch.getInstrumentCode());
		}

	}
}
