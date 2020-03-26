package io.mercury.ctp.gateway.bean.config;

public class CtpConfigInfo {

	private String traderAddress;
	private String mdAddress;

	private String brokerId;
	private String investorId;
	private String accountId;
	private String userId;
	private String userProductInfo;
	private String password;
	private String authCode;

	private String reportIpAddr;
	private String reportMacAddr;

	private String tradingDay;
	private String currencyId;

//	public static final CtpConfigInfo newEmpty() {
//		return new CtpConfigInfo();
//	}

	public String getTraderAddress() {
		return traderAddress;
	}

	public String getReportIpAddr() {
		return reportIpAddr;
	}

	public String getReportMacAddr() {
		return reportMacAddr;
	}

	public String getMdAddress() {
		return mdAddress;
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

	public CtpConfigInfo setTraderAddress(String traderAddress) {
		this.traderAddress = traderAddress;
		return this;
	}

	public CtpConfigInfo setMdAddress(String mdAddress) {
		this.mdAddress = mdAddress;
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

	public CtpConfigInfo setUserProductInfo(String userProductInfo) {
		this.userProductInfo = userProductInfo;
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

	public CtpConfigInfo setTradingDay(String tradingDay) {
		this.tradingDay = tradingDay;
		return this;
	}

	public CtpConfigInfo setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
		return this;
	}

	public CtpConfigInfo setReportIpAddr(String reportIpAddr) {
		this.reportIpAddr = reportIpAddr;
		return this;
	}

	public CtpConfigInfo setReportMacAddr(String reportMacAddr) {
		this.reportMacAddr = reportMacAddr;
		return this;
	}

}
