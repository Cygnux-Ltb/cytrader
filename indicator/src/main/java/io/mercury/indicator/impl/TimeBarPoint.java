package io.mercury.indicator.impl;

import org.eclipse.collections.api.list.primitive.MutableLongList;

import io.mercury.common.collections.MutableLists;
import io.mercury.financial.market.impl.BasicMarketData;
import io.mercury.financial.vector.TimePeriodSerial;
import io.mercury.indicator.impl.base.Bar;
import io.mercury.indicator.impl.base.FixedPeriodPoint;

public final class TimeBarPoint extends FixedPeriodPoint<BasicMarketData> {

	// 存储开高低收价格和成交量以及成交金额的字段
	private Bar bar = new Bar();

	// 总成交量
	private long volumeSum = 0L;

	// 总成交金额
	private long turnoverSum = 0L;

	private MutableLongList priceRecord = MutableLists.newLongArrayList(64);
	private MutableLongList volumeRecord = MutableLists.newLongArrayList(64);

	private TimeBarPoint(int index, TimePeriodSerial serial) {
		super(index, serial);
	}

	public static TimeBarPoint newWith(int index, TimePeriodSerial timePeriod) {
		return new TimeBarPoint(index, timePeriod);
	}

	public TimeBarPoint generateNext() {
		return new TimeBarPoint(index + 1, TimePeriodSerial.newSerial(serial.startTime().plusSeconds(serial.seconds()),
				serial.endTime().plusSeconds(serial.seconds()), serial.duration()));
	}

	public double open() {
		return bar.open();
	}

	public double highest() {
		return bar.highest();
	}

	public double lowest() {
		return bar.lowest();
	}

	public double last() {
		return bar.last();
	}

	public MutableLongList priceRecord() {
		return priceRecord;
	}

	public long volumeSum() {
		return volumeSum;
	}

	public MutableLongList volumeRecord() {
		return volumeRecord;
	}

	public double turnoverSum() {
		return turnoverSum;
	}

	@Override
	protected void handleMarketData0(BasicMarketData marketData) {
		// 处理当前价格
		bar.onPrice(marketData.getLastPrice());
		// 记录当前价格
		priceRecord.add(marketData.getLastPrice());
		// 总成交量增加处理当前行情
		volumeSum += marketData.getVolume();
		// 记录当前成交量
		volumeRecord.add(marketData.getVolume());
		// 处理当前成交额
		turnoverSum = turnoverSum + marketData.getTurnover();
	}

}
