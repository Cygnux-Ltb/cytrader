package io.cygnus.restful.client;

import java.util.List;

import io.cygnus.repository.entity.StrategyEntity;
import io.cygnus.repository.entity.StrategyParamEntity;
import io.cygnus.restful.client.base.BaseApiClient;
import io.cygnus.restful.client.base.PathParam;

public class StrategyApiClient extends BaseApiClient {

	private String baseUri = "/strategy";

	private String strategyByIdUri = baseUri + "/{strategyId}";

	public List<StrategyEntity> getStrategyById(Integer strategyId) {
		return super.getResultSet(StrategyEntity.class, strategyByIdUri,
				new PathParam("strategyId", strategyId.toString()));
	}

	private String paramByStrategyIdUri = baseUri + "/{strategyId}/param";

	public List<StrategyParamEntity> getParamByStrategyId(Integer strategyId) {
		return super.getResultSet(StrategyParamEntity.class, paramByStrategyIdUri,
				new PathParam("strategyId", strategyId.toString()));
	}

	private String putParamByStrategyIdUri = baseUri + "/{strategyId}/param";

	public boolean putParamByStrategyId(Integer strategyId, StrategyParamEntity strategyParam) {
		return super.putBody(strategyParam, putParamByStrategyIdUri,
				new PathParam("strategyId", strategyId.toString()));
	}

	public static void main(String[] args) {
		StrategyApiClient client = new StrategyApiClient();

		List<StrategyEntity> strategyById = client.getStrategyById(1);
		System.out.println("strategyById");
		for (StrategyEntity strategy : strategyById) {
			System.out.println(strategy.getStrategyName());
		}

		List<StrategyParamEntity> paramByStrategyId = client.getParamByStrategyId(1);
		System.out.println("strategyParam");
		for (StrategyParamEntity param : paramByStrategyId) {
			System.out.println(param.getParamName());
		}

	}

}
