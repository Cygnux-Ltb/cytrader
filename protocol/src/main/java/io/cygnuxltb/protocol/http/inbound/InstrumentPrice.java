package io.cygnuxltb.protocol.http.inbound;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class InstrumentPrice {

    private String instrumentCode;

    private double lastPrice;

    public InstrumentPrice() {
    }

    public InstrumentPrice(String instrumentCode) {
        this.instrumentCode = instrumentCode;
    }

}
