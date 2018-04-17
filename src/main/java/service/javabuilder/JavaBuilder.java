package service.javabuilder;

import model.SelectorType;

public class JavaBuilder {

    private final String clickActivity = "click";

    private final String setValueActicity = "set";

    private final String publicModifier = "public ";

    private final String selectActivity = "select";

    private String pageObjectCode = "";

    private String pageObjectClassName = "";

    public JavaBuilder(String pageObjectClassName) {
        this.pageObjectClassName = pageObjectClassName;
        this.pageObjectCode += publicModifier + "class " +
                this.pageObjectClassName + addClassBeginningCurlyBrackets();
    }

    public JavaBuilder addPublicMethod(String elementName, String tagName, String tagType,
                                       String selectedAttributeValue, String elementLocatorTag) {
        String acticitySuffix = getElementActivitySuffix(tagName, tagType);
        String methodSignature = constructMethodSignature(acticitySuffix, elementName);
        String methodBody = constructMethodBody(selectedAttributeValue, elementLocatorTag, acticitySuffix);
        pageObjectCode += methodSignature + methodBody;
        return this;
    }

    private String getElementActivitySuffix(String tagName, String tagType) {
        if (tagName.equals("input") && (tagType.equals("button")
                || tagType.equals("checkbox")
                || tagType.equals("radio")
                || tagType.equals("submit")
                || tagType.equals("reset"))) {
            return clickActivity;
        } else if (tagName.equals("a")) {
            return clickActivity;
        } else if (tagName.equals("select")) {
            return selectActivity;
        } else if (tagName.equals("button") && (tagType.equals("")
                || tagType.equals("button")
                || tagType.equals("reset"))
                || tagType.equals("submit")) {
            return clickActivity;
        } else if ( tagName.equals("input") && ( tagType.equals("")
                || tagType.equals("text")
                || tagType.equals("password")
                || tagType.equals("search")
                || tagType.equals("tel")
                || tagType.equals("url"))) {
            return setValueActicity;
        }
        return setValueActicity;
    }

    private String constructMethodSignature(String acticitySuffix, String elementName) {
        String methodAccessModifier = "\n\tpublic ";
        String methodReturnType = pageObjectClassName + " ";
        String methodParameter = "";
        if (acticitySuffix.equals(setValueActicity)) {
            methodParameter = "(String value) { \n\t\t";
        } else if (acticitySuffix.equals(selectActivity)) {
            methodParameter = "(String value) { \n\t\t";
        } else methodParameter = "() { \n\t\t";
        String methodName = acticitySuffix + elementName.substring(0, 1).toUpperCase()
                + elementName.substring(1) + methodParameter;
        return methodAccessModifier + methodReturnType + methodName;
    }

    private String constructMethodBody(String selectedAttributeValue, String elementLocatorTag, String activitySuffix) {
        String methodBody = "";
        if (elementLocatorTag.equals(SelectorType.ID)) {
            methodBody += constructByIdMethodBody(selectedAttributeValue, activitySuffix);
        } else if (elementLocatorTag.equals(SelectorType.CLASS_NAME)) {
            methodBody += constructByClassMethodBody(selectedAttributeValue, activitySuffix);
        } else if (elementLocatorTag.equals(SelectorType.NAME)) {
            methodBody += constructByNameMethodBody(selectedAttributeValue, activitySuffix);
        } else if (elementLocatorTag.equals(SelectorType.SELECTOR)) {
            methodBody += constructByCssSelectorMethodBody(selectedAttributeValue, activitySuffix);
        } else if (elementLocatorTag.equals(SelectorType.XPATH)) {
            methodBody += constructByXpathMethodBody(selectedAttributeValue, activitySuffix);
        }
        return methodBody + "\n\t\t" + "return this; \n\t" + "}";
    }

    private String constructByIdMethodBody(String selectedAttributeValue, String activitySuffix) {
        System.out.println("Suffix: " + activitySuffix);
        if (activitySuffix.equals(setValueActicity)) {
            return SelenideSyntaxTags.byId(selectedAttributeValue) + SelenideSyntaxTags.setValueMethod();
        } else if (activitySuffix.equals(clickActivity)) {
            return SelenideSyntaxTags.byId(selectedAttributeValue) + SelenideSyntaxTags.setClickMethod();
        } else if (activitySuffix.equals(selectActivity)) {
            return SelenideSyntaxTags.byId(selectedAttributeValue) + SelenideSyntaxTags.setSelectMethod();
        }
        return "";
    }

    private String constructByClassMethodBody(String selectedAttributeValue, String activitySuffix) {
        System.out.println("Suffix: " + activitySuffix);
        if (activitySuffix.equals(setValueActicity)) {
            return SelenideSyntaxTags.byClass(selectedAttributeValue) + SelenideSyntaxTags.setValueMethod();
        } else if (activitySuffix.equals(clickActivity)) {
            return SelenideSyntaxTags.byClass(selectedAttributeValue) + SelenideSyntaxTags.setClickMethod();
        } else if (activitySuffix.equals(selectActivity)) {
            return SelenideSyntaxTags.byClass(selectedAttributeValue) + SelenideSyntaxTags.setSelectMethod();
        }
        return "";
    }

    private String constructByNameMethodBody(String selectedAttributeValue, String activitySuffix) {
        System.out.println("Suffix: " + activitySuffix);
        if (activitySuffix.equals(setValueActicity)) {
            return SelenideSyntaxTags.byName(selectedAttributeValue) + SelenideSyntaxTags.setValueMethod();
        } else if (activitySuffix.equals(clickActivity)) {
            return SelenideSyntaxTags.byName(selectedAttributeValue) + SelenideSyntaxTags.setClickMethod();
        } else if (activitySuffix.equals(selectActivity)) {
            return SelenideSyntaxTags.byName(selectedAttributeValue) + SelenideSyntaxTags.setSelectMethod();
        }
        return "";
    }

    private String constructByCssSelectorMethodBody(String selectedAttributeValue, String activitySuffix) {
        System.out.println("Suffix: " + activitySuffix);
        if (activitySuffix.equals(setValueActicity)) {
            return SelenideSyntaxTags.byCssSelector(selectedAttributeValue) + SelenideSyntaxTags.setValueMethod();
        } else if (activitySuffix.equals(clickActivity)) {
            return SelenideSyntaxTags.byCssSelector(selectedAttributeValue) + SelenideSyntaxTags.setClickMethod();
        } else if (activitySuffix.equals(selectActivity)) {
            return SelenideSyntaxTags.byCssSelector(selectedAttributeValue) + SelenideSyntaxTags.setSelectMethod();
        }
        return "";
    }

    private String constructByXpathMethodBody(String selectedAttributeValue, String activitySuffix) {
        System.out.println("Suffix: " + activitySuffix);
        if (activitySuffix.equals(setValueActicity)) {
            return SelenideSyntaxTags.byXpath(selectedAttributeValue) + SelenideSyntaxTags.setValueMethod();
        } else if (activitySuffix.equals(clickActivity)) {
            return SelenideSyntaxTags.byXpath(selectedAttributeValue) + SelenideSyntaxTags.setClickMethod();
        } else if (activitySuffix.equals(selectActivity)) {
            return SelenideSyntaxTags.byXpath(selectedAttributeValue) + SelenideSyntaxTags.setSelectMethod();
        }
        return "";
    }

    public String buildJavaSourceCode() {
        return pageObjectCode + addClassEndCurlyBrackets();
    }

    private String addClassBeginningCurlyBrackets() {
        return " { \n" ;
    }

    private String addClassEndCurlyBrackets() {
        return "\n\n} ";
    }




}
