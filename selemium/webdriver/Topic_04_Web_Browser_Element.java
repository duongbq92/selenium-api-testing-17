package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.FindActiveElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Web_Browser_Element {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	// comment
	public void TC_01_Web_Browser() {
		// Open app need to test (AUT: application/ System Under Testing )
		driver.get("https://www.youtube.com/");
		// close tag in active - if only tag close browser
		//driver.close();
		// close browser
		//driver.quit();
		// get url page now 
		String loginUrl = driver.getCurrentUrl();
		System.out.println(loginUrl);
		Assert.assertEquals(loginUrl, "https://www.youtube.com/");
		// get title 
		String loginPageTitle = driver.getTitle();
		System.out.println(loginPageTitle);
		// HTML/ CSS / JS /
		driver.getPageSource(); // constain
		//windown tag
		String pageWindownID = driver.getWindowHandle();
		// waiting find element about 10s 
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// waiting page load done 
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		// waiting JS load done
		// JS Excutor (nhung doan code js vao browser / emlement)
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// Test GUI: Graphic user interface 
		// font // size / color / location / position / reponsive 
		// set / get position 
		// set / get size/ 
		driver.navigate().back();
		driver.navigate().forward();
		driver.navigate().refresh();
		driver.navigate().to("youtube.com");
		// 3 loaij switch to 
		//windown
		//alert
		// frame / iframe
		driver.switchTo().alert();
		driver.switchTo().window("");
		driver.switchTo().frame("");
		
	
		
	}

	@Test
	public void TC_02_Web_Element() throws InterruptedException {
		// thao tac vs 1 element
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		if(isEmplementDisplayed("mail")) {
			driver.findElement(By.id("mail")).sendKeys("Quy duong");
			Thread.sleep(2000);
		}
		if(isEmplementDisplayed("edu")) {
			driver.findElement(By.id("edu")).sendKeys("Quy duong");
			Thread.sleep(2000);
		}
		if(isEmplementDisplayed("under_18")) {
			driver.findElement(By.id("under_18")).click();
			Thread.sleep(2000);
		}
		
		Assert.assertTrue(isEmplementEnable("mail"));
		Assert.assertFalse(isEmplementEnable("password"));
		Assert.assertTrue(isEmplementEnable("under_18"));
		driver.findElement(By.id("under_18")).click();
		driver.findElement(By.id("development")).click();
		Assert.assertTrue(driver.findElement(By.id("under_18")).isSelected());
		Assert.assertTrue(driver.findElement(By.id("development")).isSelected());
		driver.findElement(By.id("under_18")).click();
		driver.findElement(By.id("development")).click();
		Assert.assertTrue(driver.findElement(By.id("under_18")).isSelected());
		Assert.assertFalse(driver.findElement(By.id("development")).isSelected());
	}

	@Test
	public void TC_03_() {
		driver.get("");
	}

	@Test
	public void TC_04_() {
		driver.get("");
	}
	public boolean isEmplementDisplayed(String idValue) {
		WebElement element = driver.findElement(By.id(idValue));
		if(element.isDisplayed()) {
			System.out.println("Element with xpath "+idValue+"is displayed");
			return true;
		}
		else {
			System.out.println("Element with xpath "+idValue+"is displayed");
			return false;
		}
		
	}
	public boolean isEmplementEnable(String idValue) {
		WebElement element = driver.findElement(By.id(idValue));
		if(element.isEnabled()) {
			System.out.println("Element with xpath "+idValue+"is enable");
			return true;
		}
		else {
			System.out.println("Element with xpath "+idValue+"is enable");
			return false;
		}
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}