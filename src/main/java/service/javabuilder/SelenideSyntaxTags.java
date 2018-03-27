package service.javabuilder;

public class SelenideSyntaxTags {

    private static final String dollarSign= "$";

    public static String byId (String locatorValue) {
        return dollarSign + "(By.id(\"" + locatorValue + "\"))";
    }

    public static String byClass (String locatorValue) {
        return dollarSign + "(By.className(\"" + locatorValue + "\"))";
    }

    public static String byName (String locatorValue) {
        return dollarSign + "(By.name(\"" + locatorValue + "\"))";
    }

    public static String byCssSelector (String locatorValue) {
        return dollarSign + "(By.cssSelector(\"" + locatorValue + "\"))";
    }

    public static String byXpath (String locatorValue) {
        return dollarSign + "(By.xpath(\"" + locatorValue + "\"))";
    }

    public static String setValueMethod () {
        return ".setValue(value);";
    }

    public static String setClickMethod () {
        return ".click();";
    }
}
