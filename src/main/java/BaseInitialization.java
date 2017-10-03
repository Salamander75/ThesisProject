import org.openqa.selenium.WebDriver;

/**
 * Created by Karl on 18.09.2017.
 */
public class BaseInitialization {
    protected WebDriver driver;
    SeleniumConf conf = SeleniumConf.getInstance();

    public BaseInitialization(){
        driver = conf.getDriver();
    }
}
