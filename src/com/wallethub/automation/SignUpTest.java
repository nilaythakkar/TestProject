package com.wallethub.automation;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
@Test()
public class SignUpTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver;
		System.out.println("started test");
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		System.setProperty("webdriver.chrome.driver", "D:\\eclipse\\Jars\\chromedriver.exe");
		driver = new ChromeDriver(options);
		/*System.setProperty("webdriver.gecko.driver", "D:\\eclipse\\Jars\\geckodriver.exe");
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);
        driver = new FirefoxDriver(capabilities);*/
        driver.get("https://wallethub.com/join/light");
        
        Thread.sleep(4000);
       // driver.findElement(By.id("//*[@id=\"viewport\"]/header/div/nav[3]/div[2]/a[2]")).click();
        driver.findElement(By.name("em")).sendKeys("nilaythakkar007@gmail.com");
        
        driver.findElement(By.name("pw1")).sendKeys("Password@123");
        
        
        driver.findElement(By.name("pw2")).sendKeys("Password@123");
        driver.findElement(By.className("check")).click();
        driver.findElement(By.cssSelector("button[type='button']")).click();
        
        
        Thread.sleep(400000);
        /*driver.findElement(By.xpath("//*[@id=\"join\"]/dip/form/div[2]/input"));
        
        driver.findElement(By.id("//*[@id=\"join\"]/div/form/div[3]/a[2]")).click();
        */
        
	}

}
