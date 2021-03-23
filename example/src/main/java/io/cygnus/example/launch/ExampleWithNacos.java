package io.cygnus.example.launch;

import io.mercury.common.datetime.DateTimeUtil;
import io.mercury.common.log.CommonLogConfigurator;
import io.mercury.common.log.CommonLogConfigurator.LogLevel;

public class ExampleWithNacos {

	static {
		CommonLogConfigurator.setFilename("cygnus-example-" + DateTimeUtil.datetimeOfSecond());
		CommonLogConfigurator.setLogLevel(LogLevel.INFO);
	}

	public static void main(String[] args) {

		

	}

}
