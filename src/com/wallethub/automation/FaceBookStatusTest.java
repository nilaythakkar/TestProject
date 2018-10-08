package com.wallethub.automation;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class FaceBookStatusTest {
	public static String UserName ="enteremailhere";
	public static String PassWord ="enterpasswordhere";

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver;
		System.out.println("started test");
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		System.setProperty("webdriver.chrome.driver", "D:\\eclipse\\Jars\\chromedriver.exe");
		driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
	    WebDriverWait wait = new WebDriverWait(driver, 500);

        driver.get("https://www.facebook.com/");
        driver.manage().window().maximize();
        System.out.println("maximized");
        Thread.sleep(4000);
       // driver.findElement(By.id("//*[@id=\"viewport\"]/header/div/nav[3]/div[2]/a[2]")).click();
        driver.findElement(By.name("email")).sendKeys(UserName);
        driver.findElement(By.name("pass")).sendKeys(PassWord);
        driver.findElement(By.id("loginbutton")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//textarea[contains(@title,'Write something here')]")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[contains(@title,'Write something here')]")));
         driver.findElement(By.xpath("//textarea[contains(@title,'Write something here')]")).click();

         wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@contenteditable='true']")));
         wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@contenteditable='true']")));
       driver.findElement(By.xpath("//div[@contenteditable='true']")).sendKeys("Hello World");
		System.out.println("typed");

     wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button//*[contains(text(),'Share')]")));
     System.out.println("present");
 
     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button//*[contains(text(),'Share')]")));
       System.out.println("visible");
       wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button//*[contains(text(),'Share')]")));
       driver.findElement(By.xpath("//button//*[contains(text(),'Share')]")).click();
       Thread.sleep(1000);
       driver.findElement(By.id("userNavigationLabel")).click();
       Thread.sleep(1000);
       driver.findElement(By.xpath("//span[@class='_54nh'][text()='Log Out']")).click();
       Thread.sleep(1000000);
       //driver.findElement(By.linkText("Share")).click();
       try {
    	  // driver.switchTo().alert().accept();
    	   System.out.println("in try ");
    	   WebElement Element= driver.findElement(By.xpath("//button//*[contains(text(),'Share')]"));
    	   //String selector = (By.xpath("//*[@id=\"js_1j\"]//button"));

    	   //String jquery1 = “jQuery(\”” + selector + “\”).eq(0).mouseover();
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			Actions action = new Actions(driver);
			action.moveToElement(Element).click(Element).build().perform();
    	   executor.executeScript("\"arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');\",", Element);

       
       }
       catch(Exception e)
       {
    	   System.out.println(e.getStackTrace());
       }
       Thread.sleep(100000);
        /* wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"placeholder-bu18o\"]")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"placeholder-bu18o\"]")));
        driver.findElement(By.xpath("//*[@id=\"placeholder-bu18o\"]")).sendKeys("Hello");
        Thread.sleep(100000);*/
       }
	
}
