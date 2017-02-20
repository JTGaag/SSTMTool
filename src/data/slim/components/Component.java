package data.slim.components;

import data.slim.SlimObject;
import data.slim.internal.DataPort;
import data.slim.internal.EventPort;
import data.slim.internal.Subcomponent;
import data.xmi.structure.Class;
import data.xmi.structure.Port;
import data.xmi.structure.Property;

import java.util.ArrayList;

/**
 * Created by Joost on 16-Feb-17.
 * Base class for SLIM components
 */
public class Component extends SlimObject{
    Class baseXmiClass;

    String name;
    String slimComponentTypeName = "unknown";

    ArrayList<DataPort> dataPorts = new ArrayList<>();
    ArrayList<EventPort> eventPorts = new ArrayList<>();
    ArrayList<data.slim.internal.Port> ports = new ArrayList<>();

    ArrayList<Subcomponent> subcomponents = new ArrayList<>();

    /**
     * Constructor
     * @param baseXmiClass
     */
    public Component(Class baseXmiClass) {
        this.baseXmiClass = baseXmiClass;
        this.name = baseXmiClass.getName();
        initPorts();
    }

    public void createSubcomponents(ArrayList<Component> components) {
        for (Property property: baseXmiClass.getProperties()) {
            for (Component component: components) {
                if (property.getTypeId().equals(component.getBaseXmiClass().getId())) {
                    subcomponents.add(new Subcomponent(property.getName(), component));
                }
            }
        }
    }

    private void initPorts() {
        dataPorts.clear();
        eventPorts.clear();
        for (Port port: baseXmiClass.getPorts()) {
            if (port.getPortType().equals(Port.PortType.EVENT)) {
                EventPort eventPort = new EventPort(port);
                eventPorts.add(eventPort);
                ports.add(eventPort);
            } else {
                DataPort dataPort = new DataPort(port);
                dataPorts.add(dataPort);
                ports.add(dataPort);
            }
        }
    }


    public ArrayList<DataPort> getDataPorts() {
        return dataPorts;
    }

    public ArrayList<EventPort> getEventPorts() {
        return eventPorts;
    }

    public ArrayList<data.slim.internal.Port> getPorts() {
        return ports;
    }

    public String getName() {
        return name;
    }

    /**
     * Return the implementation name
     * By default it is the type name with ".Imp" added
     * TODO: descibe this as limitation
     * @return
     */
    public String getImplementationName() {
        return name + ".Imp";
    }

    public Class getBaseXmiClass() {
        return baseXmiClass;
    }

    public String getSlimComponentTypeName() {
        return slimComponentTypeName;
    }

    @Override
    public String toString() {
        return "Component";
    }

    @Override
    public String toSlimTypeString() {
        StringBuilder sb = new StringBuilder();
        sb.append(slimComponentTypeName + " " + name + "\n");
        sb.append("\tfeatures\n");
        for (data.slim.internal.Port port: ports) {
            sb.append("\t\t" + port.toSlimString() + "\n");
        }
        sb.append("end " + name + ";");
        return sb.toString();
    }

    @Override
    public String toSlimImplementationString() {
        StringBuilder sb = new StringBuilder();
        sb.append(slimComponentTypeName + " implementation " + getImplementationName() + "\n");
        if(subcomponents.size()>0) {
            sb.append("\tsubcomponents\n");
            for (Subcomponent subcomponent : subcomponents) {
                sb.append("\t\t" + subcomponent.toSlimString() + "\n");
            }
        }
        sb.append("end " + getImplementationName() + ";");
        return sb.toString();
    }
}
