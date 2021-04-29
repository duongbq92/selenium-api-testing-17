package webdriver;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_UserInteraction {
	WebDriver driver;
	Actions action;
	WebElement element;
	JavascriptExecutor jsExecutor;
	String rootFolder = System.getProperty("user.dir");
	String javascriptPath = rootFolder + "\\dragAndDrop\\drag_and_drop_helper.js";
	String jqueryscriptPath = rootFolder + "\\dragAndDrop\\jquery_load_helper.js";
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor)driver;
		action = new Actions(driver);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

//	@Test
	public void TC_01_Hover_Mouse() {
		driver.get("https://www.myntra.com/");
		element = driver.findElement(By.xpath("//div[@class='desktop-navLink']//a[text()='Kids']"));
		// hover to kids menu
		action.moveToElement(element).perform();
		sleepInSeound(3);
		driver.findElement(By.xpath("//ul[@class='desktop-navBlock']//a[text()='Home & Bath']")).click();
		sleepInSeound(3);
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.myntra.com/kids-home-bath");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb']")).isDisplayed());
	}

//	@Test
	public void TC_02_ClickAndHold() {
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
		String[] selectTextExpected = {"1","2","3","4","5","6","7","8"};
		// tuong tac voi nhieu
		List <WebElement> allItiem = driver.findElements(By.cssSelector("#selectable>li"));
		// 1 click giu chuot chua nha , 2 -move chuot den . 3 nha chuot den cho can nha() release
		action.clickAndHold(allItiem.get(0)).moveToElement(allItiem.get(7)).release().perform();
		sleepInSeound(4);
		// verify chon tu 1 - 8 
		List <WebElement> allItiemSelected = driver.findElements(By.cssSelector(".ui-selected"));
		//verify size = 8
		Assert.assertEquals(allItiemSelected.size(), 8);
		
		// tao ra mot cai arraylist de luu lai selected text 
		ArrayList<String> allItiemSelectedText = new ArrayList<String>();
		// verify cai text cua cac element la so tu 1 den 8
		for (WebElement webElement : allItiemSelected) {
//			System.out.println(webElement.getText());
			allItiemSelectedText.add(webElement.getText());
		}
		Object[] selectTextActual = (Object[]) allItiemSelectedText.toArray();
		Assert.assertEquals(selectTextActual, selectTextExpected);
	}

//	@Test
	public void TC_03_ClickAndHoldRandom() {
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
//		String[] selectTextExpected = {"1","2","3","4","5","6","7","8"};
		// tuong tac voi nhieu
		List <WebElement> allItiem = driver.findElements(By.cssSelector("#selectable>li"));
		// 1 - nhan phim ctri
		action.keyDown(Keys.CONTROL);
		// 2 - click cac so can chon 1/4/7/12
		action.click(allItiem.get(0)).click(allItiem.get(3)).click(allItiem.get(6)).click(allItiem.get(11)).perform();
		// 3 -  nha phim ctri
		action.keyUp(Keys.CONTROL);
		sleepInSeound(3);
		// verify chon tu 1 - 8 
		List <WebElement> allItiemSelected = driver.findElements(By.cssSelector(".ui-selected"));
		//verify size = 8
		Assert.assertEquals(allItiemSelected.size(), 4);
	}

//	@Test
	public void TC_04_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		element = driver.findElement(By.xpath("//button[contains(text(),'Double click me')]"));
		action.doubleClick(element).perform();
		String expect = driver.findElement(By.id("demo")).getText();
		sleepInSeound(3);
		Assert.assertEquals( expect, "Hello Automation Guys!");
	}
	
//	@Test
	public void TC_05_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		element = driver.findElement(By.xpath("//span[text()='right click me']"));
		action.contextClick(element).perform();
		sleepInSeound(1);
		// hover to quit
		element = driver.findElement(By.cssSelector(".context-menu-icon-quit"));
		action.moveToElement(element).perform();
		sleepInSeound(2);
		// verify "Quit" has hover/visible status
		String quitClassAttribute = element.getAttribute("class");
		System.out.println(quitClassAttribute);
		Assert.assertTrue(quitClassAttribute.contains("context-menu-hover"));
		Assert.assertTrue(quitClassAttribute.contains("context-menu-visible"));
		//  isDisplay
		Assert.assertTrue(driver.findElement(By.cssSelector(".context-menu-icon-quit.context-menu-visible.context-menu-hover")).isDisplayed());
	}
	
