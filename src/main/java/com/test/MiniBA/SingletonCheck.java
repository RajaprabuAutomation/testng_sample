package com.test.MiniBA;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;
import com.test.configurations.ConfigFileReader;
import com.test.configurations.EB_PinPurchase;
import com.test.configurations.PinPurchase;

public class SingletonCheck {
	
	static WebDriver driver;
	
	static EB_PinPurchase eb_pinpurhase;
	static PinPurchase pinpurchase;
	static int increment = 1;
	static ConfigFileReader configFileReader;
	
	static String pin;
	
	@Parameters("browser")
	@BeforeTest
	public static void browserStartSinleton(String browser) {
		
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
		     	ConfigFileReader.ConfigFileReader_1(); 
				}
			catch (Exception e) {
				System.out.println("Browser Not opened");
				e.getMessage();
			}
	}
	
	@Test
	public static void singletonCheck() throws IOException, InterruptedException {
		try {
		String userName = configFileReader.getUserName();
		String password = configFileReader.getPassword();
		String pin = PinGeneration();
		String ngpURL = configFileReader.getngpURL() + configFileReader.getpartnerUnitId() + "&pin=" + pin + "&skup=" + configFileReader.getskup() + "&management=1";
		System.out.println(ngpURL);
		driver.get(ngpURL);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement var_1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginUsername")));
		screenshot("WelcomePage");
		Reporter.log("UserName: " + userName);
		driver.findElement(By.id("loginUsername")).sendKeys(userName);
		driver.findElement(By.id("loginPassword")).sendKeys(password);
		Thread.sleep(2000);
		driver.findElement(By.id("signin_button")).click();
		System.out.println();
		Thread.sleep(5000);
		screenshot("Singleton");
		if(driver.findElements(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div/div/div[1]")).size() != 0) {
			String text = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div/div/div[1]")).getText();
			Reporter.log(text, true);
			driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div/div/div[2]/a")).click();
			
		}
		else {
			Reporter.log("No existing Subscription found", true);
		}
		}
		catch (Exception e) {
			e.getStackTrace();
			Assert.fail(e.toString());
		}
		
	}
	
	@AfterTest
	public static void closeBrowser() {
		//driver.quit();
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
	
	public static void screenshot(String str) throws IOException, InterruptedException 
	{
	str = increment + "_" + str;	
	Thread.sleep(1000);
	Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE, 500, true).withName(str).save();
	Thread.sleep(2000);
	increment++;
	
	}
}
