package io.cygnux.client;

import io.cygnux.client.base.BaseClient;
import io.cygnuxltb.protocol.http.dto.outbound.ProductDTO;
import io.cygnuxltb.protocol.http.dto.outbound.StrategyDTO;
import io.mercury.transport.http.param.PathParam;

import java.util.List;

public class CygClient extends BaseClient {

    private final String baseUri = "/cyg_info";

    public List<ProductDTO> getAllCygInfo() {
        return super.getResultSet(ProductDTO.class, baseUri);
    }

    public List<ProductDTO> getCygInfoById(int cygId) {
        String cygInfoByIdUri = baseUri + "/{cygId}";
        return super.getResultSet(ProductDTO.class, cygInfoByIdUri,
                new PathParam("cygId", Integer.toString(cygId)));
    }

    public List<StrategyDTO> getCygStrategyById(int cygId) {
        String cygStrategyByIdUri = baseUri + "/{cygId}/strategy";
        return super.getResultSet(StrategyDTO.class, cygStrategyByIdUri,
                new PathParam("cygId", Integer.toString(cygId)));
    }

    public static void main(String[] args) {
        CygClient client = new CygClient();
        client.getAllCygInfo();
    }

}
