package io.cygnus.restful.client;

import java.util.List;

import io.cygnus.repository.entity.Bar;
import io.cygnus.restful.client.base.BaseApiClient;
import io.cygnus.restful.client.base.PathParam;

public class BinnerApiClient extends BaseApiClient {

	private String baseUri = "/binner";

	private String getTimeBinnersUri = baseUri + "/{cygId}?tradingDay={tradingDay}&instrumentId={instrumentId}";

	public List<Bar> getTimeBinners(Integer cygId, String instrumentId, String tradingDay) {
		return getResultSet(Bar.class, getTimeBinnersUri, 
				new PathParam("cygId", cygId.toString()),
				new PathParam("tradingDay", tradingDay), 
				new PathParam("instrumentId", instrumentId));
	}

}
