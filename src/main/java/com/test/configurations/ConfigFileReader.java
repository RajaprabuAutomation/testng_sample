package com.test.configurations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
 
public class ConfigFileReader {
 
	static Properties properties;
	static String propertyFilePath= System.getProperty("user.dir") + "\\configuration\\Configuration.properties";
 
 public static void ConfigFileReader_1(){
	 BufferedReader reader;
	 try {
		 reader = new BufferedReader(new FileReader(propertyFilePath));
		 properties = new Properties();
	 try {
		 properties.load(reader);
		 reader.close();
	 } catch (IOException e) {
		 e.printStackTrace();
	 }
	 } catch (FileNotFoundException e) {
		 e.printStackTrace();
		 throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
	 } 
 }
  
public static String getBaseUrl(){
 String baseUrl = properties.getProperty("baseUrl");
 if(baseUrl!= null) return baseUrl;
 else throw new RuntimeException("baseUrl not specified in the Configuration.properties file."); 
 }
 
 public static String getChallangeCode() { 
 String challangeCode = properties.getProperty("challangeCode");
 if(challangeCode != null) return challangeCode;
 else throw new RuntimeException("chellangeCode not specified in the Configuration.properties file."); 
 }
 
 public static String getUserName() {
 String username = properties.getProperty("username");
 if(username != null) return username;
 else throw new RuntimeException("username not specified in the Configuration.properties file.");
 }
 
 public static String getPassword() {
 String password = properties.getProperty("password");
 if(password != null) return password;
 else throw new RuntimeException("password not specified in the Configuration.properties file.");
 }
 
 public static String getFlow() {
	 String flow = properties.getProperty("flow");
	 if(flow != null) return flow;
	 else throw new RuntimeException("flow not specified in the Configuration.properties file.");
	 }
 
 public static String getngpURL() {
	 String ngpURL = properties.getProperty("ngpURL");
	 if(ngpURL != null) return ngpURL;
	 else throw new RuntimeException("flow not specified in the Configuration.properties file.");
 }
 public static String getusertype() {
	 String usertype = properties.getProperty("usertype");
	 if(usertype != null) return usertype;
	 else throw new RuntimeException("flow not specified in the Configuration.properties file.");
 }
 
 public static String getpartnerID() {
	 String partnerID = properties.getProperty("partnerID");
	 if(partnerID != null) return partnerID;
	 else throw new RuntimeException("flow not specified in the Configuration.properties file.");
 }
 public static String getpartnerUnitCode() {
	 String partnerUnitCode = properties.getProperty("partnerUnitCode");
	 if(partnerUnitCode != null) return partnerUnitCode;
	 else throw new RuntimeException("flow not specified in the Configuration.properties file.");
 }
 public static String getpartnerUnitId() {
	 String partnerUnitId = properties.getProperty("partnerUnitId");
	 if(partnerUnitId != null) return partnerUnitId;
	 else throw new RuntimeException("flow not specified in the Configuration.properties file.");
 }
 public static String getskup() {
	 String skup = properties.getProperty("skup");
	 if(skup != null) return skup;
	 else throw new RuntimeException("flow not specified in the Configuration.properties file.");
 }
 public static String getenv() {
	 String env = properties.getProperty("env");
	 if(env != null) return env;
	 else throw new RuntimeException("flow not specified in the Configuration.properties file.");
 }
 public static String getSSN() {
	 String SSN = properties.getProperty("SSN");
	 if(SSN != null) return SSN;
	 else throw new RuntimeException("flow not specified in the Configuration.properties file.");
 }
 public static String getPartner() {
	 String partner = properties.getProperty("partner");
	 if(partner != null) return partner;
	 else throw new RuntimeException("flow not specified in the Configuration.properties file.");
 }
 public static String getDateOfBirth() {
	 String DateOfBirth = properties.getProperty("DateOfBirth");
	 if(DateOfBirth != null) return DateOfBirth;
	 else throw new RuntimeException("flow not specified in the Configuration.properties file.");
 }
 public static String executeLLPPE() {
	 String ExecuteLLPPE = properties.getProperty("ExecuteLLPPE");
	 if(ExecuteLLPPE != null) return ExecuteLLPPE;
	 else throw new RuntimeException("flow not specified in the Configuration.properties file.");
 }
 public static String CreditFlow() {
	 String CreditFlow = properties.getProperty("CreditFlow");
	 if(CreditFlow != null) return CreditFlow;
	 else throw new RuntimeException("flow not specified in the Configuration.properties file.");
 }
 public static String executeNOF() {
	 String ExecuteNOF = properties.getProperty("ExecuteNOF");
	 if(ExecuteNOF != null) return ExecuteNOF;
	 else throw new RuntimeException("flow not specified in the Configuration.properties file.");
 }
 public static String TelusFlow() {
	 String TelusFlow = properties.getProperty("TelusFlow");
	 if(TelusFlow != null) return TelusFlow;
	 else throw new RuntimeException("flow not specified in the Configuration.properties file.");
 }
 public static String DWMFlow() {
	 String DWMFlow = properties.getProperty("DWMFlow");
	 if(DWMFlow != null) return DWMFlow;
	 else throw new RuntimeException("flow not specified in the Configuration.properties file.");
 }
}