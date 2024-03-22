package com.test.MiniBA;

import java.awt.AWTException;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.http.client.ClientProtocolException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;
import com.beust.jcommander.Parameter;
import com.test.MiniBA.*;
import com.test.configurations.ConfigFileReader;
import com.test.configurations.Database;
import com.test.configurations.DirectoryCopyExample;
import com.test.configurations.EB_PinPurchase;
import com.test.configurations.PinPurchase;

import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;
import lombok.experimental.var;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

/*import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import javax.imageio.ImageIO;*/

import com.gargoylesoftware.htmlunit.javascript.TimeoutError;
import com.google.common.collect.ImmutableBiMap.Builder;

import com.test.MiniBA.*;

public class ExecutionSet_1 {
	
	static ConfigFileReader configFileReader;
	
	static public WebDriver driver;
	
/*	public static WebDriver getdriver(){
	      return driver;
	    }*/
	
	static WebElement var_1, var_2, var_3,var_4, un, pwd, click_button, download, partnerflow, retailflow, error, download_1,download_2, memberEnroll;
	
	static String path, title1, pin, ngpURL,status_code;
	static String Title_login, Title_welcome, Title_dashboard, Title_subscription, Title_signout, title_signup;
	//static String downloadPath = "C:\\Users\\rajaprabu_r\\Downloads";	
	
	static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss"); 
	static String uniqueID;	
	static LocalDateTime now;	
	

	static Database db;
	static DirectoryCopyExample dir;

	static EB_PinPurchase eb_pinpurhase;
	static PinPurchase pinpurchase;
	static int increment = 1;
	static ATUTestRecorder recorder;
	
	static {
		Random rand = new Random();
		uniqueID = String.format("%04d", rand.nextInt(1000000));
		System.out.println("Unique ID for entire Execution: " + uniqueID);
	}
	
	@Parameters("browser")
	@BeforeTest(groups = {"all", "miniBA", "enrollment"})
	public static void browserStart(String browser) throws ClientProtocolException, IOException {
	
		now = LocalDateTime.now();
		try {
		if(browser.equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver","C:\\Browser_Driver\\geckodriver.exe");
	    	driver = new FirefoxDriver();
	    	//path = System.getProperty("user.dir") + "\\screenshots\\" + "Firefox_screenshot_" + dtf.format(now) + "_" + uniqueID + "\\";
		}
		else if(browser.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", "C:\\Browser_Driver\\chromedriver.exe");
			driver=new ChromeDriver();
			
			//path = "\\Chrome_screenshot_" +  dtf.format(now) + "_" + uniqueID + "\\";
		}
		else if(browser.equalsIgnoreCase("edge")){
			System.setProperty("webdriver.edge.driver", "C:\\Browser_Driver\\msedgedriver.exe");
			driver=new EdgeDriver();
			//path = System.getProperty("user.dir") + "\\screenshots\\" + "MSEdge_screenshot_" +  dtf.format(now) + "_" + uniqueID + "\\";
			}
	     driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
		 /*DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH-mm-ss");
		  Date date = new Date();
		  recorder = new ATUTestRecorder("D:\\Rajaprabu\\eclipse-workspace\\MiniBA\\screenshots","Execution_Video_"+uniqueID + "_" + dateFormat.format(date),false);
		  //To start video recording.
		  recorder.start();
		  System.out.println("Recording Started");*/
		 ConfigFileReader.ConfigFileReader_1(); 
			}
		catch (Exception e) {
			System.out.println("Browser Not opened");
			e.getMessage();
		}
		
		try {
		File screenshot_directory = new File(System.getProperty("user.dir") + "\\screenshots");
		FileUtils.deleteDirectory(screenshot_directory);
		System.out.println("Existing screenshot directory deleted");
		}
		catch (Exception e) {
			// TODO: handle exception
		}

	}
		
	@Test(priority = 1, groups = {"all", "miniBA", "enrollment"})
	public static void A_SignUpSignIn() throws Exception {
		String userName = "UN_" + uniqueID + "@selenium.com";
		//String userName = configFileReader.getUserName();
		Reporter.log("Unique ID for entire Execution: " + uniqueID);
		if(configFileReader.getusertype().equalsIgnoreCase("new"))	{
			try {
			if(configFileReader.getPartner().equalsIgnoreCase("EB")) {			
				String static_URL = "https://my-extint.norton.com/llonboard/idv/eb";
				boolean enroll_status = EB_enrollment_API(userName);
				
				if(enroll_status) {
					driver.get(static_URL);
					screenshot("EB_Enroll_Page");
					driver.findElement(By.id("dob")).sendKeys("01-01-1950");
					driver.findElement(By.id("ssn_number")).sendKeys(configFileReader.getSSN());					
					driver.switchTo().frame(driver.findElement(By.xpath("//*[@id=\"recaptcha_widget\"]/div/div/iframe")));
					Thread.sleep(2000);
					driver.findElement(By.xpath("/html/body/div[2]/div[3]/div[1]/div/div/span/div[1]")).click();
					driver.switchTo().defaultContent();
					Thread.sleep(2000);
					driver.findElement(By.id("btn-continue")).click();
					screenshot("SignIn_Page");
					driver.findElement(By.id("registerToggleButton")).click();	
					driver.findElement(By.id("registerEmailAddress")).sendKeys(userName);
					driver.findElement(By.id("confirmEmailAddress")).sendKeys(userName);
					driver.findElement(By.id("registerPassword")).sendKeys("Test@123");
					driver.findElement(By.id("btn-sign-up")).click();
					Reporter.log("Signed Up with EB user", true);
				}
				else {
					Reporter.log("Issue in EB Enrollment flow", true);
					Assert.fail();
					}
			
				}
			
		else {					
				String pin = PinGeneration();
				ngpURL = configFileReader.getngpURL() + configFileReader.getpartnerUnitId() + "&pin=" + pin + "&skup=" + configFileReader.getskup() + "&management=1";
				System.out.println(ngpURL);
				driver.get(ngpURL);
				WebDriverWait wait = new WebDriverWait(driver, 30);
				var_1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginUsername")));
				screenshot("WelcomePage" + uniqueID);
				//now = LocalDateTime.now();	
				//String userName = "UN_" + uniqueID + "@selenium.com";
				Reporter.log("UserName: " + userName);
				driver.findElement(By.id("registerToggleButton")).click();	
				driver.findElement(By.id("registerEmailAddress")).sendKeys(userName);
				driver.findElement(By.id("confirmEmailAddress")).sendKeys(userName);
				driver.findElement(By.id("registerPassword")).sendKeys("Test@123");
				driver.findElement(By.id("btn-sign-up")).click();
				Reporter.log("Signup Successful", true);
				StoreToDB(userName, "Test@123", pin);
				}
			}

			catch(Exception e) {
				Reporter.log("Issue in loading SignUp Welcome page", true);
				e.getMessage();
				Assert.fail(e.toString());
			}
			}
	else {
		try {
		if(configFileReader.getFlow().equalsIgnoreCase("warm")) 
			{
					driver.get(configFileReader.getBaseUrl());
					Title_welcome = driver.getTitle();
					screenshot("WelcomePage" + uniqueID);
					System.out.println();
					System.out.println("<---- Partner Warm Flow SignIn page Displayed---->");
					Reporter.log("Unique ID for entire Execution: " + uniqueID);
						partnerflow = driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/section/div/div[2]/a"));
						partnerflow.click();
						Thread.sleep(2000);
						var_2 = driver.findElement(By.id("onboard-partnercodeentry-input"));
						var_2.sendKeys(configFileReader.getChallangeCode());
						screenshot("Challenge_Code" + uniqueID);
						var_3 = driver.findElement(By.id("onboard-keyentry-btn"));
						var_3.click();
						Thread.sleep(2000);
						screenshot("Logo_confirmation" + uniqueID);
						var_4 = driver.findElement(By.id("partner-confirm-btn"));
						var_4.click();
						Thread.sleep(2000);
						screenshot("PartnerSignInPage" + uniqueID);
						driver.findElement(By.id("loginUsername")).sendKeys(configFileReader.getUserName());
						Thread.sleep(1000);
						driver.findElement(By.id("loginPassword")).sendKeys(configFileReader.getPassword());
						Thread.sleep(1000);
						driver.findElement(By.xpath("//*[@id=\"signin_button\"]")).click();
						Reporter.log("SignIn Successful", true);

			}			
			else if(configFileReader.getFlow().equalsIgnoreCase("cold")) {
				driver.get(configFileReader.getBaseUrl());
				Title_welcome = driver.getTitle();
				screenshot("WelcomePage" + uniqueID);
					System.out.println();
					System.out.println("<---- Partner Cold Flow SignIn page Displayed---->");
					driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/section/div/div[2]/button")).click();
					screenshot("PartnerSignInPage" + uniqueID);
					driver.findElement(By.id("loginUsername")).sendKeys(configFileReader.getUserName());
					Thread.sleep(2000);
					driver.findElement(By.id("loginPassword")).sendKeys(configFileReader.getPassword());
					Thread.sleep(2000);
					driver.findElement(By.xpath("//*[@id=\"signin_button\"]")).click();
									
				}
		
			else if(configFileReader.getFlow().equalsIgnoreCase("psso")) {
				driver.get(configFileReader.getBaseUrl());
				screenshot("WelcomePage" + uniqueID);
					System.out.println();
					System.out.println("<---- PSSO SignIn page Displayed---->");
					driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/section/div/div[2]/button")).click();
					screenshot("PartnerSignInPage" + uniqueID);
					driver.findElement(By.id("pw_usr")).sendKeys(configFileReader.getUserName());
					Thread.sleep(2000);
					driver.findElement(By.id("pw_submit")).click();
					Thread.sleep(2000);
					driver.findElement(By.id("pw_pwd")).sendKeys(configFileReader.getPassword());
					Thread.sleep(2000);
					driver.findElement(By.id("pw_submit")).click();
					Thread.sleep(2000);
									
				} 
			else if(configFileReader.getFlow().equalsIgnoreCase("retail")){
				driver.get(configFileReader.getBaseUrl());
				Title_welcome = driver.getTitle();
				screenshot("WelcomePage" + uniqueID);
					System.out.println("<---- Retail SignIn Page Displayed---->");
					Reporter.log("Unique ID for entire Execution: " + uniqueID);
					retailflow = driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/section/div/div[2]/button"));
					retailflow.click();
					Thread.sleep(2000);
					screenshot("RetailSignInPage" + uniqueID);
					Title_login = driver.getTitle();
					driver.findElement(By.id("loginUsername")).sendKeys(configFileReader.getUserName());
					driver.findElement(By.id("loginPassword")).sendKeys(configFileReader.getPassword());
					driver.findElement(By.xpath("//*[@id=\"signin_button\"]")).click();
					Reporter.log("SignIn Successful", true);
			}
		}
		catch (Exception e) {
			Reporter.log("Issue in loading SignIn Welcome page", true);
			e.getMessage();
			Assert.fail();
		}
	}
	}
	
