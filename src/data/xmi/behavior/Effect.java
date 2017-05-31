package data.xmi.behavior;

import data.xmi.XMIObject;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Created by Joost on 20-Feb-17.
 *
 * Basic element of SysML the tag xmi:type determines what kind of element it is.
 */
public class Effect extends XMIObject{
    public static final String TAG_NAME = "effect";
    public static final String ATTRIBUTE_TYPE = "xmi:type";
    public static final String ATTRIBUTE_NAME = "name";
    public static final String TYPE_NAME = "uml:OpaqueBehavior";

    public static final String SPECIFICATION_LANGUAGE_TAG_NAME = "language";
    public static final String SPECIFICATION_BODY_TAG_NAME = "body";

    protected String type, name;
    protected String expressionLanguage, expressionBody;

    public Effect(Element effectElement) {
        super(effectElement.getAttribute(ATTRIBUTE_ID));
        this.name = effectElement.getAttribute(ATTRIBUTE_NAME);

        extractEffectExpression(effectElement);
    }

    private void extractEffectExpression(Element effectElement) {
        if (effectElement.hasAttribute(SPECIFICATION_LANGUAGE_TAG_NAME)) this.expressionLanguage = effectElement.getElementsByTagName(SPECIFICATION_LANGUAGE_TAG_NAME).item(0).getTextContent();
        this.expressionBody = effectElement.getElementsByTagName(SPECIFICATION_BODY_TAG_NAME).item(0).getTextContent();
        //Process expression
        this.expressionBody = this.expressionBody.replace("self.", "");

        System.out.println("Effect: Language: " + this.expressionLanguage + " ; Body: " + this.expressionBody);
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
