package io.cygnux.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.cygnux.repository.constant.ColumnDefinition;
import io.cygnux.repository.constant.CommonColumn;
import io.cygnux.repository.constant.EntityName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Instrument Entity
 * 
 * @author yellow013
 * 
 *
 */
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "cyg_instrument")
@Entity(name = EntityName.Instrument)
public final class CygInstrument {

	@Id
	@Column(name = CommonColumn.UID)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long uid;

	/**
	 * instrumentCode
	 */
	@Column(name = CommonColumn.INSTRUMENT_CODE, nullable = false)
	private String instrumentCode;

	/**
	 * fee
	 */
	@Column(name = "fee", columnDefinition = ColumnDefinition.DECIMAL_19_4)
	private double fee;

	/**
	 * tradeable
	 */
	@Column(name = "tradeable")
	private boolean tradeable;

}