	@Test(priority=2, dependsOnMethods={"A_SignUpSignIn"}, groups = {"all", "miniBA", "enrollment"})
	public static void BA_DashBoard_Tiles() throws IOException, InterruptedException {
		
		try {
			System.out.println();
			Reporter.log("<--- Signed In Successfully --->");
			
			if(driver.findElements(By.id("interstitialButton")).size() != 0) {
				/*if(configFileReader.executeLLPPE().equalsIgnoreCase("yes")) {
					if(configFileReader.getpartnerID().equalsIgnoreCase("1010530")) {
						ITPS_enrollment_Telus();
					}
					else {
						ITPS_enrollment();	
						}
					}
				else {
					driver.findElement(By.xpath("/html/body/div[2]/div[3]/app-root/app-enroll/div[3]/div/div[1]/div[2]"));
				}*/
				System.out.println("Enrollment Process will be continued from Dashboard");
				Thread.sleep(5000);
				driver.findElement(By.xpath("/html/body/div[2]/div[3]/app-root/app-enroll/div[1]/div/div[1]/div[2]")).click();
				
			}
			if(driver.findElements(By.id("modal-cta")).size() != 0) {	
				if(driver.findElement(By.xpath("/html/body/div[2]/div[3]/app-boot/consent/nmp-modal/div/div/div/div/h2")).getText().equalsIgnoreCase("Customer Agreement")) {
					DWMactivate();
				}
				else {
				creditActivate();
				}
			}
			if(driver.findElements(By.xpath("/html/body/div[2]/div[3]/app-root/app-error/div/div/div[2]/div/div/div/div[2]/div[1]/div[1]")).size() != 0) {
				String Text =driver.findElement(By.xpath("/html/body/div[2]/div[3]/app-root/app-error/div/div/div[2]/div/div/div/div[2]/div[1]")).getText();
				Reporter.log(Text, true);
				screenshot("No_Subscription_found_at_dashboard");
				driver.findElement(By.xpath("/html/body/div[2]/div[3]/app-root/app-enroll/div[1]/div/div[1]/div[2]")).click();
			}
			WebDriverWait wait = new WebDriverWait(driver, 30);
			download = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("subscriptioninfo_download")));
			screenshot("Dashboard" + uniqueID);
			if(driver.findElements(By.xpath("/html/body/div[2]/div[3]/app-boot/dashboard/div/span/tiles/div/subscription-info/div/div/div[1]/div[1]")).size() != 0) {
			String subscription_status = driver.findElement(By.xpath("/html/body/div[2]/div[3]/app-boot/dashboard/div/span/tiles/div/subscription-info/div/div/div[1]/div[1]")).getText();
			System.out.println();
			Reporter.log(subscription_status, true);
			}
			else {
				Reporter.log("License Used Details Not Available", true);
			}
			System.out.println();
			Reporter.log("<--- Dashboard Tiles Details --->", true);			
			List<WebElement> tile = driver.findElements(By.xpath("/html/body/div[2]/div[3]/app-boot/dashboard/div/span/tiles/div/div/tile"));			
			int size = tile.size();
			System.out.println("Number of tiles: " + size);
			for(WebElement ele:tile)
			{
				String tile_name = ele.getAttribute("id");
				Reporter.log(tile_name, true);			       
			}	
			

			WebElement norton_logo = driver.findElement(By.xpath("/html/body/div[2]/header/div[1]"));
		    Shutterbug.shootElement(driver,norton_logo).withName("Norton_Logo").save();
		    
