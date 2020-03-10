package io.redstone.config.entity;

import com.alibaba.fastjson.annotation.JSONField;

public class LogInfo {

	@JSONField(name = "LogTime")
	private String logTime;

	@JSONField(name = "LogType")
	private String logType;

	@JSONField(name = "EpochTime")
	private long epochTime;

	@JSONField(name = "LogText")
	private String logText;

	public String getLogTime() {
		return logTime;
	}

	public LogInfo setLogTime(String logTime) {
		this.logTime = logTime;
		return this;
	}

	public String getLogType() {
		return logType;
	}

	public LogInfo setLogType(String logType) {
		this.logType = logType;
		return this;
	}

	public long getEpochTime() {
		return epochTime;
	}

	public LogInfo setEpochTime(long epochTime) {
		this.epochTime = epochTime;
		return this;
	}

	public String getLogText() {
		return logText;
	}

	public LogInfo setLogText(String logText) {
		this.logText = logText;
		return this;
	}

	@Override
	public String toString() {
		if (logText.contains("MeasureLatency")) {
			return logTime + "," + logType + "," + epochTime + "," + logText;
		} else {
			return logTime + "," + logType + "," + epochTime + ",\"" + logText + "\"";
		}
	}

}
