//package tests;
//
//import base.BaseTest;
//import org.openqa.selenium.JavascriptExecutor;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//import pages.LoginPage;
//import pages.NotePage;
//import pages.SearchPage;
//import utils.Constant;
//import utils.Helper;
//
//public class NoteFunctionalityTest extends BaseTest {
//
//    @Test
//    public void noteAdditionTest() {
//        try {
//            LoginPage lp = new LoginPage(getDriver());
//            lp.login(Constant.email_id, Constant.password);
//
//            SearchPage sp = new SearchPage(getDriver());
//            sp.searchFor("sort");
//
//            // Open problem in new tab/window via JS or Actions (depends on actual implementation)
//            ((JavascriptExecutor) getDriver()).executeScript("window.open(arguments[0])", "https://takeuforward.org/your-problem-url");
//
//            // Switch to new tab and get statement (simulate)
//            String statement = "This is a dummy extracted statement.";
//
//            // Switch back to main window
//            String mainWindow = getDriver().getWindowHandles().iterator().next();
//            getDriver().switchTo().window(mainWindow);
//
//            NotePage np = new NotePage(getDriver());
//            np.addNote(statement);
//
//            Assert.assertTrue(true); // you can validate via DB or visible note element
//        } catch (Exception e) {
//            Helper.captureScreenshot(getDriver(), "noteAddFail");
//            Assert.fail("Note test crashed");
//        }
//    }
//}
