package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Custom_Dropdown {
	WebDriver driver;
	// explicit wait
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 30);
		jsExecutor = (JavascriptExecutor) driver;
		//imlicit wait
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	
	public void TC_01_() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		// Action : select : item
		selectItemInDropdown("//span[@id='number-button']", "//ul[@id='number-menu']", "6");
		// verify so 6
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='ui-menu-item']//div[text()='6']")).isDisplayed());
		selectItemInDropdown("//span[@id='number-button']", "//ul[@id='number-menu']", "1");
		selectItemInDropdown("//span[@id='number-button']", "//ul[@id='number-menu']", "19");
		
	}

//	@Test
	public void TC_02_Anguar() {
		driver.get("https://bit.ly/2UV2vYi");
		selectItemInDropdown("//ejs-dropdownlist[@id='games']//span[contains(@class,e-search-icon)]", "//ul[@id='games_options']//li", "American Football");
		Assert.assertEquals(getHiddenText("select[id='games_hidden'] option"),"American Football");
		selectItemInDropdown("//ejs-dropdownlist[@id='games']//span[contains(@class,e-search-icon)]", "//ul[@id='games_options']//li", "Golf");
		Assert.assertEquals(getHiddenText("select[id='games_hidden'] option"),"Golf");
		
	}

	@Test
	public void TC_03_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		selectItemInDropdown("//i[@class='dropdown icon']", "//div[@class='visible menu transition']//span", "Elliot Fu");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='divider text' and text()='Elliot Fu']")).isDisplayed());
	}

//	@Test
	public void TC_04_Advance() {
		driver.get("http://multiple-select.wenzhixin.net.cn/examples#basic.html");
		
		
	}
	// ham nay duoc dung di dung lai nhieu lan , chi can truyen gia tri vao
	public void selectItemInDropdown(String parentLocator, String itemLocator, String expectedItem) {
		// click vao 1 the bat ki 
		driver.findElement(By.xpath(parentLocator)).click();
		sleepInSecond(2);
		// Cho cho tat ca item duoc co trong HTML DOM
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(itemLocator)));
		// Lay hết tất cả item này đưa vào 1 list element
		List<WebElement> allItem = driver.findElements(By.xpath(itemLocator));
		// duyệt qua từng cái item
		for (WebElement item : allItem) {
			// Mỗi lần duyệt kiểm tra xem text của item đó có bằng vs item mình cần click hay khong
			String actual = item.getText();
			// nếu như mà bằng thì click vào và thoát khỏi ko duyệt nữa
			// nếu như mà ko bằng thì lại duyệt tiếp chó đến hết tất cả item
			if(actual.equals(expectedItem)) {
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				item.click();
				sleepInSecond(2);
				break;
			}
		}
		
	}
	public String getHiddenText(String cssLocator) {
		return (String)jsExecutor.executeScript( "return document.querySelector(\""+cssLocator+"\").text");
	}
	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time*1000);
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