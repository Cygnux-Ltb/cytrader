package io.cygnus.engine.actor;

import org.eclipse.collections.api.list.MutableList;

import io.horizon.transaction.order.TrdSignal;
import io.mercury.common.collections.MutableLists;

public final class TradeSignalActor {

	private MutableList<TrdSignal> trdSignalList = MutableLists.newFastList(256);

	public static final TradeSignalActor Singleton = new TradeSignalActor();

	public boolean addTradeSignal(TrdSignal signal) {
		return trdSignalList.add(signal);
	}

	/**
	 * 
	 * @param signal
	 */
	public void handleTradeSignal(TrdSignal signal) {
		switch (signal.getAction()) {
		case Open:
			switch (signal.getDirection()) {
			case Long:

				break;
			case Short:

				break;

			default:
				break;
			}
			break;
		case Close:
			switch (signal.getDirection()) {
			case Long:

				break;

			case Short:

				break;

			default:
				break;
			}

			break;
		default:
			break;
		}

	}

}
