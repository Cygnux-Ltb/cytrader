package io.cygnux.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.cygnux.repository.constant.ColumnDefinition;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

import static io.cygnux.repository.constant.CommonQueryColumn.INSTRUMENT_CODE;
import static io.cygnux.repository.constant.CommonQueryColumn.INVESTOR_ID;
import static io.cygnux.repository.constant.CommonQueryColumn.STRATEGY_ID;
import static io.cygnux.repository.constant.CommonQueryColumn.TRADING_DAY;

/**
 * Order Entity
 *
 * @author yellow013
 */
@Data
@TableName("cyg_order")
public final class OrderEntity {

    @TableId(type = IdType.AUTO)
    private long uid;

    /**
     * strategyId [*]
     */
    @Column(name = STRATEGY_ID)
    private int strategyId;

    /**
     * tradingDay [*]
     */
    @Column(name = TRADING_DAY)
    private int tradingDay;

    /**
     * instrumentCode [*]
     */
    @Column(name = INSTRUMENT_CODE)
    private String instrumentCode;

    /**
     * investorId [*]
     */
    @Column(name = INVESTOR_ID)
    private String investorId;

    /**
     * orderSysId [*]
     */
    @Column(name = "ord_sys_id")
    private long orderSysId;

    /**
     * ordType
     */
    @Column(name = "ord_type")
    private String ordType;

    /**
     * orderRef
     */
    @Column(name = "order_ref")
    private String orderRef;

    /**
     * orderMsgType
     */
    @Column(name = "order_msg_type")
    private int orderMsgType;

    /**
     * userId
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * direction
     */
    @Column(name = "direction")
    private char direction;

    /**
     * offset
     */
    @Column(name = "side")
    private char side;

    /**
     * limitPrice
     */
    @Column(name = "offer_price", columnDefinition = ColumnDefinition.DECIMAL_19_4)
    private double offerPrice;

    /**
     * volumeTotalOriginal
     */
    @Column(name = "offer_qty")
    private int offerQty;

    /**
     * orderStatus
     */
    @Column(name = "order_status")
    private char orderStatus;

    /**
     * broker_id
     */
    @Column(name = "broker_id")
    private String brokerId;

    /**
     * counterSysID
     */
    @Column(name = "counter_sys_id")
    private long counterSysID;

    /**
     * volume
     */
    @Column(name = "volume")
    private int volume;

    /**
     * price
     */
    @Column(name = "price", columnDefinition = ColumnDefinition.DECIMAL_19_4)
    private double price;

    /**
     * tradeId
     */
    @Column(name = "trade_id")
    private String tradeId;

    /**
     * ordRejReason
     */
    @Column(name = "ord_rej_reason")
    private int ordRejReason;

    /**
     * insertTime
     */
    @Column(name = "insert_time", columnDefinition = ColumnDefinition.TIME)
    private Date insertTime;

    /**
     * updateTime
     */
    @Column(name = "update_time", columnDefinition = ColumnDefinition.TIME)
    private Date updateTime;

    /**
     * cancelTime
     */
    @Column(name = "cancel_time", columnDefinition = ColumnDefinition.TIME)
    private Date cancelTime;

    /**
     * frontId
     */
    @Column(name = "front_id")
    private int frontId;

    /**
     * sessionId
     */
    @Column(name = "session_id")
    private int sessionId;

    /**
     * statusMsg
     */
    @Column(name = "status_msg")
    private String statusMsg;

    /**
     * fee double 19_4
     */
    @Column(name = "fee")
    private double fee;

    /**
     * adaptorType
     */
    @Column(name = "adaptor_type")
    private String adaptorType;

}
