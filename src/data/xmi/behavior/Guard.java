package data.xmi.behavior;

import data.xmi.XMIObject;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

/**
 * Created by Joost on 20-Feb-17.
 *
 * Basic element of SysML the tag xmi:type determines what kind of element it is.
 */
public class Guard extends XMIObject{
    public static final String TAG_NAME = "ownedRule";
    public static final String ATTRIBUTE_TYPE = "xmi:type";
    public static final String ATTRIBUTE_NAME = "name";

    public static final String SPECIFICATION_TAG_NAME = "specification";
    public static final String SPECIFICATION_LANGUAGE_TAG_NAME = "language";
    public static final String SPECIFICATION_BODY_TAG_NAME = "body";

    protected String type, name;
    protected String expressionLanguage, expressionBody;

    public Guard(Element guardElement) {
        super(guardElement.getAttribute(ATTRIBUTE_ID));
        this.name = guardElement.getAttribute(ATTRIBUTE_NAME);

        extractGuardExpression(guardElement);
    }

    private void extractGuardExpression(Element guardElement) {
        NodeList specificationList = guardElement.getElementsByTagName(SPECIFICATION_TAG_NAME);
        if (specificationList.getLength() > 0) { //only if a specification is there select the first specification.
            Element specificationElement = (Element)specificationList.item(0);
            this.expressionLanguage = specificationElement.getElementsByTagName(SPECIFICATION_LANGUAGE_TAG_NAME).item(0).getTextContent();
            this.expressionBody = specificationElement.getElementsByTagName(SPECIFICATION_BODY_TAG_NAME).item(0).getTextContent();
            //Process expression
            this.expressionBody = this.expressionBody.replace("self.", "");


            System.out.println("Guard: Language: " + this.expressionLanguage + " ; Body: " + this.expressionBody);
        }
    }


    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getExpressionLanguage() {
        return expressionLanguage;
    }

    public String getExpressionBody() {
        return expressionBody;
    }
}
