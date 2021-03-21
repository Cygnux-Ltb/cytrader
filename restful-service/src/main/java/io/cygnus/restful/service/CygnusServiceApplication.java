package io.cygnus.restful.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.mercury.common.log.CommonLogConfigurator;
import io.mercury.common.log.CommonLogConfigurator.LogLevel;

@SpringBootApplication
public class CygnusServiceApplication {

	static {
		CommonLogConfigurator.setFolder("cygnus");
		CommonLogConfigurator.setFilename("cygnus-service");
		CommonLogConfigurator.setLogLevel(LogLevel.INFO);
	}

	public static void main(String[] args) {
		SpringApplication.run(CygnusServiceApplication.class, args);
	}

}
