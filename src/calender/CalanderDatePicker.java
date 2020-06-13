package calender;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class CalanderDatePicker {
	@Test
	public void testDatePicker() throws InterruptedException {

		String dateTime = "29/05/2014 2:00 PM";

		System.setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();

		driver.manage().window().maximize();

		driver.get("http://demos.telerik.com/kendo-ui/datetimepicker/index");

//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// button to open calendar

		WebElement calenderButton = driver.findElement(By.xpath("//span[@aria-controls='datetimepicker_dateview']"));
		calenderButton.click();

		// To go next in calender

		WebElement nextLink = driver.findElement(By.xpath(
				"//div[@id='datetimepicker_dateview']//div[@class='k-header']//a[contains(@class,'k-link k-nav-next')]"));

		WebElement prevLink = driver.findElement(By.xpath(
				"//div[@id='datetimepicker_dateview']//div[@class='k-header']//a[contains(@class,'k-link k-nav-prev')]"));

		WebElement midLink = driver.findElement(By.xpath(
				"//div[@id='datetimepicker_dateview']//div[@class='k-header']//a[contains(@class,'k-link k-nav-fast')]"));
		
		//Split the data format
		String date_dd_MM_yyyy[]=(dateTime.split(" ")[0]).split("/");
		
		
		//get the year difference between current year and year to set in calander
		int yearDiff=Integer.parseInt(date_dd_MM_yyyy[2])-Calendar.getInstance().get(Calendar.YEAR);
		
		midLink.click();
		
		if(yearDiff!=0) {
			if(yearDiff>0) {
				for(int i=0;i<yearDiff;i++) {
					System.out.println("Year Difference is :" +i);
					nextLink.click();
				}
			}
			if(yearDiff<0) {
	  			for(int i=0;i>yearDiff;i--) {
					System.out.println("Year Difference is :" +i);
					prevLink.click();
				}
			}
		}
		Thread.sleep(1000);
		
		//Get all months from calendar to select correct one
		 List<WebElement> list_AllMonthToBook = driver.findElements(By.xpath("//div[@id='datetimepicker_dateview']//table//tbody//td[not(contains(@class,'k-other-month'))]"));
	        
	        list_AllMonthToBook.get(Integer.parseInt(date_dd_MM_yyyy[1])-1).click();
	        
	        Thread.sleep(1000);

	        //get all dates from calendar to select correct one

	        List<WebElement> list_AllDateToBook = driver.findElements(By.xpath("//div[@id='datetimepicker_dateview']//table//tbody//td[not(contains(@class,'k-other-month'))]"));
	        
	        list_AllDateToBook.get(Integer.parseInt(date_dd_MM_yyyy[0])-1).click();
	        
	        ///FOR TIME

	        WebElement selectTime = driver.findElement(By.xpath("//span[@aria-controls='datetimepicker_timeview']"));

	        //click time picker button

	        selectTime.click();

	        //get list of times

	        //List<WebElement> allTime = driver.findElements(By.xpath("//div[@data-role='popup'][contains(@style,'display: block')]//ul//li[@role='option']"));
	      
	        List<WebElement> allTime = driver.findElements(By.xpath("//*[@id=\"datetimepicker_timeview\"]//ul//li[@role='option']"));
		      
	        dateTime = dateTime.split(" ")[1]+" "+dateTime.split(" ")[2];

	     //select correct time

	        for (WebElement webElement : allTime) {

	            if(webElement.getText().equalsIgnoreCase(dateTime))

	            {

	                webElement.click();

	            }

	        }

	    
		driver.close();
	}

	
	
}
