package data.stereotypes.sysml;

import data.stereotypes.Stereotype;

/**
 * Created by Joost on 15-Feb-17.
 */
public class FlowPortStereotype extends Stereotype {
    public enum Direction {
        IN, OUT, UNKNOWN
    }

    public static final String TAG_NAME = "DeprecatedElements:FlowPort";
    public static final String ATTRIBUTE_BASE_PORT = "base_Port";
    public static final String ATTRIBUTE_DIRECTION = "direction";

    String basePortId;
    Direction portDirection;


    public FlowPortStereotype(String id, String basePortId, String portDirection) {
        super(id);
        this.basePortId = basePortId;

        if(portDirection.equals("in")) this.portDirection = Direction.IN;
        else if (portDirection.equals("out")) this.portDirection = Direction.OUT;
        else this.portDirection = Direction.UNKNOWN;

    }

    public String getBasePortId() {
        return basePortId;
    }

    public Direction getPortDirection() {
        return portDirection;
    }

    @Override
    public String toString() {
        return "FlowPort stereotype";
    }
}
