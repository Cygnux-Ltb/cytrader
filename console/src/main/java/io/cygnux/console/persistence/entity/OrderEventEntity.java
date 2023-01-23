package io.cygnux.console.persistence.entity;

import io.cygnux.console.persistence.constant.ColumnDefinition;
import io.cygnux.console.persistence.constant.CommonQueryColumn;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * OrderEvent Entity 订单事件
 *
 * @author yellow013
 */
@Data
@Entity
@Table(name = "cyg_order_event")
public final class OrderEventEntity {

    @Id
    @Column(name = ColumnDefinition.UID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    /**
     * tradingDay [*]
     */
    @Column(name = CommonQueryColumn.TRADING_DAY)
    private int tradingDay;

    /**
     * strategyId [*]
     */
    @Column(name = CommonQueryColumn.STRATEGY_ID)
    private int strategyId;

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
     * brokerId [*]
     */
    @Column(name = CommonQueryColumn.BROKER_ID)
    private String brokerId;

    /**
     * accountId [*]
     */
    @Column(name = CommonQueryColumn.ACCOUNT_ID)
    private int accountId;

    /**
     * subAccountId [*]
     */
    @Column(name = CommonQueryColumn.SUB_ACCOUNT_ID)
    private int subAccountId;

    /**
     * userId [*]
     */
    @Column(name = CommonQueryColumn.USER_ID)
    private int userId;


    /**
     * ord_sys_id [*]
     */
    @Column(name = "ord_sys_id")
    private long ordSysId;

    /**
     * order_ref
     */
    @Column(name = "order_ref")
    private String orderRef;

    /**
     * order_msg_type
     */
    @Column(name = "order_msg_type")
    private int orderMsgType;


    /**
     * direction char
     */
    @Column(name = "direction")
    private char direction;

    /**
     * offset char
     */
    @Column(name = "offset")
    private char offset;

    /**
     * limit_price double 19_4
     */
    @Column(name = "limit_price")
    private double limitPrice;

    /**
     * order_status char
     */
    @Column(name = "status")
    private int status;

    /**
     * status_msg
     */
    @Column(name = "status_msg")
    private String statusMsg;


    /**
     * brokerSysID
     */
    @Column(name = "broker_sys_id")
    private Long brokerSysID;

    /**
     * volume int
     */
    @Column(name = "volume")
    private int volume;

    /**
     * volume_filled int
     */
    @Column(name = "volume_filled")
    private int volumeFilled;

    /**
     * volume_remained int
     */
    @Column(name = "volume_remained")
    private int volumeRemained;

    /**
     * price double 19_4
     */
    @Column(name = "price")
    private double price;

    /**
     * trade_id varchar 21
     */
    @Column(name = "trade_id")
    private String tradeId;

    /**
     * ord_rej_reason
     */
    @Column(name = "ord_rej_reason")
    private int ordRejReason;

    // InsertTime datetime
    /**
     * insert_time
     */
    @Column(name = "insert_time")
    private int insertTime;

    /**
     * update_time
     */
    @Column(name = "update_time")
    private int updateTime;

    /**
     * cancel_time
     */
    @Column(name = "cancel_time")
    private int cancelTime;


    /**
     * remark
     */
    @Column(name = "remark")
    private String remark;

}
