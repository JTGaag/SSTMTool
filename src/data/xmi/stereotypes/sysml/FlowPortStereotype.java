package data.xmi.stereotypes.sysml;

import data.enums.PortDirection;
import data.xmi.stereotypes.Stereotype;
import org.w3c.dom.Element;

/**
 * Created by Joost on 15-Feb-17.
 */
public class FlowPortStereotype extends Stereotype {

    public static final String TAG_NAME = "DeprecatedElements:FlowPort";
    public static final String ATTRIBUTE_BASE_PORT = "base_Port";
    public static final String ATTRIBUTE_DIRECTION = "direction";

    String basePortId;
    PortDirection portDirection;


    public FlowPortStereotype(String id, String basePortId, String portDirection) {
        super(id);
        this.basePortId = basePortId;

        if(portDirection.equals("in")) this.portDirection = PortDirection.IN;
        else if (portDirection.equals("out")) this.portDirection = PortDirection.OUT;
        else this.portDirection = PortDirection.UNKNOWN;

    }

    public FlowPortStereotype(Element flowPortStereotypeElement) {
        super(flowPortStereotypeElement.getAttribute(ATTRIBUTE_ID));
        this.basePortId = flowPortStereotypeElement.getAttribute(ATTRIBUTE_BASE_PORT);

        switch (flowPortStereotypeElement.getAttribute(ATTRIBUTE_DIRECTION)) {
            case "in":
                this.portDirection = PortDirection.IN;
                break;
            case "out":
                this.portDirection = PortDirection.OUT;
                break;
            default:
                this.portDirection = PortDirection.UNKNOWN;
                break;
        }
    }

    public String getBasePortId() {
        return basePortId;
    }

    public PortDirection getPortDirection() {
        return portDirection;
    }

    @Override
    public String toString() {
        return "FlowPort stereotype";
    }
}
