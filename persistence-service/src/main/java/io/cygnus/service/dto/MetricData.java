package io.cygnus.service.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MetricData {

	private int keyId;

	private int strategyId;

	private String exchangeCode;

	private String lastPoint;

	private String currPoint;

	private long epochTime;

	private int epochTimeDiff;

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
