package io.ffreedom.redstone.core.adaptor.impl;

public abstract class InboundAdaptor extends AbstractAdaptor {

	public InboundAdaptor(int adaptorId, String adaptorName) {
		super(adaptorId, adaptorName);
	}

	public abstract boolean activate();

	@Override
	public AdaptorType getAdaptorType() {
		return AdaptorType.Inbound;
	}

}
