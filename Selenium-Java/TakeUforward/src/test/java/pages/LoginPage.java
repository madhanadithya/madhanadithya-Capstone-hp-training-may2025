package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import tests.LoginPageTest;
import utils.WaitHelper;
import utils.Helper;

public class LoginPage {

    WebDriver driver;
    WaitHelper waitHelper;
    Logger log = Logger.getLogger(LoginPageTest.class);

    @FindBy(xpath = "//button[contains(@class, 'py-1') and text()='Login']")
    WebElement loginBtn;

    @FindBy(xpath = "//input[contains(@placeholder, 'Enter your email')]")
    WebElement emailField;

    @FindBy(xpath = "//input[contains(@placeholder, 'Enter Password')]")
    WebElement pwdField;

    @FindBy(xpath = "//button[@type='submit' and text()='Login']")
    WebElement submitBtn;

    @FindBy(xpath = "//button[@aria-haspopup='true' and .//img[contains(@src, 'user')]]")
    WebElement profileBtn;

    @FindBy(xpath = "//p[contains(text(), 'Hi,')]")
    WebElement greetText;

    @FindBy(id = "_rht_toaster" )
    WebElement toastLocator; 
    
    @FindBy(xpath = "//span[text()='Sign Out']")
    WebElement logoutBtn;
    
    @FindBy(xpath = "//button[@aria-label='Close notification']")
    WebElement toastCloseButton;


    
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitHelper = new WaitHelper(driver);
    }

    public void login(String email, String password) {
        waitHelper.waitForElementToBeClickable(loginBtn);
        loginBtn.click();

        waitHelper.waitForElementToBeVisible(emailField);
        emailField.clear();
        emailField.sendKeys(email);

        waitHelper.waitForElementToBeVisible(pwdField);
        pwdField.clear();
        pwdField.sendKeys(password);

    	Helper.pause(1000);
        waitHelper.waitForElementToBeClickable(submitBtn);
        submitBtn.click();
    }
    
    
    public void closeToastIfPresent() {
        try {
            if (toastCloseButton.isDisplayed()) {
                toastCloseButton.click();
            }
        } catch (Exception e) {
        	log.warn("Toast close button not available, Skipping toast close. Reason: " + e.getMessage());
        	
        }
    }
    
    public String VerifyToast() {
	    waitHelper.waitForToastToAppear(toastLocator);
	    String toastText = toastLocator.getText();
	    closeToastIfPresent();
	    return toastText;
    }

    public String getGreetingText() {
    	Helper.pause(1000);
        waitHelper.waitForElementToBeClickable(profileBtn);
        profileBtn.click();
        Helper.pause(2500);
        waitHelper.waitForTextInElement(greetText, "Hi,");
        return greetText.getText();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
    
    public void logout() {
        waitHelper.waitForElementToBeClickable(profileBtn);
        profileBtn.click();
    	waitHelper.waitForElementToBeClickable(logoutBtn);
    	logoutBtn.click();
    }
    
}
