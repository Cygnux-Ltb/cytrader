package io.ffreedom.redstone.core.trade;

import io.ffreedom.financial.Instrument;
import io.ffreedom.redstone.core.trade.enums.TrdAction;
import io.ffreedom.redstone.core.trade.enums.TrdDirection;

public interface TradeSignal {

	Instrument getInstrument();

	double getPrice();

	double getQty();

	TrdAction getAction();

	TrdDirection getDirection();

	public static TradeSignal openLongSignal(Instrument instrument, double price, double qty) {
		return new OpenLongSignal(instrument, price, qty);
	}

	public static TradeSignal openShortSignal(Instrument instrument, double price, double qty) {
		return new OpenShortSignal(instrument, price, qty);
	}

	public static TradeSignal closeLongSignal(Instrument instrument, double price, double qty) {
		return new CloseLongSignal(instrument, price, qty);
	}

	public static TradeSignal closeShortSignal(Instrument instrument, double price, double qty) {
		return new CloseShortSignal(instrument, price, qty);
	}

	static class OpenLongSignal extends BaseTradeSignal {
		public OpenLongSignal(Instrument instrument, double price, double qty) {
			super(instrument, price, qty);
		}

		@Override
		public TrdAction getAction() {
			return TrdAction.Open;
		}

		@Override
		public TrdDirection getDirection() {
			return TrdDirection.Long;
		}
	}

	static class OpenShortSignal extends BaseTradeSignal {
		public OpenShortSignal(Instrument instrument, double price, double qty) {
			super(instrument, price, qty);
		}

		@Override
		public TrdAction getAction() {
			return TrdAction.Open;
		}

		@Override
		public TrdDirection getDirection() {
			return TrdDirection.Short;
		}
	}

	static class CloseLongSignal extends BaseTradeSignal {
		public CloseLongSignal(Instrument instrument, double price, double qty) {
			super(instrument, price, qty);
		}

		@Override
		public TrdAction getAction() {
			return TrdAction.Close;
		}

		@Override
		public TrdDirection getDirection() {
			return TrdDirection.Long;
		}
	}

	static class CloseShortSignal extends BaseTradeSignal {
		public CloseShortSignal(Instrument instrument, double price, double qty) {
			super(instrument, price, qty);
		}

		@Override
		public TrdAction getAction() {
			return TrdAction.Close;
		}

		@Override
		public TrdDirection getDirection() {
			return TrdDirection.Short;
		}
	}

	abstract class BaseTradeSignal implements TradeSignal {
		private Instrument instrument;
		private double price;
		private double qty;

		public BaseTradeSignal(Instrument instrument, double price, double qty) {
			super();
			this.instrument = instrument;
			this.price = price;
			this.qty = qty;
		}

		@Override
		public double getPrice() {
			return price;
		}

		@Override
		public double getQty() {
			return qty;
		}

		@Override
		public Instrument getInstrument() {
			return instrument;
		}
	}

}
