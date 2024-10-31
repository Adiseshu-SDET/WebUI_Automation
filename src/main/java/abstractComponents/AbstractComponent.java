package abstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent {

	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		// TODO Auto-generated constructor stub

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[routerlink*='cart']")
	WebElement clickOnCart;

	@FindBy(css = "button[class='btn btn-custom']")
	List<WebElement> clickOnOrdersPage;

	public void waitForVisibilityOFLocator(By findBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}

	public void waitForInVisibilityOFLocator(By findBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}

	public void selectDropDownValue(WebDriver driver, WebElement element, String value) {

		Actions actions = new Actions(driver);

		actions.sendKeys(element, value).build().perform();
	}

	public String getText(WebElement element) {

		return element.getText();
	}

	public void goToCart() {

		clickOnCart.click();

	}

	public void goToOrderspage() {

		clickOnOrdersPage.get(1).click();
	}

}
