package JTAS.WebAutomationPageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class checkOutPage extends AbstractComponent {

	WebDriver driver;

	public checkOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[placeholder='Select Country']")
	WebElement enterCountryName;

	@FindBy(css = ".ta-item:nth-of-type(2)")
	WebElement clickOnCountryName;

	@FindBy(css = ".actions a")
	WebElement click;
	
	@FindBy(css = ".hero-primary")
	WebElement thankYouMessage;

	public void selectCountry(String countryName) {

		selectDropDownValue(driver, enterCountryName, countryName);
		clickOnCountryName.click();
		click.click();
	}
	
	public String validateThankYouMessage() {
		
		return getText(thankYouMessage);
	}

}
