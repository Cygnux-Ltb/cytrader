package io.cygnux.console.persistence.entity;

import io.cygnux.console.persistence.constant.CommonQueryColumn;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * OrderEvent Entity
 *
 * @author yellow013
 */
@Data
@Entity
@Table(name = "cyg_order_event")
public final class OrderEventEntity {

    @Id
    @Column(name = "uid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    /**
     * strategy_id [*]
     */
    @Column(name = CommonQueryColumn.STRATEGY_ID)
    private int strategyId;

    /**
     * trading_day [*]
     */
    @Column(name = CommonQueryColumn.TRADING_DAY)
    private int tradingDay;

    /**
     * investor_id
     */
    @Column(name = CommonQueryColumn.INVESTOR_ID)
    private String investorId;

    /**
     * instrument_code
     */
    @Column(name = CommonQueryColumn.INSTRUMENT_CODE)
    private String instrumentCode;

    /**
     * ord_sys_id [*]
     */
    @Column(name = "ord_sys_id")
    private long ordSysId;

    /**
     * order_ref
     */
    @Column(name = "order_ref")
    private Integer orderRef;

    /**
     * order_msg_type
     */
    @Column(name = "order_msg_type")
    private int orderMsgType;

    /**
     * user_id varchar 31
     */
    @Column(name = "user_id")
    private String userId;

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
    @Column(name = "ord_status")
    private int ordStatus;

    /**
     * broker_id
     */
    @Column(name = "broker_id")
    private String brokerId;

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
     * status_msg
     */
    @Column(name = "status_msg")
    private String statusMsg;

    /**
     * fee
     */
    @Column(name = "fee")
    private double fee;


    @Column(name = "remark")
    private String remark;

}
