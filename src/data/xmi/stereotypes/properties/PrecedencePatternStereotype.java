package data.xmi.stereotypes.properties;

import org.w3c.dom.Element;

/**
 * Created by Joost on 04-Apr-17.
 */
public class PrecedencePatternStereotype extends PatternPropertyStereotype {

    public static final String TAG_NAME = "SSTM.SSTM:PrecedencePattern";
    private static final String ATTRIBUTE_IF_EXPRESSION = "if";
    private static final String ATTRIBUTE_THEN_EXPRESSION = "then";

    private String ifExpression, thenExpression;

    public PrecedencePatternStereotype(Element stereotypeElement) {
        super(stereotypeElement);
        if (stereotypeElement.hasAttribute(ATTRIBUTE_IF_EXPRESSION)) this.ifExpression = stereotypeElement.getAttribute(ATTRIBUTE_IF_EXPRESSION);
        if (stereotypeElement.hasAttribute(ATTRIBUTE_THEN_EXPRESSION)) this.thenExpression = stereotypeElement.getAttribute(ATTRIBUTE_THEN_EXPRESSION);
    }

    @Override
    public String getPattern() {
        StringBuilder sb = new StringBuilder();
        sb.append("Globally, if {").append(ifExpression).append("} holds then it must be the case that {").append(thenExpression).append("} has occurred before ");
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Precedence Pattern Property stereotype";
    }
}
