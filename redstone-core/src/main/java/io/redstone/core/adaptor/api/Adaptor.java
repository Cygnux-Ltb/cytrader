package io.redstone.core.adaptor.api;

public interface Adaptor {

	int adaptorId();

	String adaptorName();

	boolean close();
	
	AdaptorType adaptorType();

	public static enum AdaptorType {
		Inbound, Outbound
	}

}
