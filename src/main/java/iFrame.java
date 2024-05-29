import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class iFrame {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\2000122010\\Documents\\chromedriver-win64\\chromedriver.exe");
		
		//Create CrhomeDriver
		ChromeOptions options = new ChromeOptions();
		
		options.addArguments("--start-maximized");
		options.addArguments("--remote-allow-origins=*");
		ChromeDriver driver = null;
		
		driver = new ChromeDriver(options);
		
		driver.get("http://127.0.0.1:5500/index.html");
		Thread.sleep(3000);


		//Find the iFrame element
		WebElement iframe = driver.findElement(By.id("iframeId"));
		
		//Switch the driver to iframe
		driver.switchTo().frame(iframe);
		
		//interact with iframe elements
		highlightElement(driver.findElement(By.id("elementInIframe")), driver);
		
		//Switch back to main document
		driver.switchTo().defaultContent();
		
		//interact with main document again
		highlightElement(iframe, driver);
		
		Thread.sleep(2000);
		
		//Close driver
		driver.quit();
	}

	public static void highlightElement(WebElement element, WebDriver driver) {
		JavascriptExecutor js =(JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.outline = '#f00 solid 2px' ", element);
	}
}
