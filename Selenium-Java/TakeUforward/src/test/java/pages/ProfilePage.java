package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Helper;
import utils.WaitHelper;

public class ProfilePage {

    WebDriver driver;
    WaitHelper waitHelper;
    
	//================ elements needed to edit the profile data===================================

    @FindBy(xpath = "//button[@aria-haspopup='true' and .//img[contains(@src, 'user')]]")
    WebElement profileIcon;

    
    @FindBy(xpath = "//span[text()='Profile']")
    WebElement profileBtn;

    @FindBy(css = "svg.lucide-square-pen.cursor-pointer")
    WebElement editBtn;
    

    @FindBy(css = "input[name=\"name\"][placeholder=\"Your full name\"]")
    WebElement nameField;

    @FindBy(xpath = "//input[@name='location' and @placeholder='City, Country']")
    WebElement locationField;

    @FindBy(css = "input[name='college'][placeholder='Select or enter your college']")
    WebElement collegeField;
    
    @FindBy(xpath = "//button[@type='submit' and ancestor::form[contains(@class, 'space-y-4') and .//input[@name='name']]]")
    WebElement savePersonalDataBtn;
    
    
    @FindBy(xpath = "//input[@name='github_link' and @placeholder='https://github.com/username']") 
    WebElement githubField;
    
    @FindBy(xpath = "//input[@name='linkedin_link' and @placeholder='https://linkedin.com/in/username']") 
    WebElement linkedinField;
    
    @FindBy(xpath = "//input[@name='twitter_link' and @placeholder='https://x.com/username']") 
    WebElement twitterField;

    @FindBy(xpath = "//input[@name='other_link' and @placeholder='https://other.com/username']") 
    WebElement otherField;

	@FindBy(xpath = "//button[@type='submit' and parent::div[contains(@class, 'pt-4')] and ancestor::form[not(@class)]]")
	WebElement saveSocialsBtn;
	  
	//================ elements needed for verifying the profile update===================================
	
	@FindBy(xpath = "//span[text()='Back to Profile']") 
	WebElement backToProfileBtn;
	
	@FindBy(xpath = "//h3[text()='Socials Links']/following-sibling::div//a[contains(@href, 'github.com')]") 
	WebElement githubLink;

	@FindBy(xpath = "//h3[text()='Socials Links']/following-sibling::div//a[contains(@href, 'linkedin.com')]") 
	WebElement linkedinLink;

//	@FindBy(xpath = "//svg[contains(@class, 'lucide-graduation-cap')]/ancestor::div[contains(@class,'items-start')]/p") 
//	WebElement collegeName;
//
//	@FindBy(xpath = "//svg[contains(@class, 'lucide-map-pin')]/ancestor::div[contains(@class,'items-start')]/p") 
//	WebElement collegeLocation;


	@FindBy(xpath = "//span[contains(@class,'text-zinc-500')]/parent::div") 
	WebElement userFullName;
	

	//================ constructor  ===================================
	
    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitHelper = new WaitHelper(driver);
    }

    
	//================ functions to edit profile data ===================================
    
    public void editPersonalData(String name, String location, String college) throws InterruptedException {
        waitHelper.waitForElementToBeClickable(profileIcon);
        profileIcon.click();

        waitHelper.waitForElementToBeClickable(profileBtn);
        profileBtn.click();
        
    	Helper.pause(1000);

        waitHelper.waitForElementToBeClickable(editBtn);
        editBtn.click();

        Helper.pause(1000);
    	
        if (location != null && !location.trim().isEmpty()) {
            Helper.scrollToElement(driver, locationField);
            waitHelper.waitForInputFieldToBeReady(locationField);
            locationField.clear();
            locationField.sendKeys(location);
        }

        if (college != null && !college.trim().isEmpty()) {
            Helper.scrollToElement(driver, collegeField);
            waitHelper.waitForInputFieldToBeReady(collegeField);
            collegeField.clear();
            collegeField.sendKeys(college);
        }

        if (name != null && !name.trim().isEmpty()) {
            Helper.scrollToElement(driver, nameField);
            waitHelper.waitForInputFieldToBeReady(nameField);
            nameField.clear();
            nameField.sendKeys(name);
        }
        
        Helper.scrollToElement(driver, savePersonalDataBtn);
        waitHelper.waitForElementToBeClickable(savePersonalDataBtn);
        Helper.pause(3500);
        savePersonalDataBtn.click();
    }

    public void editSocialLinks(String github, String linkedin, String twitter, String other) throws InterruptedException {

        Helper.pause(2000);
    	
        if (github != null && !github.trim().isEmpty()) {
            Helper.scrollToElement(driver, githubField);
            waitHelper.waitForInputFieldToBeReady(githubField);
            githubField.clear();
            githubField.sendKeys(github);
        }
        

        if (linkedin != null && !linkedin.trim().isEmpty()) {
            Helper.scrollToElement(driver, linkedinField);
            waitHelper.waitForInputFieldToBeReady(linkedinField);
            linkedinField.clear();
            linkedinField.sendKeys(linkedin);
        }

        
        if (twitter != null && !twitter.trim().isEmpty()) {
            Helper.scrollToElement(driver, twitterField);
            waitHelper.waitForInputFieldToBeReady(twitterField);
            twitterField.clear();
            twitterField.sendKeys(twitter);
        }

        if (other != null && !other.trim().isEmpty()) {
            Helper.scrollToElement(driver, otherField);
            waitHelper.waitForInputFieldToBeReady(otherField);
            otherField.clear();
            otherField.sendKeys(other);
        }

        Helper.scrollToElement(driver, saveSocialsBtn);
        waitHelper.waitForElementToBeClickable(saveSocialsBtn);
        Helper.pause(3500);
        saveSocialsBtn.click();
    }

    
    public void returnToProfileView() {
        Helper.pause(2000);
        Helper.scrollToElement(driver, backToProfileBtn);
        waitHelper.waitForInputFieldToBeReady(backToProfileBtn);
        backToProfileBtn.click();
    }

    
	//================ functions to verify the changes in profile ===================================
    
    public String verifyAndGetSocialUrl(WebElement socialLinkElement) {
        
    	Helper.pause(2000);
        
        waitHelper.waitForElementToBeClickable(socialLinkElement);
        String originalWindow = driver.getWindowHandle();
        socialLinkElement.click();

        waitHelper.waitForNumberOfWindowsToBe(2);

        for (String tab : driver.getWindowHandles()) {
            if (!tab.equals(originalWindow)) {
                driver.switchTo().window(tab);
                break;
            }
        }

        String url = driver.getCurrentUrl();
        driver.close();
        driver.switchTo().window(originalWindow);

        return url;
    }

    public String verifyAndGetGithubUrl() {
        return verifyAndGetSocialUrl(githubLink);
    }

    public String verifyAndGetLinkedinUrl() {
        return verifyAndGetSocialUrl(linkedinLink);
    }
    
    
    public String getDisplayedUserFullName() {
        return Helper.getDirectTextContent(driver, userFullName);
    }
  
}
