package com.test.MiniBA;

import java.util.List;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.collections.Lists;

public class MainClass {
	public static void main(String[] args) {
	    TestListenerAdapter tla = new TestListenerAdapter();
	    TestNG testng = new TestNG();
	    List<String> suites = Lists.newArrayList();
	    suites.add("C:\\Rajaprabu\\eclipse-workspace\\MiniBA\\testng_Sanity.xml");//path to xml..
	    testng.setTestSuites(suites);
	    testng.run();
	}

}
