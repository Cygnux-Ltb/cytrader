package io.cygnus.restful.client;

import io.cygnus.restful.client.base.BaseApiClient;
import io.cygnus.restful.client.base.PathParam;
import io.cygnus.service.dto.StrategySwitch;

public class StatusApiClient extends BaseApiClient {

	private String baseUri = "/status";

	private String updateUri = baseUri + "/update?thadId={thadId}";

	public boolean putStatus(Integer thadId, StrategySwitch strategySwitch) {
		return putBody(strategySwitch, updateUri, new PathParam("thadId", thadId.toString()));
	}

}
