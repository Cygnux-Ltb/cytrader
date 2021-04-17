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

/**
 * 
 * @author yellow013
 *
 */
@Entity
@Table(name = "CygMqConfig")
@Getter
@Setter
@Accessors(chain = true)
public class CygMqConfig {

	@Id
	@Column(name = "uid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uid;

	// CygID int
	@Column(name = "cyg_id")
	private Integer cygId;
	public static final String COLUMN_NAME_CygID = "CygID";

	// MqHost varchar 31
	@Column(name = "cyg_mq_host")
	private String CygMqHost;
	public static final String COLUMN_NAME_CygMqHost = "CygMqHost";

	// MqPort int
	@Column(name = "cyg_mq_port")
	private Integer CygMqPort;
	public static final String COLUMN_NAME_CygMqPort = "CygMqPort";

	// MqHost varchar 31
	@Column(name = "cyg_mq_username")
	private String CygMqUsername;
	public static final String COLUMN_NAME_CygMqUsername = "CygMqUsername";

	// MqHost varchar 31
	@Column(name = "cyg_mq_password")
	private String CygMqPassword;
	public static final String COLUMN_NAME_CygMqPassword = "CygMqPassword";

	// MqNameCygToServer varchar 31
	@Column(name = "queue_name_cyg_to_server")
	private String queueNameCygToServer;
	public static final String COLUMN_NAME_QueueNameCygToServer = "QueueNameCygToServer";

	// MqNameServerToCyg varchar 31
	@Column(name = "queue_name_server_to_cyg")
	private String queueNameServerToCyg;
	public static final String COLUMN_NAME_QueueNameServerToCyg = "QueueNameServerToCyg";

	// MqHost varchar 31
	@Column(name = "server_mq_host")
	private String serverMqHost;
	public static final String COLUMN_NAME_ServerMqHost = "ServerMqHost";

	// MqPort int
	@Column(name = "server_mq_port")
	private Integer serverMqPort;
	public static final String COLUMN_NAME_ServerMqPort = "ServerMqPort";

	// MqHost varchar 31
	@Column(name = "server_mq_username")
	private String serverMqUsername;
	public static final String COLUMN_NAME_ServerMqUsername = "ServerMqUsername";

	// MqHost varchar 31
	@Column(name = "server_mq_password")
	private String serverMqPassword;
	public static final String COLUMN_NAME_ServerMqPassword = "ServerMqPassword";

	// MqNameCygToServer varchar 31
	@Column(name = "server_inbox")
	private String serverInbox;
	public static final String COLUMN_NAME_ServerInbox = "ServerInbox";

	// MqNameServerToCyg varchar 31
	@Column(name = "server_outbox")
	private String serverOutbox;
	public static final String COLUMN_NAME_ServerOutbox = "ServerOutbox";

}
