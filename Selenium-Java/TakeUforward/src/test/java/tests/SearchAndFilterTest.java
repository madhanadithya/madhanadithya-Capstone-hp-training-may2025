package tests;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import base.BaseTest;
import pages.SearchPage;
import utils.Constant;
import utils.ExtentManager;
import utils.Helper;

public class SearchAndFilterTest extends BaseTest {
    Logger log = Logger.getLogger(SearchAndFilterTest.class);

    ExtentReports extent;
    ExtentTest test;
    SearchPage searchPage;

    @BeforeClass
    public void setupReport() {
        extent = ExtentManager.getInstance();
        searchPage = new SearchPage(getDriver());
        log.info("Navigating to Striver's DSA sheet");
        searchPage.goToCheatSheet();
    }

    
    @Test(priority = 5, groups = { "search", "positive" })
    public void searchTest() {
    	
        log.info("=============== search functionality testing ===============");
    	
        test = extent.createTest("TC_05 : Search Functionality Test");
        try {
        	
        	
        	
            log.info("Performing search test");
            test.log(Status.INFO, "Starting search test");

            searchPage.searchFor(Constant.searchKeyword1, Constant.searchKeyword2);

            test.log(Status.PASS, "Search performed successfully with keywords: " 
                      + Constant.searchKeyword1 + ", " + Constant.searchKeyword2);
            log.info("Search test completed successfully");

        } catch (Exception e) {
            Helper.captureScreenshot(getDriver(), "searchTestFailure");
            test.log(Status.FAIL, "Search test failed with error: " + e.getMessage());
            log.error("Search test failed!", e);
            Assert.fail("Search test crashed");
        }
    }

    @Test(priority = 6, groups = { "filter", "positive"})
    public void filterTest() {
    	
        log.info("=============== filter functionality testing ===============");
    	
        test = extent.createTest("TC_06 : Difficulty Filter Test");
        try {
            log.info("Applying difficulty filter");
            test.log(Status.INFO, "Starting difficulty filter test");

            String filterResult = searchPage.applyFilter(Constant.difficultyLevel);

            test.log(Status.INFO, "Filter result retrieved: " + filterResult);
            log.info("Filter result: " + filterResult);

            Assert.assertTrue(filterResult.toLowerCase().contains(Constant.difficultyLevel.toLowerCase()));
            test.log(Status.PASS, "Difficulty filter applied and verified successfully");
            log.info("Filter test passed");

        } catch (Exception e) {
            Helper.captureScreenshot(getDriver(), "filterTestFailure");
            test.log(Status.FAIL, "Filter test failed with error: " + e.getMessage());
            log.error("Filter test failed!", e);
            Assert.fail("Filter test crashed");
        }
    }
    
    @AfterMethod(alwaysRun = true)
    public void closeModalIfPresent() {
        Helper.pressEscapeKey(getDriver());
        extent.flush(); 
    }
}
