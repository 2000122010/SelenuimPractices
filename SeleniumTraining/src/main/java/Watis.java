import java.time.Duration;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Watis {
	
	/* Used to stop the execution until certain condition is met
	 * You might want to wait until certain element is visible
	 * characteristics:
	 * -If element is found before Duration set, execution will continue. Else, will throw a TimeOutException
	 * -Only applies to wait to specified elements
	 * ...
	 * @param driver
	 * */
	public WebElement ExplicitWait(WebDriver driver, By by) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		//WebElement element = driver.findElement(by);
		
		WebElement elementUsignExplicitWait = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		
		return elementUsignExplicitWait;
		
	}
	
	/* Is a global type of wait.
	 * characteristics:}
	 * -Applies to all WebDrivers interactions
	 * -If no element is found within the specified time, will throw a NotSuchElementException
	 * @param driver*/
	void ImplicitWait(WebDriver driver) {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//Bellow will write our steps
		// steps...
	}
	
	/**
	 * is Highly configurable.
	 * Allow us to specify the frequency of validation condition
	 * @param driver
	 * */
	private void FluentWait(WebDriver driver) {
		Wait<WebDriver> wait = new org.openqa.selenium.support.ui.FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofSeconds(10))
				.ignoring(NoSuchElementException.class);
		
		WebElement element = driver.findElement(By.id("theId"));
		
		WebElement elementWithFluentWait = wait.until(new Function<WebDriver, WebElement>(){
			public WebElement apply(WebDriver driver) {
				//
				return driver.findElement(By.id("theId"));
			}
		});
	}
}
