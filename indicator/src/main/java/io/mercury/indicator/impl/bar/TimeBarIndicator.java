package io.mercury.indicator.impl.bar;

import java.time.ZonedDateTime;

import org.eclipse.collections.api.set.sorted.ImmutableSortedSet;

import io.mercury.indicator.base.BaseTimePeriodIndicator;
import io.mercury.indicator.event.TimeBarsEvent;
import io.mercury.polaris.financial.instrument.Instrument;
import io.mercury.polaris.financial.market.impl.BasicMarketData;
import io.mercury.polaris.financial.time.TimePeriodPool;
import io.mercury.polaris.financial.vector.TimePeriod;
import io.mercury.polaris.financial.vector.TimePeriodSerial;

public final class TimeBarIndicator extends BaseTimePeriodIndicator<TimeBar, TimeBarsEvent> {

	public TimeBarIndicator(Instrument instrument, TimePeriod period) {
		super(instrument, period);
		// 从已经根据交易周期分配好的池中获取此指标的分割节点
		ImmutableSortedSet<TimePeriodSerial> timePeriodSet = TimePeriodPool.Singleton.getTimePeriodSet(instrument,
				period);
		int i = -1;
		for (TimePeriodSerial timePeriod : timePeriodSet)
			pointSet.add(TimeBar.with(++i, instrument, period, timePeriod));
		currentPoint = pointSet.getFirst();
	}

	public static TimeBarIndicator with(Instrument instrument, TimePeriod period) {
		return new TimeBarIndicator(instrument, period);
	}

	// @Override
	protected TimeBar generateNextPoint(TimeBar currentPoint) {
		// 根据当前周期的开始时间和结束时间以及时间周期创建新的点
		TimeBar newPoint = currentPoint.generateNext();
		pointSet.add(newPoint);
		return newPoint;
	}

	@Override
	protected void handleMarketData(BasicMarketData marketData) {
		TimePeriodSerial currentPointSerial = currentPoint.serial();
		ZonedDateTime marketDataTime = marketData.getDateTime();
		if (currentPointSerial.isPeriod(marketDataTime)) {
			currentPoint.onMarketData(marketData);
			for (TimeBarsEvent timeBarsEvent : events)
				timeBarsEvent.onCurrentTimeBarChanged(currentPoint);
		} else {
			for (TimeBarsEvent timeBarsEvent : events)
				timeBarsEvent.onEndTimeBar(currentPoint);
			TimeBar newBar = pointSet.nextOf(currentPoint).orElse(null);
			if (newBar == null) {
				logger.error("TimeBar [{}-{}] next is null.", currentPointSerial.startTime(),
						currentPointSerial.endTime());
				return;
			}
			while (!newBar.serial().isPeriod(marketDataTime)) {
				newBar.onMarketData(preMarketData);
				for (TimeBarsEvent timeBarsEvent : events)
					timeBarsEvent.onStartTimeBar(newBar);
				for (TimeBarsEvent timeBarsEvent : events)
					timeBarsEvent.onEndTimeBar(newBar);
				newBar = pointSet.nextOf(currentPoint).orElseGet(null);
				if (newBar == null) {
					logger.error("TimeBar [{}-{}] next is null.", currentPointSerial.startTime(),
							currentPointSerial.endTime());
					break;
				}
			}
			for (TimeBarsEvent timeBarsEvent : events)
				timeBarsEvent.onStartTimeBar(newBar);
		}

	}

}
