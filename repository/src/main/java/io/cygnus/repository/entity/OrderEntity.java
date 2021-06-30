package io.cygnus.repository.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.cygnus.repository.constant.ColumnDefinition;
import io.cygnus.repository.constant.CommonQueryColumn;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 
 * @author yellow013
 * 
 *         CygOrder
 *
 */
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "cyg_order")
@Entity(name = "CygOrder")
public final class CygOrder {

	@Id
	@Column(name = ColumnDefinition.UID)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long uid;

	/**
	 * strategyId
	 */
	@Column(name = CommonQueryColumn.STRATEGY_ID)
	private int strategyId;

	/**
	 * tradingDay
	 */
	@Column(name = CommonQueryColumn.TRADING_DAY)
	private int tradingDay;

	/**
	 * instrumentCode
	 */
	@Column(name = CommonQueryColumn.INSTRUMENT_CODE)
	private String instrumentCode;

	/**
	 * orderSysId
	 */
	@Column(name = "order_sys_id")
	private String orderSysId;

	/**
	 * 
	 */
	private String ordType;

	/**
	 * investorId
	 */
	@Column(name = "investor_id")
	private String investorId;
	public static final String COLUMN_InvestorID = "investor_id";

	/**
	 * orderRef
	 */
	@Column(name = "order_ref")
	private String orderRef;
	public static final String COLUMN_OrderRef = "order_ref";

	/**
	 * orderMsgType
	 */
	@Column(name = "order_msg_type")
	private Integer orderMsgType;
	public static final String COLUMN_OrderMsgType = "order_msg_type";

	/**
	 * userId
	 */
	@Column(name = "user_id")
	private String userId;
	public static final String COLUMN_UserID = "user_id";

	/**
	 * direction
	 */
	@Column(name = "direction")
	private char direction;
	public static final String COLUMN_Direction = "direction";

	/**
	 * offset
	 */
	@Column(name = "offset")
	private char offset;
	public static final String COLUMN_Offset = "offset";

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
	 * orderSysId
	 */

	/**
	 * orderStatus
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
	 * counterSysID
	 */
	@Column(name = "counter_sys_id")
	private long counterSysID;
	public static final String COLUMN_CounterSysID = "counter_sys_id";

	/**
	 * volume
	 */
	@Column(name = "volume")
	private int volume;
	public static final String COLUMN_Volume = "volume";

	/**
	 * volumeFilled
	 */
	@Column(name = "volume_filled")
	private int volumeFilled;
	public static final String COLUMN_VolumeFilled = "volume_filled";

	/**
	 * volumeRemained
	 */
	@Column(name = "volume_remained")
	private int volumeRemained;
	public static final String COLUMN_VolumeRemained = "volume_remained";

	/**
	 * price
	 */
	@Column(name = "price", columnDefinition = ColumnDefinition.DECIMAL_19_4)
	private double price;
	public static final String COLUMN_Price = "price";

	/**
	 * tradeId
	 */
	@Column(name = "trade_id")
	private String tradeId;
	public static final String COLUMN_TradeID = "trade_id";

	/**
	 * ordRejReason
	 */
	@Column(name = "ord_rej_reason")
	private int ordRejReason;
	public static final String COLUMN_OrdRejReason = "ord_rej_reason";

	/**
	 * insertTime
	 */
	@Column(name = "insert_time", columnDefinition = ColumnDefinition.TIME)
	private Date insertTime;
	public static final String COLUMN_InsertTime = "insert_time";

	/**
	 * updateTime
	 */
	@Column(name = "update_time", columnDefinition = ColumnDefinition.TIME)
	private Date updateTime;
	public static final String COLUMN_UpdateTime = "update_time";

	/**
	 * cancelTime
	 */
	@Column(name = "cancel_time", columnDefinition = ColumnDefinition.TIME)
	private Date cancelTime;
	public static final String COLUMN_CancelTime = "cancel_time";

	/**
	 * frontId
	 */
	@Column(name = "front_id")
	private int frontId;
	public static final String COLUMN_FrontID = "front_id";

	/**
	 * sessionId
	 */
	@Column(name = "session_id")
	private int sessionId;
	public static final String COLUMN_SessionID = "session_id";

	/**
	 * statusMsg
	 */
	@Column(name = "status_msg", length = 255)
	private String statusMsg;

	/**
	 * fee double 19_4
	 */
	@Column(name = "fee", columnDefinition = ColumnDefinition.DECIMAL_19_4)
	private double fee;

	/**
	 * adaptorType
	 */
	@Column(name = "adaptor_type")
	private String adaptorType;

}
