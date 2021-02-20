package io.cygnus.indicator.impl.base;

import io.mercury.serialization.json.JsonWrapper;
import lombok.Getter;

public final class Bar {

	// 存储开高低收价格
	@Getter
	private long open = 0L;

	@Getter
	private long highest = Long.MIN_VALUE;

	@Getter
	private long lowest = Long.MAX_VALUE;

	@Getter
	private long last = 0L;

	public Bar onPrice(long price) {
		last = price;
		if (open == 0L)
			open = price;
		if (price < lowest)
			lowest = price;
		if (price > highest)
			highest = price;
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
		builder.append(OpenField);
		builder.append(open);
		builder.append(HighestField);
		builder.append(highest == Long.MIN_VALUE ? 0L : highest);
		builder.append(LowestField);
		builder.append(lowest == Long.MAX_VALUE ? 0L : lowest);
		builder.append(LastField);
		builder.append(last);
		builder.append(End);
		return builder.toString();
	}

	public static void main(String[] args) {

		Bar bar = new Bar().onPrice(100000).onPrice(100L).onPrice(10000000L);

		System.out.println(JsonWrapper.toJson(bar));
		System.out.println(bar);

	}

}
