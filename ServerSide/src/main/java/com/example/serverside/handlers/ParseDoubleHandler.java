package com.example.serverside.handlers;

import com.example.serverside.StringProcessor;
import com.example.serverside.handlers.HandlerBase;
import com.example.stringprocessormodule.IStringProcessorProxy;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

public class ParseDoubleHandler extends HandlerBase {
   @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        System.out.println("I AM HERE IN TRIM");
        try {
            if (httpExchange.getRequestMethod().toLowerCase().equals("post")) {
                InputStream input = httpExchange.getRequestBody();
                String in = readString(input);

                Gson gson = new Gson();

                String message = gson.fromJson(in, String.class);
                IStringProcessorProxy processorProxy = new StringProcessor();
                String messageInLower = processorProxy.parseDouble(message);


                String output = new Gson().toJson(messageInLower);
                httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);


                OutputStream respBody = httpExchange.getResponseBody();

                writeString(output, respBody);
                httpExchange.getResponseBody().close();
            }
        }
        catch (IOException e)
        {
            httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            httpExchange.getResponseBody().close();

            e.printStackTrace();
        }

    }


    private String readString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);
        char[] buf = new char[1024];
        int len;
        while ((len = sr.read(buf)) > 0) {
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }

    private void writeString(String str, OutputStream os) throws IOException
    {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(str);
        sw.flush();
    }
}
