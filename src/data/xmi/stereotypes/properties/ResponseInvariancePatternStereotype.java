package data.xmi.stereotypes.properties;

import org.w3c.dom.Element;

/**
 * Created by Joost on 04-Apr-17.
 */
public class ResponseInvariancePatternStereotype extends PatternPropertyStereotype {

    public static final String TAG_NAME = "SSTM.SSTM:ResponseInvariancePattern";
    private static final String ATTRIBUTE_IF_EXPRESSION = "if";
    private static final String ATTRIBUTE_THEN_EXPRESSION = "then";

    private String ifExpression, thenExpression;

    public ResponseInvariancePatternStereotype(Element stereotypeElement) {
        super(stereotypeElement);
        if (stereotypeElement.hasAttribute(ATTRIBUTE_IF_EXPRESSION)) this.ifExpression = stereotypeElement.getAttribute(ATTRIBUTE_IF_EXPRESSION);
        if (stereotypeElement.hasAttribute(ATTRIBUTE_THEN_EXPRESSION)) this.thenExpression = stereotypeElement.getAttribute(ATTRIBUTE_THEN_EXPRESSION);
    }

    @Override
    public String getPattern() {
        StringBuilder sb = new StringBuilder();
        sb.append("Globally, if {").append(ifExpression).append("} has occurred then in response {").append(thenExpression).append("} holds continually ");
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Response Invariance Pattern Property stereotype";
    }
}
