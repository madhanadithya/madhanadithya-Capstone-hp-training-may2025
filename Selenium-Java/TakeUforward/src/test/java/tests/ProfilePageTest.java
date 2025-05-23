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
import pages.ProfilePage;
import utils.Constant;
import utils.ExtentManager;
import utils.Helper;

public class ProfilePageTest extends BaseTest {

    Logger log = Logger.getLogger(ProfilePageTest.class);

    ExtentReports extent;
    ExtentTest test;


    @BeforeClass
    public void loginBeforeProfileTests() {
        extent = ExtentManager.getInstance();
    }

    
    

    @Test(priority = 3, dependsOnMethods = {"tests.LoginPageTest.validLoginTest"}, groups = {"profile", "positive"})
    public void editProfileTest() throws Exception {
    	
        test = extent.createTest("TC_03 : Edit Profile Test - Update Personal and Social Details");

        try {
            log.info("=============== Editing profile details ===============");
            test.log(Status.INFO, "Editing profile details started");

            ProfilePage profilePage = new ProfilePage(getDriver());

            // Log personal info update
            log.info("Updating personal data: name, location, college");
            test.log(Status.INFO, "Updating personal data");

            profilePage.editPersonalData(
                    Constant.profile_name,
                    Constant.profile_location,
                    Constant.profile_college
            );

            // Log social links update
            log.info("Updating social links: GitHub, LinkedIn, Twitter, Other");
            test.log(Status.INFO, "Updating social links");

            profilePage.editSocialLinks(
                    Constant.github_link,
                    Constant.linkedin_link,
                    Constant.twitter_link,
                    Constant.other_link
            );

            profilePage.returnToProfileView();
            
            test.log(Status.PASS, "Profile updated successfully!");
            log.info("Profile updated successfully!");

        } catch (Exception e) {
            Helper.captureScreenshot(getDriver(), "editProfileTestFailure");
            test.log(Status.FAIL, "Test crashed with error: " + e.getMessage());
            log.error("Profile edit test failed", e);
            System.out.println("error : " + e);
            Assert.fail("Profile edit test crashed");
        }
    }
    
    

	//================ test 4 : verifying the updates made on the profile page ===================================
    
    @Test(priority = 4, dependsOnMethods = {"editProfileTest"}, groups = {"profile", "verification", "positive"})
    public void verifyProfileLinksTest() {
    	
        test = extent.createTest("TC_04 : Verify Profile Social Links Open Correct URLs");

        try {
            log.info("=============== Verifying social links URLs ===============");
            test.log(Status.INFO, "Verifying GitHub and LinkedIn URLs");

            ProfilePage profilePage = new ProfilePage(getDriver());

            
            //================ verify display name ===================================
            
            String extractedName = profilePage.getDisplayedUserFullName();
            log.info("Name displayed in profile: " + extractedName);
            test.log(Status.INFO, "Name displayed in profile:" + extractedName);
            
            Assert.assertEquals(extractedName, Constant.profile_name);
            log.info("Profile name matches with data used to update!");
            test.log(Status.PASS, "Profile name matches with data used to update!");


            //================ verify linked-in ===================================
            
            String linkedinUrl = profilePage.verifyAndGetLinkedinUrl();
            
            log.info("LinkedIn URL opened: " + linkedinUrl);
            test.log(Status.INFO, "LinkedIn URL opened: " + linkedinUrl);
            
            Assert.assertTrue(linkedinUrl.contains("linkedin.com"), "LinkedIn URL validation failed");
            
            
          //================ verify Github ===================================
            
            String githubUrl = profilePage.verifyAndGetGithubUrl();
            
            log.info("GitHub URL opened: " + githubUrl);
            test.log(Status.INFO, "GitHub URL opened: " + githubUrl);
            
            Assert.assertTrue(githubUrl.contains(Constant.github_link), "GitHub URL validation failed");

            test.log(Status.PASS, "Social links verified successfully!");
            log.info("Social links verified successfully!");

        } catch (Exception e) {
            Helper.captureScreenshot(getDriver(), "verifyProfileLinksTestFailure");
            test.log(Status.FAIL, "Verification test failed: " + e.getMessage());
            log.error("Verification test failed", e);
            Assert.fail("Verification test crashed");
        }
    }

    
    

    @AfterMethod(alwaysRun = true)
    public void closeModalAndFlush() {
        Helper.pressEscapeKey(getDriver());
        extent.flush();
    }
}
