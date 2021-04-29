package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Wait_P1_Element_Status {
	WebDriver driver;
	WebDriverWait explicitWait;
	String source_folder = System.getProperty("user.dir");
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", source_folder +"\\browserDriverChrome\\chromedriverVer84.exe" );
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 10);
		
		driver.manage().window().maximize();
		
	}

//	@Test
	public void TC_01_() {
		driver.get("https://www.facebook.com/");
		// wait for email visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
		
	}

	@Test
	public void TC_02_InVisible() {
		driver.get("http://live.demoguru99.com/");
		// Element co trong DOM + hien thi len UI
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='footer']//a[text()='My Account']")));
		// 2.1 Element co trong dom + ko hien thi tren UI
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='header-account']//a[text()='My Account']")));
		// 2.2 khong co trong dom + ko hien thi trong UI
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='headerXXXX-account']//a[text()='My Account']")));
		// Presence
		// 3.1 co trong DOM + hien thi tren UI
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='footer']//a[text()='My Account']")));
		// 3.2 Element co trong Dom + khong hien thi tren UI
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='header-account']//a[text()='My Account']")));
		//
		
	}

	@Test
	public void TC_03_Staleness() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		 driver.findElement(By.cssSelector("#SubmitCreate")).click(); 
		 /// page status A
		 WebElement emailInvalid = driver.findElement(By.cssSelector("#create_account_error"));
		 // page status B
		 driver.navigate().refresh();
		 // emailInvalid bi staleness
		 // 4 - Element ko co trong Dom ( bi staleness
		 explicitWait.until(ExpectedConditions.stalenessOf(emailInvalid));
	}

	@Test
	public void TC_04_() {
		driver.get("");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}