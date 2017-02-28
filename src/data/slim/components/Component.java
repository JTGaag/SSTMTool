package data.slim.components;

import data.slim.SlimObject;
import data.slim.internal.*;
import data.xmi.behavior.Region;
import data.xmi.behavior.StateMachine;
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
    ArrayList<State> states = new ArrayList<>();
    ArrayList<Mode> modes = new ArrayList<>();

    /**
     * Constructor
     * @param baseXmiClass
     */
    public Component(Class baseXmiClass) {
        this.baseXmiClass = baseXmiClass;
        this.name = baseXmiClass.getName();
        initPorts();
        initStates();
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

    private void initStates() {
        states.clear();
        StateMachine stateMachine = baseXmiClass.getStateMachine();
        if (stateMachine != null) {
            Region region = stateMachine.getRegion();
            if (region != null) {
                for (data.xmi.behavior.State state: region.getStates()) {
                    states.add(new State(state));
                }
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

    public boolean isAtomic() {
        return (subcomponents.size() == 0);
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

        //Begin tag
        sb.append(slimComponentTypeName + " " + name + "\n");

        //Ports
        if (ports.size() > 0) {
            sb.append("\tfeatures\n");
            for (data.slim.internal.Port port : ports) {
                sb.append("\t\t" + port.toSlimString() + "\n");
            }
        }

        //End tag
        sb.append("end " + name + ";");
        return sb.toString();
    }

    @Override
    public String toSlimImplementationString() {
        StringBuilder sb = new StringBuilder();
        //Begin tag
        sb.append(slimComponentTypeName + " implementation " + getImplementationName() + "\n");

        //Sub components
        if (subcomponents.size()>0) {
            sb.append("\tsubcomponents\n");
            for (Subcomponent subcomponent : subcomponents) {
                sb.append("\t\t" + subcomponent.toSlimString() + "\n");
            }
        }

        //States and Modes
        if (states.size() > 0) {
            if (isAtomic()) {
                sb.append("\tstates\n");
                for (State state: states) {
                    sb.append("\t\t" + state.toSlimString() + "\n");
                }
            } else {
                sb.append("\tmodes\n");
                for (State state: states) {
                    Mode mode = new Mode(state);
                    sb.append("\t\t" + mode.toSlimString() + "\n");
                }
            }


        }

        //End tag
        sb.append("end " + getImplementationName() + ";");
        return sb.toString();
    }
}
