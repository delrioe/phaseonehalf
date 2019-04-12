package com.example.serverside;

import com.example.stringprocessormodule.IStringProcessorProxy;

public class StringProcessor implements IStringProcessorProxy {
    @Override
    public String toLower(String s) {
        return s.toLowerCase();
    }

    @Override
    public String trim(String s) {
        return s.trim();
    }

    @Override
    public String parseDouble(String str) {
        try {


            // returns the double value
            // represented by the string argument
            double val = Double.parseDouble(str);

            // prints the double value
            System.out.println("Value = " + val);
            return String.valueOf(val);
        }
        catch (Exception e) {
            System.out.println("Exception: " + e);
            return "Exception: "  + e;

        }
    }
}
