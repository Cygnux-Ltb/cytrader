package io.redstone.core.adaptor.base;

public abstract class InboundAdaptor extends AbstractAdaptor {

	public InboundAdaptor(int adaptorId, String adaptorName) {
		super(adaptorId, adaptorName);
	}

	public abstract boolean activate();

	@Override
	public AdaptorType adaptorType() {
		return AdaptorType.Inbound;
	}

}
