package io.cygnux.client;

import io.cygnux.client.base.BaseClient;
import io.cygnuxltb.protocol.http.dto.outbound.OrderDTO;
import io.mercury.transport.http.param.PathParam;

import java.util.List;

public class OrderClient extends BaseClient {

    private final String orderUri = "/order";

    public List<OrderDTO> getOrdersByInit(String tradingDay, Integer strategyId) {
        String ordersByInitUri = orderUri + "/init?tradingDay={tradingDay}&strategyId={strategyId}";
        return getResultSet(OrderDTO.class, ordersByInitUri, new PathParam("tradingDay", tradingDay),
                new PathParam("strategyId", strategyId.toString()));
    }

    public boolean putOrders(OrderDTO order) {
        return putBody(order, orderUri);
    }

}
