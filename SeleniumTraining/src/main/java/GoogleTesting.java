import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class GoogleTesting {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		By.name("username");
		By.name("Password");
		
		
		/**
		 * Search Hexaware in Google
		 */
		
		By searchBox = By.name("q");
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\2000122010\\Documents\\chromedriver-win64\\chromedriver.exe");
	
		//Create ChromeDriver
		ChromeOptions options = new ChromeOptions();
		
		options.addArguments("--start-maximized");
		options.addArguments("--remote-allow-origins=*");
		
		ChromeDriver driver = new ChromeDriver(options);
		
		//Navigate to Page
		driver.navigate().to("https://www.google.com/");
		
		takeScreenshot("after_typing", driver);
		
		//Write Hexaware in the search box
		WebElement box = driver.findElement(searchBox);
		highlightElement(box, driver);
		box.sendKeys("Hexaware");
		box.sendKeys(Keys.ENTER);
		
		
		//Validate the page title is Hexaware
		Thread.sleep(3000);
		String title = driver.getTitle();
		
		takeScreenshot("after_search", driver);
		
		if(title.equals("Hexaware - Buscar con Google")) {
			System.out.print("Test Succeded");
		} else {
			System.out.print(String.format("Test Failed, title is: %s but 'Hexaware' was expected", title));
		}
		
		//Close driver
		driver.quit();
	}

	public static void highlightElement(WebElement element, WebDriver driver) {
		JavascriptExecutor js =(JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.outline = '#f00 solid 2px' ", element);
	}
	
	public static void takeScreenshot(String name, WebDriver driver) throws IOException{
		String ssFolder ="C:\\Users\\2000122010\\Pictures\\ScreenShot\\%s.png";
		String ssName = String.format(ssFolder, name);
		
		TakesScreenshot ss = (TakesScreenshot)driver;
		File screenshot = ss.getScreenshotAs(OutputType.FILE);
		
		FileUtils.copyFile(screenshot, new File("C:\\Users\\2000122010\\Pictures\\ScreenShot"));
		//FileUtil.copyFIle(screenshot, new File("C:\\Users\\2000122010\\Pictures\\ScreenShot"));
	}
	
	
}
