package io.redstone.runtime.config.entity;

import com.alibaba.fastjson.annotation.JSONField;

public class Init {

	@JSONField(name = "CygID")
	private Integer cygId;

	@JSONField(name = "TradingDay")
	private String tradingDay;

	public Integer getCygId() {
		return cygId;
	}

	public Init setCygId(Integer cygId) {
		this.cygId = cygId;
		return this;
	}

	public String getTradingDay() {
		return tradingDay;
	}

	public Init setTradingDay(String tradingDay) {
		this.tradingDay = tradingDay;
		return this;
	}

}
