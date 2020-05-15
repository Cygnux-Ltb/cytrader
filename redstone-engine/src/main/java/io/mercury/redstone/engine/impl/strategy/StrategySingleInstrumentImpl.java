package io.mercury.redstone.engine.impl.strategy;

import org.eclipse.collections.api.list.ImmutableList;

import io.mercury.common.collections.ImmutableLists;
import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.api.MarketData;
import io.mercury.redstone.core.adaptor.Adaptor;
import io.mercury.redstone.core.adaptor.AdaptorEvent;
import io.mercury.redstone.core.order.enums.OrdType;
import io.mercury.redstone.core.order.enums.TrdDirection;

public abstract class StrategySingleInstrumentImpl<M extends MarketData> extends StrategyBaseImpl<M> {

	// 策略订阅的合约
	protected Instrument instrument;

	/**
	 * 
	 */
	protected ImmutableList<Instrument> instruments;

	protected StrategySingleInstrumentImpl(int strategyId, String strategyName, int subAccountId,
			Instrument instrument) {
		super(strategyId, strategyName, subAccountId);
		this.instrument = instrument;
		this.instruments = ImmutableLists.newList(instrument);
	}

	@Override
	public ImmutableList<Instrument> instruments() {
		return instruments;
	}

	public Instrument instrument() {
		return instrument;
	}

	private Adaptor adaptor;

	@Override
	public void addAdaptor(Adaptor adaptor) {
		this.adaptor = adaptor;
	}

	@Override
	protected Adaptor getAdaptor(Instrument instrument) {
		return adaptor;
	}

	/************************* 做市指令 *******************************/
	/**
	 * 
	 * @param direction
	 * @param targetQty
	 */
	protected void orderWatermark(TrdDirection direction, int targetQty) {
		super.orderWatermark(instrument, direction, targetQty);
	}

	/**
	 * 
	 * @param direction
	 * @param targetQty
	 * @param limitPrice
	 */
	protected void orderWatermark(TrdDirection direction, int targetQty, long limitPrice) {
		super.orderWatermark(instrument, direction, targetQty, limitPrice);
	}

	/**
	 * 
	 * @param direction
	 * @param targetQty
	 * @param limitPrice
	 * @param floatTick
	 */
	protected void orderWatermark(TrdDirection direction, int targetQty, long limitPrice, int floatTick) {
		super.orderWatermark(instrument, direction, targetQty, limitPrice, floatTick);
	}

	/**************************** 交易指令 ******************************/
	/**
	 * 开仓
	 * 
	 * @param ordType
	 * @param offerQty
	 */
	protected void openPosition(int offerQty, OrdType ordType, TrdDirection direction) {
		super.openPosition(instrument, offerQty, ordType, direction);
	}

	/**
	 * 开仓
	 * 
	 * @param ordType
	 * @param offerQty
	 * @param offerPrice
	 */
	protected void openPosition(int offerQty, long offerPrice, OrdType ordType, TrdDirection direction) {
		super.openPosition(instrument, offerQty, offerPrice, ordType, direction);
	}

	/**
	 * 全部平仓
	 */
	protected void closeAllPosition() {
		super.closeAllPosition(instrument);
	}

	/**
	 * 平仓指定数量
	 * 
	 * @param closeQty
	 */
	protected void closePosition(int offerQty, long offerPrice) {
		super.closePosition(instrument, offerQty, offerPrice);
	}

	@Override
	public void onAdaptorEvent(AdaptorEvent event) {
		log.info("{} :: On adaptor status callback, adaptorId==[{}], status==[{}]", strategyName(), event.adaptorId(),
				event.adaptorStatus());
		switch (event.adaptorStatus()) {
		case MdEnable:
			log.info("{} :: Handle adaptor MdEnable, adaptorId==[{}]", strategyName(), event.adaptorId());
			adaptor.subscribeMarketData(instrument);
			log.info("{} :: Call subscribeMarketData, instrument -> {}", strategyName(), instrument);
			break;
		case TraderEnable:
			log.info("{} :: Handle adaptor TdEnable, adaptorId==[{}]", strategyName(), event.adaptorId());
			adaptor.queryOrder(null);
			log.info("{} :: Call queryOrder, adaptodId==[{}], account is default", strategyName(), event.adaptorId());
			adaptor.queryPositions(null);
			log.info("{} :: Call queryPositions, adaptodId==[{}], account is default", strategyName(),
					event.adaptorId());
			adaptor.queryBalance(null);
			log.info("{} :: Call queryBalance, adaptodId==[{}], account is default", strategyName(), event.adaptorId());
			break;
		default:
			break;
		}
	}

}
