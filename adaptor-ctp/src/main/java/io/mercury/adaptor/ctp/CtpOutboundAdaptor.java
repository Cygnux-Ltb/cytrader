package io.mercury.adaptor.ctp;

import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;

import ctp.thostapi.CThostFtdcInputOrderActionField;
import ctp.thostapi.CThostFtdcInputOrderField;
import io.mercury.adaptor.ctp.exception.OrderRefNotFoundException;
import io.mercury.adaptor.ctp.utils.CtpOrderRefGenerate;
import io.mercury.adaptor.ctp.utils.CtpOrderRefKeeper;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.gateway.ctp.CtpGateway;
import io.redstone.core.account.InvestorAccount;
import io.redstone.core.adaptor.base.OutboundAdaptor;
import io.redstone.core.adaptor.dto.SubscribeMarketData;
import io.redstone.core.order.api.Order;
import io.redstone.core.order.impl.ChildOrder;
import io.redstone.engine.actor.AppGlobalStatus;

public class CtpOutboundAdaptor extends OutboundAdaptor {

	private final Logger logger = CommonLoggerFactory.getLogger(getClass());

	private Function<Order, CThostFtdcInputOrderField> newOrderFunction = order -> {
		int orderRef = CtpOrderRefGenerate.next(AppGlobalStatus.appId());
		char direction;
		switch (order.ordSide().direction()) {
		case Long:
			direction = 0;
			break;
		case Short:
			direction = 1;
			break;
		default:
			throw new RuntimeException(order.ordSide() + " does not exist.");
		}
		CThostFtdcInputOrderField inputOrderField = new CThostFtdcInputOrderField();
		inputOrderField.setInstrumentID(order.instrument().code());
		inputOrderField.setOrderRef(Integer.toString(orderRef));
		inputOrderField.setDirection(direction);
		inputOrderField.setLimitPrice(order.ordPrice().offerPrice());
		inputOrderField.setVolumeTotalOriginal(Double.valueOf(order.ordQty().offerQty()).intValue());
		return inputOrderField;
	};

	private Function<Order, CThostFtdcInputOrderActionField> cancelOrderFunction = order -> {

		CThostFtdcInputOrderActionField inputOrderActionField = new CThostFtdcInputOrderActionField();
		return inputOrderActionField;

	};

	private CtpGateway gateway;

	public CtpOutboundAdaptor(int adaptorId, String adaptorName, CtpGateway gateway) {
		super(adaptorId, adaptorName);
		this.gateway = gateway;
	}

	@Override
	public boolean close() {
		return false;
	}

	@Override
	public boolean newOredr(ChildOrder order) {
		try {
			CThostFtdcInputOrderField ctpNewOrder = newOrderFunction.apply(order);
			CtpOrderRefKeeper.put(ctpNewOrder.getOrderRef(), order.ordSysId());
			gateway.newOrder(ctpNewOrder);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean cancelOrder(ChildOrder order) {
		try {
			CThostFtdcInputOrderActionField ctpCancelOrder = cancelOrderFunction.apply(order);
			String orderRef = CtpOrderRefKeeper.getOrderRef(order.ordSysId());
			ctpCancelOrder.setOrderRef(orderRef);
			gateway.cancelOrder(ctpCancelOrder);
			return true;
		} catch (OrderRefNotFoundException e) {
			logger.error(e.getMessage());
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean subscribeMarketData(SubscribeMarketData subscribeMarketData) {
		try {
			gateway.subscribeMarketData(subscribeMarketData.getInstrumentSet().stream()
					.map(instrument -> instrument.code()).collect(Collectors.toSet()));
			return true;
		} catch (Exception e) {
			logger.error("subscribeMarketData throw {}", e.getClass().getSimpleName(), e);
			return false;
		}
	}

	@Override
	public boolean queryPositions(InvestorAccount account) {
		// TODO
		return false;
	}

	@Override
	public boolean queryBalance(InvestorAccount account) {
		// TODO
		return false;
	}

}
