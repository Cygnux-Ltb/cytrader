package io.mercury.polaris.indicator.impl.bar;

class Bar {

	// 存储开高低收价格
	long open = 0L;
	long highest = Long.MIN_VALUE;
	long lowest = Long.MAX_VALUE;
	long last = 0L;

	void onPrice(long price) {
		last = price;
		if (open == 0L)
			open = price;
		if (price < lowest)
			lowest = price;
		if (price > highest)
			highest = price;
	}

}
