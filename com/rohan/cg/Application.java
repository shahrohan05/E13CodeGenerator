package com.rohan.cg;

import java.io.*;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class Application {

    private static JavaParser javaParser = new JavaParser();
    private static JavascriptParser javascriptParser = new JavascriptParser();
    
    public static void main(String[] args) {
        System.out.println("Application started!");
        
        if(args.length<1) {
            System.out.println("Error! Please provide filename as a parameter!");
            return;
        }
        String filename = args[0];
        System.out.println("Filename-"+filename);
        
        try(BufferedReader reader = new BufferedReader(new FileReader(filename));
            PrintWriter writerJava = new PrintWriter("Output.java");
            PrintWriter writerJS = new PrintWriter("Output.js")) {
            String line = reader.readLine();
            
            List<Map<String,String>> tokenList = new ArrayList<Map<String,String>>();
            
            while(line != null) {
                tokenList.add(Application.processLine(line));
                line = reader.readLine();
            }

            String javaResponse = javaParser.parse(tokenList);
            writerJava.print(javaResponse);

            String javascriptResponse = javascriptParser.parse(tokenList);
            writerJS.print(javascriptResponse);

        }catch(IOException e) {
            e.printStackTrace();
        }catch(Exception e) {
            e.printStackTrace();
        }


    }

    public static Map<String,String> processLine(String line) {
        Map<String,String> tokens = new HashMap<String,String>();
        System.out.println("LINE : "+line);
        String[] lineParts = line.split(" ");
        System.out.println("TOKENS : "+tokens);
        
        if(lineParts.length<1) { // for handling empty lines...
            tokens.put("type","#");
            tokens.put("line","");
            return tokens;    
        }

        tokens.put("type",lineParts[0]);
        
        if(line.length()>2) {
            tokens.put("line",line.substring(2));
        }
        
        switch(lineParts[0]) {
            case "N" : tokens.put("fieldName",lineParts[1]); break;
            case "F" : tokens.put("fieldName",lineParts[1]); tokens.put("fieldType",lineParts[2]); break;
        }
        return tokens;
    }

}