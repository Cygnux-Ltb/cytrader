package io.cygnuxltb.protocol.http.inbound;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
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
		return keyId + "," + interfaceType + "," + exchangeCode +
				"," + strategyId + "," + lastPoint + "<->" + currPoint +
				"," + lastPoint + "," + currPoint + "," + epochTime + "," +
				epochTimeDiff;
	}

}
