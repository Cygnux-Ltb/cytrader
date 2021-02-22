package io.cygnus.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

// Strategy
@Entity
@Table(name = "Signal")
@Getter
@Setter
@Accessors(chain = true)
public final class Signal {

	@Id
	@Column(name = "UID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uid;

	// SignalID int
	@Column(name = "SignalID")
	private Integer signalId;
	public static final String COLUMN_NAME_SignalID = "SignalID";

	// SignalName varchar 15
	@Column(name = "SignalName")
	private String signalName;
	public static final String COLUMN_NAME_SignalName = "SignalName";

}
