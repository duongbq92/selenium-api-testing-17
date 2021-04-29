package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Topic_05_Textbox_TextArea {
	WebDriver driver;
	String userID, passWord, loginPageUrl;
	String name, dateOfBirth, address, city, state, pin, phone, email;
	By customerNamTextbox = By.name("name");
	By datBrithTextbox = By.name("dob");
	By addressTextbox = By.name("addr");
	By cityNamTextbox = By.name("city");
	By stateNamTextbox = By.name("state");
	By pinNamTextbox = By.name("pinno");
	By moblieNamTextbox = By.name("telephoneno");
	By emailNamTextbox = By.name("emailid");
	By passwordNamTextbox = By.name("password");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDriverChrome\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4/");
		loginPageUrl = driver.getCurrentUrl();
		
		name = "Hana";
		dateOfBirth = "2020-02-01";
		address = "anh ngu dong duong";
		city = "Sai Gon";
		state = "mien nam";
		pin = "123456";
		phone = "092313932";
		email = "quyduong"+randomNumber()+"@gmail.com";
		//passWord = "";

	}

	@Test
	public void TC_01_Register() {
		driver.get("http://demo.guru99.com/v4/");
		// Click to "Here" link
		driver.findElement(By.xpath("//a[text()='here']")).click();
		// input email to textbox
		driver.findElement(By.name("emailid")).sendKeys(email);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// Click submit
		driver.findElement(By.name("btnLogin")).click();
		// get ra userId va Password value
		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		passWord = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
	}

	@Test
	public void TC_02_Login() {
		// lay du lieu de login
		driver.get(loginPageUrl);
		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(passWord);
		driver.findElement(By.name("btnLogin")).click();
		// verify navigate to Manager page success
		Assert.assertEquals(driver.findElement(By.xpath("//marquee[@class='heading3']")).getText(),
				"Welcome To Manager's Page of Guru99 Bank");
	}

	@Test
	public void TC_03_InputNewCustomer() {
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		driver.findElement(customerNamTextbox).sendKeys(name);
		// remove attribute type = date 
		removeAttributeInDOM("//input[@name='dob']","type");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(datBrithTextbox).sendKeys(dateOfBirth);
		driver.findElement(addressTextbox).sendKeys(address);
		driver.findElement(cityNamTextbox).sendKeys(city);
		driver.findElement(stateNamTextbox).sendKeys(state);
		driver.findElement(pinNamTextbox).sendKeys(pin);
		driver.findElement(moblieNamTextbox).sendKeys(phone);
		driver.findElement(emailNamTextbox).sendKeys(email);
		driver.findElement(passwordNamTextbox).sendKeys(passWord);
		
		driver.findElement(By.name("sub")).click();
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//verify input data 
//		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")), name);
//		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")),dateOfBirth );
//		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")), address);
//		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")), city);
//		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")), state);
//		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")), pin);
//		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")), phone);
//		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")), email);
		
	}

	public void removeAttributeInDOM( String locator, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
	}
	public int randomNumber() {
		  Random rand = new Random();
		  return rand.nextInt(99);
	  }
	@AfterClass
	public void afterClass() {
//		driver.quit();
	}

}