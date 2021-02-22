package io.cygnus.restful.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.mercury.common.log.LogConfigurator;
import io.mercury.common.log.LogConfigurator.LogLevel;

@SpringBootApplication
public class CygnusServiceApplication {

	static {
		LogConfigurator.setFolder("cygnus");
		LogConfigurator.setFilename("cygnus-service");
		LogConfigurator.setLogLevel(LogLevel.INFO);
	}

	public static void main(String[] args) {
		SpringApplication.run(CygnusServiceApplication.class, args);
	}

}
