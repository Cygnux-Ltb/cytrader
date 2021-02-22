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

@Entity
@Table(name = "DataCleanInfo")
@Getter
@Setter
@Accessors(chain = true)
public final class DataCleanInfo {

	@Id
	@Column(name = "UID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uid;

	@Column(name = "Location")
	private String location;
	public static final String COLUMN_NAME_Location = "Location";

	// TradingDay date
	@Column(name = "TradingDay", columnDefinition = ColumnDefinition.DATE)
	private Date tradingDay;
	public static final String COLUMN_NAME_TradingDay = "TradingDay";

	@Column(name = "StartTime")
	private String startTime;
	public static final String COLUMN_NAME_StartTime = "StartTime";

	@Column(name = "EndTime")
	private String endTime;
	public static final String COLUMN_NAME_EndTime = "EndTime";

	@Column(name = "UpdateTime")
	private String updateTime;
	public static final String COLUMN_NAME_UpdateTime = "UpdateTime";

}
