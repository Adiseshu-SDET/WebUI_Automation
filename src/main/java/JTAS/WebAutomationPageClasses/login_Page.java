package JTAS.WebAutomationPageClasses;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class login_Page extends AbstractComponent{

	WebDriver driver;

	public login_Page(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(id = "userEmail")
	WebElement userName;

	@FindBy(id = "userPassword")
	WebElement password;

	@FindBy(id = "login")
	WebElement loginButton;

	@FindBy(css = ".mb-3")
	List<WebElement> prodcuts;
	
	@FindBy(using  = ".mb-3")
	List<WebElement> prodcuts1;

	public void loginToApplication(String enterUserName, String enterPassword) {

		userName.sendKeys(enterUserName);
		password.sendKeys(enterPassword);
		loginButton.click();
	}
	
	public void myProfile(String enterUserName, String enterPassword) {
		userName.sendKeys(enterUserName);
		password.sendKeys(enterPassword);
		loginButton.click();
		
	}

	public void goTo() {

		driver.get("https://rahulshettyacademy.com/client");
	}

}
