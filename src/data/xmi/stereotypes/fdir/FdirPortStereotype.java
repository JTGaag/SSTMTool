package data.xmi.stereotypes.fdir;

import data.enums.PortDirection;
import data.xmi.stereotypes.Stereotype;
import org.w3c.dom.Element;

/**
 * Created by Joost on 15-Feb-17.
 */
public class FdirPortStereotype extends Stereotype {

    public static final String ATTRIBUTE_BASE_PORT = "base_Port";

    String basePortId;

    public FdirPortStereotype(Element flowPortStereotypeElement) {
        super(flowPortStereotypeElement.getAttribute(ATTRIBUTE_ID));
        this.basePortId = flowPortStereotypeElement.getAttribute(ATTRIBUTE_BASE_PORT);
    }

    public String getBasePortId() {
        return basePortId;
    }

    @Override
    public String toString() {
        return "FDIR Port stereotype";
    }
}
