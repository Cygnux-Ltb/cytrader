package io.cygnux.client;

import io.cygnux.client.base.BaseClient;
import io.cygnuxltb.protocol.http.dto.outbound.PnlDTO;
import io.mercury.transport.http.param.PathParam;

import java.util.List;

public class PnlClient extends BaseClient {

    private final String pnlUri = "/pnl";

    public List<PnlDTO> getPnlDaily(String tradingDay, Integer strategyId) {
        String getPnlDailyUri = pnlUri + "?tradingDay={tradingDay}&strategyId={strategyId}";
        return getResultSet(PnlDTO.class, getPnlDailyUri,
                new PathParam("tradingDay", tradingDay), new PathParam("strategyId", strategyId.toString()));
    }

    public boolean putPnlDaily(PnlDTO pnl) {
        return putBody(pnl, pnlUri);
    }

}
