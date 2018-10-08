package com.wallethub.commonactions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.wallethub.pages.WallethubLoginPage;

public class CommonActions extends BaseTestCase {

	public static void enterText(String objectName) {

		String obectProperties = readObjects.get(objectName);
		String locator[] = new String[2];
		locator = obectProperties.split("=", 2);
		if (locator[0].equalsIgnoreCase("name")) {
			driver.findElement(By.name(locator[1])).clear();
			driver.findElement(By.name(locator[1])).sendKeys(testDataMap.get(objectName));
			System.out.println("entered text in "+objectName+":  " + testDataMap.get(objectName));
			logger.info("entered text in "+objectName+":  " + testDataMap.get(objectName));
		} else if (locator[0].equalsIgnoreCase("\"xPath")) {
			locator[1] = locator[1].substring(0, locator[1].length() - 1);
			driver.findElement(By.xpath(locator[1])).clear();
			driver.findElement(By.xpath(locator[1])).sendKeys(testDataMap.get(objectName));
			System.out.println("entered text in "+objectName+":  " + testDataMap.get(objectName));
			logger.info("entered text in "+objectName+":  " + testDataMap.get(objectName));
		}

	}

	public static void click(String objectName) {

		String obectProperties = readObjects.get(objectName);
		String locator[] = new String[2];
		locator = obectProperties.split("=", 2);
		if (locator[0].equalsIgnoreCase("cssSelector")) {
			driver.findElement(By.cssSelector(locator[1])).click();
			System.out.println("clicked " + objectName);
			logger.info("clicked " + objectName);
		} else if (locator[0].equalsIgnoreCase("\"xPath")) {
			locator[1] = locator[1].substring(0, locator[1].length() - 1);
			driver.findElement(By.xpath(locator[1])).click();
			System.out.println("clicked " + objectName);
			logger.info("clicked " + objectName);
		} else if (locator[0].equalsIgnoreCase("className")) {
			driver.findElement(By.className(locator[1])).click();
			System.out.println("clicked " + objectName);
			logger.info("clicked " + objectName);
		}

	}

	public static String getText(String objectName) {
		String text = null;
		String obectProperties = readObjects.get(objectName);
		String locator[] = new String[2];
		locator = obectProperties.split("=", 2);
		if (locator[0].equalsIgnoreCase("cssSelector")) {
			text = driver.findElement(By.cssSelector(locator[1])).getText();
			System.out.println("text in " + objectName + " = " + text);
			logger.info("text in " + objectName + " = " + text);
		} else if (locator[0].equalsIgnoreCase("\"xPath")) {
			locator[1] = locator[1].substring(0, locator[1].length() - 1);
			text = driver.findElement(By.xpath(locator[1])).getText();
			System.out.println("text in " + objectName + " = " + text);
			logger.info("text in " + objectName + " = " + text);
		} else if (locator[0].equalsIgnoreCase("className")) {
			text = driver.findElement(By.className(locator[1])).getText();
			System.out.println("text in " + objectName + " = " + text);
			logger.info("text in " + objectName + " = " + text);
		}

		return text;

	}

	public static boolean verifyPresence(String objectName) {

		String obectProperties = readObjects.get(objectName);
		String locator[] = new String[2];
		locator = obectProperties.split("=", 2);
		if (locator[0].equalsIgnoreCase("cssSelector")) {
			if (driver.findElement(By.cssSelector(locator[1])).isDisplayed()) {
				System.out.println("Element " + objectName + " is displayed");
				logger.pass("Element " + objectName + " is displayed");
				return true;
			} else {
				System.out.println("Element " + objectName + " is not displayed");
				logger.fail("Element " + objectName + " is not displayed");
				return false;
			}
		} else if (locator[0].equalsIgnoreCase("\"xPath")) {
			locator[1] = locator[1].substring(0, locator[1].length() - 1);
			if (driver.findElement(By.xpath(locator[1])).isDisplayed()) {
				System.out.println("Element " + objectName + " is displayed");
				logger.pass("Element " + objectName + " is displayed");
				return true;
			} else {
				System.out.println("Element " + objectName + " is not displayed");
				logger.fail("Element " + objectName + " is not displayed");
				return false;
			}
		} else if (locator[0].equalsIgnoreCase("className")) {
			if (driver.findElement(By.className(locator[1])).isDisplayed()) {
				System.out.println("Element " + objectName + " is displayed");
				logger.pass("Element " + objectName + " is displayed");
				return true;
			} else {
				System.out.println("Element " + objectName + " is not displayed");
				logger.fail("Element " + objectName + " is not displayed");
				return false;
			}
		}
		return false;
	}

	public static void hover(String objectName) {
		String obectProperties = readObjects.get(objectName);
		String locator[] = new String[2];
		locator = obectProperties.split("=", 2);
		if (locator[0].equalsIgnoreCase("\"xPath")) {
			locator[1] = locator[1].substring(0, locator[1].length() - 1);
			WebElement Element = driver.findElement(By.xpath(locator[1]));
			action.moveToElement(Element).build().perform();
		}
		System.out.println("hovered on " + objectName);
		logger.info("hovered on " + objectName);
	}

	public static void navigate(String URL) {

		driver.navigate().to(testDataMap.get(URL));
	}

