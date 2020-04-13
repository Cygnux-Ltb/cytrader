package io.mercury.ctp.gateway.bean;

public class CtpConfigInfo {

	private String traderAddr;
	private String mdAddr;

	private String appId;
	private String brokerId;
	private String investorId;
	private String accountId;
	private String userId;

	private String password;
	private String authCode;

	private String ipAddr;
	private String macAddr;

	private String tradingDay;
	private String currencyId;

	public CtpConfigInfo setTraderAddr(String traderAddr) {
		this.traderAddr = traderAddr;
		return this;
	}

	public CtpConfigInfo setMdAddr(String mdAddr) {
		this.mdAddr = mdAddr;
		return this;
	}

	public CtpConfigInfo setAppId(String appId) {
		this.appId = appId;
		return this;
	}

	public CtpConfigInfo setBrokerId(String brokerId) {
		this.brokerId = brokerId;
		return this;
	}

	public CtpConfigInfo setInvestorId(String investorId) {
		this.investorId = investorId;
		return this;
	}

	public CtpConfigInfo setAccountId(String accountId) {
		this.accountId = accountId;
		return this;
	}

	public CtpConfigInfo setUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public CtpConfigInfo setPassword(String password) {
		this.password = password;
		return this;
	}

	public CtpConfigInfo setAuthCode(String authCode) {
		this.authCode = authCode;
		return this;
	}

	public CtpConfigInfo setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
		return this;
	}

	public CtpConfigInfo setMacAddr(String macAddr) {
		this.macAddr = macAddr;
		return this;
	}

	public CtpConfigInfo setTradingDay(String tradingDay) {
		this.tradingDay = tradingDay;
		return this;
	}

	public CtpConfigInfo setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
		return this;
	}

	public String getTraderAddr() {
		return traderAddr;
	}

	public String getMdAddr() {
		return mdAddr;
	}

	public String getAppId() {
		return appId;
	}

	public String getBrokerId() {
		return brokerId;
	}

	public String getInvestorId() {
		return investorId;
	}

	public String getAccountId() {
		return accountId;
	}

	public String getUserId() {
		return userId;
	}

	public String getPassword() {
		return password;
	}

	public String getAuthCode() {
		return authCode;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public String getMacAddr() {
		return macAddr;
	}

	public String getTradingDay() {
		return tradingDay;
	}

	public String getCurrencyId() {
		return currencyId;
	}

}
