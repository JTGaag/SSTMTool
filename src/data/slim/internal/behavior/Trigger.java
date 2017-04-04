package data.slim.internal.behavior;

import com.sun.istack.internal.Nullable;
import data.slim.SlimObject;
import data.slim.components.Component;
import data.slim.error.ErrorEvent;
import data.slim.internal.structure.EventPort;
import data.slim.internal.structure.Port;
import data.slim.internal.structure.Subcomponent;

/**
 * Created by Joost on 20-Feb-17.
 */
public class Trigger extends SlimObject{
    String name, portId, subcomponentId;
    Port triggerPort;
    ErrorEvent triggerErrorEvent;
    Subcomponent subcomponent;

    boolean errorTrigger = false;

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

    public void finalizeError(ErrorEvent errorEvent) {
        if (errorEvent.getErrorEventId().equals(this.portId)) {
            this.triggerErrorEvent = errorEvent;
            this.errorTrigger = true;
        }
    }

    @Override
    public String toSlimString() {
        StringBuilder sb = new StringBuilder();
        if (!errorTrigger) {
            if (subcomponent != null) {
                sb.append(subcomponent.getName() + ".");
            }
            sb.append(triggerPort.getName());
            sb.append(" ");
        } else {
            sb.append(triggerErrorEvent.getName()).append(" ");
        }
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public String getPortId() {
        return portId;
    }
}
