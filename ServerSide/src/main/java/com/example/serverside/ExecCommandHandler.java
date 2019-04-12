package com.example.serverside;

import com.example.serverside.commands.ParseDoubleCommand;
import com.example.serverside.commands.ToLowerCommandCaseCommand;
import com.example.serverside.commands.TrimCommand;
import com.example.serverside.handlers.HandlerBase;
import com.example.stringprocessormodule.CommandData;
import com.example.stringprocessormodule.CommandType;
import com.example.stringprocessormodule.IStringProcessorProxy;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

public class ExecCommandHandler extends HandlerBase {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        System.out.println("I AM HERE IN EXECCOMMANDHANDLER");
        try {
            if (httpExchange.getRequestMethod().toLowerCase().equals("post")) {
                InputStream input = httpExchange.getRequestBody();
                String in = readString(input);

                CommandData response;
                ICommand myCommand;

                Gson gson = new Gson();

                CommandData message = gson.fromJson(in, CommandData.class);

                switch (message.getType()){
                    case TOLOWER:
                        myCommand = new ToLowerCommandCaseCommand(message);
                        response = myCommand.execute();
                        break;
                    case TRIM:
                        myCommand = new TrimCommand(message);
                        response = myCommand.execute();
                        break;
                    case PARSEDOUBLE:
                        myCommand = new ParseDoubleCommand(message);
                        response = myCommand.execute();
                        break;
                    default:
                        response = new ToLowerCommandCaseCommand(message).execute();
                        System.out.println("This is a case I need to handle. ");
                }


                String output = new Gson().toJson(response);
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
