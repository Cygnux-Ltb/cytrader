package io.cygnuxltb.protocol.http.dto.outbound;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 交易账户表
 * Account Entity
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
public final class IndicatorDTO {

    private long uid;

    private int accountId;

    private int subAccountId;

    private String brokerId;

    private String investorId;

    private String adaptorType;

}

