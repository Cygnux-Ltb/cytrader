package io.cygnux.console.entity;

import io.cygnux.console.dao.constant.ColumnDefinition;
import io.cygnux.console.dao.constant.CommonQueryColumn;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

/**
 * Order Entity
 *
 * @author yellow013
 */
@Data
@Entity
@Table(name = "cyg_order")
public final class OrderEntity {

    @Id
    @Column(name = "uid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    /**
     * strategyId [*]
     */
    @Column(name = CommonQueryColumn.STRATEGY_ID)
    private int strategyId;

    /**
     * tradingDay [*]
     */
    @Column(name = CommonQueryColumn.TRADING_DAY)
    private int tradingDay;

    /**
     * instrumentCode [*]
     */
    @Column(name = CommonQueryColumn.INSTRUMENT_CODE)
    private String instrumentCode;

    /**
     * investorId [*]
     */
    @Column(name = CommonQueryColumn.INVESTOR_ID)
    private String investorId;

    /**
     * broker [*]
     */
    @Column(name = CommonQueryColumn.BROKER)
    private String broker;

    /**
     * orderSysId [*]
     */
    @Column(name = "ord_sys_id")
    private long ordSysId;

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
     * side
     */
    @Column(name = "side")
    private char side;

    /**
     * offerPrice
     */
    @Column(name = "offer_price", columnDefinition = ColumnDefinition.DECIMAL_19_4)
    private double offerPrice;

    /**
     * offerQty
     */
    @Column(name = "offer_qty")
    private int offerQty;


    /**
     * brokerSysId
     */
    @Column(name = "broker_sys_id")
    private long brokerSysId;

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

    @Column(name = "remark")
    private String remark;

}
