package io.redstone.core.adaptor.api;

public interface Adaptor {

	int getAdaptorId();

	String getAdaptorName();

	boolean close();
	
	AdaptorType getAdaptorType();

	public static enum AdaptorType {
		Inbound, Outbound
	}

}
