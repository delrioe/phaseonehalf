package com.example.phase5;

import com.example.stringprocessormodule.CommandData;
import com.example.stringprocessormodule.CommandType;
import com.example.stringprocessormodule.IStringProcessorProxy;

public class StringProcessorProxy implements IStringProcessorProxy {

    private StringProcessorProxy(){}
    private static StringProcessorProxy instance = new StringProcessorProxy();

    public static StringProcessorProxy getInstance(){
        return instance;
    }

    private ClientCommunicator mCommunicator;
    private static String ip;
    private static String port;
    private static String directory;
    private static Boolean usingCommand;

    public static void setIPandPort(Boolean withCommand_in, String ip_in, String port_in, String directory_in){
       ip = ip_in;
       port = port_in;
       directory = directory_in;
       usingCommand = withCommand_in;
    }


    @Override
    public String toLower(String s) {
        mCommunicator = new ClientCommunicator();
        if (usingCommand) {

            CommandData command = new CommandData(CommandType.TOLOWER, s);
            CommandData response = mCommunicator.sendCommand(ip, port, "/command", command);
            return response.getData();

        }

        String messageBack = mCommunicator.send(ip, port, directory, s);
        return  messageBack;

    }

    @Override
    public String trim(String s) {
        mCommunicator = new ClientCommunicator();
        if (usingCommand) {

            CommandData command = new CommandData(CommandType.TRIM, s);
            CommandData response = mCommunicator.sendCommand(ip, port, "/command", command);
            return response.getData();

        }

        String messageBack = mCommunicator.send(ip, port, directory, s);
        return  messageBack;


    }

    @Override
    public String parseDouble(String s) {
        mCommunicator = new ClientCommunicator();
        if (usingCommand) {

            CommandData command = new CommandData(CommandType.PARSEDOUBLE, s);
            CommandData response = mCommunicator.sendCommand(ip, port, "/command", command);
            return response.getData();

        }

        String messageBack = mCommunicator.send(ip, port, directory, s);
        return  messageBack;
    }
}

