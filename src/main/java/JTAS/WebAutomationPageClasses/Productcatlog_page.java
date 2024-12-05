package JTAS.WebAutomationPageClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class Productcatlog_page extends AbstractComponent {

	WebDriver driver;

	public Productcatlog_page(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	List<WebElement> prodcuts;

	By productsby = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	By toastMessageInVisible = By.cssSelector(".ng-animating");

	public List<WebElement> getProductList() {

		waitForVisibilityOFLocator(productsby);

		return prodcuts;

	}

	public WebElement getProdcutByName(String productName) {

		return getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
	}

	public void addProdcutToCart(String productName) {

		getProdcutByName(productName).findElement(addToCart).click();
	}

	public void waitForToastMessage() {

		waitForVisibilityOFLocator(toastMessage);
	}

	public void waitForToastMessageInVisible() {

		waitForInVisibilityOFLocator(toastMessageInVisible);
	}

}
