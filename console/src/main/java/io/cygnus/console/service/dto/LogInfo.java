package io.cygnus.console.service.dto;

import io.mercury.serialization.json.JsonWrapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class LogInfo {

	private long epochTime;

	private String logTime;

	private String logLevel;

	private String logTitle;

	private String logText;

	@Override
	public String toString() {
		return JsonWrapper.toJson(this);
	}

}
