package io.cygnuxltb.protocol.http.outbound;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 订单事件表
 * Trade Entity 订单事件
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
public final class OrderEventDTO {

    /**
     * 交易日 [*]
     */
    private int tradingDay;

    /**
     * strategyId [*]
     */
    private int strategyId;

    /**
     * 交易标的代码 [*]
     */
    private String instrumentCode;

    /**
     * investorId [*]
     */
    private String investorId;

    /**
     * brokerId [*]
     */
    private String brokerId;

    /**
     * accountId [*]
     */
    private int accountId;

    /**
     * subAccountId [*]
     */
    private int subAccountId;

    /**
     * userId [*]
     */
    private int userId;


    /**
     * ord_sys_id [*]
     */
    private long ordSysId;

    /**
     * order_ref
     */
    private String orderRef;

    /**
     * order_msg_type
     */
    private int orderMsgType;

    /**
     * ord_offset
     */
    private char ordOffset;

    /**
     * direction
     */
    private char direction;

    /**
     * limit_price double 19_4
     */
    private double limitPrice;

    /**
     * order_status char
     */
    private int status;

    /**
     * status_msg
     */
    private String statusMsg;


    /**
     * brokerSysID
     */
    private Long brokerSysID;

    /**
     * volume int
     */
    private int volume;

    /**
     * volume_filled int
     */
    private int volumeFilled;

    /**
     * volume_remained int
     */
    private int volumeRemained;

    /**
     * price double 19_4
     */
    private double price;

    /**
     * trade_id varchar 21
     */
    private String tradeId;

    /**
     * ord_rej_reason
     */
    private int ordRejReason;

    // InsertTime datetime
    /**
     * insert_time
     */
    private int insertTime;

    /**
     * update_time
     */
    private int updateTime;

    /**
     * cancel_time
     */
    private int cancelTime;

    /**
     * remark
     */
    private String remark;

}
