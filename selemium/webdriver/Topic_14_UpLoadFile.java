package webdriver;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class Topic_14_UpLoadFile {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String source_folder = System.getProperty("user.dir");
	String image_name_01 = "Hana1.jpg";
	String image_name_02 = "Hana2.jpg";
	String image_name_03 = "Hana3.jpg";
	
	String image_path_01 = source_folder + "\\uploadFile\\" +image_name_01;
	String image_path_02 = source_folder + "\\uploadFile\\" +image_name_02;
	String image_path_03 = source_folder + "\\uploadFile\\" +image_name_03;
//	@BeforeClass
//	public void beforeClass() {
//		
//		driver = new FirefoxDriver();
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		driver.manage().window().maximize();
//		
//	}

//	@Test
	public void TC_01_Senkey() {
		jsExecutor = (JavascriptExecutor)driver;
		System.setProperty("webdriver.gecko.driver", source_folder +"\\driverBrowser\\geckodriver.exe" );
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");
		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']")) ;
		uploadFile.sendKeys(image_path_01+"\n"+image_path_02 + "\n" + image_path_03);
		sleepInSecond(1);
//		uploadFile = driver.findElement(By.xpath("//input[@type='file']")) ;
//		uploadFile.sendKeys(image_path_02);
//		sleepInSecond(1);
//		uploadFile = driver.findElement(By.xpath("//input[@type='file']")) ;
//		uploadFile.sendKeys(image_path_03);
//		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()= '" + image_name_01 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()= '" + image_name_02 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()= '" + image_name_03+ "']")).isDisplayed());
		List<WebElement> startButton = driver.findElements(By.xpath("//button[@class='btn btn-primary start']//span[text()='Start']"));
		for (WebElement start : startButton) {
			start.click();
			sleepInSecond(1);
		}
		// After upload
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+image_name_01+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+image_name_02+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+image_name_03+"']")).isDisplayed());
		
		Assert.assertTrue(isImageDisplay("//img[contains(@src, '"+image_name_01+"')]"));
		Assert.assertTrue(isImageDisplay("//img[contains(@src, '"+image_name_02+"')]"));
		Assert.assertTrue(isImageDisplay("//img[contains(@src, '"+image_name_03+"')]"));
		
		
		
	}

//	@Test
	public void TC_02_Senkey_Chrome() {
		System.setProperty("webdriver.chrome.driver", source_folder +"\\browserDriverChrome\\chromedriverVer84.exe" );
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");
		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']")) ;
		uploadFile.sendKeys(image_path_01);
		sleepInSecond(1);
		uploadFile = driver.findElement(By.xpath("//input[@type='file']")) ;
		uploadFile.sendKeys(image_path_02);
		sleepInSecond(1);
		uploadFile = driver.findElement(By.xpath("//input[@type='file']")) ;
		uploadFile.sendKeys(image_path_03);
		sleepInSecond(1);
	}

	@Test
	public void TC_03_() {
		System.setProperty("webdriver.chrome.driver", source_folder +"\\browserDriverChrome\\chromedriverVer84.exe" );
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://gofile.io/uploadFiles");
		String parentID = driver.getWindowHandle();
		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']")) ;
		uploadFile.sendKeys(image_path_01+"\n"+image_path_02 + "\n" + image_path_03);
		sleepInSecond(1);
		driver.findElement(By.cssSelector("#uploadFiles-rightBtn #uploadFiles-btnUpload")).click();
		driver.findElement(By.cssSelector("a#uploadFiles-uploadRowResult-downloadLink")).click();
		switchToWindownById(parentID);
		// verify download icon at each file name
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+image_name_01+"']/following-sibling::td//i[contains(@class,'download')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+image_name_02+"']/following-sibling::td//i[contains(@class,'download')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+image_name_03+"']/following-sibling::td//i[contains(@class,'download')]")).isDisplayed());

		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+image_name_01+"']/following-sibling::td//i[contains(@class,'play')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+image_name_02+"']/following-sibling::td//i[contains(@class,'play')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+image_name_03+"']/following-sibling::td//i[contains(@class,'play')]")).isDisplayed());
		sleepInSecond(1);
	 
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
	public boolean isImageDisplay(String xpathLocator) {
		jsExecutor = (JavascriptExecutor)driver;
		WebElement ImageFile = driver.findElement(By.xpath(xpathLocator));
		 Boolean ImagePresent = (Boolean) ((JavascriptExecutor)driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", ImageFile);
		 return ImagePresent; 
	}
	public void sleepInSecond(long time) {
		try {
			Thread.sleep(1000*time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	@AfterClass
//	public void afterClass() {
//		driver.quit();
//	}
	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}


}