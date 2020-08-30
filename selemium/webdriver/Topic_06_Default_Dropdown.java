package webdriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Topic_06_Default_Dropdown {
	WebDriver driver;
	Select select, selectDay, selectMonth, selectYeah;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	
	public void TC_01_Drop() {
		driver.get("https://www.facebook.com/");
		// có cái dropdown xuất hiện thì khởi tạo
		// Khởi tạo 1 biến và đi tìm element
		select = new Select(driver.findElement(By.xpath("//select[@id='month']")));
		// kiêm tra 1 dr có ho trợ nhiều mutiple 
		Assert.assertFalse(select.isMultiple()); 
		// index int
		select.selectByIndex(2);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Tháng 2");
		
		// value : string
		select.selectByValue("5");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Tháng 5");
		
		// text : string
		select.selectByVisibleText("Tháng 10");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Tháng 10");
		// kiểm tra xem 1 dropdown có bao nhiêu cái item và item value của nó là gì
		// get a tất cả các thẻ option 
		List<WebElement> monthOption = select.getOptions();
		// in ra tat ca bao nhieu gia tri 
		int mothOptionSize = monthOption.size();
		System.out.println("Month item number = "+mothOptionSize);
		// kiểm tra số lượng bằng nhiêu 
		Assert.assertEquals(mothOptionSize, 13);
		
		for (WebElement webElement : monthOption) {
			System.out.println("Month"+ webElement.getText());
		}
		// kiem tra duoc dropdown no hien thi dung cac gia tri 
		ArrayList<String> actualItiem = new ArrayList<String>();	
		actualItiem.add("Tháng");
		actualItiem.add("Tháng 1");
		actualItiem.add("Tháng 2");
		actualItiem.add("Tháng 3");
		actualItiem.add("Tháng 4");
		actualItiem.add("Tháng 5");
		actualItiem.add("Tháng 6");
		actualItiem.add("Tháng 7");
		actualItiem.add("Tháng 8");
		actualItiem.add("Tháng 9");
		actualItiem.add("Tháng 10");
		actualItiem.add("Tháng 11");
		actualItiem.add("Tháng 12");
		ArrayList<String> expectedItem = new ArrayList<String>();
		for (WebElement option : monthOption) {
			expectedItem.add(option.getText());
		}
		Assert.assertEquals(actualItiem, expectedItem);
	}

	
	public void TC_02_Multiple() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		select = new Select(driver.findElement(By.xpath("//select[@id='job2']")));
		
		Assert.assertTrue(select.isMultiple());
		select.selectByVisibleText("Automation");
		select.selectByVisibleText("Manual");
		select.selectByVisibleText("Adhoc");
		int optionSelected = select.getAllSelectedOptions().size();
		System.out.println("Size item seletect : "+optionSelected );
		Assert.assertEquals(optionSelected, 3);
		List<WebElement> optionSelectElements = select.getAllSelectedOptions();
		for (WebElement option : optionSelectElements) {
			System.out.println(option.getText());
		}
				// bo chon het 3 thang
		select.deselectAll();
		optionSelected = select.getAllSelectedOptions().size();
		System.out.println("Size item seletect : "+optionSelected );
		Assert.assertEquals(optionSelected, 0);
	}

	@Test
	public void TC_03_nopCommerce() {
		driver.get("https://demo.nopcommerce.com/");
		// click Register
		driver.findElement(By.xpath("//a[@class='ico-register']")).click();
		// Input date 
		driver.findElement(By.id("gender-male")).click();
		driver.findElement(By.id("FirstName")).sendKeys("Quy");
		driver.findElement(By.id("LastName")).sendKeys("Duong");
		selectDay = new Select(driver.findElement(By.name("DateOfBirthDay")));
		
		//System.out.println("size cua Day"+selectDay.getAllSelectedOptions().size());
		List<WebElement> monthOption = selectDay.getOptions();
		Assert.assertEquals(monthOption.size(), 32);
		selectDay.selectByVisibleText("20");
		selectMonth = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		selectMonth.selectByVisibleText("July");
		selectYeah = new Select(driver.findElement(By.name("DateOfBirthYear")));
		selectYeah.selectByVisibleText("1992");
		driver.findElement(By.id("Email")).sendKeys("duong"+randomNumber()+"@gmail.com");
		driver.findElement(By.id("Company")).sendKeys("Fsoft cong ty ty do");
		driver.findElement(By.id("Password")).sendKeys("duongmk");
		driver.findElement(By.id("ConfirmPassword")).sendKeys("duongmk");
		// click tranfer page
		driver.findElement(By.id("register-button")).click();
		// verify output
		Assert.assertTrue(driver.findElement(By.xpath("//a[@class='ico-account']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[@class='ico-logout']")).isDisplayed());
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[text()='Your registration completed']")).getText(), "Your registration completed");
		
	}

	
	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int  randomNumber() {
		Random random = new Random();
		return random.nextInt(99);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}