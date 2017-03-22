package data.xmi.stereotypes.sstm;

import data.xmi.stereotypes.sysml.StateStereotype;
import org.w3c.dom.Element;

/**
 * Created by Joost on 15-Feb-17.
 */
public class SstmStateStereotype extends StateStereotype{
    public static final String TAG_NAME = "SSTM.SSTM:SstmState";
    public static final String ATTRIBUTE_WHILE_NAME = "while";

    String whileExpression;

    public SstmStateStereotype(Element stereotypeElement) {
        super(stereotypeElement);
        if (stereotypeElement.hasAttribute(ATTRIBUTE_WHILE_NAME)) whileExpression = stereotypeElement.getAttribute(ATTRIBUTE_WHILE_NAME);
    }

    public String getWhileExpression() {
        return whileExpression;
    }

    @Override
    public String toString() {
        return "SstmState stereotype";
    }
}
