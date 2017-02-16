package data.slim.internal;

import data.enums.PortDirection;
import data.slim.SlimObject;

/**
 * Created by Joost on 16-Feb-17.
 * General parent for SLIM ports
 */
public class Port extends SlimObject{
    data.xmi.uml.Port xmiPort;

    String name;
    PortDirection direction;

    public Port(data.xmi.uml.Port xmiPort) {
        this.xmiPort = xmiPort;
        this.name = xmiPort.getName();
        this.direction = xmiPort.getDirection();
    }

    public String getName() {
        return name;
    }

    public PortDirection getDirection() {
        return direction;
    }

    @Override
    public String toSlimString() {
        return "port base class <This chould not be here!>";
    }
}
