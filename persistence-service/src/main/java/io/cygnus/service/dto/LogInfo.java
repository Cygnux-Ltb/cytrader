package io.cygnus.service.dto;

import io.mercury.serialization.json.JsonWrapper;
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
