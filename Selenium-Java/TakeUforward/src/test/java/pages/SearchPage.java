package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Helper;
import utils.WaitHelper;

public class SearchPage {

    WebDriver driver;
    WaitHelper waitHelper;


    
    @FindBy(xpath = "//div[contains(@class, 'bg-white')]//a[@href='/']")
    WebElement homeBtn;
    
    @FindBy(xpath = "//button[contains(@class, 'flex') and contains(text(), 'Resources')]")
    WebElement resourceBtn;
    
    @FindBy(xpath = "//p[contains(@class, 'font-medium') and contains(text(), \"Striver's DSA Sheet\")]")
    WebElement targetedSheetbtn;
    
    @FindBy(xpath = "//button[contains(@class, 'w-9') and contains(@class, 'rounded-md') and .//*[name()='svg']]")
    WebElement searchInput;
    
    @FindBy(xpath = "//input[@placeholder='Search']")
    WebElement searchBar;
    
    @FindBy(xpath = "//button[@type='button' and contains(text(), '✕') and contains(@class, 'text-[#9CA3AF]')]")
    WebElement searchCloseBtn;
    
    @FindBy(xpath = "//button[normalize-space()='Difficulty']")
    WebElement difficultyDropdown;
    
    @FindBy(xpath = "//button[normalize-space()='Easy']")
    WebElement easyDifficultyBtn;
    
    @FindBy(xpath = "//button[normalize-space()='Medium']")
    WebElement mediumDifficultyBtn;
    
    @FindBy(xpath = "//button[normalize-space()='Hard']")
    WebElement hardDifficultyBtn;
    
    
    @FindBy(xpath = "(//div[contains(@class,'font-medium cursor-pointer')]//a)[1]")
    WebElement firstProblem;
    
    @FindBy(xpath = "//button[text()='All Problems']")
    WebElement allProblemBtn;

    @FindBy(xpath = "//button[text()='Revision']")
    WebElement revisionBtn;


    @FindBy(xpath = "//input[@type='checkbox' and contains(@class, 'text-orange-600')]")
    WebElement newTabcheckBox;
    
    
    @FindBy(xpath = "//table//tr[1]//td[9]")
    WebElement difficultyStatus;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitHelper = new WaitHelper(driver);
    }

    public void goToCheatSheet() {
    	
        Helper.pause(2000); 
    	
//        Helper.scrollToElement(driver, homeBtn);
//        waitHelper.waitForElementToBeClickable(homeBtn);
//        Helper.clickUsingJS(driver, homeBtn);

        driver.get("https://takeuforward.org/");

    	
        Helper.pause(2000);
        waitHelper.waitForElementToBeClickable(resourceBtn);
        Helper.clickUsingJS(driver, resourceBtn);
        Helper.pause(1000);

        waitHelper.waitForElementToBeClickable(targetedSheetbtn);
        Helper.clickUsingJS(driver, targetedSheetbtn);

    }

    public void searchFor(String keyword1, String keyword2) {
    	
        Helper.pause(2000);
        
        Helper.scrollToElement(driver, searchInput);
        Helper.clickUsingJS(driver, searchInput);


        waitHelper.waitForInputFieldToBeReady(searchBar);

    	
        Helper.pause(3000);
        searchBar.clear();
        searchBar.sendKeys(keyword1);

        Helper.pause(4000); 

        searchBar.clear();
        searchBar.sendKeys(keyword2);

//        Helper.captureScreenshot(driver, "Search_" + keyword1 + "_" + keyword2);
    }

    public String applyFilter(String difficultLevel) {
    	
        Helper.pause(2000);
        
        waitHelper.waitForElementToBeClickable(difficultyDropdown);
        difficultyDropdown.click();

        switch (difficultLevel.toLowerCase()) {
            case "easy":
                waitHelper.waitForElementToBeClickable(easyDifficultyBtn);
                easyDifficultyBtn.click();
                break;
            case "medium":
                waitHelper.waitForElementToBeClickable(mediumDifficultyBtn);
                mediumDifficultyBtn.click();
                break;
            case "hard":
                waitHelper.waitForElementToBeClickable(hardDifficultyBtn);
                hardDifficultyBtn.click();
                break;
            default:
                throw new IllegalArgumentException("Invalid difficulty level: " + difficultLevel);
        }

        Helper.pause(4000);

//        Helper.captureScreenshot(driver, "FilterApplied_" + difficultLevel);

        return Helper.getDirectTextContent(driver, difficultyStatus);
    }


}
