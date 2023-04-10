package io.cygnuxltb.console.hc;

import io.cygnuxltb.protocol.http.dto.outbound.BarDTO;
import io.cygnuxltb.protocol.http.dto.outbound.OrderDTO;
import io.cygnuxltb.protocol.http.dto.outbound.ParamDTO;
import io.cygnuxltb.protocol.http.dto.outbound.PnlDTO;
import io.cygnuxltb.protocol.http.dto.outbound.ProductDTO;
import io.cygnuxltb.protocol.http.dto.outbound.StrategyDTO;
import io.mercury.common.character.Charsets;
import io.mercury.common.http.param.HttpParam;
import io.mercury.common.http.JreHttpClient;
import io.mercury.common.http.MimeType;
import io.mercury.common.log.Log4j2LoggerFactory;
import io.mercury.common.sys.SysProperties;
import io.mercury.serialization.json.JsonParser;
import io.mercury.serialization.json.JsonWrapper;
import org.slf4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public final class CytraderHttpClient {

    private final Logger log = Log4j2LoggerFactory.getLogger(getClass());

    private JreHttpClient http;

    private String baseUrl;

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
            this.baseUrl = prop.getProperty("baseUrl");
        } catch (IOException e) {
            log.error("read config file has IOException -> {}", e.getMessage(), e);
            e.printStackTrace();
        }
    }


    /**
     * 发送Get请求获取数据
     *
     * @param resultType Class<T>
     * @param uri        String
     * @param params     PathParam[]
     * @return List<T>
     */
    protected <T> List<T> getResultSet(Class<T> resultType, String uri, HttpParam... params) {
        return analysisJson(sendGetRequest(replaceParam(uri, params)), resultType);
    }

    private String sendGetRequest(String uri) {
        try {
            HttpGet httpGet = new HttpGet(baseUrl + uri);
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                int statusCode = response.getCode();
                if (statusCode > 307) {
                    throw new RuntimeException(
                            "Exception -> Request URI: [" + httpGet.getUri() + "] return status code " + statusCode);
                }
                return EntityUtils.toString(response.getEntity(), Charsets.UTF8);
            }
        } catch (Exception e) {
            log.error("Error Message -> {}", e.getMessage(), e);
            throw new RuntimeException("Exception -> " + e.getMessage());
        }
    }

    private <T> List<T> analysisJson(String json, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        if (json != null && !json.equals("")) {
            list = JsonParser.toList(json, clazz);
        }
        return list;
    }

    /**
     * Put JSON格式数据到服务器
     *
     * @param body   Object
     * @param uri    String
     * @param params PathParam[]
     * @return boolean
     */
    protected boolean putBody(Object body, String uri, HttpParam... params) {
        return sendPutRequest(toJsonString(body), replaceParam(uri, params));
    }

    private String toJsonString(Object obj) {
        return JsonWrapper.toJson(obj);
    }

    private boolean sendPutRequest(String jsonBody, String uri) {
        if (jsonBody == null || jsonBody.equals("")) {
            throw new IllegalArgumentException("Put request body does not allowed null or empty string.");
        }
        StringEntity entity = new StringEntity(jsonBody, MimeType.APPLICATION_JSON_UTF8);
        HttpPut httpPut = new HttpPut(baseUrl + uri);
        httpPut.setEntity(entity);
        try (CloseableHttpResponse response = httpClient.execute(httpPut)) {
            int statusCode = response.getCode();
            return statusCode <= 307;
        } catch (Exception e) {
            throw new RuntimeException("Exception -> " + e.getMessage());
        }
    }

    private String replaceParam(String uri, HttpParam... params) {
        for (HttpParam param : params) {
            uri = uri.replace(param.getName().toString(), param.getValue().toString());
        }
        return uri;
    }


    public List<BarDTO> getBars(int cygId, String instrumentCode, int tradingDay) {
        String baseUri = "/bar";
        String getBarUri = baseUri + "/{cygId}?tradingDay={tradingDay}&instrumentId={instrumentId}";
        return getResultSet(BarDTO.class, getBarUri,
                HttpParam.with("cygId", Integer.toString(cygId)),
                HttpParam.with("tradingDay", Integer.toString(tradingDay)),
                HttpParam.with("instrumentId", instrumentCode));
    }


    private final String cygUri = "/cyg_info";

    public List<ProductDTO> getAllCygInfo() {
        return super.getResultSet(ProductDTO.class, cygUri);
    }

    public List<ProductDTO> getSysInfoById(int cygId) {
        String cygInfoByIdUri = cygUri + "/{cygId}";
        return super.getResultSet(ProductDTO.class, cygInfoByIdUri,
                HttpParam.with("cygId", Integer.toString(cygId)));
    }

    public List<StrategyDTO> getStrategyById(int cygId) {
        String cygStrategyByIdUri = cygUri + "/{cygId}/strategy";
        return super.getResultSet(StrategyDTO.class, cygStrategyByIdUri,
                HttpParam.with("cygId", Integer.toString(cygId)));
    }


    private final String orderUri = "/order";

    public List<OrderDTO> getOrdersByInit(String tradingDay, int strategyId) {
        String ordersByInitUri = orderUri + "/init?tradingDay={tradingDay}&strategyId={strategyId}";
        return getResultSet(OrderDTO.class, ordersByInitUri, HttpParam.with("tradingDay", tradingDay),
                HttpParam.with("strategyId", strategyId));
    }

    public boolean putOrders(OrderDTO order) {
        return putBody(order, orderUri);
    }


    private final String pnlUri = "/pnl";

    public List<PnlDTO> getPnlDaily(String tradingDay, Integer strategyId) {
        String getPnlDailyUri = pnlUri + "?tradingDay={tradingDay}&strategyId={strategyId}";
        return getResultSet(PnlDTO.class, getPnlDailyUri,
                HttpParam.with("tradingDay", tradingDay), HttpParam.with("strategyId", strategyId.toString()));
    }

    public boolean putPnlDaily(PnlDTO pnl) {
        return putBody(pnl, pnlUri);
    }

    private final String strategyUri = "/strategy";

    public List<StrategyDTO> getStrategyById(Integer strategyId) {
        String strategyByIdUri = strategyUri + "/{strategyId}";
        return super.getResultSet(StrategyDTO.class, strategyByIdUri,
                HttpParam.with("strategyId", strategyId.toString()));
    }

    public List<ParamDTO> getParamByStrategyId(Integer strategyId) {
        String paramByStrategyIdUri = strategyUri + "/{strategyId}/param";
        return super.getResultSet(ParamDTO.class, paramByStrategyIdUri,
                HttpParam.with("strategyId", strategyId.toString()));
    }

    public boolean putParamByStrategyId(Integer strategyId, ParamDTO param) {
        String putParamByStrategyIdUri = strategyUri + "/{strategyId}/param";
        return super.putBody(param, putParamByStrategyIdUri,
                HttpParam.with("strategyId", strategyId.toString()));
    }


}
