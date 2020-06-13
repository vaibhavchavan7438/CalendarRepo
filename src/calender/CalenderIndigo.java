package calender;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CalenderIndigo {

	
	
	WebDriver driver;
	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		driver=new ChromeDriver();
		
		driver.manage().window().maximize();
//		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
//		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
	}
	
	@Test
	public void testCalenderIndigo() {
		

		driver.get("https://www.goindigo.in/");
		
		String date="15-March-2021";
		
		String[] dateFormat=date.split("-");
		
		String day=dateFormat[0];
		
		String month=dateFormat[1];
		
		String year=dateFormat[2];
		System.out.println(day);
		System.out.println(month);
		System.out.println(year);
		
//		driver.findElement(By.name("or-src")).clear();
//		driver.findElement(By.name("or-src")).sendKeys("Delhi");
//		driver.findElement(By.name("or-dest")).clear();
//		driver.findElement(By.name("or-dest")).sendKeys("Mumbai");
		
		//driver.findElement(By.xpath("//input[@name=\"or-depart\" and @xpath='1']")).click();
		driver.findElement(By.xpath("//*[@id=\"bookFlightTab\"]/form/div[3]/div[2]/div[1]/div/input")).click();
				
		String chosenYear=driver.findElement(By.xpath("//div[@class='ui-datepicker-header ui-widget-header ui-helper-clearfix ui-corner-left']//span[@class='ui-datepicker-year']")).getText().trim();
		System.out.println("chosenYear "+chosenYear);
		while(!year.equalsIgnoreCase(chosenYear)) {
			driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
			chosenYear=driver.findElement(By.xpath("//div[@class='ui-datepicker-header ui-widget-header ui-helper-clearfix ui-corner-left']//span[@class='ui-datepicker-year']")).getText().trim();
		}
		
		String chosenMonth=driver.findElement(By.xpath("//div[@class='ui-datepicker-header ui-widget-header ui-helper-clearfix ui-corner-left']//span[@class='ui-datepicker-month']")).getText();
		System.out.println("Month "+chosenMonth);
		while(!month.equalsIgnoreCase(chosenMonth)) {
		driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
			chosenMonth=driver.findElement(By.xpath("//div[@class='ui-datepicker-header ui-widget-header ui-helper-clearfix ui-corner-left']//span[@class='ui-datepicker-month']")).getText();
		}
		
		//String chosenDay=driver.findElement(By.xpath("//div[contains(@class,'ui-datepicker-group ui-datepicker-group-first')]//tr[1]//td[1]"));
			
		boolean flag=false;
		for(int row=1;row<=6;row++) {
			
			for(int col=1;col<=7;col++) {
		
				String chosenDay=driver.findElement(By.xpath("//div[contains(@class,'ui-datepicker-group ui-datepicker-group-first')]//tr["+row+"]//td["+col+"]")).getText();
				
				if(chosenDay.equalsIgnoreCase(day)) {
					driver.findElement(By.xpath("//div[contains(@class,'ui-datepicker-group ui-datepicker-group-first')]//tr["+row+"]//td["+col+"]")).click();
				flag=true;
					break;
				}
			}
			if(flag) {
				break;
			}
		}
	}
	
	
}
