package io.cygnus.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
public class LogInfo {

	@Getter
	@Setter
	private String logTime;

	@Getter
	@Setter
	private String logType;

	@Getter
	@Setter
	private long epochTime;

	@Getter
	@Setter
	private String logText;

	@Override
	public String toString() {
		if (logText.contains("MeasureLatency")) {
			return logTime + "," + logType + "," + epochTime + "," + logText;
		} else {
			return logTime + "," + logType + "," + epochTime + ",\"" + logText + "\"";
		}
	}

}
