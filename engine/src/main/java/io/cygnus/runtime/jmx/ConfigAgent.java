package io.apollo.runtime.jmx;

import java.lang.management.ManagementFactory;

import javax.management.JMException;
import javax.management.MBeanServer;
import javax.management.ObjectName;

public class ConfigAgent {

	public static void main(String[] args) throws JMException, Exception {
		MBeanServer server = ManagementFactory.getPlatformMBeanServer();
		Class<ConfigAgent> cc = ConfigAgent.class;
		String name = cc.getName();
		String simpleName = cc.getSimpleName();

		ObjectName configName = new ObjectName(name + ":name=" + simpleName);
		// create mbean and register mbean
		Config config = new Config();
		server.registerMBean(config, configName);
		Thread.sleep(60 * 60 * 1000);

	}

}
