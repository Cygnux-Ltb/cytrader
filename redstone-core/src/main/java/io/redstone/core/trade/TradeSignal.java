package io.redstone.core.trade;

import io.mercury.polaris.financial.instrument.Instrument;
import io.redstone.core.trade.enums.TrdAction;
import io.redstone.core.trade.enums.TrdDirection;

public interface TradeSignal {

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

	static class OpenLongSignal extends BaseTradeSignal {

		public OpenLongSignal(Instrument instrument, int strategyId) {
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

	static class OpenShortSignal extends BaseTradeSignal {
		public OpenShortSignal(Instrument instrument, int strategyId) {
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

	static class CloseLongSignal extends BaseTradeSignal {
		public CloseLongSignal(Instrument instrument, int strategyId) {
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

	static class CloseShortSignal extends BaseTradeSignal {
		public CloseShortSignal(Instrument instrument, int strategyId) {
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

	abstract class BaseTradeSignal implements TradeSignal {

		private Instrument instrument;
		private int strategyId;

		public BaseTradeSignal(Instrument instrument, int strategyId) {
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
