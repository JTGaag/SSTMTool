package data.slim.internal.structure;

import data.enums.PortDirection;
import data.slim.SlimObject;

/**
 * Created by Joost on 16-Feb-17.
 * General parent for SLIM ports
 */
public class Port extends SlimObject{
    data.xmi.structure.Port xmiPort;

    String name, portId, defaultValue;
    PortDirection direction;
    boolean fdirAlarm, fdirObservable;

    public Port(data.xmi.structure.Port xmiPort) {
        this.xmiPort = xmiPort;
        this.name = xmiPort.getName();
        this.direction = xmiPort.getDirection();
        this.portId = xmiPort.getId();
        this.defaultValue = xmiPort.getDefaultValue();
        this.fdirAlarm = xmiPort.isFdirAlarm();
        this.fdirObservable = xmiPort.isFdirObservable();
    }

    public String getName() {
        return name;
    }

    public PortDirection getDirection() {
        return direction;
    }

    public String getPortId() {
        return portId;
    }

    public boolean hasProperties() {
        return (defaultValue != null || fdirAlarm || fdirObservable);
    }

    @Override
    public String toSlimString() {
        return "port base class <This should not be here!>";
    }
}
