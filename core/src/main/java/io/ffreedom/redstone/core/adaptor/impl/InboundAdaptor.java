package io.ffreedom.redstone.core.adaptor;

import org.slf4j.Logger;

import io.ffreedom.common.log.CommonLoggerFactory;

public abstract class InboundAdaptor extends AbstractAdaptor {

	protected Logger logger = CommonLoggerFactory.getLogger(getClass());

	public InboundAdaptor(int adaptorId, String adaptorName) {
		super(adaptorId, adaptorName);
	}

	public abstract boolean activate();

	@Override
	public AdaptorType getAdaptorType() {
		return AdaptorType.Inbound;
	}

}
