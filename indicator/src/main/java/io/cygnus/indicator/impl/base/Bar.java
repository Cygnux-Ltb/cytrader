package io.cygnus.indicator.impl.base;

import io.mercury.serialization.json.JsonWrapper;
import lombok.Getter;

/**
 * 
 * @author yellow013
 */
public final class Bar {

	// 开盘价
	@Getter
	private long open = 0L;

	// 最高价
	@Getter
	private long highest = Long.MIN_VALUE;

	// 最低价
	@Getter
	private long lowest = Long.MAX_VALUE;

	// 最终价
	@Getter
	private long last = 0L;

	public Bar onPrice(long price) {
		if (open == 0L)
			open = price;
		if (price > highest)
			highest = price;
		if (price < lowest)
			lowest = price;
		last = price;
		return this;
	}

	private static final String OpenField = "{\"open\" : ";
	private static final String HighestField = ", \"highest\" : ";
	private static final String LowestField = ", \"lowest\" : ";
	private static final String LastField = ", \"last\" : ";
	private static final String End = "}";

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(76);
		return builder.append(OpenField).append(open).append(HighestField)
				.append(highest == Long.MIN_VALUE ? 0L : highest).append(LowestField)
				.append(lowest == Long.MAX_VALUE ? 0L : lowest).append(LastField).append(last).append(End).toString();
	}

	public static void main(String[] args) {

		Bar bar = new Bar().onPrice(100000).onPrice(100L).onPrice(10000000L);
		System.out.println(JsonWrapper.toJson(bar));
		System.out.println(bar);

	}

}
