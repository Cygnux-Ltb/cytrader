package io.mercury.gateway.ctp.bean.config;

public class CtpConnectionInfo {

	private String traderAddress;
	private String mdAddress;

	private String brokerId;
	private String investorId;
	private String accountId;
	private String userId;
	private String userProductInfo;
	private String password;
	private String authCode;

	private String tradingDay;
	private String currencyId;

	public static final CtpConnectionInfo newEmpty() {
		return new CtpConnectionInfo();
	}

	public String getTraderAddress() {
		return traderAddress;
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

	public CtpConnectionInfo setTraderAddress(String traderAddress) {
		this.traderAddress = traderAddress;
		return this;
	}

	public CtpConnectionInfo setMdAddress(String mdAddress) {
		this.mdAddress = mdAddress;
		return this;
	}

	public CtpConnectionInfo setBrokerId(String brokerId) {
		this.brokerId = brokerId;
		return this;
	}

	public CtpConnectionInfo setInvestorId(String investorId) {
		this.investorId = investorId;
		return this;
	}

	public CtpConnectionInfo setAccountId(String accountId) {
		this.accountId = accountId;
		return this;
	}

	public CtpConnectionInfo setUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public CtpConnectionInfo setUserProductInfo(String userProductInfo) {
		this.userProductInfo = userProductInfo;
		return this;
	}

	public CtpConnectionInfo setPassword(String password) {
		this.password = password;
		return this;
	}

	public CtpConnectionInfo setAuthCode(String authCode) {
		this.authCode = authCode;
		return this;
	}

	public CtpConnectionInfo setTradingDay(String tradingDay) {
		this.tradingDay = tradingDay;
		return this;
	}

	public CtpConnectionInfo setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
		return this;
	}

}
