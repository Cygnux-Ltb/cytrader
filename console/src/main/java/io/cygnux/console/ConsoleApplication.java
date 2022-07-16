package io.cygnux.console;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.mercury.common.log.Log4j2Configurator;
import io.mercury.common.log.Log4j2Configurator.LogLevel;

@SpringBootApplication
public class ConsoleApplication {

    static {
        Log4j2Configurator.setLogFolder("console");
        Log4j2Configurator.setLogFilename("service");
        Log4j2Configurator.setLogLevel(LogLevel.INFO);
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsoleApplication.class, args);
    }

}
