package week6.day2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public RemoteWebDriver driver;

	@BeforeMethod
	@Parameters({"user_name","user_password","browser","filter"})
	public void preConditions(String user_name,String user_password,String browser,String filter  )
	{
		
		WebDriverManager.chromedriver().setup();
		 driver = new ChromeDriver();
		driver.get("https://dev121343.service-now.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		//switch into frame
		driver.switchTo().frame(0);
		driver.findElement(By.id("user_name")).sendKeys(user_name);
		//Enter password 
		driver.findElement(By.id("user_password")).sendKeys(user_password);
	
		//Search “incident “ Filter Navigator
		driver.findElement(By.id("filter")).sendKeys(filter);	
		if(browser.equalsIgnoreCase("chrome"))
		{
	       WebDriverManager.chromedriver().setup();
	       driver =new ChromeDriver();
		}
       else if(browser.equalsIgnoreCase("edge"))
       {
	      WebDriverManager.edgedriver().setup();
	      driver = new EdgeDriver();
     }	
		
	}
	
	@AfterMethod
	public void postConditions()
	{
		driver.close();
	}

}
