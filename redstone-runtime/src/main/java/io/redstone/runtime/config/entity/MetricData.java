package io.redstone.engine.config.entity;

import com.alibaba.fastjson.annotation.JSONField;

public class MetricData {

	@JSONField(name = "keyID")
	private int keyId;

	@JSONField(name = "StrategyID")
	private int strategyId;

	@JSONField(name = "ExchangeCode")
	private String exchangeCode;

	@JSONField(name = "LastPoint")
	private String lastPoint;

	@JSONField(name = "CurrPoint")
	private String currPoint;

	@JSONField(name = "EpochTime")
	private long epochTime;

	@JSONField(name = "EpochTimeDiff")
	private int epochTimeDiff;

	@JSONField(name = "InterfaceType")
	private char interfaceType;

	public int getKeyId() {
		return keyId;
	}

	public int getStrategyId() {
		return strategyId;
	}

	public String getExchangeCode() {
		return exchangeCode;
	}

	public String getLastPoint() {
		return lastPoint;
	}

	public String getCurrPoint() {
		return currPoint;
	}

	public long getEpochTime() {
		return epochTime;
	}

	public int getEpochTimeDiff() {
		return epochTimeDiff;
	}

	public char getInterfaceType() {
		return interfaceType;
	}

	public MetricData setKeyId(int keyId) {
		this.keyId = keyId;
		return this;
	}

	public MetricData setStrategyId(int strategyId) {
		this.strategyId = strategyId;
		return this;
	}

	public MetricData setExchangeCode(String exchangeCode) {
		this.exchangeCode = exchangeCode;
		return this;
	}

	public MetricData setLastPoint(String lastPoint) {
		this.lastPoint = lastPoint;
		return this;
	}

	public MetricData setCurrPoint(String currPoint) {
		this.currPoint = currPoint;
		return this;
	}

	public MetricData setEpochTime(long epochTime) {
		this.epochTime = epochTime;
		return this;
	}

	public MetricData setEpochTimeDiff(int epochTimeDiff) {
		this.epochTimeDiff = epochTimeDiff;
		return this;
	}

	public MetricData setInterfaceType(char interfaceType) {
		this.interfaceType = interfaceType;
		return this;
	}
	
	@Override
	public String toString() {
		return new StringBuffer()
		.append(keyId)
		.append(",").append(interfaceType)
		.append(",").append(exchangeCode)
		.append(",").append(strategyId)
		.append(",").append(lastPoint)
		.append("<->")
		.append(currPoint)
		.append(",").append(lastPoint)
		.append(",").append(currPoint)
		.append(",").append(epochTime)
		.append(",").append(epochTimeDiff)
		.toString();
	}

}
