package com.wallethub.commonactions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class BaseTestCase {
	public static WebDriver driver;
	public static FluentWait<WebDriver> wait;
	public static HashMap<String, String> data = new HashMap<String, String>();
	public static int noOfOptions;
	public static List<WebElement> options;
	public static ExtentTest logger;
	static ExtentHtmlReporter reporter;
	static ExtentReports extent;
	static ExtentTest test;
	public static Actions action;
	public static HashMap<String, String> readObjects = new HashMap<String, String>();
	public static HashMap<String, String> testDataMap = new HashMap<String, String>();
	public static String email;
	public static String password;
	public static String username;
	@BeforeTest
	public void launch() {
		// BufferedReader br = null;
		FileInputStream fs = null;

		Date date = new Date();

		// Loading config.properties file

		File conf = new File("./Configuration/config.property");
		try {
			fs = new FileInputStream(conf);
		} catch (FileNotFoundException fileException) {
			fileException.printStackTrace();
		}
		Properties property = new Properties();
		try {
			property.load(fs);
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}

		// config.properties file loaded
		String browser = property.getProperty("Browser");
		int waitTime = Integer.parseInt(property.getProperty("Wait"));
		String URL = property.getProperty("URL");
		email=property.getProperty("email");
		password=property.getProperty("password");
		username=property.getProperty("username");
		String logPath = property.getProperty("LogPath");
		System.out.println("Logs will be generated in " + logPath);

		// Initializing reporter

		reporter = new ExtentHtmlReporter(property.getProperty("LogPath") + "WalletHub" + date.getTime() + ".html");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		logger = extent.createTest("WalletHubTest");

		// Setting the browser based on the browser name in config.properties file

		if (browser.equalsIgnoreCase("Firefox"))

		{
			
			FirefoxProfile ffprofile = new FirefoxProfile();
			ffprofile.setPreference("dom.webnotifications.enabled", false);
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.setCapability("marionette", true);
			firefoxOptions.setProfile(ffprofile);
			System.setProperty("webdriver.gecko.driver", property.getProperty("FirefoxDriver"));
			driver = new FirefoxDriver(firefoxOptions);

		}  else {
			
			System.setProperty("webdriver.chrome.driver", property.getProperty("ChromeDriver"));
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			driver = new ChromeDriver(options);
			
		}
		action = new Actions(driver);
		wait = new WebDriverWait(driver, 500).pollingEvery(Duration.ofSeconds(5));
		driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(waitTime, TimeUnit.SECONDS);
		driver.get(URL);
		logger.info("launched url");
		driver.manage().window().maximize();
	}
	@AfterTest()
	public void flush()
	{
		extent.flush();
		driver.quit();
	}
}