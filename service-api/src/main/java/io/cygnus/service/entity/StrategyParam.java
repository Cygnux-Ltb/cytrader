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

// StrategyParam
@Entity
@Table(name = "StrategyParam")
@Getter
@Setter
@Accessors(chain = true)
public final class StrategyParam {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;

	// StrategyID int
	@Column(name = "StrategyID")
	private Integer strategyId;
	public static final String COLUMN_NAME_StrategyID = "StrategyID";

	// Name varchar 63
	@Column(name = "Name")
	private String name;
	public static final String COLUMN_NAME_Name = "Name";

	// ValueType char
	@Column(name = "ValueType")
	private String valueType;
	public static final String COLUMN_NAME_ValueType = "ValueType";

	// ValueInt int
	@Column(name = "ValueInt")
	private Integer valueInt;
	public static final String COLUMN_NAME_ValueInt = "ValueInt";

	// ValueDouble double 19_4
	@Column(name = "ValueDouble", columnDefinition = ColumnDefinition.DECIMAL_19_4)
	private Double valueDouble;
	public static final String COLUMN_NAME_ValueDouble = "ValueDouble";

	// ValueDate date
	@Column(name = "ValueDate", columnDefinition = ColumnDefinition.DATE)
	private Date valueDate;
	public static final String COLUMN_NAME_ValueDate = "ValueDate";

	// ValueString varchar 63
	@Column(name = "ValueString")
	private String valueString;
	public static final String COLUMN_NAME_ValueString = "ValueString";

	public boolean isSame(StrategyDefaultParam defaultParam) {
		return this.name.equals(defaultParam.getName());
	}

	public StrategyParam setValues4DefaultParam(StrategyDefaultParam defaultParam) {
		this.setName(defaultParam.getName());
		this.setValueType(defaultParam.getValueType());
		this.setValueDate(defaultParam.getValueDate());
		this.setValueDouble(defaultParam.getValueDouble());
		this.setValueInt(defaultParam.getValueInt());
		this.setValueString(defaultParam.getValueString());
		return this;
	}

}
