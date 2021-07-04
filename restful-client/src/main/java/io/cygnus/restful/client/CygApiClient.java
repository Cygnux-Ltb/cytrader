package io.cygnus.restful.client;

import java.util.List;

import io.cygnus.repository.entity.CygInfoEntity;
import io.cygnus.repository.entity.StrategyEntity;
import io.cygnus.restful.client.base.BaseApiClient;
import io.cygnus.restful.client.base.PathParam;

public class CygApiClient extends BaseApiClient {

	private String baseUri = "/cyg_info";

	public List<CygInfoEntity> getAllCygInfo() {
		return super.getResultSet(CygInfoEntity.class, baseUri);
	}

	private String cygInfoByIdUri = baseUri + "/{cygId}";

	public List<CygInfoEntity> getCygInfoById(Integer cygId) {
		return super.getResultSet(CygInfoEntity.class, cygInfoByIdUri, new PathParam("cygId", cygId.toString()));
	}

	private String cygStrategyByIdUri = baseUri + "/{cygId}/strategy";

	public List<StrategyEntity> getCygStrategyById(Integer cygId) {
		return super.getResultSet(StrategyEntity.class, cygStrategyByIdUri, new PathParam("cygId", cygId.toString()));
	}

	private String cygMqConfigByIdUri = baseUri + "/{cygId}/mq";

	private String cygInitConfigByIdUri = baseUri + "/{cygId}/init_config";

	public static void main(String[] args) {
		CygApiClient client = new CygApiClient();
		client.getAllCygInfo();
	}

}
