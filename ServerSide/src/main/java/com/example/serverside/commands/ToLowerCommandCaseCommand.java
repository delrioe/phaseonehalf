package com.example.serverside.commands;

import com.example.serverside.ICommand;
import com.example.serverside.StringProcessor;
import com.example.stringprocessormodule.CommandData;
import com.example.stringprocessormodule.CommandType;
import com.example.stringprocessormodule.IStringProcessorProxy;

public class ToLowerCommandCaseCommand implements ICommand {

    private CommandData myData;

    public CommandData getMyData() {
        return myData;
    }

    public void setMyData(CommandData myData) {
        this.myData = myData;
    }

    public ToLowerCommandCaseCommand(CommandData myData_in){
        myData = myData_in;
    }



    @Override
    public CommandData execute() {
        IStringProcessorProxy stringProcessorProxy = new StringProcessor();
        CommandData response = new CommandData(CommandType.TOLOWER, stringProcessorProxy.toLower(myData.getData()));
        return response;


    }
}
