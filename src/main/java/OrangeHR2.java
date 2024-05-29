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

public class OrangeHR2 {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		//Set driver path in System properties
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\2000122010\\Documents\\chromedriver-win64\\chromedriver.exe");
		
		//Create CrhomeDriver
		ChromeOptions options = new ChromeOptions();
		
		//Create wait object
		Watis waitFor = new Watis();
		
		options.addArguments("--start-maximized");
		options.addArguments("--remote-allow-origins=*");
		ChromeDriver driver = null;
		
		driver = new ChromeDriver(options);
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
		//Wait until the page is fully charged then take a screenshot
		
		
		/*
		 * 1 To log in successful
		 * */
			// Login elements
			By username = By.name("username");
			By password = By.name("password");
			By loginButton = By.xpath("//button");
			
			//Write login credentials
			WebElement inputName = waitFor.ExplicitWait(driver, username);
			takeScreenshot("1.1.-Login_before", driver);
			highlightElement(inputName, driver);
			inputName.sendKeys("Admin");
			
			
			WebElement inputPass = driver.findElement(password);
			highlightElement(inputPass, driver);
			inputPass.sendKeys("admin123");
			
			
			//To click the login button
			WebElement inpuButton = driver.findElement(loginButton);
			takeScreenshot("1.2.-Login_after", driver);
			highlightElement(inpuButton, driver);
			inpuButton.click();
		
		
		/*
		 * 2.- Go to Time module, then search a employee by its name
		 * */
			//Dashboard elements
			By buttonTime = By.xpath("//button[@title=\"Timesheets\"]");
	
			
			//Go to Time sheet menu
			WebElement TimeSheetBtn = waitFor.ExplicitWait(driver, buttonTime);
			takeScreenshot("2.2.1.-Main menu", driver);
			highlightElement(TimeSheetBtn, driver);
			TimeSheetBtn.click();
			
			
			//TimeSheetElements
			By searchInput = By.cssSelector(".oxd-autocomplete-wrapper input");
			By view = By.xpath("//button[@type=\"submit\"]");
			
			//Search something...
			WebElement employeeInput = waitFor.ExplicitWait(driver, searchInput);
			takeScreenshot("2.2.2.-Before to search", driver);
			highlightElement(employeeInput, driver);
			employeeInput.sendKeys("C");
			takeScreenshot("2.2.3.-After to write", driver);
			
			//select an option
			Thread.sleep(3000);
			employeeInput.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
			
			Thread.sleep(250);
			takeScreenshot("2.2.3.-Selected option", driver);
			Thread.sleep(500);
			
			//View selected option
			WebElement clickViewBtn = driver.findElement(view);
			takeScreenshot("2.2.4.-Click view button", driver);
			highlightElement(clickViewBtn, driver);
			clickViewBtn.click();
			
			boolean isNewRecord=false;
			
			try {
				//TimeSheet create
				By edit = By.xpath("//button[text()=\" Create Timesheet \"]");
				WebElement clickEditBtn = waitFor.ExplicitWait(driver, edit);
				highlightElement(clickEditBtn, driver);
				takeScreenshot("2.2.4.1.-Create new time sheet", driver);
				clickEditBtn.click();
				isNewRecord=true;
			}
			catch(Exception e) {}
			//TimeSheet edit
			By edit = By.cssSelector(".oxd-button--ghost");
			WebElement clickEditBtn = waitFor.ExplicitWait(driver, edit);
			highlightElement(clickEditBtn, driver);
			takeScreenshot("2.2.4.-Create to edit", driver);
			clickEditBtn.click();
			
		
		/*
		 * 3.- Add a time sheet to this employee
		 * */
			//TimeSheet elements
			By projectInput = By.cssSelector(".oxd-autocomplete-text-input input");
			By activityInput = By.cssSelector(".oxd-select-text-input");
			By mondayInput = By.xpath("//td[3]/div/div/input");
			By tuesdayInput = By.xpath("//td[4]/div/div/input");
			By wedenesdayInput = By.xpath("//td[5]/div/div/input");
			By thursdayInput = By.xpath("//td[6]/div/div/input");
			By fridayInput = By.xpath("//td[7]/div/div/input");
			By saturdayInput = By.xpath("//td[8]/div/div/input");
			By sundayInput = By.xpath("//td[9]/div/div/input");
			By saveButton = By.xpath("//button[@type=\"submit\"]");
			String EraseAll = Keys.chord(Keys.CONTROL, "a", Keys.BACK_SPACE);
			
			
			//Choose project
			WebElement projectSearch = waitFor.ExplicitWait(driver, projectInput);
			takeScreenshot("3.1.1.-Before to search", driver);
			highlightElement(projectSearch, driver);
			if(isNewRecord==true) {
				projectSearch.sendKeys("A");
				takeScreenshot("3.1.2.-After to write", driver);
				
				//select an option
				Thread.sleep(3000);
				projectSearch.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
				
				Thread.sleep(250);
				takeScreenshot("3.1.3.-Selected option", driver);
				Thread.sleep(500);
			}
			
			
			
