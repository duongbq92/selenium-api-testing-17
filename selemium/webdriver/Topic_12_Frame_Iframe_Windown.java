package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Frame_Iframe_Windown {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	Alert alert;
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDriverChrome\\chromedriverVer84.exe");
		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor)driver;
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

//	@Test
	public void TC_01_Iframe() {
		driver.get("https://kyna.vn/");
		driver.findElement(By.xpath("//a[@title='Close']")).click();
//		driver.switchTo().frame(1);
		// switch to face iframe
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='face-content']//iframe")));
		String faceLike = driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText();
		System.out.println(faceLike);
		Assert.assertEquals(faceLike, "169K likes");
		// switch iframe
		driver.switchTo().defaultContent();
		// check search textbox displayed
		Assert.assertTrue(driver.findElement(By.cssSelector(".search-button")).isDisplayed());
		//
		driver.switchTo().frame("cs_chat_iframe");
		//click show chat
		driver.findElement(By.xpath("//div[@class='button_text']")).click();
		
		
	}

//	@Test
	public void TC_02_Windown() {
		driver.get("https://kyna.vn/");
		sleepInsecound(5);
		driver.findElement(By.xpath("//a[@title='Close']")).click();
//		alert = driver.switchTo().alert();
//		alert.dismiss();
		// lay ra id tai ta co driver dang dung (active)
		String parentID = driver.getWindowHandle();
		System.out.println("parentId: "+parentID);
		System.out.println(driver.findElement(By.xpath("//img[@alt='VietnamWorks']")).isDisplayed());
		driver.findElement(By.xpath("//img[@alt='VietnamWorks']")).click();
//		clickToElementByJS("//img[@alt='VietnamWorks']");
		sleepInsecound(3);
		switchToWindownById(parentID);
		// da qua tag moi
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.vietnamworks.com/?utm_source=from_kyna");
		String childId = driver.getWindowHandle();
		// switch ve lai tag parent
		switchToWindownById(childId);
		Assert.assertEquals(driver.getCurrentUrl(), "https://kyna.vn/");
		// click vietnam learning
		driver.findElement(By.xpath("//img[@alt='VietnamWorks Learning']")).click(); 
		sleepInsecound(2);
		switchToWindownByTitle("Trang chủ | Vietnamworks Learning for Enterprise");
		Assert.assertTrue(driver.findElement(By.xpath("//img[@alt='logo']")).isDisplayed());
		// switch kyna
		switchToWindownByTitle("Kyna.vn - Học online cùng chuyên gia");
		Assert.assertEquals(driver.getCurrentUrl(), "https://kyna.vn/");
		driver.findElement(By.xpath("//img[@alt='youtube']")).click(); 
		switchToWindownByTitle("Kyna.vn - YouTube");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='logo-icon-container']")).isDisplayed());
		sleepInsecound(2);
		areAlltabcloseWithoutParent(parentID);
	}

	@Test
	public void TC_03_WindownDemoGuru() {
		driver.get("http://live.demoguru99.com/index.php/");
		driver.findElement(By.xpath("//a[(text()='Mobile')]")).click();
		String parentID = driver.getWindowHandle();
		driver.findElement(By.xpath("//a[(text()='Sony Xperia')]/parent::h2/following-sibling::div//a[text()='Add to Compare']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//span[contains(text(),'The product Sony Xperia has been added to comparison list.')]")).getText(), "The product Sony Xperia has been added to comparison list.");
		driver.findElement(By.xpath("//a[(text()='IPhone')]/parent::h2/following-sibling::div//a[text()='Add to Compare']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//span[contains(text(),'The product IPhone has been added to comparison list.')]")).getText(), "The product IPhone has been added to comparison list.");
		driver.findElement(By.xpath("//span[(text()='Compare')]")).click();
		switchToWindownByTitle("Products Comparison List - Magento Commerce");
		Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
		areAlltabcloseWithoutParent(parentID);
		driver.findElement(By.xpath("//a[contains(text(),'Clear All')]")).click();
		// switch to alert 
		alert = driver.switchTo().alert();
		sleepInsecound(2);
		alert.accept();
		Assert.assertEquals(driver.findElement(By.xpath("//span[contains(text(),'The comparison list was cleared.')]")).getText(), "The comparison list was cleared.");
		
	}
	public void switchToWindownById(String parentID) {
		// lay ra cua so tag,windown moi
		Set<String> allWindowns = driver.getWindowHandles();
		// dung vong lap for-each de duyet ra tat ca id
		for (String runwindown : allWindowns) {
			// kiem tra id nao ma khac vs parent A
			if(!runwindown.equals(parentID)) {
				// switch vao Id(windown/tag) do 
				driver.switchTo().window(runwindown);
				// thoat khoi vong lap khi thoa man dieu kien
				break;
			}
		}
	}
	public void switchToWindownByTitle(String parentID) {
		// lay ra cua so tag,windown moi
		Set<String> allWindowns = driver.getWindowHandles();
		// dung vong lap for-each de duyet ra tat ca id
		for (String runwindown : allWindowns) {
			// switch vao tung cua so cua tag trc
			driver.switchTo().window(runwindown);
			// lay ra cac title cua tag do 
			String currentPageTitle = driver.getTitle();
			// kiem tra PageTitle nao ma bang voi title mong muong (truyen vao)
			if(currentPageTitle.equals(parentID)) {
				
				// thoat khoi vong lap khi thoa man dieu kien
				break;
			}
		}
	}
	public boolean areAlltabcloseWithoutParent(String parentID) {
		// lay ra cua so tag,windown moi
		Set<String> allWindowns = driver.getWindowHandles();
		// dung vong lap for-each de duyet ra tat ca id
		for (String runwindown : allWindowns) {
			// kiem tra id nao khac vs parernt 
			if(!runwindown.equals(parentID)) {
				// switch vao tung cua so cua tag trc
				driver.switchTo().window(runwindown);
				// close di
				driver.close();
			}
			
		}
					// switch vao tung cua so cua tag trc
					driver.switchTo().window(parentID);
					if(driver.getWindowHandles().size()==1) {
						return true;
					}
					else {
						return false;
					}
	}
	public void sleepInsecound(long time) {
		try {
			Thread.sleep(1000*time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click;", driver.findElement(By.xpath(locator)));
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}