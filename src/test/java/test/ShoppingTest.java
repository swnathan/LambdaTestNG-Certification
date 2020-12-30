package test;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.Logs;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import helper.GlobalVariables;
import net.serenitybdd.core.annotations.findby.By;
import pageobjects.LoginPage;
import pageobjects.MainPage;

public class ShoppingTest {

	private static WebDriver driver;
	Logs logs;
	LogEntries logEntries;
	String destinationFile = "";
	String destinationFileName = "";

	@BeforeTest
	public void beforeTest() throws MalformedURLException {

		URL server;
		server = new URL(GlobalVariables.remoteURL);
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("build", "build");
		capabilities.setCapability("name", "test");
		capabilities.setCapability("platform", "Windows 10");
		capabilities.setCapability("browserName", "Chrome");
		capabilities.setCapability("version", "86.0");
		capabilities.setCapability("console", true);
		capabilities.setCapability("network", true);
		capabilities.setCapability("browserName", "Firefox");
		capabilities.setCapability("version","84.0");
		// System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
		// driver = new ChromeDriver();
		// logs = driver.manage().logs();
		// logEntries = logs.get(LogType.BROWSER);
		// List<LogEntry> logs= logs.getAll();
		driver = new RemoteWebDriver(server, capabilities);
		((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test(priority = 1)
	public void testShopping() throws InterruptedException, IOException, AWTException {
		try {
			driver.get(GlobalVariables.applicationURL);
			LoginPage loginpage = new LoginPage(driver);
			loginpage.waitFor(loginpage.getUserName());
			loginpage.getUserName().sendKeys(GlobalVariables.userName);
			loginpage.waitFor(loginpage.getPassword());
			loginpage.getPassword().sendKeys(GlobalVariables.password);
			loginpage.getCookiesLabel("I ACCEPT").waitUntilVisible();
			loginpage.getCookiesLabel("I ACCEPT").click();
			loginpage.getSubmit().waitUntilVisible();
			loginpage.getSubmit().click();
			waitForPageToLoad(loginpage.getDriver());
			MainPage mainPage = new MainPage(driver);
			mainPage.waitFor(mainPage.getEmail());
			mainPage.getEmail().sendKeys(GlobalVariables.email);
			mainPage.getPopulate().waitUntilVisible();
			mainPage.getPopulate().click();
			mainPage.getDriver().switchTo().alert().accept();
			mainPage.getFrequencyRadio("Every month").waitUntilVisible();
			mainPage.getFrequencyRadio("Every month").click();
			JavascriptExecutor js = (JavascriptExecutor) mainPage.getDriver();
			js.executeScript("arguments[0].scrollIntoView();", mainPage.getDecisiveFactors("customer-service"));
			js.executeScript("arguments[0].scrollIntoView();", mainPage.getPaymentMode("Wallets"));
			mainPage.getECommercePurchaseCheckbox().waitUntilVisible();
			mainPage.getECommercePurchaseCheckbox().click();
			new Actions(driver).dragAndDropBy(mainPage.getSlider(), 500, 0).build().perform();
			System.out.println(mainPage.getSlider().getAttribute("style"));
			Assert.assertTrue(mainPage.getSlider().getAttribute("style").equalsIgnoreCase("left: 88.8889%;"),
					"Rating 9 scale is not selected");
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.open()");
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			mainPage.getDriver().switchTo().window(tabs.get(1));
			mainPage.getDriver().get(GlobalVariables.seleniumAutomationURL);
			waitForPageToLoad(mainPage.getDriver());
			js.executeScript("arguments[0].scrollIntoView();", mainPage.getJenkinsLogo());
			String logo = mainPage.getJenkinsLogo().getAttribute("src");
			URL imageURL = new URL(logo);
			saveImage(imageURL);
			mainPage.getDriver().switchTo().window(tabs.get(0));
			mainPage.getComments().waitUntilVisible();
			mainPage.getComments().sendKeys("feedback");
			js.executeScript("window.scrollBy(0,500)");
			File file = new File(destinationFile);
			String absolute = file.getAbsolutePath();
			WebElement uploadElement = driver.findElement(By.xpath("//input[@id='file']"));
			((JavascriptExecutor) mainPage.getDriver()).executeScript("arguments[0].removeAttribute('style')",
					uploadElement);
			uploadElement.sendKeys(absolute);
			Alert alert = driver.switchTo().alert();
			alert.getText();
			Assert.assertTrue(alert.getText().contains("your image upload sucessfully!!"),
					"Image not uploaded successfully");
			Assert.assertTrue(file.getName().equalsIgnoreCase(destinationFileName),
					"Image Name uploaded/Download is different");
			alert.accept();
			mainPage.getSubmit().waitUntilVisible();
			mainPage.getSubmit().click();
			mainPage.waitForTextToAppear("You have successfully submitted the form");
		} catch (IOException IOException) {
			IOException.printStackTrace();
		}

	}

	public void saveImage(URL url) {
		destinationFile = "driver/jenkins.svg";
		File file = new File(destinationFile);
		destinationFileName = file.getName();
		file.getAbsolutePath();
		InputStream is;
		try {
			is = url.openStream();
			OutputStream os = new FileOutputStream(destinationFile);
			byte[] b = new byte[2048];
			int length;

			while ((length = is.read(b)) != -1) {
				os.write(b, 0, length);
			}

			is.close();
			os.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void waitForPageToLoad(WebDriver driver) {
		ExpectedCondition<Boolean> javascriptDone = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				try {
					return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
				} catch (Exception e) {
					return Boolean.FALSE;
				}
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(javascriptDone);
	}

	@AfterTest
	public void afterTest() {
		driver.quit();

	}

}