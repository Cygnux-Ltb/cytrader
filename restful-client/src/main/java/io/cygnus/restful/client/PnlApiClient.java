package io.cygnus.restful.client;

import java.util.List;

import io.cygnus.repository.entity.PnlDailyEntity;
import io.cygnus.restful.client.base.BaseApiClient;
import io.cygnus.restful.client.base.PathParam;

public class PnlApiClient extends BaseApiClient {

	private String pnlUri = "/pnl";

	private String getPnlDailysUri = pnlUri + "?tradingDay={tradingDay}&strategyId={strategyId}";

	public List<PnlDailyEntity> getPnlDailys(String tradingDay, Integer strategyId) {
		return getResultSet(PnlDailyEntity.class, getPnlDailysUri,
				new PathParam("tradingDay", tradingDay), new PathParam("strategyId", strategyId.toString()));
	}

	public boolean putPnlDailys(PnlDailyEntity pnlDaily) {
		return putBody(pnlDaily, pnlUri);
	}

}
