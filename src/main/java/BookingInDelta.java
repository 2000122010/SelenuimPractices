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
		catch(Exception e){
			//Do nothing
		};
			
			
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
			By GuadalajaraOption = By.xpath("//button[@aria-label=\"Guadalajara Jalisco, Mexico\"]");
			By check_in = By.id("d1-btn");
			By datePicker = By.cssSelector(".date-picker-menu");
			By donePicker = By.cssSelector(".uitk-date-picker-menu-footer button");
			By inDay = By.xpath("(//button[@data-day=\"10\"])[1]");
			By outDay = By.xpath("(//button[@data-day=\"11\"])[1]");
			By submit = By.xpath("//button[@type=\"submit\"]");
			//Thread.sleep(3000);
			
			//Search stay
			WebElement goingToButton = waitFor.ExplicitWait(driver, goingTo);
			takeScreenshot("2.2.1.-Main menu", driver);
			highlightElement(goingToButton, driver);
			goingToButton.click();
			
			//Search and select Guadalajara
			WebElement goingToInput = waitFor.ExplicitWait(driver, whereToGo);
			takeScreenshot("2.2.2.-Options", driver);
			highlightElement(goingToInput, driver);
			goingToInput.sendKeys("Guadalajara");
			
			//To wait until the option apears
			WebElement GuadalajaraButton = waitFor.ExplicitWait(driver, GuadalajaraOption);
			highlightElement(GuadalajaraButton, driver);
			GuadalajaraButton.click();
			
			//Select Check-in
			WebElement checkIn = driver.findElement(check_in);
			highlightElement(checkIn, driver);
			checkIn.click();
			
			WebElement timePicker = waitFor.ExplicitWait(driver, datePicker);
			highlightElement(timePicker, driver);
			WebElement checkInDay = driver.findElement(inDay);
			WebElement checkPutDay = driver.findElement(outDay);
			WebElement doneButton = waitFor.ExplicitWait(driver, donePicker);
			
			checkInDay.click();
			checkPutDay.click();
			
			takeScreenshot("2.2.3.-Dato to date", driver);
			
			doneButton.click();
		
		
			//To search
			takeScreenshot("2.2.4.-Form after", driver);
			WebElement searchButton = driver.findElement(submit);
			searchButton.click();
		
		/*
		 * 3.-Select an option 
		 * */
			waitFor.ImplicitWait(driver);
			takeScreenshot("3.1.-Options", driver);
			String secondWindow = driver.getWindowHandle();
			
			//elements
			By option = By.xpath	("(//a[@data-stid=\"open-hotel-information\"])[2]");
			
			//Select the first option
			WebElement firstOption = waitFor.ExplicitWait(driver, option);
			highlightElement(firstOption, driver);
			takeScreenshot("3.1.1.Selecting an option", driver);
			firstOption.click();
			
			
			//Switch to the recent opened window
			for(String window: driver.getWindowHandles()) {
				if(!secondWindow.contentEquals(window)&&!originalWindow.contentEquals(window)) {
					driver.switchTo().window(window);
					break;
				}
			}
	
		
		/*
		 * 4.Viewing the option
		 * */
			waitFor.ImplicitWait(driver);
			takeScreenshot("4.1.-Selected option", driver);
			//elements
			By reserveRoom = By.xpath("//button[@data-stid=\"sticky-button\"]");
			By firstReserve = By.xpath("(//button[@data-stid=\"submit-hotel-reserve\"])[2]");
			By paymentMethod = By.cssSelector("section[role=\"dialog\"] div[data-stid=\"payment-option-PAY_LATER\"] button");
			
			
			//Reserve a room
			WebElement ReserveButton = waitFor.ExplicitWait(driver, reserveRoom);
			highlightElement(ReserveButton, driver);
			ReserveButton.click();
		
			WebElement toReserve = waitFor.ExplicitWait(driver, firstReserve);
			highlightElement(toReserve, driver);
			toReserve.click();
			
			//Modal's payment method 
			WebElement PaymentMethodButton = waitFor.ExplicitWait(driver, paymentMethod);
			highlightElement(PaymentMethodButton, driver);
			takeScreenshot("4.2.-Modal payment method", driver);
			PaymentMethodButton.click();
			
			
		/* 
		 * 5. Checking info
		 * */
			waitFor.ImplicitWait(driver);
			//elements
			By divInfo = By.cssSelector(".hotel-details-wrapper.two-column");
			By durationCheckIn = By.cssSelector(".duration-check-in span:nth-child(2)");
			By durationCheckout = By.cssSelector(".duration-check-out span:nth-child(2)");
			By totalAmount = By.cssSelector(".amount-due-now span.amount-value");
			
			//Info pre-order
			WebElement divPreOrderInfo = waitFor.ExplicitWait(driver, divInfo);
			highlightElement(divPreOrderInfo, driver);
			takeScreenshot("5.1.-Final info", driver);
			
			//Valitate order
			
			WebElement checkInDate = driver.findElement(durationCheckIn);
			highlightElement(checkInDate, driver);
			
			WebElement checkOutDate = driver.findElement(durationCheckout);
			highlightElement(checkOutDate, driver);
			
			WebElement AmountToPay = driver.findElement(totalAmount);
			highlightElement(AmountToPay, driver);
			
			//get values
			String checkInData= checkInDate.getText();
			String checkOutData= checkOutDate.getText();
			String Total= AmountToPay.getText();
			
			String expectedCheckIn="Mon, Jun 10", expextedCheckOut="Tue, Jun 11", expectedTotal="$0.00";
			
			
			//Validate data
			if((checkInData.equals(expectedCheckIn))&&(checkOutData.equals(expextedCheckOut))&&(Total.equals(expectedTotal))) {
				JavascriptExecutor jse = (JavascriptExecutor)driver;
				jse.executeScript("scroll(0, 250);");
				takeScreenshot("5.2. Success!", driver);
				
				//Close the driver
				driver.quit();
				
			}
			else {
				System.out.println("Test failed");
				driver.quit();
			}
			
			
			
			
			
			
			
			
		
		
		
		
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
