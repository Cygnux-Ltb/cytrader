package io.cygnus.restful.client;

import java.util.List;

import io.cygnus.persistence.entity.CygOrder;
import io.cygnus.restful.client.base.BaseApiClient;
import io.cygnus.restful.client.base.PathParam;

public class OrderApiClient extends BaseApiClient {

	private String orderUri = "/order";

	private String ordersByInitUri = orderUri + "/init?tradingDay={tradingDay}&strategyId={strategyId}";

	public List<CygOrder> getOrdersByInit(String tradingDay, Integer strategyId) {
		return getResultSet(CygOrder.class, ordersByInitUri, new PathParam("tradingDay", tradingDay),
				new PathParam("strategyId", strategyId.toString()));
	}

	public boolean putOrders(CygOrder order) {
		return putBody(order, orderUri);
	}

}
