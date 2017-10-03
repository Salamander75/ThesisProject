import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.JavascriptExecutor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Karl on 18.09.2017.
 */
public class Testing extends BaseInitialization {

    private JavascriptExecutor jse = (JavascriptExecutor) driver;

    private String scriptId = RandomStringUtils.randomAlphanumeric(50);
    private String script =
            "var head= document.getElementsByTagName('head')[0];" +
                    "var script= document.createElement('script');" +
                    "script.id = '"+ scriptId +"'; script.type= 'text/javascript';" +
                    "script.src = 'http://localhost/jsTest/myScript.js';" +
                    "head.appendChild(script);" +
                    "var css = '.highlight { background-color: yellow; }';" +
                    "var style = document.createElement('style');" +
                    "style.type = 'text/css';" +
                    "style.appendChild(document.createTextNode(css));" +
                    "head.appendChild(style);";
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
        elementInspectionMainLoop();
    }

    private void elementInspectionMainLoop() {
        while (true) {
            try {
                // Necessary to avoid ultra fast true loop
                Thread.sleep(1000);
                if (jse.executeScript("return document.getElementById('"+ scriptId +"')") == null) {
                    System.out.println("Scripti pole olemas");
                    jse.executeScript(script);
                }
            } catch(Exception e) {
                System.out.println("Some exception wants to happen!");
                elementInspectionMainLoop();
            }
        }
    }
}
