package io.ffreedom.redstone.core.adaptor;

public interface Adaptor {

	void init();

	int getAdaptorId();

	String getAdaptorName();

	boolean close();
	
	AdaptorType getAdaptorType();

	public static enum AdaptorType {
		Inbound, Outbound
	}

}
