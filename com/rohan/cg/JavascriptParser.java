package com.rohan.cg;

import java.util.List;
import java.util.Map;

class JavascriptParser {
    
    public String parse(List<Map<String,String>> tokenList) throws Exception {
        String response = "";
        for(Map<String,String> tokens : tokenList) {
            switch(tokens.get("type")) {
                case "#" : response += "//"+tokens.get("line"); break;
                case "N" : response += "\n var "+tokens.get("fieldName")+" = {"; break;
                case "F" : response += "\n\t "+tokens.get("fieldName")+" : "+this.getType(tokens.get("fieldType"))+","; break;
                case "E" : response=response.substring(0,response.length()-1); 
                            response += "\n}"; break;
            }
            
        }
        return response;
    }

    public String getType(String inputType) throws Exception {
        switch(inputType.toLowerCase()) {
            case "number" : return "0";
            case "string" : return "\"\"";
            default : throw new Exception("Type cannot be recognized!");
        }
    }
}