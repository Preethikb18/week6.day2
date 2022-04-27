package week6.day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class ServiceNow extends BaseClass {

	@Test
	public  void service()  throws InterruptedException {	
	
		//Click Login
		driver.findElement(By.xpath("//button[text()='Log in']")).click();
			
		//Click “All”
		driver.findElement(By.xpath("(//div[text()='All'][@class='sn-widget-list-title'])[2]")).click();
		//Click New button
		driver.switchTo().frame(0);
		driver.findElement(By.xpath("//button[text()='New'][@id='sysverb_new']")).click();
		//Step8: Select a value for Caller and Enter value for short_description
		driver.findElement(By.xpath("//button[@id='lookup.incident.caller_id']")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> listHandles = new ArrayList<String>(windowHandles);
		String string = listHandles.get(1);
		driver.switchTo().window(listHandles.get(1));
		driver.findElement(By.xpath("//tbody[@class='list2_body']//tr[5]/td[3]")).click();
		driver.switchTo().window(listHandles.get(0));
		Thread.sleep(5000);
		driver.switchTo().frame(0);
		driver.findElement(By.name("incident.short_description")).sendKeys("Issue with email");
		//Step9: Read the incident number and save it a variable
		WebElement incident = driver.findElement(By.xpath("//input[@id='incident.number']"));
		String attribute = incident.getAttribute("value");
		System.out.println(attribute);
		Thread.sleep(2000);
		//Step10: Click on Submit button
		driver.findElement(By.xpath("//button[text()='Submit']")).click();
		//Step 11: Search the same incident number in the next search screen as below
		//driver.switchTo().frame(0);
		driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys(attribute,Keys.ENTER);
		//Step12: Verify the incident is created successful.
		String text = driver.findElement(By.xpath("//a[@class='linked formlink']")).getText();
		if(text.contains(attribute))
				{
		System.out.println("Incident Creation Successful");
				}
		else
				{
		System.out.println("Incident Creation Failed"); 
				};


	}

}
