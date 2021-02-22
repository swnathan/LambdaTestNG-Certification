package pageobjects;

import org.openqa.selenium.WebDriver;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class MainPage extends PageObject {

	private static final String FEATURE = "//div[contains(text(),'%s')]";
	private static final String RESOURCE_MENU = "//a[contains(text(),'%s')]";
	private static final String INTEGRATION_TITLE = "//h2[contains(text(),'%s')]";

	@FindBy(xpath = "//input[@name='email']")
	WebElementFacade email;

	@FindBy(id = "populate")
	WebElementFacade populate;

	@FindBy(id = "preferred-payment")
	WebElementFacade selectPrefferedPayment;

	@FindBy(xpath = "//input[@name='tried-ecom']")
	WebElementFacade eCommercePurchaseCheckbox;

	@FindBy(xpath = "//img[@alt='LambdaTest Jenkins integration']")
	WebElementFacade jenkinsLogo;

	@FindBy(xpath = "//textarea[@id='comments']")
	WebElementFacade comments;

	@FindBy(xpath = "//input[@id='file']")
	WebElementFacade fileUpload;

	@FindBy(xpath = "//button[@id='submit-button']")
	WebElementFacade submit;

	@FindBy(xpath = "//span[contains(@class, 'ui-slider-handle')]")
	WebElementFacade slider;

	@FindBy(xpath = "//img[@alt='circle-outlined']")
	WebElementFacade tools;

	@FindBy(xpath = "//a[contains(@href,'ci-cd')]")
	WebElementFacade learnMore;

	@FindBy(xpath = "//a[contains(text(),'Resources')]")
	WebElementFacade resources;

	public WebElementFacade getResources() {
		return resources;
	}

	public WebElementFacade getLearnMore() {
		return learnMore;
	}

	public WebElementFacade getTools() {
		return tools;
	}

	public WebElementFacade getSlider() {
		return slider;
	}

	public WebElementFacade getSubmit() {
		return submit;
	}

	public WebElementFacade getFileUpload() {
		return fileUpload;
	}

	public WebElementFacade getComments() {
		return comments;
	}

	public WebElementFacade getJenkinsLogo() {
		return jenkinsLogo;
	}

	public WebElementFacade getECommercePurchaseCheckbox() {
		return eCommercePurchaseCheckbox;
	}

	public WebElementFacade getSelectPrefferedPayment() {
		return selectPrefferedPayment;
	}

	public WebElementFacade getPopulate() {
		return populate;
	}

	public WebElementFacade getEmail() {
		return email;
	}

	public MainPage(WebDriver driver) {
		super(driver);
	}

	public WebElementFacade getFeature(String label) {
		WebElementFacade menuLabel = findBy(String.format(FEATURE, label));
		return menuLabel;
	}

	public WebElementFacade getResources(String label) {
		WebElementFacade menuLabel = findBy(String.format(RESOURCE_MENU, label));
		return menuLabel;
	}

	public WebElementFacade getIntegrationTitle(String label) {
		WebElementFacade menuLabel = findBy(String.format(INTEGRATION_TITLE, label));
		return menuLabel;
	}

	public void waitABit(final long timeInMilliseconds) {
		getClock().pauseFor(timeInMilliseconds);
	}
}
