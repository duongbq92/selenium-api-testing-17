package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Button_Radio_Checkbox {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	// checkbox
	By firstCheckbox = By.xpath("//input[@id='eq1']");
	By secoundCheckbox = By.xpath("//input[@id='eq4']");
	By lastCheckbox = By.xpath("//input[@id='eq5']");
	// radio
	By firstRadio = By.xpath("//input[@id='engine3']");
	By secondRadio = By.xpath("//input[@id='engine5']");
	By lastRadio  = By.xpath("//input[@id='engine6']");
	
	// all check box
	By allCheckbox  = By.xpath("//ul[@class='fieldlist']");
	
	

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		jsExecutor =  (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

//	@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		// Navigate Login
		driver.findElement(By.cssSelector(".popup-login-tab .popup-login-tab-login")).click();
		WebElement loginButton = driver.findElement(By.cssSelector(".fhs-btn-login"));
		// verifi loginbutton is disable
		Assert.assertFalse(loginButton.isEnabled());
		 // iput to email and passowrd
		driver.findElement(By.cssSelector("#login_username")).sendKeys("duong@gmail.com");
		driver.findElement(By.cssSelector("#login_password")).sendKeys("0909090909");
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector(".fhs-btn-login")).click(); 
		// verify enable
		Assert.assertTrue(loginButton.isEnabled());
		
		driver.findElement(By.cssSelector("#login_username")).clear();
		driver.findElement(By.cssSelector("#login_password")).clear();
		sleepInSecond(3);
		removeDisableAttribte(loginButton);
		sleepInSecond(2);
		loginButton.click();
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='login_username']/parent::div/following-sibling::div")).getText(), "Thông tin này không thể để trống");
		sleepInSecond(2);
	}

//	@Test
	public void TC_02_Default_Radido_Checkbox() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		
//		Assert.assertFalse(driver.findElement(firstCheckbox).isSelected());
//		Assert.assertFalse(driver.findElement(secoundCheckbox).isSelected());
//		Assert.assertFalse(driver.findElement(lastCheckbox).isSelected());
		
		driver.findElement(firstCheckbox).click();
//		driver.findElement(secoundCheckbox).click();
//		driver.findElement(lastCheckbox).click();
		
		sleepInSecond(1);
		driver.navigate().refresh();
		List<WebElement> checkboxes = driver.findElements(allCheckbox);
		for (WebElement check : checkboxes) {
			check.click();
			sleepInSecond(1);
		}
		// verify select
		
//		for (WebElement check : checkboxes) {
//			Assert.assertTrue(check.isSelected());
//		}
		// click de select
		for (WebElement check : checkboxes) {
			check.click();
			sleepInSecond(1);
		}
		
		// verify deselect
//		for (WebElement check : checkboxes) {
//			Assert.assertFalse(check.isSelected());
//			
//		}
		
//		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
//		Assert.assertFalse(driver.findElement(firstRadio).isSelected());
//		Assert.assertFalse(driver.findElement(secondRadio).isSelected());
//		Assert.assertFalse(driver.findElement(lastRadio).isSelected());
//		
//		driver.findElement(firstRadio).click();
//		driver.findElement(secondRadio).click();
//		driver.findElement(lastRadio).click();
//		sleepInSecond(3);
//		Assert.assertTrue(driver.findElement(firstRadio).isSelected());
//		Assert.assertTrue(driver.findElement(secondRadio).isSelected());
//		Assert.assertTrue(driver.findElement(lastRadio).isSelected());
		
	}

	@Test
	public void TC_03_Custom() {
		driver.get("https://material.angular.io/components/radio/examples");
		By checkbox = By.xpath("//*[contains(text(),'Summer')]/preceding-sibling::div/input");
		// click 
		clickByJavascripts(driver.findElement(checkbox));
		// verify checkbox is select
		Assert.assertTrue(driver.findElement(checkbox).isSelected());
	}

	@Test
	public void TC_04_() {
		driver.get("");
	}
	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void removeDisableAttribte(WebElement element) {
		jsExecutor.executeScript("arguments[0].removeAttribute('disable')", element);
	}
	public void clickByJavascripts(WebElement element) {
		jsExecutor.executeScript("arguments[0].click()", element);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}