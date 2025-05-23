//package pages;
//
//import org.openqa.selenium.*;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.PageFactory;
//import utils.WaitHelper;
//
//public class NotePage {
//
//    WebDriver driver;
//    WaitHelper waitHelper;
//
//    @FindBy(xpath = "//button[contains(@class,'plus-icon')]")
//    WebElement addNoteBtn;
//
//    @FindBy(xpath = "//textarea[contains(@placeholder,'Add your note')]")
//    WebElement noteTextarea;
//
//    @FindBy(xpath = "//button[text()='Save Note']")
//    WebElement saveNoteBtn;
//
//    public NotePage(WebDriver driver) {
//        this.driver = driver;
//        PageFactory.initElements(driver, this);
//        waitHelper = new WaitHelper(driver);
//    }
//
//    public void addNote(String noteText) {
//        waitHelper.waitForElementToBeClickable(addNoteBtn);
//        addNoteBtn.click();
//        noteTextarea.sendKeys(noteText);
//        saveNoteBtn.click();
//    }
//}
