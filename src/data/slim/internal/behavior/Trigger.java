package data.slim.internal.behavior;

import com.sun.istack.internal.Nullable;
import data.slim.SlimObject;
import data.slim.components.Component;
import data.slim.internal.structure.EventPort;
import data.slim.internal.structure.Port;
import data.slim.internal.structure.Subcomponent;

/**
 * Created by Joost on 20-Feb-17.
 */
public class Trigger extends SlimObject{
    String name, portId, subcomponentId;
    Port triggerPort;
    Subcomponent subcomponent;

    public Trigger(data.xmi.behavior.Trigger xmiTrigger) {
        this.name = xmiTrigger.getName();
        this.portId = xmiTrigger.getPortId();
        this.subcomponentId = xmiTrigger.getSubcomponentId();
    }

    /**
     * Attach port to trigger
     * ToDo: check if event port
     * @param port
     * @param subcomponent
     */
    public void finalize(Port port, @Nullable Subcomponent subcomponent) {
        //For subcomponents
        if (subcomponentId != null && subcomponent != null) {
            if (port.getPortId().equals(this.portId) && subcomponent.getSubcomponentId().equals(this.subcomponentId)) {
                this.triggerPort = port;
                this.subcomponent = subcomponent;
            }
        }else{
            if (port.getPortId().equals(this.portId)) {
                this.triggerPort = port;
            }
        }
    }

    @Override
    public String toSlimString() {
        StringBuilder sb = new StringBuilder();
        if (subcomponent != null) {
            sb.append(subcomponent.getName() + ".");
        }
        sb.append(triggerPort.getName());
        sb.append(" ");
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public String getPortId() {
        return portId;
    }
}
