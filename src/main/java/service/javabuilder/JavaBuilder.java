package service.javabuilder;

public class JavaBuilder {

    private final String clickActivity = "click";

    private final String setValueActicity = "set";

    private final String publicModifier = "public ";

    private String pageObjectCode = "";

    private String pageObjectClassName = "";

    private String methodCode = "";

    public JavaBuilder(String pageObjectClassName) {
        this.pageObjectClassName = pageObjectClassName;
        this.pageObjectCode += publicModifier + "class " +
                this.pageObjectClassName + addClassBeginningCurlyBrackets();
    }

    public JavaBuilder addPublicMethod(String elementName, String tagName, String tagType, String selectedAttributeValue) {
        String acticitySuffix = getElementActivitySuffix(tagName, tagType);
        String methodSignature = constructMethodSignature(acticitySuffix, elementName);
        String methodBody = constructMethodBody();
        pageObjectCode += methodSignature + methodBody;
        return this;
    }

    private String getElementActivitySuffix(String tagName, String tagType) {
        System.out.println("TAG NAME: " + tagName);
        System.out.println("TAG TYPE: " + tagType);
        if (tagName.equals("input") && (tagType.equals("button")
                || tagType.equals("checkbox")
                || tagType.equals("radio")
                || tagType.equals("submit")
                || tagType.equals("reset"))) {
            return clickActivity;
        } else if (tagName.equals("a")) {
            return clickActivity;
        } else if ( tagName.equals("input") && (tagType.equals("text")
                || tagType.equals("password")
                || tagType.equals("search")
                || tagType.equals("tel")
                || tagType.equals("url"))) {
            return setValueActicity;
        }
        return setValueActicity;
    }

    private String constructMethodSignature(String acticitySuffix, String elementName) {
        String methodAccessModifier = "\n public ";
        String methodReturnType = pageObjectClassName + " ";
        String methodName = acticitySuffix + elementName.substring(0, 1).toUpperCase()
                + elementName.substring(1) + "() { \n\t\t";
        return methodAccessModifier + methodReturnType + methodName;
    }

    private String constructMethodBody() {
        return "$(By.id('SomeId')).setValue('Value'); \n\t" + "return this; \n\t" + "}";
    }

    public String buildJavaSourceCode() {
        return pageObjectCode;
    }

    private String addClassBeginningCurlyBrackets() {
        return " { \n\n" ;
    }




}
