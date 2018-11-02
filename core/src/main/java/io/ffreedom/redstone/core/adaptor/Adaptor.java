package io.ffreedom.redstone.core.adaptor;

public interface Adaptor {

	void init();

	int getAdaptorId();

	String getAdaptorName();

	boolean close();
	
}
