package com.example.serverside.commands;

import com.example.serverside.ICommand;
import com.example.serverside.StringProcessor;
import com.example.stringprocessormodule.CommandData;
import com.example.stringprocessormodule.CommandType;
import com.example.stringprocessormodule.IStringProcessorProxy;

public class ParseDoubleCommand implements ICommand {

    public ParseDoubleCommand (CommandData data){
        myData = data;
    }

    private CommandData myData;

    public CommandData getMyData() {
        return myData;
    }

    public void setMyData(CommandData myData) {
        this.myData = myData;
    }

    @Override
    public CommandData execute() {
        IStringProcessorProxy stringProcessorProxy = new StringProcessor();
        CommandData response = new CommandData(CommandType.PARSEDOUBLE, stringProcessorProxy.parseDouble(myData.getData()));
        return response;
    }
}
