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
    PortDirection direction;


    public FlowPortStereotype(String id, String basePortId, String direction) {
        super(id);
        this.basePortId = basePortId;

        if(direction.equals("in")) this.direction = PortDirection.IN;
        else if (direction.equals("out")) this.direction = PortDirection.OUT;
        else this.direction = PortDirection.UNKNOWN;

    }

    public FlowPortStereotype(Element flowPortStereotypeElement) {
        super(flowPortStereotypeElement.getAttribute(ATTRIBUTE_ID));
        this.basePortId = flowPortStereotypeElement.getAttribute(ATTRIBUTE_BASE_PORT);

        switch (flowPortStereotypeElement.getAttribute(ATTRIBUTE_DIRECTION)) {
            case "in":
                this.direction = PortDirection.IN;
                break;
            case "out":
                this.direction = PortDirection.OUT;
                break;
            default:
                this.direction = PortDirection.UNKNOWN;
                break;
        }
    }

    public String getBasePortId() {
        return basePortId;
    }

    public PortDirection getDirection() {
        return direction;
    }

    @Override
    public String toString() {
        return "FlowPort stereotype";
    }
}
