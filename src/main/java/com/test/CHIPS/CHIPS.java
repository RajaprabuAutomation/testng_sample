package com.test.CHIPS;

import java.util.List;

import org.apache.xerces.util.SynchronizedSymbolTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import lombok.experimental.var;

public class CHIPS {
	
	static WebDriver driver;
	static WebElement var_1,var_2;
	
	public static void main(String args[]) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Browser_Driver\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://ltdb.lab.norton.com/");
		driver.findElement(By.xpath("/html/body/form/div[4]/table/tbody/tr[1]/td[1]/table/tbody/tr/td[1]/table/tbody/tr/td/a")).click();
		Thread.sleep(2000);
		Actions builder = new Actions(driver);			
		var_1 = driver.findElement(By.xpath("/html/body/form/div[4]/div/div/div[2]/div/div[1]/table/tbody/tr/td[1]/table/tbody/tr[3]/td/div/span[2]/div/ul/li/input"));
		Thread.sleep(2000);
		var_1.sendKeys("           NGMPSOS");
		Thread.sleep(2000);
		builder.moveToElement(driver.findElement(By.xpath("/html/body/form/div[4]/div/div/div[2]/div/div[1]/table/tbody/tr/td[1]/table/tbody/tr[3]/td/div/span[2]/div/div/ul/li"))).click().build().perform();
		driver.findElement(By.xpath("/html/body/form/div[4]/div/div/div[2]/div/div[1]/input")).click();
		
		List<WebElement> MRFs = driver.findElements(By.xpath("/html/body/form/div[4]/div/div/div[2]/div/div[2]/table/tbody/tr"));
		int number_of_MRF = MRFs.size()-1;
		System.out.println("Total Number of MRFs: " + number_of_MRF);
		
		for(int i=2; i<=number_of_MRF; i++)
		{
			String puid = driver.findElement(By.xpath("/html/body/form/div[4]/div/div/div[2]/div/div[2]/table/tbody/tr[" + i + "]/td[8]")).getText();
			//System.out.println(puid);
			if(puid.equalsIgnoreCase("1365")) {
				driver.findElement(By.xpath("/html/body/form/div[4]/div/div/div[2]/div/div[2]/table/tbody/tr[" + i + "]/td[1]/a")).click();
				Thread.sleep(2000);
				String MRFName = driver.findElement(By.xpath("/html/body/form/div[4]/div/div/div[2]/div[1]/div[1]/table/tbody/tr/td/div[1]/span[2]/span")).getText();
				System.out.println(MRFName);
				chipsParameter();
				break;
			}
		}
		
	}
	
	public static void chipsParameter() {
		//Uncategorized
		
		List<WebElement> configParameters = driver.findElements(By.xpath("/html/body/form/div[4]/div/div/div[2]/div[1]/div[2]/div[2]/div/div/table[2]/tbody/tr"));
		int size = configParameters.size();
		System.out.println("Number of Parameters: " + (size-6));
		System.out.println();
		
		for(int i=2; i<size; i++) {
			if(driver.findElements(By.xpath("/html/body/form/div[4]/div/div/div[2]/div[1]/div[2]/div[2]/div/div/table[2]/tbody/tr[" + i + "]/td/span")).size() != 0){
				WebElement configValue;
				WebElement configName = driver.findElement(By.xpath("/html/body/form/div[4]/div/div/div[2]/div[1]/div[2]/div[2]/div/div/table[2]/tbody/tr[" + i + "]/td/span"));
				String value = configName.getText();
				//System.out.println(value);
				//if(value.equalsIgnoreCase("PromotionBanners_path")) {
					System.out.println(value);
					if(driver.findElements(By.xpath("/html/body/form/div[4]/div/div/div[2]/div[1]/div[2]/div[2]/div/div/table[2]/tbody/tr[" + i + "]/td[2]/span/input")).size() != 0) {
						configValue = driver.findElement(By.xpath("/html/body/form/div[4]/div/div/div[2]/div[1]/div[2]/div[2]/div/div/table[2]/tbody/tr[" + i + "]/td[2]/span/input"));
						parameterCheckbox(configValue);
					}
					else {
						configValue = driver.findElement(By.xpath("/html/body/form/div[4]/div/div/div[2]/div[1]/div[2]/div[2]/div/div/table[2]/tbody/tr[" + i + "]/td[2]/span"));
						parameterValueCheck(configValue);
					}
				//	break;
			//	}
			}
			else {
				WebElement configName = driver.findElement(By.xpath("/html/body/form/div[4]/div/div/div[2]/div[1]/div[2]/div[2]/div/div/table[2]/tbody/tr[" + i + "]/td"));
				String value = configName.getText();
				System.out.println("<---" + value + "--->");
				System.out.println();
			}
		}
		
	}
	
/*	public static void enterValue(String str) {
		
		if(str.equalsIgnoreCase("Common_show_add_new_device"));
	}*/
	
	public static void parameterCheckbox(WebElement configValue) {
		
		 if(configValue.isSelected()) {
			System.out.println("Check Box is Checked");
			System.out.println();
		}
		else if(!configValue.isSelected()){
			System.out.println("Check Box is unchecked");
			System.out.println();
		}		
	}
	

	public static void parameterValueCheck(WebElement configValue) {			
		String value = configValue.getText();
		if(value.isEmpty()) {
			System.out.println("** Value is Empty ***");
			System.out.println();
		}
		else {
			System.out.println(value);
			System.out.println();
		}
	}
	

}
