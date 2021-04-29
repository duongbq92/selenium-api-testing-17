package webdriver;

import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_15_Wait_P5_Mixing {
	WebDriver driver;
	WebDriverWait explicitWait;
	FluentWait<WebElement> wait;
	String source_folder = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
//		driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", source_folder +"\\browserDriverChrome\\chromedriver.exe" );
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 15);
		
		driver.manage().window().maximize();
		
	}

//	@Test
	public void TC_01_Found() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://www.facebook.com/");
		// explicit wait
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email"))).sendKeys("sugia_hoabinh115@yahoo.com");
		// implicit wait
		driver.findElement(By.name("login")).click();
	}
//	@Test
	public void TC_02_Element_Not_Found_Only_Implicit() {
		driver.get("https://www.facebook.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		System.out.println("TC_02: "+getDateTimeNow()+"---");
		try {
			WebElement emailTextbox = driver.findElement(By.xpath("//div[@class='Im_not_here']"));
			Assert.assertTrue(emailTextbox.isDisplayed());
			System.out.println("Swith try");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("TC_02:"+e.getMessage());
		}
		System.out.println("TC_02: "+getDateTimeNow()+"---");
	}
	@Test
	public void TC_03_Element_Not_Found_Implicit_Greater_Than_Explicit() {
		driver.get("https://www.facebook.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 15);
		System.out.println("TC_03_Element_Not_Found_Implicit_Greater_Than_Explicit: "+getDateTimeNow()+"---");
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Im_not_here']")));
			System.out.println("Swith try");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("TC_03_Element_Not_Found_Implicit_Greater_Than_Explicit:"+e.getMessage());
		}
		System.out.println("TC_03_Element_Not_Found_Implicit_Greater_Than_Explicit: "+getDateTimeNow()+"---");
	}
//	@Test
	public void TC_05_Element_Not_Found_Only_Explicit() {
		driver.get("https://www.facebook.com/");
		explicitWait = new WebDriverWait(driver, 10);
		System.out.println("TC_04_Element_Not_Found_Only_Explicit: "+getDateTimeNow()+"---");
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Im_not_here']")));
			System.out.println("Swith try");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("TC_04_Element_Not_Found_Only_Explicit:"+e.getMessage());
		}
		System.out.println("TC_04_Element_Not_Found_Only_Explicit: "+getDateTimeNow()+"---");
	}
//	@Test
	public void TC_06_Element_Not_Found_Only_Explicit_WebElement() {
		driver.get("https://www.facebook.com/");
		explicitWait = new WebDriverWait(driver, 10);
		System.out.println("TC_06_Element_Not_Found_Only_Explicit_WebElement: "+getDateTimeNow()+"---");
		try {
			explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='Im_not_here']"))));
			System.out.println("Swith try");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("TC_06_Element_Not_Found_Only_Explicit_WebElement:"+e.getMessage());
		}
		System.out.println("TC_06_Element_Not_Found_Only_Explicit_WebElement: "+getDateTimeNow()+"---");
	}
//	@Test
	// Fulent wait
	public void TC_07_Fluent_Wait() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		explicitWait = new WebDriverWait(driver, 10);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("start"))).click();
//		WebElement finish = driver.findElement(By.id("finish"));
		  wait = new FluentWait<WebElement>( driver.findElement(By.id("finish")));
				  wait.withTimeout(100, TimeUnit.MILLISECONDS)
			      .pollingEvery(100, TimeUnit.MILLISECONDS)
			      .ignoring(NoSuchElementException.class)
					.until(new Function<WebElement, Boolean>() 
			  {
			    public Boolean apply(WebElement element)
			    {
			    	boolean flag = element.getText().endsWith("0.2");
			    	System.out.println("  time    "+element.getText());
			      return flag;
			    }
			  });
	}
//	public WebElement waitedElement(By locator)
//	{
//	  Wait wait = new FluentWait(driver)
//	      .withTimeout(100, TimeUnit.MILLISECONDS)
//	      .pollingEvery(100, TimeUnit.MILLISECONDS)
//	      .ignoring(NoSuchElementException.class);
//	  WebElement element = wait.until(new Function<WebDriver, WebElement>() 
//	  {
//	    public WebElement apply(WebDriver driver)
//	    {
//	      return driver.findElement(locator);
//	    }
//	  });
//	  return element;
//	}
	public String getDateTimeNow() {
		Date date = new Date();
		return date.toString();
	}

	@AfterClass
	public void afterClass() {
//		driver.quit();
	}
}