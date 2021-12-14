package io.cygnus.example.launch;

import io.mercury.common.datetime.DateTimeUtil;
import io.mercury.common.log.LogConfigurator;
import io.mercury.common.log.LogConfigurator.LogLevel;

public class ExampleLauncher {

	static {
		LogConfigurator.setLogFilename("example-" + DateTimeUtil.datetimeOfSecond());
		LogConfigurator.setLogLevel(LogLevel.INFO);
	}

	public static void main(String[] args) {
		// 此处可以以多种方式读取配置
		
	}

}
