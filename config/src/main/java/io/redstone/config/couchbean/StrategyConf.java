package io.redstone.config.couchbean;

import io.mercury.persistence.json.JsonWrapper;

public class StrategyConf {

	private int strategyId;
	private String[] tradeables;

	public int getStrategyId() {
		return strategyId;
	}

	public String[] getTradeables() {
		return tradeables;
	}

	public StrategyConf setStrategyId(int strategyId) {
		this.strategyId = strategyId;
		return this;
	}

	public StrategyConf setTradeables(String[] tradeables) {
		this.tradeables = tradeables;
		return this;
	}

	public static void main(String[] args) {

		StrategyConf conf = new StrategyConf();

		conf.strategyId = 1;
		conf.tradeables = new String[] { "1", "2", "3" };

		System.out.println(JsonWrapper.toJson(conf));

		System.out.println();

	}

}