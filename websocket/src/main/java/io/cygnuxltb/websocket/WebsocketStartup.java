package io.cygnuxltb.websocket;

import io.mercury.common.log4j2.Log4j2Configurator;

import static spark.Spark.get;
import static spark.Spark.port;

public class WebsocketStartup {

    static {
        Log4j2Configurator.setLogFolder("cytrader");
        Log4j2Configurator.setLogFilename("websocket");
        Log4j2Configurator.setLogLevel(Log4j2Configurator.LogLevel.INFO);
    }

    public static void main(String[] args) {
        port(8089);
        get("/hello", (req, res) -> "Hello World");
    }

}