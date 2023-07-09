package io.cygnuxltb.console.hc;

import io.cygnuxltb.protocol.http.outbound.BarM1DTO;
import io.cygnuxltb.protocol.http.outbound.OrderDTO;
import io.cygnuxltb.protocol.http.outbound.ParamDTO;
import io.cygnuxltb.protocol.http.outbound.PnlDTO;
import io.cygnuxltb.protocol.http.outbound.ProductDTO;
import io.cygnuxltb.protocol.http.outbound.StrategyDTO;
import io.mercury.common.http.JreHttpClient;
import io.mercury.common.http.PathParams.PathParam;
import io.mercury.common.sys.SysProperties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public final class CytraderHttpClient extends BaseHttpClient {

    private JreHttpClient http;

    private String sysUri;
    private String barUri;
    private String pnlUri;
    private String orderUri;
    private String strategyUri;

    private CytraderHttpClient() {
        var prop = new Properties();
        try {
            if (SysProperties.USER_HOME == null) {
                prop.load(CytraderHttpClient.class.getResourceAsStream("config.properties"));
            } else {
                FileInputStream fileInputStream = new FileInputStream(
                        SysProperties.USER_HOME + "/config/config.properties");
                prop.load(fileInputStream);
            }
            var baseUrl = prop.getProperty("baseUrl");
            this.sysUri = baseUrl + "/sys";
            this.barUri = baseUrl + "/bar";
            this.pnlUri = baseUrl + "/pnl";
            this.orderUri = baseUrl + "/order";
            this.strategyUri = baseUrl + "/strategy";
        } catch (IOException e) {
            log.error("read config file has IOException -> {}", e.getMessage(), e);
            e.printStackTrace();
        }
    }

    public List<BarM1DTO> getBars(int sysId, String instrumentCode, int tradingDay) {
        return sendGetRequest(BarM1DTO.class, barUri,
                new PathParam("sysId", sysId),
                new PathParam("instrumentId", instrumentCode),
                new PathParam("tradingDay", tradingDay));
    }


    public List<ProductDTO> getAllSysInfo() {
        return sendGetRequest(ProductDTO.class, sysUri);
    }

    public List<ProductDTO> getSysInfoById(int sysId) {
        return sendGetRequest(ProductDTO.class, sysUri,
                new PathParam("sysId", Integer.toString(sysId)));
    }

    public List<StrategyDTO> getStrategyById(int sysId) {
        return sendGetRequest(StrategyDTO.class, sysUri + "/strategy",
                new PathParam("sysId", Integer.toString(sysId)));
    }


    public List<OrderDTO> getOrdersByInit(String tradingDay, int strategyId) {
        return sendGetRequest(OrderDTO.class, orderUri + "/init",
                new PathParam("tradingDay", tradingDay),
                new PathParam("strategyId", strategyId));
    }

    public boolean putOrders(OrderDTO order) {
        return sendPutRequest(order, orderUri);
    }


    public List<PnlDTO> getPnlDaily(String tradingDay, Integer strategyId) {
        return sendGetRequest(PnlDTO.class, pnlUri,
                new PathParam("tradingDay", tradingDay),
                new PathParam("strategyId", strategyId.toString()));
    }

    public boolean putPnlDaily(PnlDTO pnl) {
        return sendPutRequest(pnl, pnlUri);
    }


    public List<StrategyDTO> getStrategyById(Integer strategyId) {
        return sendGetRequest(StrategyDTO.class, strategyUri,
                new PathParam("strategyId", strategyId.toString()));
    }

    public List<ParamDTO> getParamByStrategyId(Integer strategyId) {
        return sendGetRequest(ParamDTO.class, strategyUri + "/param",
                new PathParam("strategyId", strategyId.toString()));
    }

    public boolean putParamByStrategyId(Integer strategyId, ParamDTO param) {
        return sendPutRequest(param, strategyUri + "/param",
                new PathParam("strategyId", strategyId.toString()));
    }


}
