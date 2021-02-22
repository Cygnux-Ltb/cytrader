package io.cygnus.restful.client;

import java.util.List;

import io.cygnus.restful.client.base.BaseApiClient;
import io.cygnus.restful.client.base.PathParam;
import io.cygnus.service.entity.StrategyInstrumentPNLDaily;

public class PnlApiClient extends BaseApiClient {

	private String baseUri = "/pnl";

	private String getPnlDailysUri = baseUri + "?tradingDay={tradingDay}&strategyId={strategyId}";

	public List<StrategyInstrumentPNLDaily> getPnlDailys(String tradingDay, Integer strategyId) {
		return getResultSet(StrategyInstrumentPNLDaily.class, getPnlDailysUri,
				new PathParam("tradingDay", tradingDay), new PathParam("strategyId", strategyId.toString()));
	}

	public boolean putPnlDailys(StrategyInstrumentPNLDaily pnlDaily) {
		return putBody(pnlDaily, baseUri);
	}

}
