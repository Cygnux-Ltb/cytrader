package io.cygnuxltb.protocol.http.outbound;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 订单基本信息表
 * Order Entity 基本信息
 * [*] 为不可为空字段
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
public final class OrderDTO {

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
     * ordSysId [*]
     */
    private long ordSysId;

    /**
     * ordType
     */
    private String ordType;

    /**
     * orderRef
     */
    private String orderRef;

    /**
     * direction
     */
    private char direction;

    /**
     * side
     */
    private char side;

    /**
     * offerPrice
     */
    private double offerPrice;

    /**
     * offerQty
     */
    private int offerQty;

    /**
     * insertTime
     */
    private Date insertTime;

    /**
     * updateTime
     */
    private Date updateTime;

    /**
     * cancelTime
     */
    private Date cancelTime;

    /**
     * frontId
     */
    private int frontId;

    /**
     * sessionId
     */
    private int sessionId;

    /**
     * fee double 19_4
     */
    private double fee;

    /**
     * adaptorType
     */
    private String adaptorType;

    /**
     * remark
     */
    private String remark;

}
