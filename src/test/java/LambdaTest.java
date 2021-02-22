
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.Logs;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import helper.GlobalVariables;
import pageobjects.EditionsPage;
import pageobjects.MainPage;
import pageobjects.NewsLetterPage;

public class LambdaTest {

	private static WebDriver driver;
	Logs logs;
	LogEntries logEntries;
	String destinationFile = "";
	String destinationFileName = "";
	URL server;

	@BeforeTest
	public void beforeTest() throws MalformedURLException {

		server = new URL(GlobalVariables.remoteURL);
	}

	@Parameters({ "os", "browser", "version" })
	@Test()
	public void testChrome1(String os, String browser, String version) throws InterruptedException, IOException {

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("build", "build");
		capabilities.setCapability("name", "test");
		capabilities.setCapability("platform", os);
		capabilities.setBrowserName(browser);
		capabilities.setCapability("version", version);
		capabilities.setCapability("console", true);
		capabilities.setCapability("network", true);
		driver = new RemoteWebDriver(server, capabilities);
		((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(GlobalVariables.applicationURL);
		MainPage mainPage = new MainPage(driver);
		waitForPageToLoad(mainPage.getDriver());
		JavascriptExecutor js = (JavascriptExecutor) mainPage.getDriver();
		js.executeScript("arguments[0].scrollIntoView(true);",
				mainPage.getIntegrationTitle("Integrations With CI/CD Tools"));
		Actions actions = new Actions(driver);
		actions.moveToElement(mainPage.getTools()).perform();
		System.out.println(mainPage.getTools().getAttribute("class"));
		String attribute = mainPage.getLearnMore().getAttribute("href");
		System.out.println(attribute);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		mainPage.getDriver().switchTo().window(tabs.get(1));
		mainPage.getDriver().get(attribute);
		waitForPageToLoad(driver);
		Assert.assertTrue(
				attribute.equalsIgnoreCase("https://www.lambdatest.com/support/docs/integrations-with-ci-cd-tools/"),
				"URL opened is Different");
		jse.executeScript("window.scrollBy(0 , window.innerHeight)");
		driver.close();
		mainPage.getDriver().switchTo().window(tabs.get(0));
		mainPage.getResources().waitUntilVisible();
		mainPage.getResources().click();
		mainPage.getResources("Newsletter").waitUntilVisible();
		mainPage.getResources("Newsletter").click();
		NewsLetterPage NewsLetterPage = new NewsLetterPage(driver);
		NewsLetterPage.getlink().waitUntilVisible();
		NewsLetterPage.getlink().click();
		EditionsPage EditionsPage = new EditionsPage(driver);
		EditionsPage.getHeaderTitle("All Editions").waitUntilVisible();
		Assert.assertTrue(EditionsPage.getHeaderTitle("All Editions").isDisplayed(),
				"All Editions Header is not displayed");
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