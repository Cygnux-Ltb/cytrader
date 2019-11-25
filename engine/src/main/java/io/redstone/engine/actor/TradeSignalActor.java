package io.redstone.actor;

import org.eclipse.collections.api.list.MutableList;

import io.ffreedom.common.collections.MutableLists;
import io.redstone.core.trade.TradeSignal;

public final class TradeSignalActor {

	private MutableList<TradeSignal> tradeSignalList = MutableLists.newFastList(256);

	public static final TradeSignalActor Singleton = new TradeSignalActor();

	public boolean addTradeSignal(TradeSignal signal) {
		return tradeSignalList.add(signal);
	}

	public void handleTradeSignal(TradeSignal signal) {
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
