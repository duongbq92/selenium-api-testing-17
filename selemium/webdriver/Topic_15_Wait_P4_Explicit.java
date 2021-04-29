package webdriver;

import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
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

public class Topic_15_Wait_P4_Explicit {
	WebDriver driver;
	WebDriverWait explicitWait;
	String source_folder = System.getProperty("user.dir");
	Alert alert;
	String today = "Saturday, October 10, 2020";
	WebElement dateSelected ;
	String image_name_01 = "Hana1.jpg";
	String image_name_02 = "Hana2.jpg";
	String image_name_03 = "Hana3.jpg";
	
	String image_path_01 = source_folder + "\\uploadFile\\" +image_name_01;
	String image_path_02 = source_folder + "\\uploadFile\\" +image_name_02;
	String image_path_03 = source_folder + "\\uploadFile\\" +image_name_03;

	@BeforeClass
	public void beforeClass() {
//		driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", source_folder +"\\browserDriverChrome\\chromedriver.exe" );
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 15);
		driver.manage().window().maximize();
		
	}

//	@Test
	public void TC_01_AlertPresent() {
		driver.get("http://demo.guru99.com/v4/index.php");
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.name("btnLogin")));
		driver.findElement(By.name("btnLogin")).click();
		// switch to alert
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		alert = driver.switchTo().alert();
		alert.accept();
	}

//	@Test
	public void TC_02_Visible() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.xpath("//button[(text()='Start')]")).click();
		WebElement resultText = explicitWait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//h4[text()='Hello World!']")))) ;
		Assert.assertTrue(resultText.isDisplayed());
	}

//	@Test
	public void TC_03_InVisible() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[(text()='Start')]"))).click();
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated((By.xpath("//div[@id='loading']")))) ;
		Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());
	}

//	@Test
	public void TC_04_Ajax_Loading() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		// wait datetime picker (visible/isdisplay)
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_ContentPlaceholder1_Label1")));
		// dom truoc khi seletecd
		dateSelected = driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1"));
		// ngay da chon 
		Assert.assertEquals(dateSelected.getText(), "No Selected Dates to display.");
		// click vao ngay hien tai
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@title='"+today+"']"))).click();
		// cho loading icon bien mat
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[not@style='display:none;']//div[@class='raDiv']")));
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.xpath("//div[@class='raDiv']"))));
		// dom sau ckick vi dom load lai
		dateSelected = driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1"));
		//  ngay da chon 
		Assert.assertEquals(dateSelected.getText(),"Saturday, October 10, 2020");
		
	}
//	@Test
	public void TC_05_Uploadfile() {
		driver.get("https://gofile.io/uploadFiles");
		/*Waiting for upload file button visible*/
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#dropZoneBtnSelect")));
		//lay ra id cua tag hien tai
		String parentID = driver.getWindowHandle();
		// Dinh nghia element upload va sendkey vao
		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']")) ;
		uploadFile.sendKeys(image_path_01+"\n"+image_path_02 + "\n" + image_path_03);
		// waiting button upload visible to click
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#uploadFiles-btnUpload"))).click();
		
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a#uploadFiles-uploadRowResult-downloadLink"))).click();
		switchToWindownById(parentID);
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='\"+image_name_01+\"']/following-sibling::td//i[contains(@class,'download')]")));
		// verify download icon at each file name
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+image_name_01+"']/following-sibling::td//i[contains(@class,'download')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+image_name_02+"']/following-sibling::td//i[contains(@class,'download')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+image_name_03+"']/following-sibling::td//i[contains(@class,'download')]")).isDisplayed());

		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+image_name_01+"']/following-sibling::td//i[contains(@class,'play')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+image_name_02+"']/following-sibling::td//i[contains(@class,'play')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+image_name_03+"']/following-sibling::td//i[contains(@class,'play')]")).isDisplayed());
	 
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
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public String getDateTimeNow() {
		Date date = new Date();
		return date.toString();
	}
	public void sleepInsecond(long time) {
		try {
			Thread.sleep(1000*time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}