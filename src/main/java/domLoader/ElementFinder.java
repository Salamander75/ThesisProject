package domLoader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karl on 6.09.2017.
 */
public class ElementFinder {

    private List<String> linkElements;

    private List<String> inputElements;

    private final String[] pageElementTags = {"a", "select", "button", "input", "span", "p", "h1"
            , "h2", "h3", "h4", "h5", "h6"};

    public ElementFinder() {
        try {
            Document doc = Jsoup.connect("http://www.urbandead.com/").get();
            findAllLinks(doc);
            findAllInputs(doc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void findAllLinks(Document doc) {
        Elements visibleLinks = doc.select("a");
        this.linkElements = new ArrayList<String>();

        for(int i=0; i<visibleLinks.size(); i++) {
            this.linkElements.add(generateLinkElementName(visibleLinks.get(i)));
        }

        for(int j=0; j<linkElements.size(); j++) {
            System.out.println(linkElements.get(j));
        }
    }

    private void findAllInputs(Document doc) {
        Elements visibleInputs = doc.select("input");
        this.inputElements = new ArrayList<String>();
        for(int i=0; i<visibleInputs.size(); i++) {
            System.out.println("SELECTOR: " + findSelectorForElement(visibleInputs.get(i)));
            this.inputElements.add(generateInputElementName(visibleInputs.get(i)));
        }

        for(int j=0; j<inputElements.size(); j++) {
            System.out.println(inputElements.get(j));
        }
    }

    private String generateLinkElementName(Element link) {
        return "private SelenideElement " + link.text() + ";";
    }

    private String generateInputElementName(Element input) {
        return "private SelenideElement " + input.attr("name") + ";";
    }

    // TODO: What if no attributes or whatever exist
    private String findSelectorForElement(Element element) {
        if(!element.attr("id").equals("")) {
            return element.attr("id");
        } else if (!element.attr("name").equals("")) {
            return element.attr("name");
        } else if (!element.attr("class").equals("")) {
            return element.attr("class");
        } else return element.cssSelector();
    }
}