			//Choose activity
			WebElement ActivityInput = waitFor.ExplicitWait(driver, activityInput);
			takeScreenshot("3.2.1.-Before to search", driver);
			highlightElement(ActivityInput, driver);
			ActivityInput.click();
			takeScreenshot("3.2.2.-View options", driver);
			
			//select an option
			Thread.sleep(3000);
			ActivityInput.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
			
			Thread.sleep(250);
			takeScreenshot("3.2.3.-Selected option", driver);
			Thread.sleep(500);
			
			//Modify inputs
			WebElement mondayBox = driver.findElement(mondayInput);
			highlightElement(mondayBox, driver);
			mondayBox.sendKeys(EraseAll);
			mondayBox.sendKeys("8");

			WebElement tuesdayBox = driver.findElement(tuesdayInput);
			highlightElement(tuesdayBox, driver);
			tuesdayBox.sendKeys(EraseAll);
			tuesdayBox.sendKeys("8");

			WebElement wedenesdayBox = driver.findElement(wedenesdayInput);
			highlightElement(wedenesdayBox, driver);
			wedenesdayBox.sendKeys(EraseAll);
			wedenesdayBox.sendKeys("8");

			WebElement thursdayBox = driver.findElement(thursdayInput);
			highlightElement(thursdayBox, driver);
			thursdayBox.sendKeys(EraseAll);
			thursdayBox.sendKeys("8");

			WebElement fridayBox = driver.findElement(fridayInput);
			highlightElement(fridayBox, driver);
			fridayBox.sendKeys(EraseAll);
			fridayBox.sendKeys("8");

			WebElement saturdayBox = driver.findElement(saturdayInput);
			highlightElement(saturdayBox, driver);
			saturdayBox.sendKeys(EraseAll);
			saturdayBox.sendKeys("0");

			WebElement sundayBox = driver.findElement(sundayInput);
			highlightElement(sundayBox, driver);
			sundayBox.sendKeys(EraseAll);
			sundayBox.sendKeys("0");
			
			//To save record
			WebElement save = driver.findElement(saveButton);
			highlightElement(save, driver);
			takeScreenshot("3.3.1.-Input done", driver);
			save.click();
		
		
		/*
		 * 4.- Validate that the time sheet was created successfully
		 * */
			//Success toast
			String subTotal="";
			By SuccessToast = By.xpath("//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']");
			WebElement recordSaved = waitFor.ExplicitWait(driver, SuccessToast);
			
			highlightElement(recordSaved, driver);
			takeScreenshot("4.1.1.-Success!", driver);
		
		/*
		 * 5.- Validate total hours
		 * */
			//To wait preview
			By submit = By.xpath("//button[text()=\" Submit \"]");
			WebElement submitButton = waitFor.ExplicitWait(driver, submit);
			
			
			//Validate data
			By mon = By.xpath("//tr[@class=\"orangehrm-timesheet-table-body-row --total\"]/td[3]");
			WebElement monData = driver.findElement(mon);
			
