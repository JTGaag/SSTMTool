package data.xmi.stereotypes.properties;

import org.w3c.dom.Element;

/**
 * Created by Joost on 04-Apr-17.
 */
public class UntilPatternStereotype extends PatternPropertyStereotype {

    public static final String TAG_NAME = "SSTM.SSTM:UntilPattern";
    private static final String ATTRIBUTE_EXPRESSION = "expression";
    private static final String ATTRIBUTE_UNTIL_EXPRESSION = "until";

    private String expression, untilExpression;

    public UntilPatternStereotype(Element stereotypeElement) {
        super(stereotypeElement);
        if (stereotypeElement.hasAttribute(ATTRIBUTE_EXPRESSION)) this.expression = stereotypeElement.getAttribute(ATTRIBUTE_EXPRESSION);
        if (stereotypeElement.hasAttribute(ATTRIBUTE_UNTIL_EXPRESSION)) this.untilExpression = stereotypeElement.getAttribute(ATTRIBUTE_UNTIL_EXPRESSION);
    }

    @Override
    public String getPattern() {
        StringBuilder sb = new StringBuilder();
        sb.append("Globally, {").append(expression).append("} holds without interruption until {").append(untilExpression).append("} holds ");
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Until Pattern Property stereotype";
    }
}
