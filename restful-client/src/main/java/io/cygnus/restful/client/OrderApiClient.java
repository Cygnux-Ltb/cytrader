package io.cygnus.restful.client;

import java.util.List;

import io.cygnus.repository.entity.OrderEntity;
import io.cygnus.restful.client.base.BaseApiClient;
import io.cygnus.restful.client.base.PathParam;

public class OrderApiClient extends BaseApiClient {

	private String orderUri = "/order";

	private String ordersByInitUri = orderUri + "/init?tradingDay={tradingDay}&strategyId={strategyId}";

	public List<OrderEntity> getOrdersByInit(String tradingDay, Integer strategyId) {
		return getResultSet(OrderEntity.class, ordersByInitUri, new PathParam("tradingDay", tradingDay),
				new PathParam("strategyId", strategyId.toString()));
	}

	public boolean putOrders(OrderEntity order) {
		return putBody(order, orderUri);
	}

}
