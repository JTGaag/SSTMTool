package data.xmi.stereotypes.error;

import data.xmi.stereotypes.sysml.StateStereotype;
import org.w3c.dom.Element;

/**
 * Created by Joost on 15-Feb-17.
 */
public class ErrorStateStereotype extends StateStereotype{
    public static final String TAG_NAME = "SSTM.SSTM:ErrorState";

    String whileExpression;

    public ErrorStateStereotype(Element stereotypeElement) {
        super(stereotypeElement);
    }

    public String getWhileExpression() {
        return whileExpression;
    }

    @Override
    public String toString() {
        return "Error State stereotype";
    }
}
