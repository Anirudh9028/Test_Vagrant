package vagrant_test;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class VagrantReport implements ITestListener
{

	ExtentSparkReporter htmlReporter;
	ExtentReports reports;
	ExtentTest test;
	
	public void configureExtentReport() 
	{
		htmlReporter = new ExtentSparkReporter(".\\Reports\\TestVagrantReport.html");
		reports = new ExtentReports();
		reports.attachReporter(htmlReporter);
		
		/*Add system info/environment info to reports*/
		
		reports.setSystemInfo("Machine", "Lenovo");
		reports.setSystemInfo("OS", "Window 10");
		reports.setSystemInfo("QA", "Anirudh");
		
		/*Configuration to change look report*/
		
		htmlReporter.config().setDocumentTitle("Test Vagrant");
		htmlReporter.config().setReportName("Code Assingment");
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setTimeStampFormat("EEEE, MMMM, dd, yyyy, hh:mm a '('zzz')'");
		
		
	}
	
	//This belong to ITestListener and It will Execute Before Starting Of Test set/Batch
	public void onStart(ITestContext Result)
	{
		configureExtentReport();
		System.out.println("Starts Test Execution...." ); 
	}

	//This belongs to ITestListner and it will execute after Starting Of Test set/Batch
	public void onFinish(ITestContext Result) 
	{
		reports.flush();
		System.out.println("Finish Test Execution....");
	}
	
	//This Belongs To ITestListener and will execute  before every the main test start ie. @Test
	public void onTestStart(ITestResult Result) 
	{
		System.out.println("Starts test....."+Result.getName());
	}

	//This Belongs To ITestListener and it will execute when a test is skipped 
	public void onTestSkipped(ITestResult Result) 
	{
		System.out.println("Skipped test: "+Result.getName());
		test = reports.createTest(Result.getName());
		test.log(Status.SKIP, MarkupHelper.createLabel("Name of the Skipped Test Case is: "+ Result.getName(), ExtentColor.YELLOW));
	}
	
	// This belongs to ITlisteners and it will execute when test is passed
	public void onTestSuccess(ITestResult Result) 
	{
		System.out.println("Passed test: "+Result.getName());
		test = reports.createTest(Result.getName());
		test.log(Status.PASS, MarkupHelper.createLabel("Name of the Pass Test Case is: "+ Result.getName(), ExtentColor.GREEN));
	}
	

	// This belongs to ITlisteners and it will execute when test is failed
	public void onTestFailure(ITestResult Result) 
	{
		System.out.println("Failed test: "+Result.getName());
	
		test = reports.createTest(Result.getName());
		test.log(Status.FAIL, MarkupHelper.createLabel("Name of the Failed Test Case is: "+ Result.getName(), ExtentColor.RED));
	}
	
	public void onTestFailureButWithinSuccessPercentage(ITestResult Result) 
	{
	
	}
	
	
	
	
	
}
