package io.cygnuxltb.protocol.http.outbound;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 订单扩展信息表
 * <p>
 * OrderExt Entity
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
public final class OrderExtDTO {

    /**
     * tradingDay [*]
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
     * orderSysId [*]
     */
    private long ordSysId;


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
     * brokerSysId
     */
    private long brokerSysId;

    /**
     * volume
     */
    private int volume;

    /**
     * price
     */
    private double price;


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
