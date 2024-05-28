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

public class OrangeHR {

	public static void main(String[] args) throws Exception {
		//Login elements
		By username = By.name("username");
		By password = By.name("password");
		By loginButton = By.xpath("//button");
		
		//To wait object
		Watis waitFor = new Watis();
		
		
		/* Go to OrangeHR */
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\2000122010\\Documents\\chromedriver-win64\\chromedriver.exe");
		
		
		//Create ChromeDriver
		ChromeOptions options = new ChromeOptions();
		
		options.addArguments("--start-maximized");
		options.addArguments("--remote-allow-origins=*");
		
		ChromeDriver driver = new ChromeDriver(options);
		
		//Navigate to OrangeHr's page
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
		//Wait until the page is fully charged
		takeScreenshot("1.1.Login_before", driver);
		
		/*
		 * 1. Login
		 * */
		
			//Write login credentials
			WebElement inputName = waitFor.ExplicitWait(driver, username);
			highlightElement(inputName, driver);
			inputName.sendKeys("Admin");
			
			
			WebElement inputPass = driver.findElement(password);
			highlightElement(inputPass, driver);
			inputPass.sendKeys("admin123");
			
			
			//To click the login button
			WebElement inpuButton = driver.findElement(loginButton);
			highlightElement(inpuButton, driver);
			takeScreenshot("1.2.Login_after", driver);
			inpuButton.click();
		
		/*
		 * 2.Main menu
		 * */
		
			//Menu elements
			By menuButton = By.xpath("//div/ul[@class=\"oxd-main-menu\"]/li[1]/a");
			
			//To click in button
			WebElement adminButton = waitFor.ExplicitWait(driver, menuButton);
			highlightElement(adminButton, driver);
			takeScreenshot("2.Main_menu", driver);
			adminButton.click();
		
		/*
		 * 3.Admin menu
		 * */
		
			//Admin menu elements
			By user = By.xpath("//div[@class=\"oxd-form-row\"]/div/div[1]/div/div[2]/input");
			By srchButton = By.xpath("//div[@class=\"oxd-form-actions\"]/button[2]");
			By edit = By.cssSelector(".oxd-table-card button:nth-child(2)");
			String userToSearch="Isaac";
			By alreadyExists = By.xpath("//div[contains(@class,\"oxd-padding-cell\")]/div[contains(., \""+userToSearch+"\")]");
			
			WebElement searchButton = waitFor.ExplicitWait(driver, srchButton);
			
			try {
				WebElement defaultSearch = waitFor.ExplicitWait(driver, alreadyExists);
				highlightElement(defaultSearch, driver);
			}
			catch(Exception e){
				userToSearch="Emmanuel";
			};
			
			
			
			//To search user
			WebElement inputSearch = driver.findElement(user);
			highlightElement(inputSearch, driver);
			inputSearch.sendKeys(userToSearch);
			
			
			//To click the search button
			highlightElement(searchButton, driver);
			takeScreenshot("3.Before search", driver);
			searchButton.click();
			
			//To click the edit button
			WebElement editButton = waitFor.ExplicitWait(driver, edit);
			highlightElement(editButton, driver);
			takeScreenshot("3.After search", driver);
			editButton.click();
		
		/*
		 * 4. Edit user
		 * */
		
			//To edit selected user elements
			By role = By.cssSelector("div.oxd-select-text-input");
			By userEdit = By.cssSelector("div input:nth-child(1)");
			By save = By.cssSelector("div.oxd-form-actions button:nth-child(3)");
			By modal = By.xpath("//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']");
			
			//To search user
			WebElement newUserInput = waitFor.ExplicitWait(driver,userEdit);
			takeScreenshot("4.1.Old input", driver);
			highlightElement(newUserInput, driver);
			
			String newUser="Isaac";
			if(newUser==userToSearch) {
			newUser="Emmanuel";
			}
			
			String EraseAll = Keys.chord(Keys.CONTROL, "a", Keys.BACK_SPACE);
			
			
			newUserInput.sendKeys(EraseAll);
			newUserInput.sendKeys(newUser);
			
			
			//To click the search button
			WebElement divSelect = driver.findElement(role);
			highlightElement(divSelect, driver);
			divSelect.click();
			divSelect.sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER);
			
			
			//To click the submit button
			WebElement toSave = driver.findElement(save);
			highlightElement(toSave, driver);
			takeScreenshot("4.2.New input", driver);
			toSave.click();
			
			//Submit successful

			By SuccessToast = By.xpath("//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']");
			WebElement recordSaved = waitFor.ExplicitWait(driver, SuccessToast);
			waitFor.ExplicitWait(driver, SuccessToast);
			takeScreenshot("4.3. success!", driver);
			
			WebElement saved = waitFor.ExplicitWait(driver, user);
			highlightElement(saved, driver);
			takeScreenshot("4.4.Redord saved", driver);
		
		/*
		 * 5. Validate results
		 * */
			By admin = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div[1]/div/div[6]/div/button[2]/i");
			waitFor.ExplicitWait(driver, admin);
			takeScreenshot("5.Final form", driver);
		
			driver.quit();
		
		
	}
	
	public static void highlightElement(WebElement element, WebDriver driver) {
		JavascriptExecutor js =(JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.outline = '#f00 solid 2px' ", element);
	}
	
	public static void takeScreenshot(String name, WebDriver driver) throws IOException{
		String ssFolder ="C:\\Users\\2000122010\\Pictures\\Orange\\%s.png";
		String ssName = String.format(ssFolder, name);
		
		TakesScreenshot ss = (TakesScreenshot)driver;
		File screenshot = ss.getScreenshotAs(OutputType.FILE);
		
		FileUtils.copyFile(screenshot, new File(ssName));
		//FileUtil.copyFIle(screenshot, new File("C:\\Users\\2000122010\\Pictures\\ScreenShot"));
	}

}