			By tue = By.xpath("//tr[@class=\"orangehrm-timesheet-table-body-row --total\"]/td[4]");
			WebElement tueData = driver.findElement(tue);
			subTotal=sumTime(monData.getText(), tueData.getText());
			
			By wed = By.xpath("//tr[@class=\"orangehrm-timesheet-table-body-row --total\"]/td[5]");
			WebElement wedData = driver.findElement(wed);
			subTotal=sumTime(subTotal, wedData.getText());
			
			By thu = By.xpath("//tr[@class=\"orangehrm-timesheet-table-body-row --total\"]/td[6]");
			WebElement thuData = driver.findElement(thu);
			subTotal=sumTime(subTotal, thuData.getText());
			
			By fri = By.xpath("//tr[@class=\"orangehrm-timesheet-table-body-row --total\"]/td[7]");
			WebElement friData = driver.findElement(fri);
			subTotal=sumTime(subTotal, friData.getText());
			
			By sat = By.xpath("//tr[@class=\"orangehrm-timesheet-table-body-row --total\"]/td[8]");
			WebElement satData = driver.findElement(sat);
			subTotal=sumTime(subTotal, satData.getText());
			
			By sun = By.xpath("//tr[@class=\"orangehrm-timesheet-table-body-row --total\"]/td[9]");
			WebElement sunData = driver.findElement(sun);
			subTotal=sumTime(subTotal, sunData.getText());
			
			By total = By.xpath("//tr[@class=\"orangehrm-timesheet-table-body-row --total\"]/td[10]");
			WebElement totalData = driver.findElement(total);
			String Total= totalData.getText();
			
			if(subTotal.equalsIgnoreCase(Total)) {
				//To submit
				highlightElement(submitButton, driver);
				submitButton.click();
				takeScreenshot("4.2.1.-Submit", driver);
			}
			else {
				System.out.println("SubTotal= "+subTotal+"/nTotal= "+Total);
			}
		
			//Final success
			waitFor.ExplicitWait(driver, SuccessToast);
			takeScreenshot("5.1.1.-Final success!", driver);
			
			//Thread.sleep(30000);
			driver.quit();
		
	}
	public static void highlightElement(WebElement element, WebDriver driver) {
		JavascriptExecutor js =(JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.outline = '#f00 solid 2px' ", element);
	}
	
	public static void takeScreenshot(String name, WebDriver driver) throws IOException{
		String ssFolder ="C:\\Users\\2000122010\\Pictures\\Orange2\\%s.png";
		String ssName = String.format(ssFolder, name);
		
		TakesScreenshot ss = (TakesScreenshot)driver;
		File screenshot = ss.getScreenshotAs(OutputType.FILE);
		
		FileUtils.copyFile(screenshot, new File(ssName));
		//FileUtil.copyFIle(screenshot, new File("C:\\Users\\2000122010\\Pictures\\ScreenShot"));
	}

	public static String sumTime(String time1, String time2) {
		String[] time1Str = time1.split(":");
		String[] time2Str = time2.split(":");
		
		//System.out.println("/ntime1= "+time1Str[0]+"/ttime2="+time2Str[0]);
		
		int h1=Integer.valueOf(time1Str[0]);
		int m1=Integer.valueOf(time1Str[1]);
		int h2=Integer.valueOf(time2Str[0]);
		int m2=Integer.valueOf(time2Str[1]);
		
		//System.out.println("/n"+h1+"/t"+m1+"/t"+h2+"/t"+m2);
		
		int totalHours = h1 + h2; 
		int totalMinutes = m1 + m2; 
		if (totalMinutes >= 60) { 
		  totalHours ++; 
		  totalMinutes = totalMinutes % 60; 
		} 
		String strH=String.valueOf(totalHours);  
		String strM=String.valueOf(totalMinutes);  
		if(strH.length()==1) {strH="0"+strH;}
		if(strM.length()==1) {strM="0"+strM;}
		
		return strH+":"+strM;
	}
}