	public static boolean readTestData(String file) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(new File(".\\resources\\data\\testdata\\" + file)));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			return false;
		}
		String str = "";

		try {
			while ((str = br.readLine()) != null) {
				testDataMap.put(str.split(",")[0], str.split(",")[1]);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		testDataMap.put("homeurl", "https://wallethub.com/profile/"+username);
		return true;
	}

	public static void login() {
		new WallethubLoginPage();
		testDataMap.putIfAbsent(WallethubLoginPage.EMAIL, email);
		testDataMap.putIfAbsent(WallethubLoginPage.PASSWORD, password);

		enterText(WallethubLoginPage.EMAIL);
		enterText(WallethubLoginPage.PASSWORD);
		click(WallethubLoginPage.LOGIN);

	}

	public static void readObjectFile(String fileName) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(
					new FileReader(new File(".\\resources\\data\\objectproperties\\" + fileName + ".csv")));
		} catch (FileNotFoundException fileNotFound) {
			fileNotFound.printStackTrace();
			System.out.println("Object repository file not found for" + fileName);
			logger.warning("Object repository file not found for" + fileName);
		}
		String str = "";

		try {
			while ((str = br.readLine()) != null) {
				readObjects.put(str.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)")[0],
						str.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)")[1]);
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("IO Exception occured while reading object repository for " + fileName);
			logger.warning("IO Exception occured while reading object repository for " + fileName);
		}
		try {
			br.close();
		} catch (IOException e) {
			System.out.println("IO Exception occured while reading object repository for " + fileName);
			logger.warning("IO Exception occured while reading object repository for " + fileName);
			e.printStackTrace();
		}
	}

	public static String setObjectProperty(String objectName, String variableName, String value) {
		String obectProperties = readObjects.get(objectName);
		obectProperties = obectProperties.replaceAll(variableName, value);
		System.out.println("object properties " + obectProperties);
		readObjects.put(objectName, obectProperties);
		return obectProperties;
	}

	public static WebElement returnElement(String objectName) {
		String obectProperties = readObjects.get(objectName);
		String locator[] = new String[2];
		locator = obectProperties.split("=", 2);
		
		if (locator[0].equalsIgnoreCase("cssSelector")) {
			WebElement Element = driver.findElement(By.cssSelector(locator[1]));
			if (Element.isDisplayed()) {
				System.out.println("Element " + objectName + " is displayed");
				logger.pass("Element " + objectName + " is displayed");
				return Element;
			} else {
				System.out.println("Element " + objectName + " is not displayed");
				logger.fail("Element " + objectName + " is not displayed");
				return null;
			}
		} else if (locator[0].equalsIgnoreCase("\"xPath")) {
			locator[1] = locator[1].substring(0, locator[1].length() - 1);
			WebElement Element = driver.findElement(By.xpath(locator[1]));
			if (Element.isDisplayed()) {
				System.out.println("Element " + objectName + " is displayed");
				logger.pass("Element " + objectName + " is displayed");
				return Element;
			} else {
				System.out.println("Element " + objectName + " is not displayed");
				logger.fail("Element " + objectName + " is not displayed");
				return null;
			}
		} else if (locator[0].equalsIgnoreCase("className")) {
			WebElement Element = driver.findElement(By.className(locator[1]));
			if (Element.isDisplayed()) {
				System.out.println("Element " + objectName + " is displayed");
				logger.pass("Element " + objectName + " is displayed");
				return Element;
			} else {
				System.out.println("Element " + objectName + " is not displayed");
				logger.fail("Element " + objectName + " is not displayed");
				return null;
			}
		}
		return null;
	}

	public static void javaScriptClick(String objectName) {
		String obectProperties = readObjects.get(objectName);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		String locator[] = new String[2];
		locator = obectProperties.split("=", 2);
		
		if (locator[0].equalsIgnoreCase("cssSelector")) {
			WebElement Element = driver.findElement(By.cssSelector(locator[1]));
			executor.executeScript("arguments[0].click();", Element);

			if (Element.isDisplayed()) {
				System.out.println("Element " + objectName + " is displayed");
				logger.pass("Element " + objectName + " is displayed");
			} else {
				System.out.println("Element " + objectName + " is not displayed");
				logger.fail("Element " + objectName + " is not displayed");
			}
		} else if (locator[0].equalsIgnoreCase("\"xPath")) {
			locator[1] = locator[1].substring(0, locator[1].length() - 1);
			WebElement Element = driver.findElement(By.xpath(locator[1]));
			executor.executeScript("arguments[0].click();", Element);
			if (Element.isDisplayed()) {
				System.out.println("Element " + objectName + " is displayed");
				logger.pass("Element " + objectName + " is displayed");
			} else {
				System.out.println("Element " + objectName + " is not displayed");
				logger.fail("Element " + objectName + " is not displayed");
			}
		} else if (locator[0].equalsIgnoreCase("className")) {
			WebElement Element = driver.findElement(By.className(locator[1]));
			executor.executeScript("arguments[0].click();", Element);
			if (Element.isDisplayed()) {
				System.out.println("Element " + objectName + " is displayed");
				logger.pass("Element " + objectName + " is displayed");
			} else {
				System.out.println("Element " + objectName + " is not displayed");
				logger.fail("Element " + objectName + " is not displayed");
			}
		}
	}

	public static boolean compareStrings(String actual, String expected) {
		if (actual.equalsIgnoreCase(expected)) {
			logger.pass("actual =" + actual );
			logger.pass(" expected = " + expected);
			System.out.println("actual =" + actual);
			System.out.println(" expected = " + expected);
			return true;
		} else {
			logger.fail("actual =" + actual );
			logger.fail(" expected = " + expected);
			System.out.println("actual =" + actual);
			System.out.println(" expected = " + expected);
			return false;
		}
	}

	public static String getCurrentURL() {
		return driver.getCurrentUrl();
	}
	public static void waitMilliSeconds(long milliseconds) {
try {
	Thread.sleep(milliseconds);
} catch (InterruptedException e) {
	e.printStackTrace();
}
	}
}