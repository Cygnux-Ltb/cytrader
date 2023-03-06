package io.cygnuxltb.example.strategy;

import io.cygnuxltb.engine.strategy.SingleInstrumentStrategy;
import io.horizon.market.data.impl.BasicMarketData;
import io.horizon.market.indicator.impl.SMA.SmaEvent;
import io.horizon.market.indicator.impl.SmaPoint;
import io.horizon.market.instrument.Instrument;
import io.horizon.trader.account.SubAccount;
import io.horizon.trader.order.Order;
import io.mercury.common.param.ParamKey;
import io.mercury.common.param.Params;

/**
 * @author yellow013
 */
public final class ExampleSmaStrategy extends SingleInstrumentStrategy<BasicMarketData, ParamKey> implements SmaEvent {

    public ExampleSmaStrategy(SubAccount subAccount, Params<ParamKey> params, Instrument instrument) {
        super(100, "ExampleSmaStrategy", subAccount, params, instrument);
    }

    @Override
    public String getStrategyName() {
        return "SmaStrategyExample";
    }

    @Override
    protected void handleMarketData(BasicMarketData marketData) {

    }

    @Override
    protected void handleOrder(Order order) {

    }

    @Override
    public void onCurrentPointAvgPriceChanged(SmaPoint point) {

    }

    @Override
    public void onStartSmaPoint(SmaPoint point) {

    }

    @Override
    public void onEndSmaPoint(SmaPoint point) {

    }

    @Override
    public String getEventName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub

    }

}
