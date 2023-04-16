package io.cygnuxltb.protocol.http.dto.outbound;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 交易标的表
 * Instrument Entity
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
public final class InstrumentDTO {

    /**
     * instrumentCode [*]
     */
    private String instrumentCode;

    /**
     * instrumentType [*]
     */
    private String instrumentType;

    /**
     * exchangeCode [*]
     */
    private String exchangeCode;

    /**
     * fee
     */
    private double fee;

    /**
     * tradable
     */
    private boolean tradable;

}
