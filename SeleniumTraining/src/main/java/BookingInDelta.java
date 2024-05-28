import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BookingInDelta {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		/*
		 * Book a room to Guadalajara
		 * Objectives
		 * - Check-in: 	Mon, June 10
		 * - Check-out: Tue, June 11
		 * - For 2 people
		 * 
		 * Validations in checkout:
		 * - Dates
		 * - Total due today: 0
		 * */
		
		
		//Set driver path in System propeties
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\2000122010\\Documents\\chromedriver-win64\\chromedriver.exe");
		
		//Create CrhomeDriver
		ChromeOptions options = new ChromeOptions();
		
		//Create wait object
		Watis waitFor = new Watis();
		
		options.addArguments("--start-maximized");
		options.addArguments("--remote-allow-origins=*");
		ChromeDriver driver = null;
		
		//Create driver and open the page
		driver = new ChromeDriver(options);
		driver.get("https://es.delta.com/");
		
		/*
		 * 0. Select the language
		 * */
		//Try to set lnguage if it's the first time
		try {
			//Click the button to select language
			//To click the login button
			By languageModal = By.xpath("//button[text()=\" México - Español \"]");
			WebElement selectingLanguage = driver.findElement(languageModal);
			highlightElement(selectingLanguage, driver);
			takeScreenshot("0. Language selector", driver);
			selectingLanguage.click();
			
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("scroll(0, 250);");
			//waitFor.ImplicitWait(driver);
			
		}
		catch(Exception e){}//Do nothing};
			
			
		/*
		 * 1. Go to the link to book an hotel
		 * */
		//Get the identifier of current window
		String originalWindow = driver.getWindowHandle();
		By hotelLink = By.xpath("//*[@data-analytics-id=\"home-shop-2\" and contains(., \"Comprar hoteles\")]");
		
		//Click the button or (anchor) to open a new window
		WebElement HotelBookingLink= waitFor.ExplicitWait(driver, hotelLink);
		highlightElement(HotelBookingLink, driver);
		takeScreenshot("1. Click to the link", driver);
		HotelBookingLink.click();
		
		//Switch to the recent opened window
		for(String window: driver.getWindowHandles()) {
			if(!originalWindow.contentEquals(window)) {
				driver.switchTo().window(window);
				break;
			}
		}
		
		
		/*
		 * 2. Search stays
		 * */
		By goingTo = By.cssSelector(".uitk-typeahead button");
		By whereToGo = By.id("location-field-destination");
		//
		Thread.sleep(3000);
		
		//Go to Time menu
		WebElement goingToButton = waitFor.ExplicitWait(driver, goingTo);
		takeScreenshot("2.2.1.-Main menu", driver);
		highlightElement(goingToButton, driver);
		goingToButton.click();
		
		
		//Close the driver
		//driver.quit();
		
		
	}
	public static void highlightElement(WebElement element, WebDriver driver) {
		JavascriptExecutor js =(JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.outline = '#f00 solid 2px' ", element);
	}
	
	public static void takeScreenshot(String name, WebDriver driver) throws IOException{
		String ssFolder ="C:\\Users\\2000122010\\Pictures\\DeltaSS\\%s.png";
		String ssName = String.format(ssFolder, name);
		
		TakesScreenshot ss = (TakesScreenshot)driver;
		File screenshot = ss.getScreenshotAs(OutputType.FILE);
		
		FileUtils.copyFile(screenshot, new File(ssName));
	}

}
