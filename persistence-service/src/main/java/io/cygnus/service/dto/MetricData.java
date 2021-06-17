package io.cygnus.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
public class MetricData {

	@Getter
	@Setter
	private int keyId;

	@Getter
	@Setter
	private int strategyId;

	@Getter
	@Setter
	private String exchangeCode;

	@Getter
	@Setter
	private String lastPoint;

	@Getter
	@Setter
	private String currPoint;

	@Getter
	@Setter
	private long epochTime;

	@Getter
	@Setter
	private int epochTimeDiff;

	@Getter
	@Setter
	private char interfaceType;

	
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
