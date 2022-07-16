package io.cygnux.repository.entities;

import io.cygnux.repository.constant.ColumnDefinition;
import io.cygnux.repository.constant.CommonColumn;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * OrderEvent Entity
 * 
 * @author yellow013
 * 
 */
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "t_order_event")
@Entity(name = TOrderEvent.ENTITY_NAME)
public final class TOrderEvent {

	public final static String ENTITY_NAME = "TOrderEvent";

	@Id
	@Column(name = CommonColumn.UID)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long uid;

	/**
	 * strategyId
	 */
	@Column(name = CommonColumn.STRATEGY_ID)
	private int strategyId;

	/**
	 * tradingDay
	 */
	@Column(name = CommonColumn.TRADING_DAY)
	private int tradingDay;

	/**
	 * investorId
	 */
	@Column(name = CommonColumn.INVESTOR_ID)
	private String investorId;

	/**
	 * instrumentCode
	 */
	@Column(name = CommonColumn.INSTRUMENT_CODE)
	private String instrumentCode;

	/**
	 * orderRef
	 */
	@Column(name = "order_ref")
	private Integer orderRef;
	public static final String COLUMN_OrderRef = "order_ref";

	/**
	 * orderMsgType
	 */
	@Column(name = "order_msg_type")
	private int orderMsgType;
	public static final String COLUMN_OrderMsgType = "order_msg_type";

	// UserID varchar 31
	@Column(name = "user_id")
	private String userId;
	public static final String COLUMN_UserID = "user_id";

	// Direction char
	@Column(name = "direction")
	private char direction;
	public static final String COLUMN_Direction = "direction";

	// Offset char
	@Column(name = "offset")
	private char offset;
	public static final String COLUMN_Offset = "offset";

	// LimitPrice double 19_4
	@Column(name = "limit_price", columnDefinition = ColumnDefinition.DECIMAL_19_4)
	private double limitPrice;
	public static final String COLUMN_LimitPrice = "limit_price";

	// VolumeTotalOriginal int
	@Column(name = "volume_total_original")
	private Integer volumeTotalOriginal;
	public static final String COLUMN_VolumeTotalOriginal = "volume_total_original";

	// OrderSysID varchar 21
	@Column(name = "order_sys_id")
	private String orderSysId;
	public static final String COLUMN_OrderSysID = "order_sys_id";

	// OrderStatus char
	@Column(name = "order_status")
	private char orderStatus;
	public static final String COLUMN_OrderStatus = "order_status";

	@Column(name = "broker_id")
	private String brokerId;
	public static final String COLUMN_BrokerID = "broker_id";

	@Column(name = "order_type")
	private Integer orderType;
	public static final String COLUMN_OrderType = "order_type";

	@Column(name = "counter_sys_id")
	private Long counterSysID;
	public static final String COLUMN_CounterSysID = "counter_sys_id";

	// Volume int
	@Column(name = "volume")
	private Integer volume;
	public static final String COLUMN_Volume = "volume";

	// VolumeFilled int
	@Column(name = "volume_filled")
	private Integer volumeFilled;
	public static final String COLUMN_VolumeFilled = "volume_filled";

	// VolumeRemained int
	@Column(name = "volume_remained")
	private Integer volumeRemained;
	public static final String COLUMN_VolumeRemained = "volume_remained";

	// Price double 19_4
	@Column(name = "price", columnDefinition = ColumnDefinition.DECIMAL_19_4)
	private double price;
	public static final String COLUMN_Price = "price";

	// TradeID varchar 21
	@Column(name = "trade_id")
	private String tradeId;
	public static final String COLUMN_TradeID = "trade_id";

	// OrdRejReason int
	@Column(name = "ord_rej_reason")
	private Integer ordRejReason;
	public static final String COLUMN_OrdRejReason = "ord_rej_reason";

	// InsertTime datetime
	@Column(name = "insert_time", columnDefinition = ColumnDefinition.TIME)
	private Date insertTime;
	public static final String COLUMN_InsertTime = "insert_time";

	// UpdateTime datetime
	@Column(name = "update_time", columnDefinition = ColumnDefinition.TIME)
	private Date updateTime;
	public static final String COLUMN_UpdateTime = "update_time";

	// CancelTime datetime
	@Column(name = "cancel_time", columnDefinition = ColumnDefinition.TIME)
	private Date cancelTime;
	public static final String COLUMN_CancelTime = "cancel_time";

	// FrontID int
	@Column(name = "front_id")
	private Integer frontId;
	public static final String COLUMN_FrontID = "front_id";

	// SessionID int
	@Column(name = "session_id")
	private Integer sessionId;
	public static final String COLUMN_SessionID = "session_id";

	// StatusMsg varchar 81
	@Column(name = "status_msg")
	private String statusMsg;
	public static final String COLUMN_StatusMsg = "status_msg";

	// Fee double 19.4
	@Column(name = "fee", columnDefinition = ColumnDefinition.DECIMAL_19_4)
	private double fee;
	public static final String COLUMN_Fee = "fee";

	// CounterType char
	@Column(name = "counter_type")
	private char counterType;
	public static final String COLUMN_CounterType = "counter_type";

}
