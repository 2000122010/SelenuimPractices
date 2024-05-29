import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

public class SwitchWindows {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		//Set driver path in System propeties
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\2000122010\\Documents\\chromedriver-win64\\chromedriver.exe");
		
		//Create CrhomeDriver
		ChromeOptions options = new ChromeOptions();
		
		options.addArguments("--start-maximized");
		options.addArguments("--remote-allow-origins=*");
		ChromeDriver driver = null;
		
		driver = new ChromeDriver(options);
		
		driver.get("http://127.0.0.1:5500/index.html");
		Thread.sleep(3000);
		
		//Get the identifier of current window
		String originalWindow = driver.getWindowHandle();
		
		//Click the button or (anchor) to open a new window
		WebElement googleLink= driver.findElement(By.id("newTabLink2"));
		googleLink.click();
		
		//Switch to the recent opened window
		for(String window: driver.getWindowHandles()) {
			if(!originalWindow.contentEquals(window)) {
				driver.switchTo().window(window);
				break;
			}
		}
		
		Thread.sleep(3000);
		
		//Interact with elements in the second window
		driver.findElement(By.name("q")).sendKeys("Hexaware");
		Thread.sleep(3000);
		
		//Close the current window
		driver.close();
		
		//Change the DOM tab
		driver.switchTo().window(originalWindow);

		//Highlight the element
		highlightElement(googleLink, driver);
		Thread.sleep(3000);
		
		//Close the driver
		driver.quit();
	}
	
	public static void highlightElement(WebElement element, WebDriver driver) {
		JavascriptExecutor js =(JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.outline = '#f00 solid 2px' ", element);
	}

}



