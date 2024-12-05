package JTAS.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	public static ExtentReports getReportObject() {

		String path = System.getProperty("user.dir") + "/reports/";

		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Regression Suit");
		reporter.config().setDocumentTitle("ProjectName");

		ExtentReports extent = new ExtentReports();

		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Name OF the Tester");
		return extent;
	}

}
