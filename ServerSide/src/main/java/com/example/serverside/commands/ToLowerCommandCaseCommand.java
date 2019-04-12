package com.example.serverside.commands;

import com.example.serverside.ICommand;
import com.example.serverside.StringProcessor;
import com.example.stringprocessormodule.IStringProcessorProxy;

public class ToLowerCommandCaseCommand implements ICommand {


    @Override
    public String execute(String message) {
        IStringProcessorProxy stringProcessorProxy = new StringProcessor();
        return stringProcessorProxy.toLower(message);
    }
}
