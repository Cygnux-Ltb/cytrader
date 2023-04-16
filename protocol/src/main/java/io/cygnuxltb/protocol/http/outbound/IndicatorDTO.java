package io.cygnuxltb.protocol.http.outbound;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 指标
 * Account Entity
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
public final class IndicatorDTO {

    private int accountId;

    private int subAccountId;

    private String brokerId;

    private String investorId;

    private String adaptorType;

}

