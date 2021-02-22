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

@Entity
@Table(name = "CygInitConfig")
@Getter
@Setter
@Accessors(chain = true)
public final class CygInitConfig {

	@Id
	@Column(name = "UID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uid;

	// CygID int
	@Column(name = "CygID")
	private Integer cygId;
	public static final String COLUMN_NAME_CygID = "CygID";

	// FieldName varchar 63
	@Column(name = "FieldName")
	private String fieldName;
	public static final String COLUMN_NAME_FieldName = "FieldName";

	// FieldValue varchar 63
	@Column(name = "FieldValue")
	private String fieldValue;
	public static final String COLUMN_NAME_FieldValue = "FieldValue";

	// FieldType varchar 15
	@Column(name = "FieldType")
	private String fieldType;
	public static final String COLUMN_NAME_FieldType = "FieldType";

}
