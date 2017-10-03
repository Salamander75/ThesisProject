import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static com.codeborne.selenide.Selenide.sleep;


/**
 * Created by Karl on 18.09.2017.
 */
public class Testing extends BaseInitialization {

    public static void main(String args[]) throws InterruptedException {
        new Testing();
    }

    public Testing() throws InterruptedException {
        String javascript = "";
        try {
            Scanner sc = new Scanner(new FileInputStream(new File("src/main/java/elementController.js")));
            while (sc.hasNext()) {
                javascript += sc.nextLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(javascript);
        driver.get("http://www.urbandead.com/");
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        String script =
                "var head= document.getElementsByTagName('head')[0];" +
                        "var script= document.createElement('script');" +
                        "script.type= 'text/javascript';" +
                        "script.src = 'http://localhost/jsTest/myScript.js';" +
                        "head.appendChild(script);" +
                        "var css = '.highlight { background-color: yellow; }';" +
                        "var style = document.createElement('style');" +
                        "style.type = 'text/css';" +
                        "style.appendChild(document.createTextNode(css));" +
                        "head.appendChild(style);";
        jse.executeScript(script);
        // main loop
        while (true) {
            checkAlert();
            String oldUrl = driver.getCurrentUrl();
            Thread.sleep(1000);
            System.out.println("test");
            System.out.println(oldUrl);
            if (!(oldUrl.equals(driver.getCurrentUrl())) || oldUrl.equals(driver.getCurrentUrl())) {
                System.out.println("Url has changed");
                jse.executeScript(script);
            }
        }
    }

    public void checkAlert() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 2);
            wait.until(ExpectedConditions.alertIsPresent());
       //     Alert alert = driver.switchTo().alert();
      //      alert.accept();
        } catch (Exception e) {
            checkAlert();
        }
    }
}
