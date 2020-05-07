package io.mercury.ftdc.gateway;

public class FtdcConfig {

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

	public FtdcConfig setTraderAddr(String traderAddr) {
		this.traderAddr = traderAddr;
		return this;
	}

	public FtdcConfig setMdAddr(String mdAddr) {
		this.mdAddr = mdAddr;
		return this;
	}

	public FtdcConfig setAppId(String appId) {
		this.appId = appId;
		return this;
	}

	public FtdcConfig setBrokerId(String brokerId) {
		this.brokerId = brokerId;
		return this;
	}

	public FtdcConfig setInvestorId(String investorId) {
		this.investorId = investorId;
		return this;
	}

	public FtdcConfig setAccountId(String accountId) {
		this.accountId = accountId;
		return this;
	}

	public FtdcConfig setUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public FtdcConfig setPassword(String password) {
		this.password = password;
		return this;
	}

	public FtdcConfig setAuthCode(String authCode) {
		this.authCode = authCode;
		return this;
	}

	public FtdcConfig setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
		return this;
	}

	public FtdcConfig setMacAddr(String macAddr) {
		this.macAddr = macAddr;
		return this;
	}

	public FtdcConfig setTradingDay(String tradingDay) {
		this.tradingDay = tradingDay;
		return this;
	}

	public FtdcConfig setCurrencyId(String currencyId) {
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
