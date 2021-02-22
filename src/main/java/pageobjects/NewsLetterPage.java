package pageobjects;

import org.openqa.selenium.WebDriver;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;


public class NewsLetterPage extends PageObject {
	
	
	@FindBy(xpath="//a[contains(text(),'Let me read it first')]")
	WebElementFacade link;
	
	
	public WebElementFacade getlink() {
		return link;
	}

	public NewsLetterPage(WebDriver driver) {
		super(driver);
	}

}
