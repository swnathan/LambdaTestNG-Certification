package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class LoginPage extends PageObject {
	
	private static final String COOKIES_DIALOG = "//a[text()='%s']";

	
	@FindBy(id = "username")
	WebElementFacade userName;

	@FindBy(xpath = "//input[@id='password']")
	WebElementFacade password;
	
	@FindBy(xpath = "//button[@type='submit']")
	WebElementFacade submit;
	
	@FindBy(xpath = "//input[@name='email']")
	WebElementFacade email;
	
	public WebElementFacade getEmail() {
		return email;
	}
	
	
	public WebElementFacade getSubmit() {
		return submit;
	}

	public WebElementFacade getUserName() {
		return userName;
	}

	public WebElementFacade getPassword() {
		return password;
	}

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	public WebElementFacade getCookiesLabel(String label) {
		WebElementFacade menuLabel = findBy(String.format(COOKIES_DIALOG, label));
		return menuLabel;
	}

}