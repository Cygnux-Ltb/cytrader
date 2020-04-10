package io.mercury.ftdc.gateway.bak;

/**
 * @author Administrator
 *
 */
@Deprecated
public class CtpConfig {

	private String mdAddress;
	private String tdAddress;
	private String brokerId;
	private String userId;
	private String password;
	private String authCode;

	public String getMdAddress() {
		return mdAddress;
	}

	public CtpConfig setMdAddress(String mdAddress) {
		this.mdAddress = mdAddress;
		return this;
	}

	public String getTdAddress() {
		return tdAddress;
	}

	public CtpConfig setTdAddress(String tdAddress) {
		this.tdAddress = tdAddress;
		return this;
	}

	public String getBrokerId() {
		return brokerId;
	}

	public CtpConfig setBrokerId(String brokerId) {
		this.brokerId = brokerId;
		return this;
	}

	public String getUserId() {
		return userId;
	}

	public CtpConfig setUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public CtpConfig setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getAuthCode() {
		return authCode;
	}

	public CtpConfig setAuthCode(String authCode) {
		this.authCode = authCode;
		return this;
	}

}
