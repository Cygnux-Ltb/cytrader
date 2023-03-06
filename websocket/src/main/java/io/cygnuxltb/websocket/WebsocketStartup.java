package io.cygnuxltb.websocket;

import io.javalin.Javalin;
import io.mercury.common.log.Log4j2Configurator;

public class Main {

    static {
        Log4j2Configurator.setLogFolder("cytrader");
        Log4j2Configurator.setLogFilename("websocket");
        Log4j2Configurator.setLogLevel(Log4j2Configurator.LogLevel.INFO);
    }

    public static void main(String[] args) {
        var app = Javalin.create(/*config*/)
                .get("/", ctx -> ctx.result("Hello World"))
                .start(7070);
    }

}