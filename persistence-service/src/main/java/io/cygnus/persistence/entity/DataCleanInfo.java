package io.cygnus.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.cygnus.persistence.entity.base.ColumnDefinition;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 
 * @author yellow013
 *
 */

@Entity
@Table(name = "data_clean_info")
@Getter
@Setter
@Accessors(chain = true)
public final class DataCleanInfo {

	@Id
	@Column(name = "uid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uid;

	@Column(name = "location")
	private String location;
	public static final String COLUMN_Location = "location";

	// TradingDay date
	@Column(name = "trading_day", columnDefinition = ColumnDefinition.DATE)
	private long tradingDay;
	public static final String COLUMN_TradingDay = "trading_day";

	@Column(name = "start_time")
	private String startTime;
	public static final String COLUMN_StartTime = "start_time";

	@Column(name = "end_time")
	private String endTime;
	public static final String COLUMN_EndTime = "end_time";

	@Column(name = "update_time")
	private String updateTime;
	public static final String COLUMN_UpdateTime = "update_time";

}