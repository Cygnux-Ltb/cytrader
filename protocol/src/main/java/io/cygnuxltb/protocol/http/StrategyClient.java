package io.cygnux.client;

import io.cygnux.client.base.BaseClient;
import io.cygnuxltb.protocol.http.dto.outbound.ParamDTO;
import io.cygnuxltb.protocol.http.dto.outbound.StrategyDTO;
import io.mercury.transport.http.param.PathParam;

import java.util.List;

public class StrategyClient extends BaseClient {

    private final String baseUri = "/strategy";

    public List<StrategyDTO> getStrategyById(Integer strategyId) {
        String strategyByIdUri = baseUri + "/{strategyId}";
        return super.getResultSet(StrategyDTO.class, strategyByIdUri,
                new PathParam("strategyId", strategyId.toString()));
    }

    public List<ParamDTO> getParamByStrategyId(Integer strategyId) {
        String paramByStrategyIdUri = baseUri + "/{strategyId}/param";
        return super.getResultSet(ParamDTO.class, paramByStrategyIdUri,
                new PathParam("strategyId", strategyId.toString()));
    }

    public boolean putParamByStrategyId(Integer strategyId, ParamDTO param) {
        String putParamByStrategyIdUri = baseUri + "/{strategyId}/param";
        return super.putBody(param, putParamByStrategyIdUri,
                new PathParam("strategyId", strategyId.toString()));
    }

    public static void main(String[] args) {
        StrategyClient client = new StrategyClient();

        List<StrategyDTO> strategyById = client.getStrategyById(1);
        System.out.println("strategyById");
        for (StrategyDTO strategy : strategyById) {
            System.out.println(strategy.getStrategyName());
        }

        List<ParamDTO> paramByStrategyId = client.getParamByStrategyId(1);
        System.out.println("strategyParam");
        for (ParamDTO param : paramByStrategyId) {
            System.out.println(param.getParamName());
        }

    }

}
