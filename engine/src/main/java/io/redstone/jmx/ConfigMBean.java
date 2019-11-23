package io.ffreedom.redstone.jmx;

public interface ConfigMBean {

	String getName();

	void setName(String name);

	default void start() {
		for (;;) {
			System.out.println(getName());
			try {
				Thread.sleep(10 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	default void stop() {
		System.exit(0);
	}

}
