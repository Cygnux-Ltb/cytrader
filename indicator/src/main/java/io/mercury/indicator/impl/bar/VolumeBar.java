package io.mercury.polaris.indicator.impl.bar;

import java.time.ZonedDateTime;

import io.mercury.polaris.financial.instrument.Instrument;
import io.mercury.polaris.financial.market.impl.BasicMarketData;
import io.mercury.polaris.financial.vector.RandomTimeSerial;
import io.mercury.polaris.indicator.base.RandomTimePoint;

public final class VolumeBar extends RandomTimePoint<VolumeBar> {

	// 存储开高低收价格的对象
	private Bar bar = new Bar();

	// 此bar限制的成交量
	private long limitVolume;

	// 当前已写入的成交量
	private long currentVolume = 0L;

	private VolumeBar(int index, Instrument instrument, RandomTimeSerial timeStarted, long limitVolume) {
		super(index, instrument, timeStarted);
		this.limitVolume = limitVolume;
	}

	public static VolumeBar with(int index, Instrument instrument, ZonedDateTime datetime, long limitVolume) {
		return new VolumeBar(index, instrument, RandomTimeSerial.with(datetime), limitVolume);
	}

	public static VolumeBar with(int index, Instrument instrument, RandomTimeSerial timeStarted, long limitVolume) {
		return new VolumeBar(index, instrument, RandomTimeSerial.with(timeStarted), limitVolume);
	}

	public long open() {
		return bar.open;
	}

	public long highest() {
		return bar.highest;
	}

	public long lowest() {
		return bar.lowest;
	}

	public long last() {
		return bar.last;
	}

	public void initOpenPrice(long price) {
		if (bar.open == 0L)
			bar.open = price;
	}

	public long limitVolume() {
		return limitVolume;
	}

	public long currentVolume() {
		return currentVolume;
	}

	public long remainingVolume() {
		return limitVolume - currentVolume;
	}

	@Override
	protected void handleMarketData(BasicMarketData marketData) {
		handleData(marketData.getLastPrice(), marketData.getVolume());
	}

	public void handleData(long price, long volume) {
		if (limitVolume - currentVolume > volume)
			currentVolume += volume;
		else
			currentVolume = limitVolume;
		bar.onPrice(price);
	}

}
