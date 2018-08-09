package com.rohan.cg;

import java.util.List;
import java.util.Map;

class JavaParser {
    
    public String parse(List<Map<String,String>> tokenList) throws Exception {
        String response = "";
        for(Map<String,String> tokens : tokenList) {
            switch(tokens.get("type")) {
                case "#" : response += "//"+tokens.get("line"); break;
                case "N" : response += "\n class "+tokens.get("fieldName")+" {"; break;
                case "F" : response += "\n "+this.getType(tokens.get("fieldType"))+" "+tokens.get("fieldName")+";"; break;
                case "E" : response += "\n}"; break;
            }
            
        }
        return response;
    }

    public String getType(String inputType) throws Exception {
        switch(inputType.toLowerCase()) {
            case "number" : return "int";
            case "string" : return "String";
            default : throw new Exception("Type cannot be recognized!");
        }
    }
}