package io.mercury.polaris.indicator.impl.bar;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import io.mercury.polaris.financial.instrument.Instrument;
import io.mercury.polaris.financial.market.impl.BasicMarketData;
import io.mercury.polaris.financial.vector.RandomTimeSerial;
import io.mercury.polaris.indicator.base.BaseRandomTimeIndicator;
import io.mercury.polaris.indicator.events.VolumeBarsEvent;

public final class VolumeBarIndicator extends BaseRandomTimeIndicator<VolumeBar, VolumeBarsEvent> {

	// VolumeBar的计算成交量
	private long limitVolume;

	public VolumeBarIndicator(Instrument instrument, long limitVolume) {
		super(instrument);
		this.limitVolume = limitVolume;
		ZoneId zoneId = instrument.symbol().exchange().zoneId();
		LocalTime startTime = instrument.symbol().tradingPeriodSet().getFirstOptional().get().startTime();
		// 创建指标的第一个节点
		this.currentPoint = VolumeBar.with(0, instrument, ZonedDateTime.of(LocalDate.now(), startTime, zoneId),
				limitVolume);
		pointSet.add(currentPoint);
	}

	@Override
	protected void handleMarketData(BasicMarketData marketData) {
		// 当前节点剩余的写入量
		long remainingVolume = currentPoint.remainingVolume();
		// 此次行情成交量
		long volume = marketData.getVolume();
		// 此次行情最新价
		long lastPrice = marketData.getLastPrice();
		// 如果行情成交量小于剩余数量,则此次行情可以全部写入当前节点
		if (volume <= remainingVolume)
			currentPoint.onMarketData(marketData);
		// 否则则此次行情需要写入多个节点
		else {
			if (remainingVolume > 0) {
				// 当前节点将剩余可计算量全部使用
				currentPoint.handleData(lastPrice, remainingVolume);
				// 更新节点的上一次行情
				currentPoint.updatePreMarketData(marketData);
			}
			// 未处理的成交量等于行情成交量减去当前节点写入的数量
			long unhandledVolume = volume - remainingVolume;
			// 获取行情的时间
			ZonedDateTime marketDataDatetime = marketData.getDateTime();
			// 未处理的成交量大于单个Bar的写入成交量上限
			while (unhandledVolume > limitVolume) {
				// 创建新节点,写入当前行情,当前行情时间,需要写入的价格,Bar最大写入数量
				createNewBarByCurrentPoint(marketData, marketDataDatetime, lastPrice, limitVolume);
				// 剩余计算量减去单个Bar限制量
				unhandledVolume -= limitVolume;
			}
			// 剩余的未计算量小于单个Bar的最大写入量但不为0
			if (unhandledVolume > 0)
				// 创建新节点,写入当前行情,当前行情时间,需要写入的价格,行情未处理的数量
				createNewBarByCurrentPoint(marketData, marketDataDatetime, lastPrice, unhandledVolume);
		}
	}

	private void createNewBarByCurrentPoint(BasicMarketData marketData, ZonedDateTime marketDataDatetime, long price,
			long volume) {
		// 获取当前节点的序列
		RandomTimeSerial currentPointSerial = currentPoint.serial();
		// 获取当前节点时间
		ZonedDateTime currentPointDatetime = currentPointSerial.timePoint();
		// 创建newBar指针
		VolumeBar newBar = null;
		// 如果当前节点与行情时间一致,则使用当前序列创建新节点序列,否则使用行情时间创建新节点序列
		if (marketDataDatetime.equals(currentPointDatetime))
			newBar = VolumeBar.with(currentPoint.index() + 1, instrument, currentPointSerial, limitVolume);
		else
			newBar = VolumeBar.with(currentPoint.index() + 1, instrument, marketDataDatetime, limitVolume);
		// 将新创建的节点加入节点集合
		pointSet.add(newBar);
		// 初始化新节点的OpenPrice
		newBar.initOpenPrice(currentPoint.last());
		// 新节点写入价格,处理的数量
		newBar.handleData(price, volume);
		// 更新节点的上一次行情
		newBar.updatePreMarketData(marketData);
		// 调用当前节点结束事件
		for (VolumeBarsEvent volumeBarsEvent : events) {
			volumeBarsEvent.onEndVolumeBar(currentPoint);
		}
		// 更新当前节点
		currentPoint = newBar;
		// 调用当前节点开始事件
		for (VolumeBarsEvent volumeBarsEvent : events) {
			volumeBarsEvent.onStartVolumeBar(currentPoint);
		}
	}

}
