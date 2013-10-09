package com.example.android_start_app;

import org.junit.runner.*;



public class RunTests {

    public static void main(String[] args) {
    	JUnitCore junit = new JUnitCore();
    	Result result = junit.run(com.example.android_start_app.MainActivityTest.class);
    	System.out.println("completed " + result.getRunCount() + " tests");
    }
}
