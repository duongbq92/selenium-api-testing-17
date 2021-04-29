package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Popup {
	WebDriver driver;
	WebDriverWait explicitWait;
	boolean status;
	@BeforeClass
	public void beforeClass() {
//		System.setProperty("webdriver.chrome.driver", ".\\browserDriverChrome\\chromedriverVer84.exe");
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

//	@Test
	public void TC_01_() {
		driver.get("https://www.zingpoll.com/");
		driver.findElement(By.id("Loginform")).click();
		sleepInsecound(3);
		// loggin popup hien thi
		status = driver.findElement(By.id("Login")).isDisplayed();
		System.out.println("Login popup hien thi =" +status);
		Assert.assertTrue(status);
		
		// close popup 
		driver.findElement(By.cssSelector("#Login .close")).click();
		sleepInsecound(2);
		status = driver.findElement(By.id("Login")).isDisplayed();
		System.out.println("Login popup hien thi =" +status);
		Assert.assertFalse(status);
		
		// loggin popup hien thi
		driver.findElement(By.id("Loginform")).click();
		
		driver.findElement(By.id("loginEmail")).sendKeys("automationfc.vn@gmail.com");
		driver.findElement(By.id("loginPassword")).sendKeys("automationfc");
		sleepInsecound(3);
		driver.findElement(By.id("button-login")).click();
		
//		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='username'  and contains(text(),'Automation Testing')]")).isDisplayed());
//		String a = driver.findElement(By.xpath("//div[@class='username'  and contains(text(),'Automation Testing') ]")).getText();
//		System.out.println(a);
	}

	@Test
	public void TC_02_Popup_Random() {
		driver.get("https://blog.testproject.io/");
		sleepInsecound(6);
		if(driver.findElement(By.xpath("//div[@class='mailch-wrap rocket-lazyload']")).isDisplayed()) {
			sleepInsecound(2);
			System.out.println("Into Case IF");
			String a = driver.findElement(By.xpath("//a[@class='link']")).getText();
			System.out.println("button: "+a);
			// verify button SIGN UP NOW
			Assert.assertEquals(a, "SIGN UP NOW");
			//Close popup 
			driver.findElement(By.id("close-mailch")).click();
		}
			// sendkeys selemium
			driver.findElement(By.cssSelector("#search-2 .search-field")).sendKeys("selenium");
			// click search
			sleepInsecound(2);
			driver.findElement(By.cssSelector("#search-2 .glass")).click();
			// verify "Selenium" in all article title on first page
			List<WebElement> allArticle = driver.findElements(By.cssSelector(".post-title"));
			// step - verify article
			for (WebElement article : allArticle) {
				String articleText = article.getText().trim();
				Assert.assertTrue(articleText.contains("Selenium"));
			}
	}

	@Test
	public void TC_03_Popup_RadomCaseElse() {
		driver.get("https://blog.testproject.io/");
//		driver.navigate().refresh();
		sleepInsecound(3);
		if(driver.findElement(By.xpath("//div[@class='mailch-wrap rocket-lazyload']")).isDisplayed()) {
			sleepInsecound(2);
			System.out.println("Into");
			String a = driver.findElement(By.xpath("//a[@class='link']")).getText();
			System.out.println("button: "+a);
			// verify button SIGN UP NOW
			Assert.assertEquals(a, "SIGN UP NOW");
			//Close popup 
			driver.findElement(By.id("close-mailch")).click();
		}
			// sendkeys selemium
//			driver.findElement(By.cssSelector("#search-2 .search-field")).clear();
			driver.findElement(By.cssSelector("#search-2 .search-field")).sendKeys("selenium");
			// click search
			driver.findElement(By.cssSelector("#search-2 .glass")).click();
			// verify "Selenium" in all article title on first page
			List<WebElement> allArticle = driver.findElements(By.cssSelector(".post-title"));
			// step - verify article
			for (WebElement article : allArticle) {
				String articleText = article.getText().trim();
				Assert.assertTrue(articleText.contains("Selenium"));
			}
			
		
	}

	@Test
	public void TC_04_() {
		
	}
	public void sleepInsecound(long time) {
		try {
			Thread.sleep(1000*time);
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