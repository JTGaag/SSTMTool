package data.xmi.behavior;

import com.sun.istack.internal.Nullable;
import data.xmi.XMIObject;
import data.xmi.structure.Class;
import data.xmi.structure.Port;
import org.w3c.dom.Element;

import java.util.ArrayList;

/**
 * Created by Joost on 20-Feb-17.
 *
 * Basic element of SysML the tag xmi:type determines what kind of element it is.
 */
public class Trigger extends XMIObject{
    public static final String TAG_NAME = "trigger";
    public static final String ATTRIBUTE_TYPE = "xmi:type";
    public static final String ATTRIBUTE_NAME = "name";
    public static final String ATTRIBUTE_PORT = "port";

    protected String type, portId, name;
    protected Port triggerPort;
    protected Class triggerSubcomponent;

    public Trigger(Element triggerElement) {
        super(triggerElement.getAttribute(ATTRIBUTE_ID));
        this.name = triggerElement.getAttribute(ATTRIBUTE_NAME);
        this.portId = triggerElement.getAttribute(ATTRIBUTE_PORT);
    }

    public void addPortToTrigger(ArrayList<Port> classPorts, @Nullable Class triggerSubcomponent) {
        for (Port port : classPorts) {
            if (port.getId().equals(portId)) {
                this.triggerSubcomponent = triggerSubcomponent;
                triggerPort = port;
                return;
            }
        }
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Port getTriggerPort() {
        return triggerPort;
    }
}
