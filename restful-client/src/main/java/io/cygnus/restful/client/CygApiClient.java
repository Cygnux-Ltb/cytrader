package io.cygnus.restful.client;

import java.util.List;

import io.cygnus.restful.client.base.BaseApiClient;
import io.cygnus.restful.client.base.PathParam;
import io.cygnus.service.entity.CygInfo;
import io.cygnus.service.entity.CygInitConfig;
import io.cygnus.service.entity.CygMqConfig;
import io.cygnus.service.entity.CygStrategy;


public class CygApiClient extends BaseApiClient {

	private String baseUri = "/cyg_info";

	public List<CygInfo> getAllCygInfo() {
		return super.getResultSet(CygInfo.class, baseUri);
	}

	private String cygInfoByIdUri = baseUri + "/{cygId}";

	public List<CygInfo> getCygInfoById(Integer cygId) {
		return super.getResultSet(CygInfo.class, cygInfoByIdUri, new PathParam("cygId", cygId.toString()));
	}

	private String cygStrategyByIdUri = baseUri + "/{cygId}/strategy";

	public List<CygStrategy> getCygStrategyById(Integer cygId) {
		return super.getResultSet(CygStrategy.class, cygStrategyByIdUri, new PathParam("cygId", cygId.toString()));
	}

	private String cygMqConfigByIdUri = baseUri + "/{cygId}/mq";

	public List<CygMqConfig> getCygMqConfigById(Integer cygId) {
		return super.getResultSet(CygMqConfig.class, cygMqConfigByIdUri, new PathParam("cygId", cygId.toString()));
	}

	private String cygInitConfigByIdUri = baseUri + "/{cygId}/init_config";

	public List<CygInitConfig> getCygInitConfigById(Integer cygId) {
		return super.getResultSet(CygInitConfig.class, cygInitConfigByIdUri, new PathParam("cygId", cygId.toString()));
	}
	
	public static void main(String[] args) {
		CygApiClient client = new CygApiClient();
		client.getAllCygInfo();
	}

}
