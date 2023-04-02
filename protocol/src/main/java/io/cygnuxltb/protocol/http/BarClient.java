package io.cygnux.client;

import io.cygnux.client.base.BaseClient;
import io.cygnuxltb.protocol.http.dto.outbound.BarDTO;
import io.mercury.transport.http.param.PathParam;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BarClient extends BaseClient {

    private final String baseUri = "/bar";

    public List<BarDTO> getBars(int cygId, String instrumentCode, int tradingDay) {
        String getBarUri = baseUri + "/{cygId}?tradingDay={tradingDay}&instrumentId={instrumentId}";
        return getResultSet(BarDTO.class, getBarUri,
                new PathParam("cygId", Integer.toString(cygId)),
                new PathParam("tradingDay", Integer.toString(tradingDay)),
                new PathParam("instrumentId", instrumentCode));
    }

}
