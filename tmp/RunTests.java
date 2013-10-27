package com.example.android_start_app;

import org.junit.runner.*;
import org.junit.runner.notification.Failure;  

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.example.android_start_app.DialogExamples;
import org.eclipse.jface.window.ApplicationWindow;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import com.example.android_start_app.HttpRequest;


public class RunTests {
    public static void main(String[] args) {
    	//Get username password from strings.xml
    	Properties prop = new Properties();
    	try {
    		prop.load(new FileInputStream("config.properties"));
    	} catch (IOException ex) {
    		ex.printStackTrace();
        }
    	String username = prop.getProperty("username");
    	if ( username == null || username.length() == 0){
    		System.out.println("username not found. Show dialog");
    		ApplicationWindow window = new DialogExamples(null);
            //window.setBlockOnOpen(true);
            window.open();
            username = new DialogExamples(null).username;
            prop.setProperty("username",username);
            
            try {
            	prop.store(new FileOutputStream("config.properties"), null);
        	} catch (IOException ex) {
        		ex.printStackTrace();
            }
    		//return;
    	}
    	System.out.println("username found: " + username);
    
    	
    	JUnitCore junit = new JUnitCore();
    	Result result = junit.run(com.example.android_start_app.MainActivityTest.class);
    	System.out.println("completed " + result.getRunCount() + " tests");
    	System.out.println("No. of failed test cases="+result.getFailures().size());
    	
    	JSONObject obj = new JSONObject();
    	JSONArray failureList = new JSONArray();
        for (Failure failure : result.getFailures())
            //System.out.println("Failure message " + failure.toString()); 
        	failureList.add(failure.toString());
        	
        obj.put("username",username);
        obj.put("failures", failureList);
    	
    	System.out.println(obj.toString());
    	
    	HttpRequest.main(obj.toString());
     }			

}
