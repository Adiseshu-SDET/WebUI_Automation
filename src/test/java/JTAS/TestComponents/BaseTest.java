package JTAS.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import JTAS.Resources.ExcelUtils;
import JTAS.WebAutomationPageClasses.login_Page;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public login_Page landingPage;

	protected Map<String, String> credentials = new HashMap<>();
	Properties prop = new Properties();

	public WebDriver initializeDriver() throws IOException {

		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/java/JTAS/Resources/GlobalData.properties");
		prop.load(fis);

		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");

//		String browserName = prop.getProperty("browser");

		if (browserName.contains("chrome")) {

//			WebDriverManager.chromedriver().setup();
			Path tempDir = Files.createTempDirectory("chrome-user-data");
			ChromeOptions options = new ChromeOptions();

			options.addArguments("--no-sandbox"); // Bypass OS security model
			options.addArguments("--disable-dev-shm-usage"); // Overcome limited resource problems
			options.addArguments("--remote-allow-origins=*"); // Address Chrome's CORS policy changes
			options.addArguments("--disable-gpu"); // Disable GPU (Optional)
			options.addArguments("--headless=new"); // Use headless mode
			options.addArguments("--user-data-dir=" + tempDir.toAbsolutePath().toString()); // Unique user data
																							// directory

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

	public void loadTestData() throws IOException {
		// Using a relative path that resolves to an absolute path
		String excelPath = System.getProperty("user.dir") + "/resources/TestData.xlsx";
		String sheetName = "Getdata";

		ExcelUtils excelUtils = new ExcelUtils(excelPath, sheetName);

		// Assuming you want data from Row 1
		Map<String, String> testData = excelUtils.getRowDataByColumnNames(1);

		// Store the data in the credentials map
		credentials.put("username", testData.get("username"));
		credentials.put("password", testData.get("password"));

		excelUtils.closeWorkbook();
	}

	@BeforeMethod
	public login_Page launchApplication() throws IOException {

		driver = initializeDriver();
		landingPage = new login_Page(driver);
		landingPage.goTo(prop.getProperty("test"));

		return landingPage;

	}

	@AfterMethod
	public void tearDown() {

		if (driver != null) {
			driver.quit();
		}
	}

}
