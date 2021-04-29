package webdriver;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Wait_P2_Find_Implicit {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
//		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.demoguru99.com/");
		
	}

//	@Test
	public void TC_01_FindElement() {
		// 1 element
		System.out.println("1 Start : "+getDateTimeNow());
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		System.out.println("1 end : "+getDateTimeNow());
		//  > 1 element
		driver.navigate().back();
		System.out.println("2 Start : "+getDateTimeNow());
		driver.findElement(By.xpath("//a[text()='My Account']"));
		System.out.println("2 End : "+getDateTimeNow());
		// khong tim thay element nao
		driver.navigate().back();
		System.out.println("3 Start : "+getDateTimeNow());
		try {
			driver.findElement(By.xpath("//a[text()='Create']")).click();
		}catch (Exception e) {
			System.out.println("3 End : "+getDateTimeNow());
		}
		
		
	}

//	@Test
	public void TC_02_FindElements() {
		List<WebElement> elements ;
		// 1 element
		System.out.println("1 Start : "+getDateTimeNow());
		elements = driver.findElements(By.xpath("//div[@class='footer']//a[text()='My Account']"));
		System.out.println("Elements = "+elements.size());
		System.out.println("1 end : "+getDateTimeNow());
		//  > 1 element
//		driver.navigate().back();
		System.out.println("2 Start : "+getDateTimeNow());
		elements = driver.findElements(By.xpath("//a[text()='My Account']"));
		System.out.println("Elements = "+elements.size());
		System.out.println("2 End : "+getDateTimeNow());
		// khong tim thay element nao
//		driver.navigate().back();
		System.out.println("3 Start : "+getDateTimeNow());
		elements = 	driver.findElements(By.xpath("//a[text()='Create']"));
			System.out.println("Elements = "+elements.size());
			System.out.println("3 End : "+getDateTimeNow());
	}

	@Test
	public void TC_03_() {
		
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//button[(text()='Start')]")).click();
		// fail if implicitly 1s, else pass implicitly > 1s
		Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());
	}

	@Test
	public void TC_04_() {
		driver.get("");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public String getDateTimeNow() {
		Date date = new Date();
		return date.toString();
	}

}