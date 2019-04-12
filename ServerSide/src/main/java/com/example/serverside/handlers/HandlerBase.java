package com.example.serverside;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class HandlerBase implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {}
}
