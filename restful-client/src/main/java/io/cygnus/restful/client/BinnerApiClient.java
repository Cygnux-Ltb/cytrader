package io.cygnus.restful.client;

import java.util.List;

import org.springframework.stereotype.Component;

import io.cygnus.repository.entity.BarEntity;
import io.cygnus.restful.client.base.BaseApiClient;
import io.cygnus.restful.client.base.PathParam;

@Component
public class BinnerApiClient extends BaseApiClient {

	private String baseUri = "/bar";

	private String getTimeBinnersUri = baseUri + "/{cygId}?tradingDay={tradingDay}&instrumentId={instrumentId}";

	public List<BarEntity> getTimeBinners(Integer cygId, String instrumentId, String tradingDay) {
		return getResultSet(BarEntity.class, getTimeBinnersUri, new PathParam("cygId", cygId.toString()),
				new PathParam("tradingDay", tradingDay), new PathParam("instrumentId", instrumentId));
	}

}
