package io.mercury.ftdc.adaptor.converter;

import java.util.function.Function;

import io.mercury.ftdc.gateway.bean.FtdcOrder;
import io.mercury.redstone.core.order.structure.OrdReport;

public class FtdcOrderConverter implements Function<FtdcOrder, OrdReport> {

	@Override
	public OrdReport apply(FtdcOrder ftdcOrder) {
		// TODO Auto-generated method stub
		
		new OrdReport(ordSysId).setOrderRef(orderRef);
		
		return null;
	}

}
