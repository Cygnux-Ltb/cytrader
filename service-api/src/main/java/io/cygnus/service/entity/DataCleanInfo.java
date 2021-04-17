package io.cygnus.service.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.cygnus.service.entity.base.ColumnDefinition;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 
 * @author yellow013
 *
 */
@Entity
@Table(name = "DataCleanInfo")
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
	public static final String COLUMN_NAME_Location = "Location";

	// TradingDay date
	@Column(name = "trading_day", columnDefinition = ColumnDefinition.DATE)
	private Date tradingDay;
	public static final String COLUMN_NAME_TradingDay = "TradingDay";

	@Column(name = "start_time")
	private String startTime;
	public static final String COLUMN_NAME_StartTime = "StartTime";

	@Column(name = "end_time")
	private String endTime;
	public static final String COLUMN_NAME_EndTime = "EndTime";

	@Column(name = "update_time")
	private String updateTime;
	public static final String COLUMN_NAME_UpdateTime = "UpdateTime";

}
