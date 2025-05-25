package tests;

import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import base.BaseTest;
import pages.LoginPage;
import utils.ExcelUtils;
import utils.ExtentManager;
import utils.Helper;

public class LoginPageTest extends BaseTest {
    Logger log = Logger.getLogger(LoginPageTest.class);

    ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void setupReport() {
        extent = ExtentManager.getInstance();
    }

    private void performLoginTest(String email, String password, String expectedToastMessage, boolean expectGreeting) {
        test = extent.createTest((expectGreeting ? "TC_02 : " : "TC_01 : ") + "Login Test - " + (expectGreeting ? "Valid" : "Invalid") + " Credentials");

        
        try {
        	
            LoginPage lp = new LoginPage(getDriver());

            log.info("Opening login page...");
            test.log(Status.INFO, "Opening login page");

            lp.login(email, password);
            log.info((expectGreeting ? "valid" : "invalid") + " credentials submitted.");
            test.log(Status.INFO, (expectGreeting ? "Valid" : "Invalid") + " credentials submitted");

            String toastMessage = lp.VerifyToast();
            log.info("Captured Toast Message: " + toastMessage);
            test.log(Status.INFO, "Toast Message: " + toastMessage);

            Assert.assertEquals(toastMessage, expectedToastMessage);
            test.log(Status.PASS, "Toast message verified successfully!");

            if (expectGreeting) {
                String greeting = lp.getGreetingText();
                log.info("Captured greeting Message: " + greeting);
                test.log(Status.INFO, "Greeting Message: " + greeting);
                Assert.assertTrue(greeting.contains("Hi,"), "Greeting was not displayed as expected!");
                log.info("Verified greeting message. Login successful!");
                test.log(Status.PASS, "Greeting verified. Login successful!");
            }

            log.info("As expected test passed!");
            test.log(Status.PASS, "Test passed for " + (expectGreeting ? "valid" : "invalid") + " credentials");

        } catch (Exception e) {
            Helper.captureScreenshot(getDriver(), "loginTestFailure");
            log.error("Test failed!");
            test.log(Status.FAIL, "Test crashed with error: " + e.getMessage());
            Assert.fail("Login test crashed");
        }
    }

    @Test(priority = 1, groups = { "login", "negative" })
    public void invalidLoginTest() {
        log.info("===============login with invalid credentials===============");
        
        
        //using Constant file
//        performLoginTest(Constant.email_id, Constant.invalid_password, "Invalid Password", false);
        
        //using Data Driven Method
        Map<String, String> data = ExcelUtils.getTestData("Login", "TC_01");
        performLoginTest(data.get("email"), data.get("password"), data.get("expectedToast"), false);

    }

    @Test(priority = 2, groups = { "login", "positive" })
    public void validLoginTest() {
        log.info("===============login with valid credentials===============");
        
        
        //using Constant file
//        performLoginTest(Constant.email_id, Constant.password, "Login Successful", true);
        
        //using Data Driven Method
        Map<String, String> data = ExcelUtils.getTestData("Login", "TC_02");
        performLoginTest(data.get("email"), data.get("password"), data.get("expectedToast"), true);

    }

    @AfterMethod(alwaysRun = true)
    public void closeModalIfPresent() {
        Helper.pressEscapeKey(getDriver());
        extent.flush(); 
    }
}
