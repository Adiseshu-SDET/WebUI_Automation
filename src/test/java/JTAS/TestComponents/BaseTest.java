package JTAS.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.manager.SeleniumManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import JTAS.WebAutomationPageClasses.login_Page;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public login_Page landingPage;

	public WebDriver initializeDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\JTAS\\Resources\\GlobalData.properties");
		prop.load(fis);

		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");

//		String browserName = prop.getProperty("browser");

		if (browserName.contains("chrome")) {

//			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();

			if (browserName.contains("headless")) {
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
//			driver.manage().window().setSize(new Dimension(1440, 900)); //Full screen

		} else if (browserName.equalsIgnoreCase("firefox")) {

//			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} else if (browserName.equalsIgnoreCase("edge")) {

			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();

		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		return driver;

	}

	// Reusable method to capture screenshots
	public static String takeScreenshot(WebDriver driver, String testName) throws IOException {
		// Set up the path for the screenshot
		String screenshotPath = System.getProperty("user.dir") + "/reports/screenshots/" + testName + ".png";

		// Create the directory if it doesn't exist
		new File(System.getProperty("user.dir") + "/reports/screenshots/").mkdirs();

		// Take the screenshot and save it
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(screenshotPath));

		return screenshotPath; // Return the path to the screenshot
	}

	@BeforeMethod
	public login_Page launchApplication() throws IOException {

		driver = initializeDriver();
		landingPage = new login_Page(driver);
		landingPage.goTo();

		return landingPage;

	}

	@AfterMethod
	public void tearDown() {

		if (driver != null) {
			driver.quit();
		}
	}

}