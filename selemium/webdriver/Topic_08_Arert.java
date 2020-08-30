package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Arert {
	WebDriver driver;
	Alert alert;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	public void TC_01_Alert() {
		/*----------------------1 Alert accept--------------*/
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		// switch to alert
		alert = driver.switchTo().alert();
		// verify alert text
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		sleepInSecound(2);
		// accept alert 
		alert.accept();
		// verify accept alert success 
		Assert.assertEquals(driver.findElement(By.xpath(".//p[@id='result']")).getText(), "You clicked an alert successfully");
		/*----------------------2-Alert Confirm--------------*/
		driver.navigate().refresh();
		// click buuton 
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		// switch alert 
		alert = driver.switchTo().alert();
		// verify alert text
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		//verify alert dismiss or success
		alert.accept();
		sleepInSecound(2);
		//verify alert success
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked: Ok");
		sleepInSecound(2);
		/*----------------------3-Alert Prompt--------------*/
		driver.navigate().refresh();
		// click buuton 
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		// switch alert 
		alert = driver.switchTo().alert();
		// verify alert text
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		// send key to alert 
		alert.sendKeys("Duong dep trai");
		sleepInSecound(3);
		//verify alert dismiss or success
		alert.accept();
		sleepInSecound(3);
		//verify alert success
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You entered: Duong dep trai");
		sleepInSecound(2);
		
		
		
	}

//	@Test
	public void TC_02_Authentication_Alert() {
		String username = "admin";
		String password = "admin";
		// authentication via link not open alert 
		driver.get("http://"+username+":"+password+"@"+"the-internet.herokuapp.com/basic_auth");
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials')]")).isDisplayed());
		
	}

	@Test
	public void TC_03_Authentication_Alert() {
		String username = "admin";
		String password = "admin";
		driver.get("http://the-internet.herokuapp.com/");
		// click to Basic authen
		
		// get link href
		String linkAu = driver.findElement(By.xpath("//a[contains(text(),'Basic Auth')]")).getAttribute("href"); 
		sleepInSecound(3);
		handleAuthenticationAlert(linkAu,username,password);
		sleepInSecound(3);
		
	}
	public void handleAuthenticationAlert(String linkAu, String username, String passwrod  ) {
		// http://the-internet.herokuapp.com/
		String splitLink[] = linkAu.split("//");
		linkAu = splitLink[0]+ "//"+username+ ":" +passwrod+ "@" +splitLink[1];
		// 0 - http:
		driver.get(linkAu);
		// 1 - the-internet.herokuapp.com/basic_auth
	}

	
	public void sleepInSecound(long time) {
		try {
			Thread.sleep(time*100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}