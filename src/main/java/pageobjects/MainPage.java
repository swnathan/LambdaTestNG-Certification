package pageobjects;

import org.openqa.selenium.WebDriver;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class MainPage extends PageObject {

	private static final String FREQUENCY_RADIO = "//input[@value='%s']";
	private static final String DECISIVE_FACTORS_CHECKBOX = "//input[@name='%s']";
	private static final String PAYMENT_TYPE = "//option[contains(text(),'%s')]";
	private static final String RATE_EXPERIENCE = "//div[@class='slider-values active']/div[text()='%d']";
	private static final String PAYMENT_MODE = "//select[@id='preferred-payment']//option[contains(text(),'%s')]";

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

	public WebElementFacade getFrequencyRadio(String label) {
		WebElementFacade menuLabel = findBy(String.format(FREQUENCY_RADIO, label));
		return menuLabel;
	}

	public WebElementFacade getDecisiveFactors(String label) {
		WebElementFacade menuLabel = findBy(String.format(DECISIVE_FACTORS_CHECKBOX, label));
		menuLabel.click();
		return menuLabel;
	}

	public WebElementFacade getPaymentType(String label) {
		WebElementFacade menuLabel = findBy(String.format(PAYMENT_TYPE, label));
		return menuLabel;
	}

	public WebElementFacade getRateExperience(int label) {
		WebElementFacade menuLabel = findBy(String.format(RATE_EXPERIENCE, label));
		return menuLabel;
	}

	public WebElementFacade getPaymentMode(String label) {
		WebElementFacade menuLabel = findBy(String.format(PAYMENT_MODE, label));
		menuLabel.click();
		return menuLabel;
	}

	public void waitABit(final long timeInMilliseconds) {
		getClock().pauseFor(timeInMilliseconds);
	}
}
