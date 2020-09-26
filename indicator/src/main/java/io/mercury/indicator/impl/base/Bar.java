package io.mercury.indicator.impl.base;

import io.mercury.serialization.json.JsonWrapper;

public final class Bar {

	// 存储开高低收价格
	private long open = 0L;
	private long highest = Long.MIN_VALUE;
	private long lowest = Long.MAX_VALUE;
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

	public long open() {
		return open;
	}

	public long highest() {
		return highest;
	}

	public long lowest() {
		return lowest;
	}

	public long last() {
		return last;
	}

	private static final String str0 = "{\"open\" : ";
	private static final String str1 = ", \"highest\" : ";
	private static final String str2 = ", \"lowest\" : ";
	private static final String str3 = ", \"last\" : ";
	private static final String str4 = "}";

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(76);
		builder.append(str0);
		builder.append(open);
		builder.append(str1);
		builder.append(highest == Long.MIN_VALUE ? 0L : highest);
		builder.append(str2);
		builder.append(lowest == Long.MAX_VALUE ? 0L : lowest);
		builder.append(str3);
		builder.append(last);
		builder.append(str4);
		return builder.toString();
	}

	public static void main(String[] args) {
		
		Bar bar = new Bar().onPrice(100000).onPrice(100L).onPrice(10000000L);
		
		System.out.println(JsonWrapper.toJson(bar));
		System.out.println(bar);
		
	}

}
