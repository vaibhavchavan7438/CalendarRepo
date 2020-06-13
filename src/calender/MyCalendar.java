package calender;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyCalendar {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.get("https://msrtc.maharashtra.gov.in/");

		String travelDate = "16-April-2020";

		String[] dateFormat = travelDate.split("-");

		String day = dateFormat[0];

		String month = dateFormat[1];

		String year = dateFormat[2];

		driver.findElement(By.xpath("//input[@id='from_txt']")).sendKeys("Kolhapur");
		driver.findElement(By.xpath("//input[@id='to_txt']")).sendKeys("Pune");

		driver.findElement(By.xpath("//input[@id='journeydate']")).click();

		WebElement monthDropDown = driver.findElement(By.xpath("//select[@class='datepick-new-month']"));

		WebElement yeatDropDown = driver.findElement(By.xpath("//select[@class='datepick-new-year']"));

		Select select = new Select(monthDropDown);
		select.selectByVisibleText("April");

		Select select1 = new Select(yeatDropDown);
		select1.selectByVisibleText("2020");

		WebElement busTypeDropDown = driver.findElement(By.xpath("//*[@id=\"busservicetype\"]"));
		Select select2 = new Select(busTypeDropDown);
		select2.selectByVisibleText("Night Express");

		boolean flag = false;
		for (int i = 1; i <= 6; i++) {

			for (int j = 1; j <= 7; j++) {

				// //*[@id="datepick-div"]/div[3]/table/tbody/tr[2]/td[2]
				String beforeXpath = "//*[@id=\"datepick-div\"]/div[3]/table/tbody/tr[";
				String afterXpath = "]/td[";
				String dayVal = driver.findElement(By.xpath(beforeXpath + i + afterXpath + j + "]")).getText();
				System.out.println(dayVal);
				if (dayVal.equals(day)) {
					driver.findElement(By.xpath(beforeXpath + i + afterXpath + j + "]")).click();
					flag = true;
					break;
				}

			}
			if (flag) {
				break;
			}

		}
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='submit']")).click();
		
		driver.close();

	}

}
