package io.cygnus.example.launch;

import io.mercury.common.datetime.DateTimeUtil;
import io.mercury.common.log.LogConfigurator;
import io.mercury.common.log.LogConfigurator.LogLevel;

public class ExampleWithNacos {

	static {
		LogConfigurator.setLogFilename("cygnus-example-" + DateTimeUtil.datetimeOfSecond());
		LogConfigurator.setLogLevel(LogLevel.INFO);
	}

	public static void main(String[] args) {

	}

}
