package JTAS.WebAutomation;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import JTAS.TestComponents.BaseTest;
import JTAS.WebAutomationPageClasses.Cart_Page;
import JTAS.WebAutomationPageClasses.Orders_page;
import JTAS.WebAutomationPageClasses.Productcatlog_page;
import JTAS.WebAutomationPageClasses.checkOutPage;

public class SubmitOrder extends BaseTest {

	@Test
	public void submitOrder() throws IOException {

		loadTestData(); // Load test data once

		String username = credentials.get("username");
		String password = credentials.get("password");

		Cart_Page cartPage = new Cart_Page(driver);
		checkOutPage cp = new checkOutPage(driver);

		landingPage.loginToApplication(username, password);

		Productcatlog_page productCatolog = new Productcatlog_page(driver);
		List<WebElement> products = productCatolog.getProductList();
		productCatolog.addProdcutToCart("IPHONE 13 PRO");

		productCatolog.waitForToastMessage();

		productCatolog.waitForToastMessageInVisible();

		productCatolog.goToCart();

		Boolean match = cartPage.verifyProductDisplay();

		Assert.assertTrue(match);

		cartPage.checkOut();

		cp.selectCountry("India");

		String actulaText = cp.validateThankYouMessage();

		System.out.println(actulaText);

		Assert.assertTrue(actulaText.equalsIgnoreCase("Thankyou for the order."));

	}

	@Test
	public void verifyOrdersHistory() {
		landingPage.loginToApplication("jampaniadi503@gmail.com", "Web@Automation1");
		Productcatlog_page productCatolog = new Productcatlog_page(driver);
		Orders_page op = new Orders_page(driver);
		productCatolog.goToOrderspage();
		op.verifyOrdersDisplaying();
		tearDown();
	}

	@AfterMethod
	public void closeBrowser() {

		tearDown();

	}

}
