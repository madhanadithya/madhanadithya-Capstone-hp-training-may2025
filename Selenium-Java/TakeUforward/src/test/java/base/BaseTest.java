package base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import utils.ConfigReader;

public class BaseTest {

	protected static WebDriver driver;
    Properties prop;

//    @BeforeTest
    @BeforeSuite(alwaysRun = true)
    public void launchApp() throws Exception {
        System.out.println("========launchApp==========");

        prop = ConfigReader.initProp();
        String browser = prop.getProperty("browser").toLowerCase();
        String mode = prop.getProperty("mode").toLowerCase();

        switch (browser) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                if (mode.equals("headless")) {
                    chromeOptions.addArguments("--headless=new");
                }
                driver = new ChromeDriver(chromeOptions);
                break;

            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                if (mode.equals("headless")) {
                    edgeOptions.addArguments("--headless=new");
                }
                driver = new EdgeDriver(edgeOptions);
                break;

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (mode.equals("headless")) {
                    firefoxOptions.addArguments("--headless");
                }
                driver = new FirefoxDriver(firefoxOptions);
                break;

            default:
                throw new RuntimeException("⚠️ Browser not supported: " + browser);
        }

        
        driver.manage().window().maximize();
        driver.get(prop.getProperty("url"));
        Thread.sleep(3000);
    }
    
    
//    @AfterTest
    @AfterSuite(alwaysRun = true)
    public void closeApp() throws Exception {
		System.out.println("========closeApp==========");
        Thread.sleep(3000);
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }
    
}

