package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Topic_03_XpathTest {
	WebDriver driver;
 
 
  @BeforeTest
  public void beforeTest() {
	  	driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
  }
 // @Test
  public void TC01_LoginEmptyEmailPass() {
	  driver.get("http://live.demoguru99.com/");
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	  driver.findElement(By.id("email")).sendKeys("");
	  driver.findElement(By.id("pass")).sendKeys("");
	  driver.findElement(By.id("send2")).click();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  String a = driver.findElement(By.id("advice-required-entry-pass")).getText();
	  Assert.assertEquals(a, "This is a required field.");
  }
  //@Test
  public void TC01_LoginInvalidateEmail() {
	  driver.get("http://live.demoguru99.com/");
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	  driver.findElement(By.id("email")).sendKeys("duong@123");
	  driver.findElement(By.id("pass")).sendKeys("123456");
	  driver.findElement(By.id("send2")).click();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  String a = driver.findElement(By.id("advice-validate-email-email")).getText();
	  Assert.assertEquals(a, "Please enter a valid email address. For example johndoe@domain.com.");
  }
 // @Test
  public void TC01_LoginPassLess6() {
	  driver.get("http://live.demoguru99.com/");
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	  driver.findElement(By.id("email")).sendKeys("duong@gmail.com");
	  driver.findElement(By.id("pass")).sendKeys("1234");
	  driver.findElement(By.id("send2")).click();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  String a = driver.findElement(By.id("advice-validate-password-pass")).getText();
	  Assert.assertEquals(a, "Please enter 6 or more characters without leading or trailing spaces.");
  }
 // @Test
  public void TC01_LoginIncorrectPass() {
	  driver.get("http://live.demoguru99.com/");
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	  driver.findElement(By.id("email")).sendKeys("duong@gmail.com");
	  driver.findElement(By.id("pass")).sendKeys("123123123");
	  driver.findElement(By.id("send2")).click();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  String a = driver.findElement(By.xpath("//li[@class='error-msg']")).getText();
	  Assert.assertEquals(a, "Invalid login or password.");
  }
  @Test
  public void TC01_LoginCreateAccount() {
	  driver.get("http://live.demoguru99.com/");
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	  driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
	  driver.findElement(By.id("firstname")).sendKeys("quy");
	  driver.findElement(By.id("lastname")).sendKeys("duong");
	  String email = "quyduong"+randomNumber()+"@gmail.com";
	  driver.findElement(By.id("email_address")).sendKeys(email);
	  driver.findElement(By.id("password")).sendKeys("123456");
	  driver.findElement(By.id("confirmation")).sendKeys("123456");
	  driver.findElement(By.xpath("//button[@title='Register']")).click();
	
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  String messAccInfor = driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText();
	  Assert.assertEquals(messAccInfor, "Thank you for registering with Main Website Store.");
	  //Name
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p[contains(text(),'quy duong')]")).isDisplayed());
	  //email
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p[contains(.,'"+email+"')]")).isDisplayed());
	  //click acccount 
	  driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
	  //cick log out
	  driver.findElement(By.xpath("//div[@id='header-account']//a[@title='Log Out']")).click();
	  // cho cho elemaent hien thi 
	  Assert.assertTrue(driver.findElement(By.xpath("//img[contains(@src,'logo.png')]")).isDisplayed());
	 
  }
  public int randomNumber() {
	  Random rand = new Random();
	  return rand.nextInt(99);
  }
  @AfterTest
  public void afterTest() {
		driver.quit();
  }

}
