package io.mercury.indicator.impl;

import java.time.ZonedDateTime;

import org.eclipse.collections.api.set.sorted.ImmutableSortedSet;

import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.impl.BasicMarketData;
import io.mercury.financial.time.TimePeriodPool;
import io.mercury.financial.vector.TimePeriod;
import io.mercury.financial.vector.TimePeriodSerial;
import io.mercury.indicator.api.IndicatorEvent;
import io.mercury.indicator.impl.TimeBarIndicator.TimeBarEvent;
import io.mercury.indicator.impl.base.FixedPeriodIndicator;

public final class TimeBarIndicator extends FixedPeriodIndicator<TimeBarPoint, TimeBarEvent, BasicMarketData> {

	public TimeBarIndicator(Instrument instrument, TimePeriod period) {
		super(instrument, period);
		// 从已经根据交易周期分配好的池中获取此指标的分割节点
		ImmutableSortedSet<TimePeriodSerial> timePeriodSet = TimePeriodPool.Singleton.getTimePeriodSet(instrument,
				period);
		int i = -1;
		for (TimePeriodSerial timePeriod : timePeriodSet)
			pointSet.add(TimeBarPoint.newWith(++i, timePeriod));
		currentPoint = pointSet.getFirst();
	}

	public static TimeBarIndicator with(Instrument instrument, TimePeriod period) {
		return new TimeBarIndicator(instrument, period);
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
		TimePeriodSerial currentPointSerial = currentPoint.serial();
		ZonedDateTime marketDatatime = marketData.zonedDatetime();
		if (currentPointSerial.isPeriod(marketDatatime)) {
			currentPoint.onMarketData(marketData);
			for (TimeBarEvent timeBarsEvent : events) {
				timeBarsEvent.onCurrentTimeBarChanged(currentPoint);
			}
		} else {
			for (TimeBarEvent timeBarsEvent : events) {
				timeBarsEvent.onEndTimeBar(currentPoint);
			}
			TimeBarPoint newBar = pointSet.nextOf(currentPoint).orElse(null);
			if (newBar == null) {
				log.error("TimeBar [{}-{}] next is null.", currentPointSerial.startTime(),
						currentPointSerial.endTime());
				return;
			}
			while (!newBar.serial().isPeriod(marketDatatime)) {
				newBar.onMarketData(preMarketData);
				for (TimeBarEvent timeBarsEvent : events) {
					timeBarsEvent.onStartTimeBar(newBar);
				}
				for (TimeBarEvent timeBarsEvent : events) {
					timeBarsEvent.onEndTimeBar(newBar);
				}
				newBar = pointSet.nextOf(currentPoint).orElseGet(null);
				if (newBar == null) {
					log.error("TimeBar [{}-{}] next is null.", currentPointSerial.startTime(),
							currentPointSerial.endTime());
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
		default String eventName() {
			return "TimeBarEvent";
		}

		void onCurrentTimeBarChanged(TimeBarPoint bar);

		void onStartTimeBar(TimeBarPoint bar);

		void onEndTimeBar(TimeBarPoint bar);

	}


}