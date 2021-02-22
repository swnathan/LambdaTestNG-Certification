package pageobjects;

import org.openqa.selenium.WebDriver;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;




public class EditionsPage extends PageObject {
	
	
	private static final String HEADER_TITLE = "//h1[contains(text(),'%s')]";
	
	public EditionsPage(WebDriver driver) {
		super(driver);
	}
	
	public WebElementFacade getHeaderTitle(String label) {
		WebElementFacade menuLabel = findBy(String.format(HEADER_TITLE, label));
		return menuLabel;
	}

}
