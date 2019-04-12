package com.example.serverside.commands;

import com.example.serverside.ICommand;
import com.example.serverside.StringProcessor;
import com.example.stringprocessormodule.CommandData;
import com.example.stringprocessormodule.CommandType;
import com.example.stringprocessormodule.IStringProcessorProxy;

public class TrimCommand implements ICommand {

    public TrimCommand (CommandData data){
        myData = data;
    }


    public CommandData getMyData() {
        return myData;
    }

    public void setMyData(CommandData myData) {
        this.myData = myData;
    }

    private CommandData myData;


    @Override
    public CommandData execute() {
        IStringProcessorProxy processorProxy = new StringProcessor();

        CommandData response = new CommandData(CommandType.TRIM, processorProxy.trim(myData.getData()));
        return response;


    }
}
