package io.mercury.ftdc.gateway.converter;

import java.util.function.Function;

import ctp.thostapi.CThostFtdcTradingAccountField;
import io.mercury.ftdc.gateway.bean.FtdcTradingAccount;

public class FromCThostFtdcTradingAccount implements Function<CThostFtdcTradingAccountField, FtdcTradingAccount> {

	@Override
	public FtdcTradingAccount apply(CThostFtdcTradingAccountField from) {
		return new FtdcTradingAccount()

				.setBrokerID(from.getBrokerID())

				.setAccountID(from.getAccountID())

				.setPreMortgage(from.getPreMortgage())

				.setPreCredit(from.getPreCredit())

				.setPreDeposit(from.getPreDeposit())

				.setPreBalance(from.getPreBalance())

				.setPreMargin(from.getPreMargin())

				.setInterestBase(from.getInterestBase())

				.setInterest(from.getInterest())

				.setDeposit(from.getDeposit())

				.setWithdraw(from.getWithdraw())

				.setFrozenMargin(from.getFrozenMargin())

				.setFrozenCash(from.getFrozenCash())

				.setFrozenCommission(from.getFrozenCommission())

				.setCurrMargin(from.getCurrMargin())

				.setCashIn(from.getCashIn())

				.setCommission(from.getCommission())

				.setCloseProfit(from.getCloseProfit())

				.setPositionProfit(from.getPositionProfit())

				.setBalance(from.getBalance())

				.setAvailable(from.getAvailable())

				.setWithdrawQuota(from.getWithdrawQuota())

				.setReserve(from.getReserve())

				.setTradingDay(from.getTradingDay())

				.setSettlementID(from.getSettlementID())

				.setCredit(from.getCredit())

				.setMortgage(from.getMortgage())

				.setExchangeMargin(from.getExchangeMargin())

				.setDeliveryMargin(from.getDeliveryMargin())

				.setExchangeDeliveryMargin(from.getExchangeDeliveryMargin())

				.setReserveBalance(from.getReserveBalance())

				.setCurrencyID(from.getCurrencyID())

				.setPreFundMortgageIn(from.getPreFundMortgageIn())

				.setPreFundMortgageOut(from.getPreFundMortgageOut())

				.setFundMortgageIn(from.getFundMortgageIn())

				.setFundMortgageOut(from.getFundMortgageOut())

				.setFundMortgageAvailable(from.getFundMortgageAvailable())

				.setMortgageableFund(from.getMortgageableFund())

				.setSpecProductMargin(from.getSpecProductMargin())

				.setSpecProductFrozenMargin(from.getSpecProductFrozenMargin())

				.setSpecProductCommission(from.getSpecProductCommission())

				.setSpecProductFrozenCommission(from.getSpecProductFrozenCommission())

				.setSpecProductPositionProfit(from.getSpecProductPositionProfit())

				.setSpecProductCloseProfit(from.getSpecProductCloseProfit())

				.setSpecProductPositionProfitByAlg(from.getSpecProductPositionProfitByAlg())

				.setSpecProductExchangeMargin(from.getSpecProductExchangeMargin())

				.setBizType(from.getBizType())

				.setFrozenSwap(from.getFrozenSwap())

				.setRemainSwap(from.getRemainSwap());
	}

}
