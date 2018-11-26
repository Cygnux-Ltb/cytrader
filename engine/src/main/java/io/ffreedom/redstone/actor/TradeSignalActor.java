package io.ffreedom.redstone.actor;

import org.eclipse.collections.api.list.MutableList;

import io.ffreedom.common.collect.EclipseCollections;
import io.ffreedom.redstone.core.trade.TradeSignal;

public final class TradeSignalActor {

	public static final TradeSignalActor INSTANCE = new TradeSignalActor();

	private MutableList<TradeSignal> tradeSignalList = EclipseCollections.newFastList(256);

	public static boolean addTradeSignal(TradeSignal tradeSignal) {
		return INSTANCE.addTradeSignal0(tradeSignal);
	}

	private boolean addTradeSignal0(TradeSignal signal) {
		return tradeSignalList.add(signal);
	}

	private void handleTradeSignal(TradeSignal signal) {
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