//	@Test
	public void TC_06_Drag_Drop_HTML4() {
		driver.get("http://demos.telerik.com/kendo-ui/dragdrop/angular");
		WebElement sourceCircle = driver.findElement(By.id("draggable"));
		WebElement targetCircle = driver.findElement(By.id("droptarget"));
		action.dragAndDrop(sourceCircle, targetCircle).perform();
		// c2 -
		//action.clickAndHold(sourceCircle).moveToElement(targetCircle).release().perform();
		sleepInSeound(3);
		Assert.assertEquals(targetCircle.getText(), "You did great!");
	}
	@Test
	public void TC_07_Drag_Drop_HTML5_CSS() throws IOException {
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		String sourceCss = "#column-a";
		String targetCss = "#column-b";

		String java_script = readFile(javascriptPath);

		// Inject Jquery lib to site
		// String jqueryscript = readFile(jqueryPath);
		// javascriptExecutor.executeScript(jqueryscript);

		// A to B
		java_script = java_script + "$(\"" + sourceCss + "\").simulateDragDrop({ dropTarget: \"" + targetCss + "\"});";
		jsExecutor.executeScript(java_script);
		sleepInSeound(5);
		Assert.assertTrue(isElementDisplayed("//div[@id='column-a']/header[text()='B']"));

		// B to A
		jsExecutor.executeScript(java_script);
		sleepInSeound(3);
		Assert.assertTrue(isElementDisplayed("//div[@id='column-b']/header[text()='B']"));
	}
//	@Test
	public void TC_08_DragDrop_HTML5_Offset() throws InterruptedException, IOException, AWTException {
		driver.get("https://automationfc.github.io/drag-drop-html5/");
	
		String sourceXpath = "//div[@id='column-a']";
		String targetXpath = "//div[@id='column-b']";
		
		drag_the_and_drop_html5_by_xpath(sourceXpath, targetXpath);
		Thread.sleep(3000);
		
		drag_the_and_drop_html5_by_xpath(sourceXpath, targetXpath);
		Thread.sleep(3000);
		
		drag_the_and_drop_html5_by_xpath(sourceXpath, targetXpath);
		Thread.sleep(3000);
	}
	public void drag_the_and_drop_html5_by_xpath(String sourceLocator, String targetLocator) throws AWTException {

		WebElement source = driver.findElement(By.xpath(sourceLocator));
		WebElement target = driver.findElement(By.xpath(targetLocator));

		// Setup robot
		Robot robot = new Robot();
		robot.setAutoDelay(500);

		// Get size of elements
		Dimension sourceSize = source.getSize();
		Dimension targetSize = target.getSize();

		// Get center distance
		int xCentreSource = sourceSize.width / 2;
		int yCentreSource = sourceSize.height / 2;
		int xCentreTarget = targetSize.width / 2;
		int yCentreTarget = targetSize.height / 2;

		Point sourceLocation = source.getLocation();
		Point targetLocation = target.getLocation();
		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Make Mouse coordinate center of element
		sourceLocation.x += 20 + xCentreSource;
		sourceLocation.y += 110 + yCentreSource;
		targetLocation.x += 20 + xCentreTarget;
		targetLocation.y += 110 + yCentreTarget;

		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Move mouse to drag from location
		robot.mouseMove(sourceLocation.x, sourceLocation.y);

		// Click and drag
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

		// Move to final position
		robot.mouseMove(targetLocation.x, targetLocation.y);

		// Drop
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	public boolean isElementDisplayed(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if(element.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public String readFile(String file) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(file);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
	}
	
	public void sleepInSeound(long time) {
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