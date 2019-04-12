package com.example.serverside;

import com.example.serverside.handlers.ParseDoubleHandler;
import com.example.serverside.handlers.ToLowerCaseHandler;
import com.example.serverside.handlers.TrimHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {


    private static final int MAX_WAITING_CONNECTIONS = 12;

    private HttpServer server;



    private void run(){
        try
        {
            server = HttpServer.create(
                    new InetSocketAddress(Integer.parseInt("8080")),
                    MAX_WAITING_CONNECTIONS);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return;
        }

        server.setExecutor(null);
        server.createContext("/command", new ExecCommandHandler());
        server.createContext("/toLower", new ToLowerCaseHandler());
        server.createContext("/trim", new TrimHandler());
        server.createContext("/parseDouble", new ParseDoubleHandler());
        server.start();
        System.out.println("Server started");
    }










    public static void main (String args[]){
        new Server().run();
    }
}
