package data.xmi.stereotypes.sysml;

import data.xmi.stereotypes.Stereotype;
import org.w3c.dom.Element;

/**
 * Created by Joost on 15-Feb-17.
 */
public class StateStereotype extends Stereotype {
    public static final String ATTRIBUTE_BASE_STATE = "base_State";

    String baseStateId;

    public StateStereotype(Element blockStereotypeElement) {
        super(blockStereotypeElement.getAttribute(ATTRIBUTE_ID));
        this.baseStateId = blockStereotypeElement.getAttribute(ATTRIBUTE_BASE_STATE);
    }

    public String getBaseStateId() {
        return baseStateId;
    }

    @Override
    public String toString() {
        return "State stereotype";
    }
}
