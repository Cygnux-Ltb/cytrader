package io.cygnux.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import static io.cygnux.repository.constant.CommonQueryColumn.INSTRUMENT_CODE;
import static io.cygnux.repository.constant.CommonQueryColumn.INVESTOR_ID;
import static io.cygnux.repository.constant.CommonQueryColumn.STRATEGY_ID;
import static io.cygnux.repository.constant.CommonQueryColumn.TRADING_DAY;

/**
 * OrderEvent Entity
 *
 * @author yellow013
 */
@Data
@TableName("cyg_order_event")
public final class OrderEventEntity {

    @TableId(type = IdType.AUTO)
    private long uid;

    /**
     * strategy_id [*]
     */
    @TableField(STRATEGY_ID)
    private int strategyId;

    /**
     * trading_day [*]
     */
    @TableField(TRADING_DAY)
    private int tradingDay;

    /**
     * investor_id
     */
    @TableField(INVESTOR_ID)
    private String investorId;

    /**
     * instrument_code
     */
    @TableField(INSTRUMENT_CODE)
    private String instrumentCode;

    /**
     * order_ref
     */
    @TableField("order_ref")
    private Integer orderRef;

    /**
     * order_msg_type
     */
    @TableField("order_msg_type")
    private int orderMsgType;

    /**
     * user_id varchar 31
     */
    @TableField("user_id")
    private String userId;

    /**
     * direction char
     */
    @TableField("direction")
    private char direction;

    /**
     * offset char
     */
    @TableField("offset")
    private char offset;

    /**
     * limit_price double 19_4
     */
    @TableField("limit_price")
    private double limitPrice;

    /**
     * volume_total_original
     */
    @TableField("volume_total_original")
    private int volumeTotalOriginal;

    /**
     * order_sys_id
     */
    @TableField("order_sys_id")
    private String orderSysId;

    /**
     * order_status char
     */
    @TableField("order_status")
    private int orderStatus;

    /**
     * broker_id
     */
    @TableField("broker_id")
    private String brokerId;

    /**
     * order_type
     */
    @TableField("order_type")
    private int orderType;

    /**
     * counter_sys_id
     */
    @TableField("counter_sys_id")
    private Long counterSysID;

    /**
     * volume int
     */
    @TableField("volume")
    private int volume;

    /**
     * volume_filled int
     */
    @TableField("volume_filled")
    private int volumeFilled;

    /**
     * volume_remained int
     */
    @TableField("volume_remained")
    private int volumeRemained;

    /**
     * price double 19_4
     */
    @TableField("price")
    private double price;

    /**
     * trade_id varchar 21
     */
    @TableField("trade_id")
    private String tradeId;

    /**
     * ord_rej_reason
     */
    @TableField("ord_rej_reason")
    private int ordRejReason;

    // InsertTime datetime
    /**
     * insert_time
     */
    @TableField("insert_time")
    private int insertTime;

    /**
     * update_time
     */
    @TableField("update_time")
    private int updateTime;

    /**
     * cancel_time
     */
    @TableField("cancel_time")
    private int cancelTime;

    /**
     * front_id
     */
    @TableField("front_id")
    private int frontId;

    /**
     * session_id
     */
    @TableField("session_id")
    private int sessionId;

    /**
     * status_msg
     */
    @TableField("status_msg")
    private String statusMsg;

    /**
     * fee
     */
    @TableField("fee")
    private double fee;

    /**
     * counter_type
     */
    @TableField("counter_type")
    private int counterType;

}
