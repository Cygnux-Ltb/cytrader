package io.ffreedom.redstone.core.trade;

import io.ffreedom.redstone.core.trade.enums.Action;
import io.ffreedom.redstone.core.trade.enums.Direction;

public final class TradeSignalFactory {

	public static OpenLongSignal newOpenLongSignal(double price, double qty) {
		return new OpenLongSignal(price, qty);
	}

	public static class OpenLongSignal extends BaseTradeSignal {

		public OpenLongSignal(double price, double qty) {
			super(price, qty);
		}

		@Override
		public Action getAction() {
			return Action.Open;
		}

		@Override
		public Direction getDirection() {
			return Direction.Long;
		}
	}

	public static OpenShortSignal newOpenShortSignal(double price, double qty) {
		return new OpenShortSignal(price, qty);
	}

	public static class OpenShortSignal extends BaseTradeSignal {

		public OpenShortSignal(double price, double qty) {
			super(price, qty);
		}

		@Override
		public Action getAction() {
			return Action.Open;
		}

		@Override
		public Direction getDirection() {
			return Direction.Short;
		}
	}

	public static CloseLongSignal newCloseLongSignal(double price, double qty) {
		return new CloseLongSignal(price, qty);
	}

	public static class CloseLongSignal extends BaseTradeSignal {

		public CloseLongSignal(double price, double qty) {
			super(price, qty);
		}

		@Override
		public Action getAction() {
			return Action.Close;
		}

		@Override
		public Direction getDirection() {
			return Direction.Long;
		}
	}

	public static CloseShortSignal newCloseShortSignal(double price, double qty) {
		return new CloseShortSignal(price, qty);
	}

	public static class CloseShortSignal extends BaseTradeSignal {

		public CloseShortSignal(double price, double qty) {
			super(price, qty);
		}

		@Override
		public Action getAction() {
			return Action.Close;
		}

		@Override
		public Direction getDirection() {
			return Direction.Short;
		}

	}

	private abstract static class BaseTradeSignal implements TradeSignal {

		private double price;
		private double qty;

		public BaseTradeSignal(double price, double qty) {
			super();
			this.price = price;
			this.qty = qty;
		}

		public double getPrice() {
			return price;
		}

		public double getQty() {
			return qty;
		}
	}

}
