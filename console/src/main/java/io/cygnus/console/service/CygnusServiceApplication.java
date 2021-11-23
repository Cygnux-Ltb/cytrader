package io.cygnus.console.service;

import static io.mercury.common.log.LogConfigurator.setLogFilename;
import static io.mercury.common.log.LogConfigurator.setLogFolder;
import static io.mercury.common.log.LogConfigurator.setLogLevel;
import static io.mercury.common.log.LogConfigurator.LogLevel.INFO;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CygnusServiceApplication {

	static {
		setLogFolder("cygnus");
		setLogFilename("cygnus-service");
		setLogLevel(INFO);
	}

	public static void main(String[] args) {
		SpringApplication.run(CygnusServiceApplication.class, args);
	}

}
