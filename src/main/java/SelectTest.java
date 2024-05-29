import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

public class SelectTest {
	public static void main(String[] args) throws InterruptedException {
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
		
		//Find the select element
		WebElement selectElement = driver.findElement(By.id("selectId"));
		
		//Create Select instance
		Select select = new Select(selectElement);
		
		//Fin option
		select.selectByValue("3");
		Thread.sleep(3000);
		
		driver.quit();
	}	
}
