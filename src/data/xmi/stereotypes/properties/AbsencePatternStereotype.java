package data.xmi.stereotypes.properties;

import org.w3c.dom.Element;

/**
 * Created by Joost on 04-Apr-17.
 */
public class AbsencePatternStereotype extends PatternPropertyStereotype {

    public static final String TAG_NAME = "SSTM.SSTM:AbsencePattern";
    private static final String ATTRIBUTE_THAT_EXPRESSION = "that";

    private String thatExpression;

    public AbsencePatternStereotype(Element stereotypeElement) {
        super(stereotypeElement);
        if (stereotypeElement.hasAttribute(ATTRIBUTE_THAT_EXPRESSION)) this.thatExpression = stereotypeElement.getAttribute(ATTRIBUTE_THAT_EXPRESSION);
    }

    @Override
    public String getPattern() {
        StringBuilder sb = new StringBuilder();
        sb.append("Globally, it is never the case that {").append(thatExpression).append("} holds ");
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Absence Pattern Property stereotype";
    }
}
