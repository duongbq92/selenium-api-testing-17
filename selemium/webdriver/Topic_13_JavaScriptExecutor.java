package webdriver;

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

public class Topic_13_JavaScriptExecutor {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebElement element;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor)driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

//	@Test
	public void TC_01_Click_Hidden_Executor() {
		driver.get("https://www.myntra.com/");
		WebElement homeAndPathLink = driver.findElement(By.xpath("//a[text()='Hooks & Holders']"));
		jsExecutor.executeScript("arguments[0].click();", homeAndPathLink);
	}

	@Test
	public void TC_02_Live_Guru() {
		navigateToUrlByJS("http://live.demoguru99.com/");
		String liveGuruDomain = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(liveGuruDomain, "live.demoguru99.com");
		String liveGuruURL = (String) executeForBrowser("return document.URL");
		Assert.assertEquals(liveGuruURL, "http://live.demoguru99.com/");
		highlightElement("//a[text()= 'Mobile']");
		clickToElementByJS("//a[text()= 'Mobile']");
		clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div//button");
		Assert.assertTrue(verifyTextInInnerText("Samsung Galaxy was added to your shopping cart.")); 
		highlightElement("//a[text()= 'Customer Service']");
		clickToElementByJS("//a[text()= 'Customer Service']");
		String customerServiceTitle = (String) executeForBrowser("return document.title");
		Assert.assertEquals(customerServiceTitle, "Customer Service");
		highlightElement("//input[@name='email']");
		scrollToElement("//input[@name='email']");
		String GuruDomain = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(GuruDomain, "live.demoguru99.com");
	}
	
	public void TC_03_Remove() {
		// viet tren toppic 05
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public Object executeForBrowser( String javaSript) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaSript);
	}

	public boolean verifyTextInInnerText( String textExpected) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS( String url) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void highlightElement( String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);

	}

	public void clickToElementByJS( String locator) {
		
		WebElement element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].click();", element);
	}

	public void scrollToElement( String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void sendkeyToElementByJS( String locator, String value) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
	}

	public void removeAttributeInDOM( String locator, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
	}
	public void sleepInSecond(long time) {
		try {
			Thread.sleep(1000*time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}