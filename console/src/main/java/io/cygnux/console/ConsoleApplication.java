package io.cygnux.console;


import io.mercury.common.log.Log4j2Configurator;
import io.mercury.common.log.Log4j2Configurator.LogLevel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsoleApplication {

    static {
        Log4j2Configurator.setLogFolder("cygnux");
        Log4j2Configurator.setLogFilename("console");
        Log4j2Configurator.setLogLevel(LogLevel.INFO);
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsoleApplication.class, args);
    }

}
