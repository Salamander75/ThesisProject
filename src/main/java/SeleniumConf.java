import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Karl on 18.09.2017.
 */
public class SeleniumConf {

    private static SeleniumConf ourInstance = new SeleniumConf();

    private WebDriver driver;

    public static SeleniumConf getInstance() {
        return ourInstance;
    }

    private SeleniumConf() {
        System.setProperty("webdriver.chrome.driver",
                System.getProperty("user.dir")+"/src/main/resources/chromedriver.exe");
        defineWebDriverBrowser();
    }

    private void defineWebDriverBrowser(){
        driver = new ChromeDriver();
    }

    public WebDriver getDriver(){
        return driver;
    }
}
