package io.cygnus.restful.client;

import java.util.List;

import io.cygnus.restful.client.base.BaseApiClient;
import io.cygnus.restful.client.base.PathParam;
import io.cygnus.service.entity.TimeBinner;

public class BinnerApiClient extends BaseApiClient {

	private String baseUri = "/binner";

	private String getTimeBinnersUri = baseUri + "/{thadId}?tradingDay={tradingDay}&instrumentId={instrumentId}";

	public List<TimeBinner> getTimeBinners(Integer thadId, String instrumentId, String tradingDay) {
		return getResultSet(TimeBinner.class, getTimeBinnersUri, 
				new PathParam("thadId", thadId.toString()),
				new PathParam("tradingDay", tradingDay), 
				new PathParam("instrumentId", instrumentId));
	}

}
