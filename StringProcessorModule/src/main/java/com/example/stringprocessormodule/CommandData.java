package com.example.stringprocessormodule;

public class CommandData {

    public void setType(CommandType type) {
        this.type = type;
    }

    public void setData(String data) {
        this.data = data;
    }

    private CommandType type;
    private String data;

    public CommandData (CommandType type_in, String data_in){
        type = type_in;
        data = data_in;
    }

    public CommandType getType() {
        return type;
    }

    public String getData() {
        return data;
    }
}
