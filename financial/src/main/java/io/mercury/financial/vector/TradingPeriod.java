package io.mercury.financial.vector;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.annotation.Nonnull;

import org.eclipse.collections.api.list.MutableList;

import io.mercury.common.collections.MutableLists;
import io.mercury.common.datetime.DateTimeUtil;
import io.mercury.common.datetime.TimeConst;
import io.mercury.common.datetime.TimeZone;
import io.mercury.common.sequence.Serial;
import io.mercury.financial.vector.TimePeriodSerial;

/**
 * 指示某交易标的一段交易时间
 * 
 * @author yellow013
 */
public final class TradingPeriod implements Serial<TradingPeriod> {

	private int serialNumber;
	private LocalTime startTime;
	private int startSecondOfDay;
	private LocalTime endTime;
	private int endSecondOfDay;
	private boolean isCrossDay;
	private Duration totalDuration;

	public static TradingPeriod with(int serialNumber, LocalTime startTime, LocalTime endTime) {
		return new TradingPeriod(serialNumber, startTime, endTime);
	}

	private TradingPeriod(int serialNumber, LocalTime startTime, LocalTime endTime) {
		this.serialNumber = serialNumber;
		this.startTime = startTime;
		this.startSecondOfDay = startTime.toSecondOfDay();
		this.endTime = endTime;
		this.endSecondOfDay = endTime.toSecondOfDay();
		if (startSecondOfDay > endSecondOfDay) {
			isCrossDay = true;
			totalDuration = Duration.ofSeconds(endSecondOfDay - startSecondOfDay + TimeConst.SECONDS_PER_DAY);
		} else {
			isCrossDay = false;
			totalDuration = Duration.ofSeconds(endSecondOfDay - startSecondOfDay);
		}
	}

	public LocalTime startTime() {
		return startTime;
	}

	public LocalTime endTime() {
		return endTime;
	}

	public int startSecondOfDay() {
		return startSecondOfDay;
	}

	public int endSecondOfDay() {
		return endSecondOfDay;
	}

	@Override
	public long serialNumber() {
		return serialNumber;
	}

	public boolean isPeriod(LocalTime time) {
		int secondOfDay = time.toSecondOfDay();
		if (!isCrossDay)
			return (startSecondOfDay <= secondOfDay && endSecondOfDay >= secondOfDay) ? true : false;
		else
			return (startSecondOfDay <= secondOfDay || endSecondOfDay >= secondOfDay) ? true : false;
	}

	public MutableList<TimePeriodSerial> segmentation(@Nonnull ZoneId zoneId, @Nonnull Duration duration) {
		// 获取分割参数的秒数
		int seconds = (int) duration.getSeconds();
		// 判断分割段是否大于半天
		if (seconds > TimeConst.SECONDS_PER_HALF_DAY) {
			// 如果交易周期跨天,则此分割周期等于当天开始时间至次日结束时间
			// 如果交易周期未跨天,则此分割周期等于当天开始时间至当天结束时间
			return MutableLists.newFastList(isCrossDay
					? TimePeriodSerial.with(ZonedDateTime.of(DateTimeUtil.CurrentDate(), startTime, zoneId),
							ZonedDateTime.of(DateTimeUtil.NextDate(), endTime, zoneId))
					: TimePeriodSerial.with(ZonedDateTime.of(DateTimeUtil.CurrentDate(), startTime, zoneId),
							ZonedDateTime.of(DateTimeUtil.CurrentDate(), endTime, zoneId)));
		} else {
			// 获取此交易时间段的总时长
			int totalSeconds = (int) totalDuration.getSeconds();
			// 计算按照分割参数总的段数
			int count = totalSeconds / seconds;
			if (totalSeconds % seconds > 0)
				count++;
			MutableList<TimePeriodSerial> list = MutableLists.newFastList(count);
			// 计算开始时间点
			ZonedDateTime startPoint = ZonedDateTime.of(DateTimeUtil.CurrentDate(), startTime, zoneId);
			// 计算结束时间点,如果跨天则日期加一天
			ZonedDateTime lastEndPoint = ZonedDateTime
					.of(isCrossDay ? DateTimeUtil.NextDate() : DateTimeUtil.CurrentDate(), endTime, zoneId);
			for (int i = 0; i < count; i++) {
				ZonedDateTime nextStartPoint = startPoint.plusSeconds(seconds);
				if (nextStartPoint.isBefore(lastEndPoint)) {
					ZonedDateTime endPoint = nextStartPoint.minusNanos(1);
					list.add(TimePeriodSerial.with(startPoint, endPoint));
				} else {
					list.add(TimePeriodSerial.with(startPoint, lastEndPoint));
					break;
				}
				startPoint = nextStartPoint;
			}
			return list;
		}
	}

	public static void main(String[] args) {

		TradingPeriod tradingPeriod = TradingPeriod.with(0, LocalTime.of(21, 00, 00), LocalTime.of(2, 30, 00));

		System.out.println(tradingPeriod.isPeriod(LocalTime.of(14, 00, 00)));

		tradingPeriod.segmentation(TimeZone.CST, Duration.ofMinutes(45))
				.each(timePeriod -> System.out.println(timePeriod.startTime() + " - " + timePeriod.endTime()));

		LocalDateTime of = LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 55, 30));

		System.out.println(of);
		System.out.println(of.plusMinutes(30));

	}

}
