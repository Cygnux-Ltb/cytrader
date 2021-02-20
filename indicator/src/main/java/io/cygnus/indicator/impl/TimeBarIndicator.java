package io.cygnus.indicator.impl;

import java.time.Duration;
import java.time.ZonedDateTime;

import org.eclipse.collections.api.list.primitive.MutableLongList;
import org.eclipse.collections.api.set.sorted.ImmutableSortedSet;
import org.slf4j.Logger;

import io.cygnus.indicator.IndicatorEvent;
import io.cygnus.indicator.impl.TimeBarIndicator.TimeBarEvent;
import io.cygnus.indicator.impl.TimeBarIndicator.TimeBarPoint;
import io.cygnus.indicator.impl.base.Bar;
import io.cygnus.indicator.impl.base.FixedPeriodIndicator;
import io.cygnus.indicator.impl.base.FixedPeriodPoint;
import io.horizon.structure.market.data.impl.BasicMarketData;
import io.horizon.structure.market.instrument.Instrument;
import io.horizon.structure.pool.TimePeriodPool;
import io.horizon.structure.serial.TimePeriodSerial;
import io.mercury.common.collections.MutableLists;
import io.mercury.common.log.CommonLoggerFactory;

/**
 * K-Line 指标
 * 
 * @author yellow013
 *
 */
public final class TimeBarIndicator extends FixedPeriodIndicator<TimeBarPoint, TimeBarEvent, BasicMarketData> {

	private static final Logger log = CommonLoggerFactory.getLogger(TimeBarIndicator.class);

	public TimeBarIndicator(Instrument instrument, Duration duration) {
		super(instrument, duration);
		// 从已经根据交易周期分配好的池中获取此指标的分割节点
		ImmutableSortedSet<TimePeriodSerial> timePeriodSet = TimePeriodPool.Singleton.getTimePeriodSet(instrument,
				duration);
		int i = -1;
		for (TimePeriodSerial timePeriod : timePeriodSet)
			pointSet.add(new TimeBarPoint(++i, timePeriod));
		currentPoint = pointSet.getFirst();
	}

	public static TimeBarIndicator with(Instrument instrument, Duration duration) {
		return new TimeBarIndicator(instrument, duration);
	}

	// @Override
	protected TimeBarPoint generateNextPoint(TimeBarPoint currentPoint) {
		// 根据当前周期的开始时间和结束时间以及时间周期创建新的点
		TimeBarPoint newPoint = currentPoint.generateNext();
		pointSet.add(newPoint);
		return newPoint;
	}

	@Override
	protected void handleMarketData(BasicMarketData marketData) {
		TimePeriodSerial currentPointSerial = currentPoint.getSerial();
		ZonedDateTime marketDatatime = marketData.getZonedDatetime();
		if (currentPointSerial.isPeriod(marketDatatime)) {
			currentPoint.handleMarketData(marketData);
			for (TimeBarEvent timeBarsEvent : events) {
				timeBarsEvent.onCurrentTimeBarChanged(currentPoint);
			}
		} else {
			for (TimeBarEvent timeBarsEvent : events) {
				timeBarsEvent.onEndTimeBar(currentPoint);
			}
			TimeBarPoint newBar = pointSet.nextOf(currentPoint).orElse(null);
			if (newBar == null) {
				log.error("TimeBar [{}-{}] next is null.", currentPointSerial.getStartTime(),
						currentPointSerial.getEndTime());
				return;
			}
			while (!newBar.getSerial().isPeriod(marketDatatime)) {
				newBar.handleMarketData(preMarketData);
				for (TimeBarEvent timeBarsEvent : events) {
					timeBarsEvent.onStartTimeBar(newBar);
				}
				for (TimeBarEvent timeBarsEvent : events) {
					timeBarsEvent.onEndTimeBar(newBar);
				}
				newBar = pointSet.nextOf(currentPoint).orElseGet(null);
				if (newBar == null) {
					log.error("TimeBar [{}-{}] next is null.", currentPointSerial.getStartTime(),
							currentPointSerial.getEndTime());
					break;
				}
			}
			for (TimeBarEvent timeBarsEvent : events) {
				timeBarsEvent.onStartTimeBar(newBar);
			}
		}

	}

	public interface TimeBarEvent extends IndicatorEvent {

		@Override
		default String getEventName() {
			return "TimeBarEvent";
		}

		void onCurrentTimeBarChanged(TimeBarPoint point);

		void onStartTimeBar(TimeBarPoint point);

		void onEndTimeBar(TimeBarPoint point);

	}

	/**
	 * 
	 * @author yellow013
	 *
	 */
	public static final class TimeBarPoint extends FixedPeriodPoint<BasicMarketData> {

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

		private TimeBarPoint generateNext() {
			return new TimeBarPoint(index + 1,
					TimePeriodSerial.newSerial(serial.getStartTime().plusSeconds(serial.getDuration().getSeconds()),
							serial.getEndTime().plusSeconds(serial.getDuration().getSeconds()), serial.getDuration()));
		}

		public double getOpen() {
			return bar.getOpen();
		}

		public double getHighest() {
			return bar.getHighest();
		}

		public double getLowest() {
			return bar.getLowest();
		}

		public double getLast() {
			return bar.getLast();
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

}
