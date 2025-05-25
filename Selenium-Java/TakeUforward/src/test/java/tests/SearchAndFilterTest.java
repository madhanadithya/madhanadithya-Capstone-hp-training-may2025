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
import pages.SearchPage;
import utils.Constant;
import utils.ExcelUtils;
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
        log.info("=============== search and filter feature testing ===============");
        log.info("Navigating to Striver's DSA sheet");
        searchPage.goToCheatSheet();
    }

    
    
    @Test(priority = 5, groups = { "search", "positive" })
    public void searchTest() {
    	
        log.info("=============== search test ===============");
    	
        test = extent.createTest("TC_05 : Search Functionality Test");
        try {
        	        	
        	
            log.info("Performing search test");
            test.log(Status.INFO, "Starting search test");

//            searchPage.searchFor(Constant.searchKeyword1, Constant.searchKeyword2);
            
            Map<String, String> data = ExcelUtils.getTestData("Search", "TC_05_06");
            searchPage.searchFor(data.get("keyword1"), data.get("keyword2"));


//            test.log(Status.PASS, "Search performed successfully with keywords: " 
//                      + Constant.searchKeyword1 + ", " + Constant.searchKeyword2);
            
            test.log(Status.PASS, "Search performed successfully with keywords: " 
                    + data.get("keyword1") + ", " + data.get("keyword2") );
            
            
            
            log.info("Search test completed successfully, As expected test passed!");
            

        } catch (Exception e) {
            Helper.captureScreenshot(getDriver(), "searchTestFailure");
            test.log(Status.FAIL, "Search test failed with error: ");
            log.error("Search test failed!");
            Assert.fail("Search test crashed");
        }
    }

    @Test(priority = 6, groups = { "filter", "positive"})
    public void filterTest() {
    	
        log.info("=============== filter test ===============");
    	
        test = extent.createTest("TC_06 : Difficulty Filter Test");
        try {
//            log.info("Applying difficulty filter");
//            test.log(Status.INFO, "Starting difficulty filter test");
//
//            String filterResult = searchPage.applyFilter(Constant.difficultyLevel);
//
//            test.log(Status.INFO, "Filter result retrieved: " + filterResult);
//            log.info("Filter result: " + filterResult);
//
//            Assert.assertTrue(filterResult.toLowerCase().contains(Constant.difficultyLevel.toLowerCase()));
//            test.log(Status.PASS, "Difficulty filter applied and verified successfully");
//            log.info("Filter test passed");
            
            
            
            Map<String, String> data = ExcelUtils.getTestData("Search", "TC_05_06");
                    
            log.info("Applying difficulty filter");
            test.log(Status.INFO, "Starting difficulty filter test");

            String filterResult = searchPage.applyFilter(data.get("difficulty"));

            test.log(Status.INFO, "Filter result retrieved: " + filterResult);
            log.info("Filter result: " + filterResult);

            Assert.assertTrue(filterResult.toLowerCase().contains(data.get("difficulty").toLowerCase()));
            test.log(Status.PASS, "Difficulty filter applied and verified successfully");
            log.info("Filter test passed, As expected test passed!");
            
            


        } catch (Exception e) {
            Helper.captureScreenshot(getDriver(), "filterTestFailure");
            test.log(Status.FAIL, "Filter test failed! ");
            log.error("Filter test failed!");
            Assert.fail("Filter test crashed");
        }
    }
    
    @AfterMethod(alwaysRun = true)
    public void closeModalIfPresent() {
        Helper.pressEscapeKey(getDriver());
        extent.flush(); 
    }
}
