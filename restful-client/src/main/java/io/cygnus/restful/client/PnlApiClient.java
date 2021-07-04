package io.cygnus.restful.client;

import java.util.List;

import io.cygnus.persistence.entity.CygPnlDaily;
import io.cygnus.restful.client.base.BaseApiClient;
import io.cygnus.restful.client.base.PathParam;

public class PnlApiClient extends BaseApiClient {

	private String pnlUri = "/pnl";

	private String getPnlDailysUri = pnlUri + "?tradingDay={tradingDay}&strategyId={strategyId}";

	public List<CygPnlDaily> getPnlDailys(String tradingDay, Integer strategyId) {
		return getResultSet(CygPnlDaily.class, getPnlDailysUri,
				new PathParam("tradingDay", tradingDay), new PathParam("strategyId", strategyId.toString()));
	}

	public boolean putPnlDailys(CygPnlDaily pnlDaily) {
		return putBody(pnlDaily, baseUri);
	}

}
