package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import utility.DriverFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import utility.ExtentReportManager;


public class Hooks {
	
	//Khai báo các đối tượng để sử dụng report
	//private static ExtentReports extent = ExtentReportManager.getInstance();
    //private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static com.aventstack.extentreports.ExtentReports extent = ExtentReportManager.getInstance();

    public static ExtentTest getTest()
    {
    	return test.get();
    }


    @Before
    public void setUp(Scenario scenario) {	
    	String browserTypeName = "chrome";
    	if(scenario.getSourceTagNames().contains("@firefox"))
    	{
    		browserTypeName = "firefox";
    	}
    	// Set browser type and init according browser type
    	DriverFactory.setBrowser(browserTypeName);
        DriverFactory.initDriver();
        //driver.manage().window().maximize();
        System.out.println("Already Khởi tạo WebDriver from Hooks");
        
        //Gắn report
        ExtentTest extentTest = extent.createTest(scenario.getName());
        test.set(extentTest);

    }
    
    
    @AfterStep
    public void afterStep(Scenario scenario) 
    {
        if (scenario.isFailed()) {
            test.get().fail("❌ Step failed: " + scenario.getName());
        } else {
            test.get().pass("✅ Step passed");
        }
        // Ghi ra file report
        extent.flush();
        
    }
    

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }
    

}
