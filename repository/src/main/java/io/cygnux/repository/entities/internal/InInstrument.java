package io.cygnux.repository.entities.internal;

import io.cygnux.repository.constant.ColumnDefinition;
import io.cygnux.repository.constant.CommonColumn;
import io.cygnux.repository.constant.EntityName;
import jakarta.persistence.*;
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
@Table(name = "in_instrument")
@Entity(name = EntityName.Instrument)
public final class InInstrument {

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
