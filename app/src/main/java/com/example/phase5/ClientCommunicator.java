package com.example.phase5;
import com.example.stringprocessormodule.CommandData;
import com.example.stringprocessormodule.CommandType;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class ClientCommunicator {


    public static String send (String serverHost, String serverPort, String directory, String data) {
        try {

            URL url = new URL("http://" + serverHost + ":" + serverPort + directory);

            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("GET");
            http.setDoOutput(true);
//            http.addRequestProperty("Accept", "application/json");
            http.connect();


            String output = new Gson().toJson(data);

            OutputStream reqBody = http.getOutputStream();
            writeString(output, reqBody);

            reqBody.close();


            if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {
                // The HTTP response status code indicates success,
                // so print a success message
                System.out.println("Route successfully claimed.");


                InputStream input = http.getInputStream();

                String in = readString(input);

                Gson gson = new Gson();

                String response = gson.fromJson(in, String.class);
                return response;
            }	else {
                // The HTTP response status code indicates an error
                // occurred, so print out the message from the HTTP response
                System.out.println("Get Reguster Request - ERROR: " + http.getResponseMessage());
                String myresponse = "Unable to connect to the server";
                return myresponse;
            }

        }catch (IOException e) {
            // An exception was thrown, so display the exception's stack trace
            e.printStackTrace();

            String myresponse = "An error occurred trying to connect to the server.";
            return myresponse;
        }
    }


    public static CommandData sendCommand (String serverHost, String serverPort, String directory, CommandData commandData) {
        try {

            URL url = new URL("http://" + serverHost + ":" + serverPort + directory);

            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("GET");
            http.setDoOutput(true);
            http.connect();


            String output = new Gson().toJson(commandData);

            OutputStream reqBody = http.getOutputStream();
            writeString(output, reqBody);

            reqBody.close();


            if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {
                // The HTTP response status code indicates success,
                // so print a success message
                System.out.println("Route successfully claimed.");


                InputStream input = http.getInputStream();

                String in = readString(input);

                Gson gson = new Gson();

                CommandData response = gson.fromJson(in, CommandData.class);
                return response;
            }	else {
                // The HTTP response status code indicates an error
                // occurred, so print out the message from the HTTP response
                System.out.println("Get Reguster Request - ERROR: " + http.getResponseMessage());
                CommandData response = new CommandData(CommandType.ERROR,  "Unable to connect to the server");
                return response;
            }

        }catch (IOException e) {
            // An exception was thrown, so display the exception's stack trace
            e.printStackTrace();

            CommandData myresponse = new CommandData(CommandType.ERROR, "An error occurred trying to connect to the server.");
            return myresponse;
        }
    }












    /*
        The readString method shows how to read a String from an InputStream.
    */
    private static String readString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);
        char[] buf = new char[1024];
        int len;
        while ((len = sr.read(buf)) > 0) {
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }

    /*
        The writeString method shows how to write a String to an OutputStream.
    */
    private static void writeString(String str, OutputStream os) throws IOException {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(str);
        sw.flush();
    }

}
