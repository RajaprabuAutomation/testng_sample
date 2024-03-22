package com.test.configurations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Reporter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class EB_PinPurchase {

	static String endpoint_PROD = "https://cebe.symantec.com:443/entitlement/PartnerService?wsdl";
	static String endpoint_INT = "https://cebe-int.symantec.com:443/entitlement/PartnerService?wsdl";
	static String filepath = System.getProperty("user.dir")+"//Request//EB_purchasePartnerProduct_INT.xml";
	static Random rand = new Random();
	static File responseXML = new File(System.getProperty("user.dir")+"\\Response\\EB_ResponsePurchasePIN_INT.xml");
	

	/*public static void main(String args[]) throws ClientProtocolException, IOException {
		
		modifyPurchaseAPI("12121212", "SOS1", "21389232", "User_detail", "INT", "test@ssn.com", "52211322");
		getSoapResponse("INT");
		getResponseElement("INT");
	}*/
	
	public static void modifyPurchaseAPI(String SOS_pid, String SOS_puCode, String SOS_skup, String partnerUserID, String SOS_env, String SOS_email, String SOS_SSN) {


			   try {
				   if(SOS_env.equalsIgnoreCase("PROD")){
					   filepath = System.getProperty("user.dir")+"\\Request\\EB_purchasePartnerProduct_PROD.xml";
					   System.out.println("Environment is PROD");
				   }
				   else{
					   filepath = System.getProperty("user.dir")+"\\Request\\EB_purchasePartnerProduct_INT.xml"; 
					   System.out.println("Environment is INT");
				   }
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				Document doc = docBuilder.parse(filepath);
				//int num = rand.nextInt(9000000) + 1000000;
				
				// Partner Unit Codes	
				Node vendor = doc.getElementsByTagName("purchasePartnerProductRequest").item(0);
				NodeList list = vendor.getChildNodes();
				for (int i = 0; i < list.getLength(); i++) {			
		                   Node node = list.item(i); 
				   // get the salary element, and update the value
				   if ("partnerUnitCode".equals(node.getNodeName())) {
					  String s = node.getNodeValue();
					 // System.out.println("/n Test"+ s );
					  String puCode = SOS_puCode;
					node.setTextContent(puCode);
					System.out.println("partnerUnitCode: " + puCode);
				   }
				}
				
				/*  //Partner ID & partner User ID		   
				   Node partner = doc.getElementsByTagName("partnerAccountDetails").item(0);
					NodeList list1 = partner.getChildNodes();
					for (int j = 0; j < list1.getLength(); j++) {				
			                   Node node1 = list1.item(j);	
					   // get the salary element, and update the value
					   if ("partnerId".equals(node1.getNodeName())) {
						   String pID = SOS_pid;
						   node1.setTextContent(pID);
						   System.out.println("Partner ID: "+ pID);
					   }
					   if ("partnerUserId".equals(node1.getNodeName())) {
						 //  int num = rand.nextInt(9000000) + 1000000;
						   //String userID = "SOS"+num+"TEST";
						   node1.setTextContent(partnerUserID);
						   System.out.println("PartnerUserId: "+partnerUserID);
					   	}
					   }
					
					//Partner ID & partner User ID		   
					   Node partner2 = doc.getElementsByTagName("userAccount").item(0);
						NodeList mylist = partner2.getChildNodes();
						for (int j = 0; j < mylist.getLength(); j++) {				
				                   Node mynode = mylist.item(j);
				                   System.out.println("Second partner: " + mynode.getNodeName());
				                   if ("partnerAccountDetails".equals(mynode.getNodeName())) {
				                	   Node partner3 = doc.getElementsByTagName("partnerAccountDetails").item(0);
										NodeList mylist1 = partner3.getChildNodes();
										for (int k = 0; k < mylist1.getLength(); k++) {				
								                   Node mynode2 = mylist1.item(k);
								                   ///System.out.println("bmma " + mynode2.getNodeName().toString());
								                   if ("partnerId".equals(mynode2.getNodeName())) {
													   String pID = SOS_pid;
													   mynode2.setTextContent(SOS_pid);
													   System.out.println("Second Partner ID: "+ pID);								                	   
								                   }
												   if ("partnerUserId".equals(mynode2.getNodeName())) {
													 //  int num = rand.nextInt(9000000) + 1000000;
													  // String userID = "SOS"+num+"TEST";
													   mynode2.setTextContent(partnerUserID);
													   System.out.println("Second PartnerUserId: "+partnerUserID);
												   	}
										}
				                   
				                   }}*/
				
				
				//Node partner = doc.getElementsByTagName("partnerAccountDetails").item(0);
				NodeList partner = doc.getElementsByTagName("partnerAccountDetails");
				for (int i = 0; i < partner.getLength(); i++) {
				    //System.out.println("Given Name : " + partner.item(i).getTextContent());
					NodeList list1 = partner.item(i).getChildNodes();
					for (int j = 0; j < list1.getLength(); j++) {				
			                   Node node1 = list1.item(j);	
					   // get the salary element, and update the value
					   if ("partnerId".equals(node1.getNodeName())) {
						   String pID = SOS_pid;
						   node1.setTextContent(pID);
						   System.out.println("Partner ID: "+ pID);
					   }
					   if ("partnerUserId".equals(node1.getNodeName())) {
						 //  int num = rand.nextInt(9000000) + 1000000;
						   //String userID = "SOS"+num+"TEST";
						   node1.setTextContent(partnerUserID);
						   System.out.println("PartnerUserId: "+partnerUserID);
					   	}
					   }
				}
				
					
					
				//	SKUP
					Node skup = doc.getElementsByTagName("skup").item(0);
					NodeList list2 = skup.getChildNodes();
					for (int k = 0; k < list2.getLength(); k++) {				
			                   Node node2 = list2.item(k);
					   // get the salary element, and update the value
					   if ("value".equals(node2.getNodeName())) {
						   String skupNO = SOS_skup;
						   node2.setTextContent(skupNO);
						   System.out.println("SKUP: "+ skupNO);
					   }
					}
					
				//request ID		   
					Node requestID = doc.getElementsByTagName("requestInfo").item(0);
						NodeList list3 = requestID.getChildNodes();
						for (int j = 0; j < list3.getLength(); j++) {				
				                   Node node3 = list3.item(j);
						   // get the salary element, and update the value
						   if ("requestID".equals(node3.getNodeName())) {
							   int num1 = rand.nextInt(9000000) + 1000000;
							   String rID = "req"+num1+"TEST";
							   node3.setTextContent(rID);
							   System.out.println("requestID: "+rID);
						   	}
						   }
					
				//request ID		   
						Node partnerOrderDetail = doc.getElementsByTagName("partnerOrderDetail").item(0);
							NodeList list4 = partnerOrderDetail.getChildNodes();
							for (int j = 0; j < list4.getLength(); j++) {				
					                   Node node4 = list4.item(j);
							   // get the salary element, and update the value
							   if ("orderID".equals(node4.getNodeName())) {
								   int num2 = rand.nextInt(9000000) + 1000000;
								   String oID = "req"+num2+"TEST";						   
								   node4.setTextContent(oID);
								   System.out.println("partnerOrderDetail: "+oID);
							   	}
							   }
				//email
							Node emailAddress = doc.getElementsByTagName("emailAddress").item(0);
							NodeList list5 = emailAddress.getChildNodes();
							for (int j = 0; j < list5.getLength(); j++) {				
					                   Node node5 = list5.item(j);
							   // get the salary element, and update the value
					                   if ("address".equals(node5.getNodeName())) {
										   String email = SOS_email;
										   node5.setTextContent(email);
										   System.out.println("Email: "+ email);
									   }
							   }		
				//SSN
							Node personalIdentification = doc.getElementsByTagName("personalIdentification").item(0);
							NodeList list6 = personalIdentification.getChildNodes();
							for (int j = 0; j < list6.getLength(); j++) {				
					                   Node node6 = list6.item(j);
							   // get the salary element, and update the value
					                   if ("value".equals(node6.getNodeName())) {
										   String SSN = SOS_SSN;
										   node6.setTextContent(SSN);
										   System.out.println("SSN: "+ SSN);
									   }
							   }
				// write the content into xml file
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(filepath);
				transformer.transform(source, result);

			   } catch (ParserConfigurationException pce) {
				pce.printStackTrace();
			   } catch (IOException ioe) {
				ioe.printStackTrace();
			   } catch (TransformerException tfe) {
					tfe.printStackTrace();
			   } catch (SAXException sae) {
				sae.printStackTrace();
			   }
			}
	
	public static void getSoapResponse(String env) throws ClientProtocolException, IOException
	{
		// ----- Processing the PROD Request -------
		
		if(env.equalsIgnoreCase("PROD")) {
		File requestFile = new File(System.getProperty("user.dir")+"\\Request\\EB_purchasePartnerProduct_PROD.xml");
		HttpClient cilent = HttpClientBuilder.create().build(); // Build h
		HttpPost post = new HttpPost(endpoint_PROD); // POST request
		post.setEntity(new InputStreamEntity(new FileInputStream(requestFile)));
		post.setHeader("Content-type","text/xml");
		HttpResponse response = cilent.execute(post);
		System.out.println("Status Code: " + response.getStatusLine().getStatusCode());
		BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		String line = "";
		StringBuffer sb = new StringBuffer();
		while((line=br.readLine())!=null)
		{
			sb.append(line);
		}
		PrintWriter pw = new PrintWriter(System.getProperty("user.dir")+"\\Response\\EB_ResponsePurchasePIN_PROD.xml");
		pw.write(sb.toString());
		pw.close();
		pw.flush();
		}
		
		// ----- Processing the INT Request -------
		
		else 
		{
			File requestFile = new File(System.getProperty("user.dir")+"\\Request\\EB_purchasePartnerProduct_INT.xml");
			HttpClient cilent = HttpClientBuilder.create().build();
			HttpPost post = new HttpPost(endpoint_INT);
			post.setEntity(new InputStreamEntity(new FileInputStream(requestFile)));
			post.setHeader("Content-type","text/xml");
			HttpResponse response = cilent.execute(post);
			System.out.println(response.getStatusLine().getStatusCode());
			BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			StringBuffer sb = new StringBuffer();
			while((line=br.readLine())!=null)
			{
				sb.append(line);
			}
			PrintWriter pw = new PrintWriter(System.getProperty("user.dir")+"\\Response\\EB_ResponsePurchasePIN_INT.xml");
			pw.write(sb.toString());
			pw.close();
			pw.flush();
		}
	}
	
	public static String getResponseElement(String SOS_env)
	{
		String PIN = null;
		String PSN = null;
		String Main_Trial = null;
		String Main_Paid= null;
		String PartnerUserID = null;
		String Error_Code = null;
		
		// ------- Choosing Environment -----
		if(SOS_env.equalsIgnoreCase("PROD")) {
			responseXML = new File(System.getProperty("user.dir")+"\\Response\\EB_ResponsePurchasePIN_PROD.xml");
		}
		else if(SOS_env.equalsIgnoreCase("INT")){
				responseXML = new File(System.getProperty("user.dir")+"\\Response\\EB_ResponsePurchasePIN_INT.xml");		
		}
		else {
			System.out.println("You haven't entered any Environment, So default INT will be taken as Environment");
		}
		
		// ------------ Declaration-------
		
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			
			
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(responseXML);
			
			
		//------ Error Code Fetch from XML --------	
			
			NodeList resultList3 = doc.getElementsByTagName("result");
			
			for(int l=0; l< resultList3.getLength(); l++) {
				Node nNode4 = resultList3.item(l);
					
				if (nNode4.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode4;
					Error_Code = eElement.getElementsByTagName("status").item(0).getTextContent();
					System.out.println("Error Code : " + Error_Code);
						} 
					}
			if(Error_Code.equals("0000")) {
				
		//------ PIN and PUID Fetch from XML --------	
				
			NodeList resultList = doc.getElementsByTagName("userDetail");
			
			for(int i=0; i< resultList.getLength(); i++) {
				Node nNode = resultList.item(i);
				
				 if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			            Element eElement = (Element) nNode;
			            PIN = eElement.getElementsByTagName("userPin").item(0).getTextContent();
			            System.out.println("PIN : " + PIN);
			            
			            PartnerUserID = eElement.getElementsByTagName("partnerUserId").item(0).getTextContent();	
			            System.out.println("PartnerUserID : " + PartnerUserID);
			            		}
					} 
			
		// ------ PSN Fetch from XML --------	
			
			NodeList resultList2 = doc.getElementsByTagName("productCore");
					
			for(int k=0; k< resultList2.getLength(); k++) {
				Node nNode2 = resultList2.item(k);
					
				if (nNode2.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode2;
					PSN = eElement.getElementsByTagName("productSerialNumber").item(0).getTextContent();
					System.out.println("PSN : " + PSN);
						} 
					}
				 
		/*// ------ Storage Fetch from XML --------	
				 
			NodeList resultList1 = doc.getElementsByTagName("licenseTerms");
			
			for(int j=0; j< resultList1.getLength(); j++) {
				Node nNode1 = resultList1.item(j);
				
					 
				System.out.println("Current Element :" + nNode1.getNodeName());
				 if (nNode1.getNodeType() == Node.ELEMENT_NODE) {
			            Element eElement = (Element) nNode1;
			            String classID = eElement.getElementsByTagName("classId").item(0).getTextContent();
			            if(classID.equals("MAIN")) {
			            Main_Paid =eElement.getElementsByTagName("paidBackupStorageMb").item(0).getTextContent();
			            System.out.println( "paidBackupStorageMb : " + Main_Paid);
			            Main_Trial = eElement.getElementsByTagName("trialBackupStorageMb").item(0).getTextContent();
			            System.out.println("trialBackupStorageMb : " + Main_Trial);
			            }
			            }
			}*/
			 System.out.println("\n");
			 
			}
			else {
				
				PIN = "NA";
				PSN = "NA";
				/*Main_Trial = "NA";
				Main_Paid = "NA";*/
				PartnerUserID = "NA";
				Error_Code = "NA";
			}
			
			// ------- Send to Excel File --------
				 
		// write.writeExcelSheet(rowNo, PIN, PSN, Main_Trial, Main_Paid, PartnerUserID, Error_Code);
			/*Reporter.log("PIN: " + PIN, true);
			Reporter.log("PSN: " + PSN, true);
			Reporter.log("Main_Trial(MB): " + Main_Trial, true);
			Reporter.log("Main_Paid(MB): " + Main_Paid, true);*/
			System.out.println();
			
				
						
		} 
		
		
		catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		return Error_Code;

	}
}
