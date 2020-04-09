package io.mercury.ftdc.gateway.bean;

public class FtdcConfigInfo {

	private String traderAddr;
	private String mdAddr;

	private String brokerId;
	private String investorId;
	private String accountId;
	private String userId;
	private String userProductInfo;
	private String authCode;
	private String password;

	private String reportIpAddr;
	private String reportMacAddr;

	private String tradingDay;
	private String currencyId;

	public String getTraderAddr() {
		return traderAddr;
	}

	public String getReportIpAddr() {
		return reportIpAddr;
	}

	public String getReportMacAddr() {
		return reportMacAddr;
	}

	public String getMdAddr() {
		return mdAddr;
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

	public String getUserProductInfo() {
		return userProductInfo;
	}

	public String getPassword() {
		return password;
	}

	public String getAuthCode() {
		return authCode;
	}

	public String getTradingDay() {
		return tradingDay;
	}

	public String getCurrencyId() {
		return currencyId;
	}

	public FtdcConfigInfo setTraderAddr(String traderAddr) {
		this.traderAddr = traderAddr;
		return this;
	}

	public FtdcConfigInfo setMdAddr(String mdAddr) {
		this.mdAddr = mdAddr;
		return this;
	}

	public FtdcConfigInfo setBrokerId(String brokerId) {
		this.brokerId = brokerId;
		return this;
	}

	public FtdcConfigInfo setInvestorId(String investorId) {
		this.investorId = investorId;
		return this;
	}

	public FtdcConfigInfo setAccountId(String accountId) {
		this.accountId = accountId;
		return this;
	}

	public FtdcConfigInfo setUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public FtdcConfigInfo setUserProductInfo(String userProductInfo) {
		this.userProductInfo = userProductInfo;
		return this;
	}

	public FtdcConfigInfo setPassword(String password) {
		this.password = password;
		return this;
	}

	public FtdcConfigInfo setAuthCode(String authCode) {
		this.authCode = authCode;
		return this;
	}

	public FtdcConfigInfo setTradingDay(String tradingDay) {
		this.tradingDay = tradingDay;
		return this;
	}

	public FtdcConfigInfo setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
		return this;
	}

	public FtdcConfigInfo setReportIpAddr(String reportIpAddr) {
		this.reportIpAddr = reportIpAddr;
		return this;
	}

	public FtdcConfigInfo setReportMacAddr(String reportMacAddr) {
		this.reportMacAddr = reportMacAddr;
		return this;
	}

}
