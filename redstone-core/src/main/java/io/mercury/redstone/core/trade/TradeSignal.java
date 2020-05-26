package io.mercury.redstone.core.trade;

import io.mercury.common.fsm.Signal;
import io.mercury.financial.instrument.Instrument;
import io.mercury.redstone.core.order.enums.TrdAction;
import io.mercury.redstone.core.order.enums.TrdDirection;

public interface TradeSignal extends Signal {

	Instrument instrument();

	int strategyId();

	TrdAction trdAction();

	TrdDirection trdDirection();

	public static TradeSignal openLongSignal(Instrument instrument, int strategyId) {
		return new OpenLongSignal(instrument, strategyId);
	}

	public static TradeSignal openShortSignal(Instrument instrument, int strategyId) {
		return new OpenShortSignal(instrument, strategyId);
	}

	public static TradeSignal closeLongSignal(Instrument instrument, int strategyId) {
		return new CloseLongSignal(instrument, strategyId);
	}

	public static TradeSignal closeShortSignal(Instrument instrument, int strategyId) {
		return new CloseShortSignal(instrument, strategyId);
	}

	public static class OpenLongSignal extends BaseTradeSignal {

		private OpenLongSignal(Instrument instrument, int strategyId) {
			super(instrument, strategyId);
		}

		@Override
		public TrdAction trdAction() {
			return TrdAction.Open;
		}

		@Override
		public TrdDirection trdDirection() {
			return TrdDirection.Long;
		}
	}

	public static class OpenShortSignal extends BaseTradeSignal {

		private OpenShortSignal(Instrument instrument, int strategyId) {
			super(instrument, strategyId);
		}

		@Override
		public TrdAction trdAction() {
			return TrdAction.Open;
		}

		@Override
		public TrdDirection trdDirection() {
			return TrdDirection.Short;
		}
	}

	public static class CloseLongSignal extends BaseTradeSignal {

		private CloseLongSignal(Instrument instrument, int strategyId) {
			super(instrument, strategyId);
		}

		@Override
		public TrdAction trdAction() {
			return TrdAction.Close;
		}

		@Override
		public TrdDirection trdDirection() {
			return TrdDirection.Long;
		}
	}

	public static class CloseShortSignal extends BaseTradeSignal {

		private CloseShortSignal(Instrument instrument, int strategyId) {
			super(instrument, strategyId);
		}

		@Override
		public TrdAction trdAction() {
			return TrdAction.Close;
		}

		@Override
		public TrdDirection trdDirection() {
			return TrdDirection.Short;
		}
	}

	public abstract class BaseTradeSignal implements TradeSignal {

		private Instrument instrument;
		private int strategyId;

		private BaseTradeSignal(Instrument instrument, int strategyId) {
			super();
			this.instrument = instrument;
			this.strategyId = strategyId;
		}

		@Override
		public int strategyId() {
			return strategyId;
		}

		@Override
		public Instrument instrument() {
			return instrument;
		}
	}

}
