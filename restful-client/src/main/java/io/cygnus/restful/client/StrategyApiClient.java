package io.cygnus.restful.client;

import java.util.List;

import io.cygnus.restful.client.base.BaseApiClient;
import io.cygnus.restful.client.base.PathParam;
import io.cygnus.service.entity.Strategy;
import io.cygnus.service.entity.StrategyParam;
import io.cygnus.service.entity.StrategySignal;
import io.cygnus.service.entity.StrategySymbol;

public class StrategyApiClient extends BaseApiClient {

	private String baseUri = "/strategy";

	private String strategyByIdUri = baseUri + "/{strategyId}";

	public List<Strategy> getStrategyById(Integer strategyId) {
		return super.getResultSet(Strategy.class, strategyByIdUri, 
				new PathParam("strategyId", strategyId.toString()));
	}

	private String paramByStrategyIdUri = baseUri + "/{strategyId}/param";

	public List<StrategyParam> getParamByStrategyId(Integer strategyId) {
		return super.getResultSet(StrategyParam.class, paramByStrategyIdUri,
				new PathParam("strategyId", strategyId.toString()));
	}

	private String symbolByStrategyIdUri = baseUri + "/{strategyId}/symbol";

	public List<StrategySymbol> getSymbolByStrategyId(Integer strategyId) {
		return super.getResultSet(StrategySymbol.class, symbolByStrategyIdUri,
				new PathParam("strategyId", strategyId.toString()));
	}

	private String signalByStrategyIdUri = baseUri + "/{strategyId}/signal";

	public List<StrategySignal> getSignalByStrategyId(Integer strategyId) {
		return super.getResultSet(StrategySignal.class, signalByStrategyIdUri,
				new PathParam("strategyId", strategyId.toString()));
	}
	
	private String putParamByStrategyIdUri = baseUri + "/{strategyId}/param";

	public boolean putParamByStrategyId(Integer strategyId, StrategyParam strategyParam) {
		return super.putBody(strategyParam, putParamByStrategyIdUri,
				new PathParam("strategyId", strategyId.toString()));
	}

	private String putSignalByStrategyIdUri = baseUri + "/{strategyId}/signal";

	public boolean putSignalByStrategyId(Integer strategyId, StrategySignal strategySignal) {
		return super.putBody(strategySignal, putSignalByStrategyIdUri,
				new PathParam("strategyId", strategyId.toString()));
	}	

	public static void main(String[] args) {
		StrategyApiClient client = new StrategyApiClient();

		List<Strategy> strategyById = client.getStrategyById(1);
		System.out.println("strategyById");
		for (Strategy strategy : strategyById) {
			System.out.println(strategy.getStrategyName());
		}

		List<StrategyParam> paramByStrategyId = client.getParamByStrategyId(1);
		System.out.println("strategyParam");
		for (StrategyParam param : paramByStrategyId) {
			System.out.println(param.getName());
		}

		List<StrategySymbol> symbolByStrategyId = client.getSymbolByStrategyId(1);
		System.out.println("strategySymbol");
		for (StrategySymbol symbol : symbolByStrategyId) {
			System.out.println(symbol.getSymbol());
		}
		
		List<StrategySignal> signalByStrategyId = client.getSignalByStrategyId(1);
		System.out.println("strategySignal");
		for (StrategySignal signal : signalByStrategyId) {
			System.out.println(signal.getSignalID());
		}

	}

}
