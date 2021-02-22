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
@Table(name = "CygMqConfig")
@Getter
@Setter
@Accessors(chain = true)
public class CygMqConfig {

	@Id
	@Column(name = "UID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uid;

	// CygID int
	@Column(name = "CygID")
	private Integer cygId;
	public static final String COLUMN_NAME_CygID = "CygID";

	// MqHost varchar 31
	@Column(name = "CygMqHost")
	private String CygMqHost;
	public static final String COLUMN_NAME_CygMqHost = "CygMqHost";

	// MqPort int
	@Column(name = "CygMqPort")
	private Integer CygMqPort;
	public static final String COLUMN_NAME_CygMqPort = "CygMqPort";

	// MqHost varchar 31
	@Column(name = "CygMqUsername")
	private String CygMqUsername;
	public static final String COLUMN_NAME_CygMqUsername = "CygMqUsername";

	// MqHost varchar 31
	@Column(name = "CygMqPassword")
	private String CygMqPassword;
	public static final String COLUMN_NAME_CygMqPassword = "CygMqPassword";

	// MqNameCygToServer varchar 31
	@Column(name = "QueueNameCygToServer")
	private String queueNameCygToServer;
	public static final String COLUMN_NAME_QueueNameCygToServer = "QueueNameCygToServer";

	// MqNameServerToCyg varchar 31
	@Column(name = "QueueNameServerToCyg")
	private String queueNameServerToCyg;
	public static final String COLUMN_NAME_QueueNameServerToCyg = "QueueNameServerToCyg";

	// MqHost varchar 31
	@Column(name = "ServerMqHost")
	private String serverMqHost;
	public static final String COLUMN_NAME_ServerMqHost = "ServerMqHost";

	// MqPort int
	@Column(name = "ServerMqPort")
	private Integer serverMqPort;
	public static final String COLUMN_NAME_ServerMqPort = "ServerMqPort";

	// MqHost varchar 31
	@Column(name = "ServerMqUsername")
	private String serverMqUsername;
	public static final String COLUMN_NAME_ServerMqUsername = "ServerMqUsername";

	// MqHost varchar 31
	@Column(name = "ServerMqPassword")
	private String serverMqPassword;
	public static final String COLUMN_NAME_ServerMqPassword = "ServerMqPassword";

	// MqNameCygToServer varchar 31
	@Column(name = "ServerInbox")
	private String serverInbox;
	public static final String COLUMN_NAME_ServerInbox = "ServerInbox";

	// MqNameServerToCyg varchar 31
	@Column(name = "ServerOutbox")
	private String serverOutbox;
	public static final String COLUMN_NAME_ServerOutbox = "ServerOutbox";

}
