package io.cygnuxltb.engine.status;

import org.eclipse.collections.api.list.MutableList;

import io.horizon.trader.order.TradeSignal;
import io.mercury.common.collections.MutableLists;

public final class TradeSignalActor {

    private final MutableList<TradeSignal> trdSignalList = MutableLists.newFastList(256);

    public static final TradeSignalActor Singleton = new TradeSignalActor();

    public boolean addTradeSignal(TradeSignal signal) {
        return trdSignalList.add(signal);
    }

    /**
     * @param signal TradeSignal
     */
    public void handleTradeSignal(TradeSignal signal) {
        switch (signal.getAction()) {
            case Open -> {
                switch (signal.getDirection()) {
                    case Long:

                        break;
                    case Short:

                        break;

                    default:
                        break;
                }
            }
            case Close -> {
                switch (signal.getDirection()) {
                    case Long:

                        break;

                    case Short:

                        break;

                    default:
                        break;
                }
            }
            default -> {
            }
        }

    }

}
