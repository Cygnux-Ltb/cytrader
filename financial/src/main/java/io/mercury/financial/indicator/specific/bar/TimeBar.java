package io.mercury.financial.indicator.specific.bar;

import org.eclipse.collections.api.list.primitive.MutableDoubleList;
import org.eclipse.collections.api.list.primitive.MutableLongList;

import io.mercury.common.collections.MutableLists;
import io.mercury.financial.indicator.base.FixedPeriodPoint;
import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.impl.BasicMarketData;
import io.mercury.financial.vector.TimePeriod;
import io.mercury.financial.vector.TimePeriodSerial;

public final class TimeBar extends FixedPeriodPoint {

	// 存储开高低收价格和成交量以及成交金额的字段
	private Bar bar = new Bar();

	// 总成交量
	private long volumeSum = 0L;

	// 总成交金额
	private long turnoverSum = 0L;

	private MutableDoubleList priceRecord = MutableLists.newDoubleArrayList(64);
	private MutableLongList volumeRecord = MutableLists.newLongArrayList(64);

	private TimeBar(int index, Instrument instrument, TimePeriod period, TimePeriodSerial timePeriod) {
		super(index, instrument, period, timePeriod);
	}

	public static TimeBar with(int index, Instrument instrument, TimePeriod period, TimePeriodSerial timePeriod) {
		return new TimeBar(index, instrument, period, timePeriod);
	}

	public TimeBar generateNext() {
		return new TimeBar(index + 1, instrument, period, TimePeriodSerial.with(
				serial.startTime().plusSeconds(period.seconds()), serial.endTime().plusSeconds(period.seconds())));
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

	public MutableDoubleList priceRecord() {
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
	protected void handleMarketData(BasicMarketData marketData) {
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
