package io.cygnux.repository.entity;

import io.cygnux.repository.constant.ColumnDefinition;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

import static io.cygnux.repository.constant.RdbColumn.INSTRUMENT_CODE;
import static io.cygnux.repository.constant.RdbColumn.INVESTOR_ID;
import static io.cygnux.repository.constant.RdbColumn.STRATEGY_ID;
import static io.cygnux.repository.constant.RdbColumn.TRADING_DAY;
import static io.cygnux.repository.constant.RdbColumn.UID;

/**
 * OrderEvent Entity
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "t_order_event")
@Entity
public final class OrderEventEntity {

    @Id
    @Column(name = UID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    /**
     * strategy_id
     */
    @Column(name = STRATEGY_ID)
    private int strategyId;

    /**
     * trading_day
     */
    @Column(name = TRADING_DAY)
    private int tradingDay;

    /**
     * investor_id
     */
    @Column(name = INVESTOR_ID)
    private String investorId;

    /**
     * instrument_code
     */
    @Column(name = INSTRUMENT_CODE)
    private String instrumentCode;

    /**
     * order_ref
     */
    @Column(name = "order_ref")
    private Integer orderRef;
    public static final String COLUMN_OrderRef = "order_ref";

    /**
     * order_msg_type
     */
    @Column(name = "order_msg_type")
    private int orderMsgType;
    public static final String COLUMN_OrderMsgType = "order_msg_type";

    /**
     * user_id varchar 31
     */
    @Column(name = "user_id")
    private String userId;
    public static final String COLUMN_UserID = "user_id";

    /**
     * direction char
     */
    @Column(name = "direction")
    private char direction;
    public static final String COLUMN_Direction = "direction";

    /**
     * offset char
     */
    @Column(name = "offset")
    private char offset;
    public static final String COLUMN_Offset = "offset";

    /**
     * limit_price double 19_4
     */
    @Column(name = "limit_price", columnDefinition = ColumnDefinition.DECIMAL_19_4)
    private double limitPrice;
    public static final String COLUMN_LimitPrice = "limit_price";

    /**
     * volume_total_original int
     */
    @Column(name = "volume_total_original")
    private Integer volumeTotalOriginal;
    public static final String COLUMN_VolumeTotalOriginal = "volume_total_original";

    /**
     * order_sys_id varchar 21
     */
    @Column(name = "order_sys_id")
    private String orderSysId;
    public static final String COLUMN_OrderSysID = "order_sys_id";

    /**
     * order_status char
     */
    @Column(name = "order_status")
    private char orderStatus;
    public static final String COLUMN_OrderStatus = "order_status";

    /**
     * broker_id
     */
    @Column(name = "broker_id")
    private String brokerId;
    public static final String COLUMN_BrokerID = "broker_id";

    /**
     * order_type
     */
    @Column(name = "order_type")
    private Integer orderType;
    public static final String COLUMN_OrderType = "order_type";

    /**
     * counter_sys_id
     */
    @Column(name = "counter_sys_id")
    private Long counterSysID;
    public static final String COLUMN_CounterSysID = "counter_sys_id";

    /**
     * volume int
     */
    @Column(name = "volume")
    private Integer volume;

    /**
     * volume_filled int
     */
    @Column(name = "volume_filled")
    private Integer volumeFilled;

    /**
     * volume_remained int
     */
    @Column(name = "volume_remained")
    private Integer volumeRemained;

    /**
     * price double 19_4
     */
    @Column(name = "price", columnDefinition = ColumnDefinition.DECIMAL_19_4)
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
    private Integer ordRejReason;

    // InsertTime datetime
    /**
     * insert_time
     */
    @Column(name = "insert_time", columnDefinition = ColumnDefinition.TIME)
    private Date insertTime;

    /**
     * update_time
     */
    @Column(name = "update_time", columnDefinition = ColumnDefinition.TIME)
    private Date updateTime;

    /**
     * cancel_time
     */
    @Column(name = "cancel_time", columnDefinition = ColumnDefinition.TIME)
    private Date cancelTime;

    /**
     * front_id
     */
    @Column(name = "front_id")
    private Integer frontId;

    /**
     * session_id
     */
    @Column(name = "session_id")
    private Integer sessionId;

    /**
     * status_msg varchar 81
     */
    @Column(name = "status_msg")
    private String statusMsg;

    /**
     * fee
     */
    @Column(name = "fee", columnDefinition = ColumnDefinition.DECIMAL_19_4)
    private double fee;

    /**
     * counter_type
     */
    @Column(name = "counter_type")
    private char counterType;

}
