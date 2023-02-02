package io.cygnux.console;

import io.javalin.Javalin;

public class ConsoleMain {

    public static void main(String[] args) {
        var app = Javalin.create(/*config*/)
                .get("/", ctx -> ctx.result("Hello World"))
                .start(7070);

    }

}
