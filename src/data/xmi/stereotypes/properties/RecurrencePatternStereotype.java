package data.xmi.stereotypes.properties;

import org.w3c.dom.Element;

/**
 * Created by Joost on 04-Apr-17.
 */
public class RecurrencePatternStereotype extends PatternPropertyStereotype {

    public static final String TAG_NAME = "SSTM.SSTM:RecurrencePattern";
    private static final String ATTRIBUTE_EXPRESSION = "expression";

    private String expression;

    public RecurrencePatternStereotype(Element stereotypeElement) {
        super(stereotypeElement);
        if (stereotypeElement.hasAttribute(ATTRIBUTE_EXPRESSION)) this.expression = stereotypeElement.getAttribute(ATTRIBUTE_EXPRESSION);
    }

    @Override
    public String getPattern() {
        StringBuilder sb = new StringBuilder();
        sb.append("Globally, {").append(expression).append("} holds repeatedly ");
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Recurrence Pattern Property stereotype";
    }
}
