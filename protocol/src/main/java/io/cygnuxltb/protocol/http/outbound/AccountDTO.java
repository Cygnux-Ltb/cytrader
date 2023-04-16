package io.cygnuxltb.protocol.http.outbound;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 交易账户
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
public final class AccountDTO {

    /**
     * 账户ID [*]
     */
    private int accountId;

    /**
     * 子账户ID [*]
     */
    private int subAccountId;

    /**
     * 经纪商ID [*]
     */
    private String brokerId;

    /**
     * 投资账户ID [*]
     */
    private String investorId;

    /**
     * 适配器类型
     */
    private String adaptorType;

}
