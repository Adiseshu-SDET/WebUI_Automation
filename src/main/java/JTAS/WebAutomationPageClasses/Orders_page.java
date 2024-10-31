package JTAS.WebAutomationPageClasses;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class Orders_page extends AbstractComponent {

	WebDriver driver;

	public Orders_page(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "tr td:nth-child(3)")
	private List<WebElement> productNames_OrdersPage;

	public boolean verifyOrdersDisplaying() {

//		goToOrderspage();

		return productNames_OrdersPage.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase("ZARA COAT 3"));

	}

}