			Title_dashboard = driver.getTitle();
			Assert.assertEquals(Title_dashboard,"Norton - My Subscription");
		}
		
		catch (Exception e) 
			{
			System.out.println();
			Reporter.log("Issue in Dashboard display", true);
			String url = driver.getCurrentUrl();
			Reporter.log(url, true);
			screenshot("issue_in_display");
			Assert.fail(e.toString());	
			}
		
	}

	@Test(priority =3, dependsOnMethods={"A_SignUpSignIn"}, groups = {"all", "miniBA"})
	public static void BB_DashBoard_Alerts() {
		// Alerts Details
		try {
		System.out.println();
		System.out.println("<--- Alerts Details --->");
		List<WebElement> alerts = driver.findElements(By.xpath("/html/body/div[2]/div[3]/app-boot/dashboard/div/span/hero/div/nor-hero/div/div/span/hero-card/div"));
		int size_alerts = alerts.size();
		if(size_alerts>0) {
		System.out.println("Number of Alerts: " + size_alerts);
		for(WebElement ele:alerts)
		{
			String tile_name = ele.getText();
			Reporter.log(tile_name, true);			       
		}
		}
		else {
			Reporter.log("No alerts Displayed", true);
		}
		}
		catch (Exception e) {
			Reporter.log("Issue in Alerts display");
			Assert.fail(e.toString());
		}
	}
	
	@Test(priority=4, dependsOnMethods={"A_SignUpSignIn"}, groups = {"all", "miniBA"})
	public static void BC_DashBoard_3PTiles() {
	 try {
		 System.out.println();
		System.out.println("<--- 3P Tiles Details --->");
		List<WebElement> iframe = driver.findElements(By.id("partnertiles"));
		int size_3p = iframe.size();
		System.out.println("Size of 3P tiles: " + size_3p);
		if(size_3p>0) {
		driver.switchTo().frame(driver.findElement(By.id("partnertiles")));
			String st = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[1]/div/div[2]/div[1]/div/div[1]/div[3]/span")).getText();			
			String st_1 = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[1]/div/div[2]/div[2]/div/div[1]/div[3]/span")).getText();
			Reporter.log(st, true);
			Reporter.log(st_1, true);
		driver.switchTo().defaultContent();
		screenshot("3P_tiles" + uniqueID);
		}
		else {
			Reporter.log("No 3P tiles are available", true);
		}
		}
		catch (Exception e) {
			Reporter.log("Issue in 3P Tile display", true);
			Assert.fail(e.toString());
		}
	}
	
	@Test(priority = 5, dependsOnMethods={"A_SignUpSignIn"}, groups = {"all", "miniBA"})
	public static void BD_Device_Security() throws InterruptedException, IOException {
		try {
			System.out.println();
			Reporter.log("Device Security: ", true);
			if(driver.findElements(By.id("DeviceSecurity_cta")).size()!=0) {
				String deviceSecurity_message = driver.findElement(By.id("DeviceSecurity_message")).getText();
				Reporter.log(deviceSecurity_message, true);
				if(driver.findElements(By.id("subscriptioninfo_licensesUsed")).size() != 0) {
					String license_used = driver.findElement(By.id("subscriptioninfo_licensesUsed")).getText();
					Reporter.log(license_used, true);
				}
				String test = driver.findElement(By.id("DeviceSecurity_cta")).getText();
				if(test.equalsIgnoreCase("Protect My Device")) {
					driver.findElement(By.id("DeviceSecurity_cta")).click();
					screenshot("Device_Security_Download_Page" + uniqueID);
					Reporter.log("As its a new login. No devices are listed", true);
					Reporter.log(driver.getCurrentUrl().toString(), true);
					driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div/div/div/div[1]/p[3]/a")).click();
					Reporter.log(driver.getCurrentUrl().toString(), true);
					Thread.sleep(2000);
					driver.findElement(By.id("onboard-modal-close-btn")).click();
					Reporter.log("None of the License used yet", true);
					}
				else {
					driver.findElement(By.id("DeviceSecurity_cta")).click();
					System.out.println();
					String DeviceSecurity_URL = driver.getCurrentUrl();
					Reporter.log("DeviceSecurity URL: " + DeviceSecurity_URL, true);
					List<WebElement> var = driver.findElements(By.xpath("/html/body/div[2]/div[3]/div/div/div[3]/div[2]/div"));
					System.out.println();
					Reporter.log("<--- List_of_Active_Inactive_Devices --->", true);
					System.out.println();
					Reporter.log("Number of Devices: "+ (var.size()-1));
						for(WebElement list: var) {						
							String list_str = list.getText();
							Reporter.log(list_str, true);
						}
						screenshot("List_of_Active_Inactive_Devices" + uniqueID);
						driver.findElement(By.xpath("/html/body/div[2]/header/div[2]/ul/li[2]/a")).click();
					}				
				}
			}
		 catch (Exception e) {
			Reporter.log("Issue in Device Security Tile details", true);
			Assert.fail(e.toString());
		}
	}
	
	@Test(priority=6, dependsOnMethods={"A_SignUpSignIn"}, groups = {"all", "miniBA"})
	public static void BE_Cloud_bakup() throws IOException, InterruptedException {
		try {
			System.out.println();
			Reporter.log("Cloud Backup: ", true);
			if(driver.findElements(By.id("BackUp_cta")).size()!=0) {
				String backUp_message = driver.findElement(By.id("BackUp_message")).getText();
				Reporter.log(backUp_message, true);
				String backup_cta = driver.findElement(By.id("BackUp_cta")).getText();
				driver.findElement(By.id("BackUp_cta")).click();
				System.out.println();
				String CLoudBackup_URL = driver.getCurrentUrl();
				Reporter.log("Cloud Backup URL: " + CLoudBackup_URL, true);
				if(backup_cta.equalsIgnoreCase("Set Up Backup"))
				{
					screenshot("Cloud_Backup_video");
					Reporter.log("No files are backed up yet", true);
					driver.findElement(By.id("modal-close")).click();
					}
			else {
				Thread.sleep(6000);
				screenshot("Cloud_Backup_Restore_Page");
				driver.switchTo().frame(driver.findElement(By.id("frameContent")));
				List<WebElement> cloud_active = driver.findElements(By.xpath("/html/body/div[3]/div[1]/ui-view/div[4]/div/div[2]/div"));
				int length = cloud_active.size();
				Reporter.log("Number of Backup Sets Available: " + length, true);
				System.out.println();
			    for(int i=1; i<=length; i++)
			    {
			    	String element_cons = "/html/body/div[3]/div[1]/ui-view/div[4]/div/div[2]/div["+i+"]/div[1]/div/div[1]/span";			    	
			    	String backup = driver.findElement(By.xpath(element_cons)).getText();
			    	Reporter.log(backup, true);
			    }
			    driver.switchTo().defaultContent();
			    driver.findElement(By.xpath("/html/body/div[1]/header/div[2]/ul/li[2]/a")).click();
				Thread.sleep(2000);
			}
			}
			else {
				Reporter.log("Cloud Backup is not available for this Subscription", true);
				driver.findElement(By.xpath("/html/body/div[2]/header/div[2]/ul/li[2]/a")).click();
				Thread.sleep(2000);
			}
		}
		catch (Exception e) {
			Reporter.log("Issue in Cloud Backup Tile", true);
			driver.findElement(By.xpath("/html/body/div[1]/header/div[2]/ul/li[2]/a")).click();
			Assert.fail(e.toString());
		}
	}
	
	@Test(priority=7, dependsOnMethods={"A_SignUpSignIn"}, groups = {"all", "miniBA"})
	public static void BF_Secure_Vpn() throws IOException, InterruptedException {
		try {
			System.out.println();
			Reporter.log("Secure_VPN: ", true);
			if(driver.findElements(By.id("SecureVpn_cta")).size()!=0) {
				driver.findElement(By.id("SecureVpn_cta")).click();
				Reporter.log("Secure VPN is available for this subscription", true);
				System.out.println();
				String VPN_URL = driver.getCurrentUrl();
				Reporter.log("VPN URL: " + VPN_URL, true);
					screenshot("Secure_VPN Download page");
					driver.findElement(By.id("onboard-modal-close-btn")).click();
					Thread.sleep(2000);
				}
			else {
				Reporter.log("Secure VPN not available for this subscription", true);
			}
		}
		catch (Exception e) {
			Reporter.log("Issue in Secure VPN Tile", true);
			screenshot("Issue_in_VPN");
			Assert.fail(e.toString());
		}
	}
	
	@Test(priority=8, dependsOnMethods={"A_SignUpSignIn"}, groups = {"all", "miniBA"})
	public static void BG_Password_Manager() throws IOException, InterruptedException {
		try {
			System.out.println();
			Reporter.log("Password Manager: ", true);
			if(driver.findElements(By.id("PasswordManager_cta")).size()!=0) {
				driver.findElement(By.id("PasswordManager_cta")).click();
				System.out.println();
				String PasswordManager_URL = driver.getCurrentUrl();
				Reporter.log("Password Manager URL: " + PasswordManager_URL, true);
				Thread.sleep(5000);
				/*screenshot("Password Manager Create vault page");
				Reporter.log("Password Manager is available for this subscription", true);
				driver.findElement(By.xpath("/html/body/div[2]/header/div[2]/ul/li[2]/a")).click();
				Thread.sleep(2000);*/
				driver.switchTo().frame(driver.findElement(By.id("idsafeIframe")));
				if(driver.findElements(By.xpath("/html/body/div/div/div[2]/div/div/div[2]/button")).size() != 0) {
					screenshot("Password Manager Create vault page");
					driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div/div[2]/button")).click();
					Thread.sleep(2000);
					driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div/div[2]/div[2]/div/div/div/input")).sendKeys("SeleniumTest1");
					driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div/div[2]/div[6]/div/button")).click();
					driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div/div[2]/div[3]/div/div/div/input")).sendKeys("SeleniumTest1");
					driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div/div[2]/div[5]/div/button")).click();
					driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div/div[2]/div[4]/div/div/div/textarea")).sendKeys("abdsvdiiwcsixmk");
					driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div/div[2]/div[5]/div/button")).click();
					Thread.sleep(2000);
					screenshot("Password_Manager_vault_created");
					Reporter.log("New Vault Created", true);
					driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div/div[3]")).click();
					screenshot("Password_Manager_Welcome_page");
					driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div/div[1]/div[1]")).click();
					Thread.sleep(2000);
					screenshot("Password_Manager_Page");
					driver.switchTo().defaultContent();
					driver.findElement(By.xpath("/html/body/div[2]/header/div[2]/ul/li[2]/a")).click();				
				}
				else {
					Reporter.log("Vault already Created", true);
					//driver.switchTo().frame(driver.findElement(By.id("idsafeIframe")));
					driver.findElement(By.xpath("//*[@id=\"unlock-container\"]/div/div[2]/div[1]/div/div/input")).sendKeys("SeleniumTest1"); //SeleniumTest1
					driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div/div[2]/div[3]/button")).click();
					Thread.sleep(2000);
					if(driver.findElements(By.xpath("/html/body/div/div/div[2]/div/div/div[1]/div[1]")).size() != 0) {
						screenshot("Password_Manager_Welcome_page");
						driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div/div[1]/div[1]")).click();
					}
					screenshot("Password_Manager_Welcome_page");
					driver.switchTo().defaultContent();
					driver.findElement(By.xpath("/html/body/div[2]/header/div[2]/ul/li[2]/a")).click();	
					}				
				}
			else {
				Reporter.log("Password Manager not available for this subscription", true);
				}
		}
		catch (Exception e) {
			Reporter.log("Issue in Password Manager", true);
			screenshot("Issue_Password_Manager");
			driver.switchTo().defaultContent();
			driver.findElement(By.xpath("/html/body/div[2]/header/div[2]/ul/li[2]/a")).click();
			e.getMessage();
			Assert.fail(e.toString());
			
		}
	}

	
	@Test(priority=9, dependsOnMethods={"A_SignUpSignIn"}, groups = {"all", "enrollment"})
	public static void CA_postEnrollment() throws IOException, InterruptedException {
		
		if(configFileReader.executeLLPPE().equalsIgnoreCase("Yes")) {
		if(driver.findElements(By.id("Identity_cta")).size() != 0) {
		try {
		//now = LocalDateTime.now();
			System.out.println();
			Reporter.log("Entering Lifelock enrollment process", true);
			WebDriverWait wait = new WebDriverWait(driver, 40);
			memberEnroll = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Identity_cta")));
			String text = driver.findElement(By.id("Identity_cta")).getText();
			
			if(text.equalsIgnoreCase("Enroll Now")) {
				driver.findElement(By.id("Identity_cta")).click();
				
				if (driver.findElements(By.xpath("/html/body/div[2]/div[3]/app-root/app-error/div/div/div[2]/div/div/div/div[2]/div[2]/div")).size() != 0) {
					screenshot("No_Subscription_found");
					System.out.println();
					Reporter.log("No Subscription Found", true);
					driver.findElement(By.xpath("/html/body/div[2]/div[3]/app-root/app-error/div/div/div[2]/div/div/div/div[2]/div[2]/div")).click();
				}
				else {
					if(configFileReader.getpartnerID().equalsIgnoreCase("1010530")) {
						
						String IDV_Status = configFileReader.TelusFlow();
						
//						switch(IDV_Status)
//						{
//						case "IDV_Pass_KBA_Pass": 	Telus_ITPS_Credit();
//													break;
//						case "IDV_Pass_KBA_Fail": 	Telus_ITPS_NON_CREDIT();
//													break;
//													
//						case "IDV_Fail_KBA_Fail": 	Telus_IDV_FAIL_KBA_FAIL();
//													break;
//						case "Credit_Fail": 		Telus_creditActivate_Fail();
//													break;
//						case "Credit_Pass": 		creditActivate();
//													break;
//						default: 					Reporter.log("LLPPE Flow Not selected", true);
//													break;
//							
//						}
					}
					else {
						if(configFileReader.CreditFlow().equalsIgnoreCase("Yes")) {
							ITPS_enrollment_Credit();	
							}
						else { ITPS_enrollment_Non_Credit();
							}
						}					
				}
			}
			else {
				System.out.println();
				Reporter.log("User Already enrolled to LifeLock", true);
			}
		}
		catch (Exception e) {
			System.out.println();
			Reporter.log("<-- Error: Issue in Enrollment -->", true);
			screenshot("Issue_ITPS_Enrollment");
			driver.findElement(By.xpath("/html/body/div[2]/div[3]/app-root/app-error/div/div/div[1]/div[2]")).click();
			e.getStackTrace();
			Assert.fail(e.toString());
		}
		}
		else
		{
			System.out.println();
			Reporter.log("Not enrolled to LifeLock", true);
		}
		}
		else {
			System.out.println();
			Reporter.log("LLPPE execution skipped", true);
		}
		
	}
	
	@Test(priority=10, dependsOnMethods={"A_SignUpSignIn"}, groups = {"all", "enrollment", "miniBA"})
	public static void BH_Download() throws IOException, InterruptedException {
		try {
			System.out.println();
			Reporter.log("Download Page", true);
		driver.findElement(By.id("subscriptioninfo_download")).click();
		Thread.sleep(3000);
		screenshot("Download Page_1" + uniqueID);
		Reporter.log(driver.getCurrentUrl().toString(), true);
			if(driver.findElements(By.id("onboard-download-btn")).size() != 0) {
				driver.findElement(By.id("onboard-download-btn")).click();
			}
			else {
				driver.findElement(By.id("agreeanddownload-download-cta")).click();
			}
		Thread.sleep(2000);
		Reporter.log(driver.getCurrentUrl().toString(), true);
		if(driver.findElements(By.id("onboard-modal-close-btn")).size() != 0) {		
			driver.findElement(By.id("onboard-modal-close-btn")).click();
		}
		else {
			driver.findElement(By.id("download-header-close")).click();
		}
		System.out.println();
		Reporter.log("Download button works", true);
		Thread.sleep(2000);
		screenshot("Dashboard_after_enrollment" + uniqueID);
		}
		catch (Exception e) {
			Reporter.log("Issue in Download", true);
			Assert.fail(e.toString());
			driver.findElement(By.id("onboard-modal-close-btn")).click();
		}
	}
	
	@Test(priority = 11, dependsOnMethods={"A_SignUpSignIn", "CA_postEnrollment"}, groups = {"all", "enrollment"})
	public static void CB_ID_Theft_Dashboard() throws IOException, InterruptedException {
		if(configFileReader.executeLLPPE().equalsIgnoreCase("Yes")) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			memberEnroll = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Identity_cta")));
			if(driver.findElements(By.id("Identity_cta")).size() != 0) {						
			String text1 = driver.findElement(By.id("Identity_cta")).getText();
			if(text1.equalsIgnoreCase("Enroll Now")) {
				Reporter.log("Enrollment is not completed, so cann't see the ID Theft Dashboard", true);
			}
			else {
				Actions builder = new Actions(driver);
				var_1 = driver.findElement(By.id("Identity_cta"));
				builder.moveToElement(var_1).click().build().perform();
				driver.switchTo().frame(driver.findElement(By.id("dspIframe")));
				if(driver.findElements(By.xpath("/html/body/div[2]/div/div/div/div[1]/img")).size() !=0) {
					screenshot("ID_Theft_Dashboard_Welcome_page");
					driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[1]/img")).click();
				}
				memberEnroll = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[2]/div/div[2]/div[1]/div/button")));
				screenshot("ITPS_dashboard" + uniqueID);
				List<WebElement> ID_Theft_Dashboard = driver.findElements(By.xpath("/html/body/div/div/div[1]/div[2]/div/div"));
				int no_of_tab = ID_Theft_Dashboard.size();
				System.out.println();
				Reporter.log("<--- ITPS Dashboard --->", true);
				Reporter.log("No of Taps in ID Theft dashboard: " + no_of_tab, true);
				if(no_of_tab>0) {				
					for(int i=1; i<=no_of_tab;i++) {					
						var_1 = driver.findElement(By.xpath("/html/body/div/div/div[1]/div[2]/div/div[" + i + "]"));
						var_1.click();
						Thread.sleep(2000);
						String value = var_1.getText();
						Reporter.log(value, true);
						screenshot("ID_Theft_Dashboard_Tab_" + i + "_" + uniqueID);
					}
					
				driver.switchTo().defaultContent();
				driver.findElement(By.xpath("/html/body/div[1]/header/div[2]/ul/li[2]/a")).click();
					
				}
			}
			}

		else
			{
				System.out.println();
				Reporter.log("User not enrolled to LifeLock", true);
			}
		}
		catch (Exception e) {
			e.getStackTrace();
			Reporter.log("Issue in ID Theft Dashboard");
			screenshot("Issue_in_ID_Theft_Dashboard");
			if(driver.findElements(By.xpath("/html/body/div[2]/header/div[2]/ul/li[2]/a")).size() != 0) {
				driver.findElement(By.xpath("/html/body/div[2]/header/div[2]/ul/li[2]/a")).click();
			}
			if(driver.findElements(By.xpath("/html/body/div[2]/div[3]/app-root/app-error/div/div/div[1]/div[2]")).size() != 0) {
				driver.findElement(By.xpath("/html/body/div[2]/div[3]/app-root/app-error/div/div/div[1]/div[2]")).click();
			}
			Assert.fail(e.toString());
		}
		}
		else {
			System.out.println();
			Reporter.log("LLPPE Execution Skipped", true);			
		}
		
	}
	
	@Test(priority = 12, dependsOnMethods={"A_SignUpSignIn"}, groups = {"all", "enrollment"})
	public static void CC_CreditTile() throws IOException, InterruptedException {
		
			if(driver.findElements(By.id("Credit_cta")).size() != 0) {
				Reporter.log("Credit tile Available", true);
			}
			else
			{
				Reporter.log("Credit tile Not Available", true);
			}
			screenshot("DashBoard_to_check_Credit_Tile");

	}
	
	@Test(priority = 13, dependsOnMethods={"A_SignUpSignIn"}, groups = {"all", "miniBA"})
	public static void BI_NOF() throws IOException, InterruptedException {
		if(configFileReader.executeNOF().equalsIgnoreCase("Yes")) {
		try {
		if(driver.findElements(By.id("ParentControl_cta")).size() != 0) {
			String nof_status = driver.findElement(By.id("ParentControl_cta")).getText();
			if(nof_status.equalsIgnoreCase("Set Up Family")) {
				driver.findElement(By.id("ParentControl_cta")).click();
				Thread.sleep(4000);
				screenshot("NOF_Setup_1");
				driver.findElement(By.id("nfAcceptTermsBtn")).click();
				screenshot("NOF_Setup_2");
				driver.findElement(By.xpath("//html/body/div[2]/div[3]/div/nf-root/div/nf-add-child/nf-fullscreen/div/div[2]/div[2]/nf-child-ui/form/div/div[2]/div[1]/input")).sendKeys("Child_selenium_Test");;
				driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/nf-root/div/nf-add-child/nf-fullscreen/div/div[2]/div[2]/nf-child-ui/form/div/div[2]/div[2]/div[2]/nf-context-menu/div/div/div/div")).click();
				driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/nf-root/div/nf-add-child/nf-fullscreen/div/div[2]/div[2]/nf-child-ui/form/div/div[2]/div[2]/div[2]/nf-context-menu/popper-content/div/div[1]/div[1]/div[1]/div[1]")).click();
				driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/nf-root/div/nf-add-child/nf-fullscreen/div/div[2]/div[2]/div[2]/button")).click();
				screenshot("NOF_Setup_3");
				driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/nf-root/div/nf-device-base/nf-choose-device/nf-fullscreen/div/div[2]/div/form/section[2]/div[3]")).click();
				driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/nf-root/div/nf-device-base/nf-choose-device/nf-fullscreen/div/div[2]/div/div/button")).click();
				screenshot("NOF_Setup_4");
				driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/nf-root/div/nf-device-base/nf-install-windows/nf-fullscreen/div/div[2]/div[1]/div/div/div[1]/label")).click();
				driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/nf-root/div/nf-device-base/nf-install-windows/nf-fullscreen/div/div[2]/div[1]/div/div[2]/a[1]")).click();
				screenshot("NOF_Setup_5");
				driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/nf-root/div/nf-device-base/nf-install-windows/nf-fullscreen/div/div[2]/div[1]/div/div[3]/div/nf-download-chrome/nf-fullscreen/div/div[1]/a")).click();
				screenshot("NOF_Setup_6");
				Reporter.log("NOF Setup Completed", true);
				driver.findElement(By.xpath("//*[@id=\"showTour\"]/div/div/div/div[4]/footer/div[2]/a")).click();
				screenshot("NOF_Setup_7");
				driver.findElement(By.xpath("/html/body/div[2]/header/div[2]/ul/li[2]/a")).click();
			}
			else {
				Reporter.log("NOF_Already_Registered", true);
				driver.findElement(By.id("ParentControl_cta")).click();
				Thread.sleep(5000);
				screenshot("NOF_8");
				if(driver.findElements(By.xpath("//*[@id=\"showTour\"]/div/div/div/div[4]/footer/div[2]/a")).size() != 0) {
					driver.findElement(By.xpath("//*[@id=\"showTour\"]/div/div/div/div[4]/footer/div[2]/a")).click();
				}
				if(driver.findElements(By.xpath("/html/body/div[2]/div[3]/div/nf-root/div/nf-dashboard/div/nf-parentapp-promo-banner/div[1]/div/nf-fullscreen/div/div[1]/a")).size() != 0) {
					driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/nf-root/div/nf-dashboard/div/nf-parentapp-promo-banner/div[1]/div/nf-fullscreen/div/div[1]/a")).click();
				}
				screenshot("NOF_9");
				driver.findElement(By.xpath("/html/body/div[2]/header/div[2]/ul/li[2]/a")).click();
			}
			

		}
		else {
			Reporter.log("NOF not available for this subscription", true);
		}
		}
		catch(Exception e) {
			Reporter.log(e.getMessage(), true);
			Assert.fail();
		}
		}
		else {
			System.out.println();
			Reporter.log("NOF not executed");
		}
		
	}
	
	@Test(priority=14, dependsOnMethods={"A_SignUpSignIn"}, groups = {"all", "miniBA", "enrollment"})
	public static void DWM( ) {
		try {
			if(configFileReader.DWMFlow().equalsIgnoreCase("Yes")) {
				driver.findElement(By.id("DarkWebMonitor_cta")).click();
				Thread.sleep(5000);
				screenshot("DWM_Dashboard");
				driver.findElement(By.xpath("/html/body/div[1]/header/div[2]/ul/li[2]/a")).click();
			}
		}
		catch (Exception e) {
			//driver.findElement(By.xpath("/html/body/div[1]/header/div[2]/ul/li[2]/a")).click();
			e.getStackTrace();
			Assert.fail(e.toString());
		}
	}
	
	@Test(priority=15, dependsOnMethods={"A_SignUpSignIn"}, groups = {"all", "miniBA", "enrollment"})
	public static void D_Subscription() throws IOException, AWTException, InterruptedException {
			
		try {
				if (driver.findElements(By.xpath("/html/body/div[2]/header/div[2]/ul/li[3]/a/span")).size() != 0) {
				var_1 = driver.findElement(By.xpath("/html/body/div[2]/header/div[2]/ul/li[3]/a/span"));
				}
				else {
					var_1 = driver.findElement(By.xpath("/html/body/div[2]/header/div[2]/ul/li[3]/ul/li[1]/a"));
				}
				
				Actions builder = new Actions(driver);
				builder.moveToElement(var_1).click().build().perform();	
				driver.findElement(By.xpath("//*[@id=\"User\"]/ul/li[1]/a")).click();
				System.out.println();
				Reporter.log("<--- License details --->", true);
				System.out.println();
				WebDriverWait wait = new WebDriverWait(driver, 30);
				download = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div[3]/app-root/app-my-account/div/app-hero/section/div/section/ul/li[1]/a")));
				List<WebElement> subscription_active = driver.findElements(By.xpath("/html/body/div[2]/div[3]/app-root/app-my-account/div/div/app-my-subscription/section/div/section/app-header/div"));
				int length = subscription_active.size();
				Reporter.log("Number of New license available for this Account: " + length, true);
				System.out.println();
			    for(int i=1; i<=length; i++)
			    {
			    	String element_cons = "/html/body/div[2]/div[3]/app-root/app-my-account/div/div/app-my-subscription/section/div/section/app-header/div["+i+"]/app-title-row/div/div/h3/span[2]";
			    	String license = driver.findElement(By.xpath(element_cons)).getText();
			    	Reporter.log(license, true);
			    	String element_cons_1 = "/html/body/div[2]/div[3]/app-root/app-my-account/div/div/app-my-subscription/section/div/section/app-header/div["+i+"]/app-title-row/div/div/div/div/span[1]";
			    	if(driver.findElements(By.xpath(element_cons_1)).size()!=0) {
			    	String device_used = driver.findElement(By.xpath(element_cons_1)).getText();
			    	Reporter.log(device_used, true);
			    	}
			    	else {
			    		Reporter.log("No of used License Details Not available", true);
			    	}
			    	String element_cons_2 = "/html/body/div[2]/div[3]/app-root/app-my-account/div/div/app-my-subscription/section/div/section/app-header/div["+i+"]/app-info-row/div/div[2]";
			    	String device_status = driver.findElement(By.xpath(element_cons_2)).getText();
					Reporter.log(device_status, true);
					String pin = "/html/body/div[2]/div[3]/app-root/app-my-account/div/div/app-my-subscription/section/div/section/app-header[1]/div["+i+"]/app-info-row/div/div[3]";
					if(driver.findElements(By.xpath(pin)).size()!=0) {
				    	String pin_details = driver.findElement(By.xpath(pin)).getText();
				    	Reporter.log(pin_details, true);
				    	}
					else {
						Reporter.log("Pin Detailes Not available", true);
					}
					System.out.println();
			    }
			    if(driver.findElements(By.xpath("/html/body/div[2]/div[3]/app-root/app-my-account/div/div/app-my-subscription/section/div/section/h3/span")).size()!=0) {
			    	driver.findElement(By.xpath("/html/body/div[2]/div[3]/app-root/app-my-account/div/div/app-my-subscription/section/div/section/h3/span")).click();
			    	System.out.println();
			    	Reporter.log("<-- Old subscription History -->");
			    	List<WebElement> subscription_active_history = driver.findElements(By.xpath("/html/body/div[2]/div[3]/app-root/app-my-account/div/div/app-my-subscription/section/div/section/app-header[2]/div"));
					int length_hostory = subscription_active_history.size();
					Reporter.log("Number of Old license available for this Account: " + length_hostory, true);
					System.out.println();
				    for(int i=1; i<=length_hostory; i++)
				    {
				    	String element_cons = "/html/body/div[2]/div[3]/app-root/app-my-account/div/div/app-my-subscription/section/div/section/app-header[2]/div["+i+"]/app-title-row/div/div/h3/span[2]";
				    	String license = driver.findElement(By.xpath(element_cons)).getText();
				    	Reporter.log(license, true);
				    	String element_cons_1 = "/html/body/div[2]/div[3]/app-root/app-my-account/div/div/app-my-subscription/section/div/section/app-header[2]/div["+i+"]/app-title-row/div/div/div/div/span[1]";
				    	if(driver.findElements(By.xpath(element_cons_1)).size()!=0) {
				    	String device_used = driver.findElement(By.xpath(element_cons_1)).getText();
				    	Reporter.log(device_used, true);
				    	}
				    	else {
				    		Reporter.log("No of used License Details Not available", true);
				    	}
				    	String element_cons_2 = "/html/body/div[2]/div[3]/app-root/app-my-account/div/div/app-my-subscription/section/div/section/app-header[2]/div["+i+"]/app-info-row/div/div[2]";
				    	String device_status = driver.findElement(By.xpath(element_cons_2)).getText();
						Reporter.log(device_status, true);						
						String pin_History = "/html/body/div[2]/div[3]/app-root/app-my-account/div/div/app-my-subscription/section/div/section/app-header[2]/div["+i+"]/app-info-row/div/div[3]";
						if(driver.findElements(By.xpath(pin_History)).size()!=0) {
					    	String pin_details_1 = driver.findElement(By.xpath(pin_History)).getText();
					    	Reporter.log(pin_details_1, true);
					    }
						else {
							Reporter.log("Pin Detailes Not available", true);
						}
						System.out.println();
				    }
			    }
				screenshot("MySubscription" + uniqueID);
			    driver.findElement(By.xpath("/html/body/div[2]/div[3]/app-root/app-my-account/div/app-hero/section/div/a")).click();
			    screenshot("Help_Content");
			    driver.findElement(By.xpath("/html/body/div[2]/div[3]/app-root/app-my-account/div/help/div/div/a")).click();
		    
		    }
		catch (Exception e) 
			{
			Reporter.log("Error: Issue in Subscriptions page", true);
			Reporter.log(e.getMessage(), true);
			Assert.fail();
			}
	}
	
	@Test(priority=16, dependsOnMethods={"A_SignUpSignIn"}, groups = {"all"})
	public static void E_Support() throws InterruptedException, IOException {
		
		String windowHandle = driver.getWindowHandle();
		try {
			driver.findElement(By.id("Support")).click();
			
			//Get the list of window handles
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			System.out.println("Number of tabs opened in current browser: " + tabs.size());
			//Use the list of window handles to switch between windows
			driver.switchTo().window(tabs.get(1));
			String url = driver.getCurrentUrl();
			Thread.sleep(4000);
			Reporter.log("Support URL: " + url, true);
			screenshot("Support Page");
			if(driver.findElements(By.id("sos-alt-htext")).size() != 0) {
			String support_contact = driver.findElement(By.id("sos-alt-htext")).getText();
			Reporter.log(support_contact, true);
			}	
			//Switch back to original window
			driver.switchTo().window(windowHandle);
		}
		catch (Exception e) {
			Reporter.log("Issue in Support Page redirection", true);
			screenshot("Support_Page_Issue");
			driver.switchTo().window(windowHandle);
			Reporter.log(e.getMessage(), true);
			Assert.fail();
		}
	}
		
	
	@Test(priority=17, dependsOnMethods={"A_SignUpSignIn"}, groups = {"all", "miniBA"})
	public static void F_SignOut() throws IOException, InterruptedException {
		try {
				Actions builder = new Actions(driver);			
				var_1 = driver.findElement(By.xpath("/html/body/div[2]/header/div[2]/ul/li[3]/a/span"));					
				builder.moveToElement(var_1).click().build().perform();	
				if(driver.findElements(By.xpath("/html/body/div[2]/header/div[2]/ul/li[3]/ul/li[5]/a")).size() != 0) {
						driver.findElement(By.xpath("/html/body/div[2]/header/div[2]/ul/li[3]/ul/li[5]/a")).click();
					}
					else if(driver.findElements(By.xpath("/html/body/div[2]/header/div[2]/ul/li[3]/ul/li[4]/a")).size() != 0) {
						driver.findElement(By.xpath("/html/body/div[2]/header/div[2]/ul/li[3]/ul/li[4]/a")).click();
					}
					else {
					driver.findElement(By.xpath("/html/body/div[2]/header/div[2]/ul/li[3]/ul/li[3]/a")).click();
					}
					screenshot("After_SignOut" + uniqueID);
				}
		catch (Exception e) {
			Reporter.log("Error: Issue in Sign out", true);
			Reporter.log(e.getMessage(), true);
			Assert.fail();
		}
			
	}
	
	@AfterTest(groups = {"all", "miniBA", "enrollment"})
	public static void closeBrowser() throws IOException, InterruptedException, ATUTestRecorderException {
		driver.quit();
		dir = new DirectoryCopyExample();
		dir.copyScreenshots(uniqueID);
		//recorder.stop();
		Thread.sleep(1000);
		//dir.copyMiniBAReport(uniqueID);
		
	}
	
	public static String PinGeneration() {
	
	try {
		System.out.println("<--- PIN Generation --->");
		pinpurchase.modifyPurchaseAPI(configFileReader.getpartnerID(), configFileReader.getpartnerUnitCode(), configFileReader.getskup(), configFileReader.getenv());
		pinpurchase.getSoapResponse(configFileReader.getenv());
		pin = pinpurchase.getResponseElement(configFileReader.getenv());
	}
	catch (Exception e) {
		System.out.println("Issue in Pin Generation");
	}
	return pin;
	
}

	public static boolean EB_enrollment_API(String email) {
	try {
		String partnerUserID = "EB_UserID_"+ uniqueID;
		eb_pinpurhase.modifyPurchaseAPI(configFileReader.getpartnerID(), configFileReader.getpartnerUnitCode(), configFileReader.getskup(), partnerUserID, configFileReader.getenv(), email, configFileReader.getSSN());
		eb_pinpurhase.getSoapResponse(configFileReader.getenv());
		status_code = eb_pinpurhase.getResponseElement(configFileReader.getenv());
		if(status_code.equalsIgnoreCase("0000")) {
			Reporter.log("Enrollment is sucessfull", true);
				return true;
		}
		else {
		return false;
		}
}
catch (Exception e) {
	System.out.println("Issue in EB _Pin Generation");
	return false;
}
	
}

	public static void StoreToDB(String username, String password, String pin) throws ClassNotFoundException {
	
	db = new Database();
	String pid = configFileReader.getpartnerID();
	String puid = configFileReader.getpartnerUnitId();
	String pucode = configFileReader.getpartnerUnitCode();
	String skup = configFileReader.getskup();
	String env = configFileReader.getenv();
	
	db.pinDetailsToDB(uniqueID, username, password, pid, puid, pucode, skup, pin, env);
	
}
	
	public static void screenshot(String str) throws IOException, InterruptedException 
	{
	str = increment + "_" + str;	
	Thread.sleep(1000);
	Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE, 500, true).withName(str).save();
	Thread.sleep(2000);
	increment++;
	
	}

	// Other then Telus
	
	public static void ITPS_enrollment_Credit() throws IOException, InterruptedException {
	try {
	WebDriverWait wait = new WebDriverWait(driver, 40);
	System.out.println();	
	memberEnroll = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("interstitialButton")));
	screenshot("MemberEnrollPage" + uniqueID);
	driver.findElement(By.id("interstitialButton")).click();
	memberEnroll = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstName")));
	screenshot("MemberEnrollPage_user_details" + uniqueID);
	String firstname = "Selenium";
	String lastname = "Lastname";
	driver.findElement(By.id("firstName")).sendKeys(firstname);
	driver.findElement(By.id("lastName")).sendKeys(lastname);
	driver.findElement(By.id("address")).sendKeys("900 Corporate Pointe");
	driver.findElement(By.id("zipCode")).sendKeys("123456");
	driver.findElement(By.id("city")).sendKeys("USA");
	Select drpCountry = new Select(driver.findElement(By.id("stateDropDown")));
	drpCountry.selectByValue("CA");
	driver.findElement(By.id("firstEnrollFormButton")).click();
	screenshot("memberEnroll_2" + uniqueID);
	driver.findElement(By.id("dob")).sendKeys("01011980");
	driver.findElement(By.id("phonenumber")).sendKeys("4247507464");
	driver.findElement(By.id("ssnOne")).sendKeys(configFileReader.getSSN());
	driver.findElement(By.id("ssnTwo")).sendKeys(configFileReader.getSSN());
	driver.findElement(By.xpath("/html/body/div[2]/div[3]/app-root/app-enroll/div[3]/div/div[2]/div/div/div/form[2]/div/div/span")).click();
	driver.findElement(By.id("secondEnrollFormButton")).click();
	memberEnroll = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div[3]/app-root/app-finish/div[1]/div/div[2]/div/div/div/div[2]/div[3]/div[2]/div")));
	screenshot("Enrollment_Success" + uniqueID);
	driver.findElement(By.xpath("/html/body/div[2]/div[3]/app-root/app-finish/div[1]/div/div[2]/div/div/div/div[2]/div[3]/div[2]/div")).click();
	Reporter.log("ITPS Enrollment Success");
	Thread.sleep(5000);
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	}
	
	public static void ITPS_enrollment_Non_Credit() throws IOException, InterruptedException {
		try {
		WebDriverWait wait = new WebDriverWait(driver, 40);
		System.out.println();	
		memberEnroll = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("interstitialButton")));
		screenshot("MemberEnrollPage" + uniqueID);
		driver.findElement(By.id("interstitialButton")).click();
		memberEnroll = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstName")));
		screenshot("MemberEnrollPage_user_details" + uniqueID);
		String firstname = "Selenium_" + uniqueID;
		String lastname = "highrisk";
		driver.findElement(By.id("firstName")).sendKeys(firstname);
		driver.findElement(By.id("lastName")).sendKeys(lastname);
		driver.findElement(By.id("address")).sendKeys("900 Corporate Pointe");
		driver.findElement(By.id("zipCode")).sendKeys("123456");
		driver.findElement(By.id("city")).sendKeys("USA");
		Select drpCountry = new Select(driver.findElement(By.id("stateDropDown")));
		drpCountry.selectByValue("CA");
		driver.findElement(By.id("firstEnrollFormButton")).click();
		screenshot("memberEnroll_2" + uniqueID);
		driver.findElement(By.id("dob")).sendKeys("01011970");
		driver.findElement(By.id("phonenumber")).sendKeys("4247507464");
		driver.findElement(By.id("ssnOne")).sendKeys(configFileReader.getSSN());
		driver.findElement(By.id("ssnTwo")).sendKeys(configFileReader.getSSN());
		driver.findElement(By.xpath("/html/body/div[2]/div[3]/app-root/app-enroll/div[3]/div/div[2]/div/div/div/form[2]/div/div/span")).click();
		driver.findElement(By.id("secondEnrollFormButton")).click();
		
		driver.findElement(By.xpath("/html/body/div[2]/div[3]/app-root/app-kba/div[3]/div/div[2]/div/div/div/form/div[1]/div[4]/div/input")).click();
		driver.findElement(By.xpath("/html/body/div[2]/div[3]/app-root/app-kba/div[3]/div/div[2]/div/div/div/form/div[2]/div[2]/div/input")).click();
		driver.findElement(By.xpath("/html/body/div[2]/div[3]/app-root/app-kba/div[3]/div/div[2]/div/div/div/form/div[3]/div[2]/div/input")).click();
		driver.findElement(By.id("kbaNextButton")).click();
		memberEnroll = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div[3]/app-root/app-error/div/div/div[2]/div/div/div/div[2]/div/div[2]/div")));
		String enrollment_status = driver.findElement(By.xpath("/html/body/div[2]/div[3]/app-root/app-error/div/div/div[2]/div/div/div/div[2]/div/div[1]")).getText();
		System.out.println();
		Reporter.log(enrollment_status, true);
		screenshot("Non_Credit_Enrollment_Success");
		driver.findElement(By.xpath("/html/body/div[2]/div[3]/app-root/app-error/div/div/div[2]/div/div/div/div[2]/div/div[2]/div")).click();
		System.out.println();
		Reporter.log("User made IDV Pass and KBA FAIL)", true);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		}

	//For Telus Enrollment
	public static void Telus_ITPS_Credit() throws IOException, InterruptedException {
	try
	{
			WebDriverWait wait = new WebDriverWait(driver, 30);
			System.out.println();
			Reporter.log("User Entered to Telus LLPPE process", true);	
			memberEnroll = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("interstitialButton")));
			screenshot("MemberEnrollPage" + uniqueID);
			driver.findElement(By.id("interstitialButton")).click();
			memberEnroll = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstName")));
			screenshot("MemberEnrollPage_user_details" + uniqueID);
			String firstname = "FirstSeleniumCredit" + uniqueID;
			String lastname = "LastTest" + uniqueID;
			String middlename = "Middlena" + uniqueID;
			driver.findElement(By.id("firstName")).sendKeys(firstname);
			driver.findElement(By.id("lastName")).sendKeys(lastname);
			driver.findElement(By.id("middleName")).sendKeys(middlename);
			Select suffix = new Select(driver.findElement(By.id("suffix")));
			suffix.selectByValue("SR");		
			driver.findElement(By.id("address")).sendKeys("22 Metropolitan Rd, Scarborough");
			driver.findElement(By.id("city")).sendKeys("Toronto");
			Select province = new Select(driver.findElement(By.id("stateDropDown")));
			province.selectByValue("ON");
			driver.findElement(By.id("zipCode")).sendKeys("M1R 2T5");	
			driver.findElement(By.id("firstEnrollFormButton")).click();
			screenshot("memberEnroll_2" + uniqueID);
			driver.findElement(By.id("dob")).sendKeys("19800101");
			driver.findElement(By.id("phonenumber")).sendKeys("416-293-8171");
			driver.findElement(By.id("ssnOne")).sendKeys(configFileReader.getSSN());
			driver.findElement(By.id("ssnTwo")).sendKeys(configFileReader.getSSN());
			driver.findElement(By.xpath("/html/body/div[2]/div[3]/app-root/app-enroll/div[3]/div/div[2]/div/div/div/form[2]/div/div/span")).click();
			driver.findElement(By.id("secondEnrollFormButton")).click();
			memberEnroll = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div[3]/app-root/app-kba/div[3]/div/div[2]/div/div/div/div[1]/div")));
			screenshot("Identity_Verification" + uniqueID);	

			driver.findElement(By.xpath("/html/body/div[2]/div[3]/app-root/app-kba/div[3]/div/div[2]/div/div/div/form/div[1]/div[2]/div/input")).click();
			driver.findElement(By.xpath("/html/body/div[2]/div[3]/app-root/app-kba/div[3]/div/div[2]/div/div/div/form/div[2]/div[2]/div/input")).click();
			driver.findElement(By.xpath("/html/body/div[2]/div[3]/app-root/app-kba/div[3]/div/div[2]/div/div/div/form/div[3]/div[2]/div/input")).click();
			driver.findElement(By.id("kbaNextButton")).click();
			memberEnroll = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div[3]/app-root/app-finish/div[1]/div/div[2]/div/div/div/div[2]/div[1]")));
			String enrollment_status = driver.findElement(By.xpath("/html/body/div[2]/div[3]/app-root/app-finish/div[1]/div/div[2]/div/div/div/div[2]/div[1]")).getText();
			System.out.println();
			Reporter.log(enrollment_status, true);
			screenshot("Credit_Enrollment_Success");
			driver.findElement(By.xpath("/html/body/div[2]/div[3]/app-root/app-finish/div[1]/div/div[2]/div/div/div/div[2]/div[3]/div[2]/div")).click();
			System.out.println();
			Reporter.log("User made IDENTITY VERIFCATION (Credit Activation PASS)", true);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	public static void Telus_ITPS_NON_CREDIT() {
		try {
			
			WebDriverWait wait = new WebDriverWait(driver, 30);
			System.out.println();
			Reporter.log("User Entered to Telus LLPPE process", true);	
			memberEnroll = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("interstitialButton")));
			screenshot("MemberEnrollPage" + uniqueID);
			driver.findElement(By.id("interstitialButton")).click();
			memberEnroll = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstName")));
			screenshot("MemberEnrollPage_user_details" + uniqueID);
			String firstname = "SeleniumNonCredit" + uniqueID;
			String lastname = "Test" + uniqueID;
			String middlename = "Middle" + uniqueID;
			driver.findElement(By.id("firstName")).sendKeys(firstname);
			driver.findElement(By.id("lastName")).sendKeys(lastname);
			driver.findElement(By.id("middleName")).sendKeys(middlename);
			Select suffix = new Select(driver.findElement(By.id("suffix")));
			suffix.selectByValue("SR");		
			driver.findElement(By.id("address")).sendKeys("22 Metropolitan Rd, Scarborough");
			driver.findElement(By.id("city")).sendKeys("Toronto");
			Select province = new Select(driver.findElement(By.id("stateDropDown")));
			province.selectByValue("ON");
			driver.findElement(By.id("zipCode")).sendKeys("M1R 2T5");	
			driver.findElement(By.id("firstEnrollFormButton")).click();
			screenshot("memberEnroll_2" + uniqueID);
			driver.findElement(By.id("dob")).sendKeys("19700101");
			driver.findElement(By.id("phonenumber")).sendKeys("416-293-8171");
			driver.findElement(By.id("ssnOne")).sendKeys(configFileReader.getSSN());
			driver.findElement(By.id("ssnTwo")).sendKeys(configFileReader.getSSN());
			driver.findElement(By.xpath("/html/body/div[2]/div[3]/app-root/app-enroll/div[3]/div/div[2]/div/div/div/form[2]/div/div/span")).click();
			driver.findElement(By.id("secondEnrollFormButton")).click();
			memberEnroll = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div[3]/app-root/app-kba/div[3]/div/div[2]/div/div/div/div[1]/div")));
			screenshot("Identity_Verification" + uniqueID);	
			
			driver.findElement(By.xpath("/html/body/div[2]/div[3]/app-root/app-kba/div[3]/div/div[2]/div/div/div/form/div[1]/div[4]/div/input")).click();
			driver.findElement(By.xpath("/html/body/div[2]/div[3]/app-root/app-kba/div[3]/div/div[2]/div/div/div/form/div[2]/div[2]/div/input")).click();
			driver.findElement(By.xpath("/html/body/div[2]/div[3]/app-root/app-kba/div[3]/div/div[2]/div/div/div/form/div[3]/div[2]/div/input")).click();
			driver.findElement(By.id("kbaNextButton")).click();
			memberEnroll = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div[3]/app-root/app-finish/div[3]/div/div[2]/div/div/div/div[2]/div[1]")));
			String enrollment_status = driver.findElement(By.xpath("/html/body/div[2]/div[3]/app-root/app-finish/div[3]/div/div[2]/div/div/div/div[2]")).getText();
			System.out.println();
			Reporter.log(enrollment_status, true);
			screenshot("Non_Credit_Enrollment_Success");
			driver.findElement(By.xpath("/html/body/div[2]/div[3]/app-root/app-finish/div[3]/div/div[2]/div/div/div/div[3]/div")).click();
			System.out.println();
			Reporter.log("User made IDENTITY VERIFCATION (Credit Activation FAIL)", true);
	Thread.sleep(5000);
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	}

	public static void Telus_IDV_FAIL_KBA_FAIL() throws IOException, InterruptedException {
		try
		{
		WebDriverWait wait = new WebDriverWait(driver, 30);
		System.out.println();
		Reporter.log("<-- Telus LLPPE IDV/KBA FAIL case -->", true);	
		memberEnroll = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("interstitialButton")));
		screenshot("MemberEnrollPage" + uniqueID);
		driver.findElement(By.id("interstitialButton")).click();
		memberEnroll = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstName")));
		screenshot("MemberEnrollPage_user_details" + uniqueID);
		String firstname = "Selenium_" + uniqueID;
		// For IDV Fail / KBA Fail
		String lastname = "kbabasicfail";
		String middlename = "Middle" + uniqueID;
		driver.findElement(By.id("firstName")).sendKeys(firstname);
		driver.findElement(By.id("lastName")).sendKeys(lastname);
		driver.findElement(By.id("middleName")).sendKeys(middlename);
		Select suffix = new Select(driver.findElement(By.id("suffix")));
		suffix.selectByValue("SR");		
		driver.findElement(By.id("address")).sendKeys("22 Metropolitan Rd, Scarborough");
		driver.findElement(By.id("city")).sendKeys("Toronto");
		Select province = new Select(driver.findElement(By.id("stateDropDown")));
		province.selectByValue("ON");
		driver.findElement(By.id("zipCode")).sendKeys("M1R 2T5");	
		driver.findElement(By.id("firstEnrollFormButton")).click();
		screenshot("memberEnroll_2" + uniqueID);
		driver.findElement(By.id("dob")).sendKeys("19700101");
		driver.findElement(By.id("phonenumber")).sendKeys("416-293-8171");
		driver.findElement(By.id("ssnOne")).sendKeys(configFileReader.getSSN());
		driver.findElement(By.id("ssnTwo")).sendKeys(configFileReader.getSSN());
		driver.findElement(By.xpath("/html/body/div[2]/div[3]/app-root/app-enroll/div[3]/div/div[2]/div/div/div/form[2]/div/div/span")).click();
		driver.findElement(By.id("secondEnrollFormButton")).click();
		memberEnroll = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div[3]/app-root/app-kba/div[3]/div/div[2]/div/div/div/div[1]/div")));
		screenshot("Identity_Verification" + uniqueID);
		
		driver.findElement(By.xpath("/html/body/div[2]/div[3]/app-root/app-kba/div[3]/div/div[2]/div/div/div/form/div[1]/div[4]/div/input")).click();
		driver.findElement(By.xpath("/html/body/div[2]/div[3]/app-root/app-kba/div[3]/div/div[2]/div/div/div/form/div[2]/div[2]/div/input")).click();
		driver.findElement(By.xpath("/html/body/div[2]/div[3]/app-root/app-kba/div[3]/div/div[2]/div/div/div/form/div[3]/div[2]/div/input")).click();
		driver.findElement(By.id("kbaNextButton")).click();
		memberEnroll = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div[3]/app-root/app-error/div/div/div[2]/div/div/div/div[2]/div/div[2]/div")));
		String enrollment_status = driver.findElement(By.xpath("/html/body/div[2]/div[3]/app-root/app-error/div/div/div[2]/div/div/div/div[2]/div/div[1]")).getText();
		System.out.println();
		Reporter.log(enrollment_status, true);
		screenshot("IDV_FAIL_KBA_FAIL");
		driver.findElement(By.xpath("/html/body/div[2]/div[3]/app-root/app-error/div/div/div[2]/div/div/div/div[2]/div/div[2]/div")).click();
		System.out.println();
		Reporter.log("IDV fail and KBA fail case verified", true);
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void Telus_creditActivate_Fail() {	
	}

	public static void creditActivate() {
	try {
		Reporter.log("Credit Activation Page Displayed", true);
		screenshot("Credit_Activation");
		driver.findElement(By.id("modal-cta")).click();
		driver.findElement(By.xpath("/html/body/div[2]/div[3]/app-root/app-creditactivation/div[1]/div/div[2]/div/div/div/div[3]/div")).click();
		Thread.sleep(2000);
		screenshot("Credit_Activation");
		driver.findElement(By.xpath("/html/body/div[2]/div[3]/app-root/app-creditactivation/div[2]/div/div[2]/div/div/div/form/div/div/span")).click();
		driver.findElement(By.xpath("/html/body/div[2]/div[3]/app-root/app-creditactivation/div[2]/div/div[2]/div/div/div/div[3]/div")).click();
		Thread.sleep(2000);
		screenshot("Credit_Activation");
		driver.findElement(By.xpath("/html/body/div[2]/div[3]/app-root/app-finish/div[4]/div/div[2]/div/div/div/div[2]/div[3]/div[2]/div")).click();
		Thread.sleep(2000);
		
	}
	catch (Exception e) {
		Reporter.log("Issue in Credit Activation");
		e.getMessage();
		Assert.fail(e.toString());
	}
}
	
	public static void DWMactivate() {
		try {
			Reporter.log("DWM Agreement Page Displayed", true);
			screenshot("DWM_Agreement");
			driver.findElement(By.id("modal-cta")).click();
			Thread.sleep(2000);
		}
		catch (Exception e) {
			Reporter.log("Issue in DWM Agreement Page");
			e.getMessage();
			Assert.fail(e.toString());
		}
	}

}
