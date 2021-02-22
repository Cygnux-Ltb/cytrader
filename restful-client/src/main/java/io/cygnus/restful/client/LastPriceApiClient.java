package io.cygnus.restful.client;

import io.cygnus.restful.client.base.BaseApiClient;
import io.cygnus.service.dto.LastPrice;

public class LastPriceApiClient extends BaseApiClient {

	private String baseUri = "/instrument";

	private String lastPriceUri = baseUri + "/last_price";

	public boolean putLastPrice(LastPrice lastPrice) {
		return putBody(lastPrice, lastPriceUri);
	}

}
