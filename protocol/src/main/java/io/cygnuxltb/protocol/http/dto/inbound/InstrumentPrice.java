package io.cygnuxltb.protocol.http.dto.inbound;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class InstrumentPrice {

    private String instrumentCode;

    private double lastPrice;

}
