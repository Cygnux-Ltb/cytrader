package io.cygnus.restful.client;

import java.util.List;

import io.cygnus.restful.client.base.BaseApiClient;
import io.cygnus.restful.client.base.PathParam;
import io.cygnus.service.entity.Order;

public class OrdersApiClient extends BaseApiClient {

	private String baseUri = "/orders";

	private String ordersByInitUri = baseUri + "/init?tradingDay={tradingDay}&strategyId={strategyId}";

	public List<Order> getOrdersByInit(String tradingDay, Integer strategyId) {
		return getResultSet(Order.class, ordersByInitUri, new PathParam("tradingDay", tradingDay),
				new PathParam("strategyId", strategyId.toString()));
	}

	public boolean putOrders(Order order) {
		return putBody(order, baseUri);
	}

}
