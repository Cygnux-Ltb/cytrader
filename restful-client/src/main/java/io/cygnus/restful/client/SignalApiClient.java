package io.cygnus.restful.client;

import java.util.List;

import io.cygnus.restful.client.base.BaseApiClient;
import io.cygnus.restful.client.base.PathParam;
import io.cygnus.service.entity.Signal;
import io.cygnus.service.entity.SignalParam;
import io.cygnus.service.entity.SignalSymbol;

public class SignalApiClient extends BaseApiClient {

	private String baseUri = "/signal";

	private String signalByIdUri = baseUri + "/{signalId}";

	public List<Signal> getSignalById(Integer signalId) {
		return super.getResultSet(Signal.class, signalByIdUri, 
				new PathParam("signalId", signalId.toString()));
	}

	private String paramBySignalIdUri = baseUri + "/{signalId}/param";

	public List<SignalParam> getParamBySignalId(Integer signalId) {
		return super.getResultSet(SignalParam.class, paramBySignalIdUri,
				new PathParam("signalId", signalId.toString()));
	}

	private String symbolBySignalIdUri = baseUri + "/{signalId}/symbol";

	public List<SignalSymbol> getSymbolBySignalId(Integer signalId) {
		return super.getResultSet(SignalSymbol.class, symbolBySignalIdUri,
				new PathParam("signalId", signalId.toString()));
	}

	public static void main(String[] args) {
		SignalApiClient client = new SignalApiClient();

		List<Signal> signalById = client.getSignalById(1);
		System.out.println("signalById");
		for (Signal signal : signalById) {
			System.out.println(signal.getSignalName());
		}

		List<SignalParam> paramBySignalId = client.getParamBySignalId(1);
		System.out.println("signalParam");
		for (SignalParam param : paramBySignalId) {
			System.out.println(param.getName());
		}
	}

}
