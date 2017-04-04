package data.slim.components;

import data.slim.SlimObject;
import data.slim.error.ErrorComponent;
import data.slim.error.ErrorEffect;
import data.slim.internal.behavior.Mode;
import data.slim.internal.behavior.State;
import data.slim.internal.behavior.Transition;
import data.slim.internal.structure.*;
import data.slim.internal.structure.Flow;
import data.slim.internal.structure.Port;
import data.slim.properties.PatternProperty;
import data.xmi.OwnedConnector;
import data.xmi.behavior.Region;
import data.xmi.behavior.StateMachine;
import data.xmi.structure.*;
import data.xmi.structure.Class;

import java.lang.*;
import java.lang.System;
import java.util.ArrayList;

/**
 * Created by Joost on 16-Feb-17.
 * Base class for SLIM components
 */
public class Component extends SlimObject{
    protected Class baseXmiClass;

    protected String name;
    protected String slimComponentTypeName = "unknown";

    boolean fdirComponent;

    ArrayList<DataPort> dataPorts = new ArrayList<>();
    ArrayList<EventPort> eventPorts = new ArrayList<>();
    ArrayList<Port> ports = new ArrayList<>();
    ArrayList<Flow> flows = new ArrayList<>();
    ArrayList<Connection> connections = new ArrayList<>();

    ArrayList<Subcomponent> subcomponents = new ArrayList<>();
    ArrayList<ClockSubcomponent> clockSubcomponents = new ArrayList<>();
    ArrayList<State> states = new ArrayList<>();
    ArrayList<Mode> modes = new ArrayList<>();

    ArrayList<Transition> transitions = new ArrayList<>();

    ArrayList<ErrorEffect> errorEffects = new ArrayList<>();
    ArrayList<PatternProperty> patternProperties = new ArrayList<>();

    /**
     * Constructor
     * @param baseXmiClass
     */
    public Component(Class baseXmiClass) {
        this.baseXmiClass = baseXmiClass;
        this.name = baseXmiClass.getName();
        this.fdirComponent = baseXmiClass.isFdirComponent();
        initPorts();
        initFlows();
        initConnections();
        initStates();
        initTransitions();
        initErrorEffects();
        initPatternProperties();
    }

    private void initErrorEffects() {
        for (data.xmi.error.ErrorEffect errorEffect: baseXmiClass.getErrorEffects()) {
            errorEffects.add(new ErrorEffect(errorEffect));
        }
    }

    private void initPatternProperties() {
        for (data.xmi.properties.PatternProperty patternProperty: baseXmiClass.getPatternProperties()) {
            patternProperties.add(new PatternProperty(patternProperty));
        }
    }


    public void createSubcomponents(ArrayList<Component> components) {
        for (Property property: baseXmiClass.getProperties()) {
            for (Component component: components) {
                if (property.getTypeId().equals(component.getBaseXmiClass().getId())) {
                    subcomponents.add(new Subcomponent(property.getName(), component, property.getId()));
                }
            }
        }

    }

    public void createClockSubcomponents(ArrayList<Clock> clocks) {
        for (Property property: baseXmiClass.getProperties()) {
            for (Clock clock: clocks) {
                if (property.getTypeId().equals(clock.getBaseXmiClass().getId())) {
                    clockSubcomponents.add(new ClockSubcomponent(property.getName(), clock, property.getId()));
                }
            }
        }
    }

    public void finalizeErrorEffects(ArrayList<ErrorComponent> errorModels) {
        for (ErrorEffect errorEffect: errorEffects) {
            errorEffect.finalize(errorModels, ports);
        }
    }

    private void initPorts() {
        ports.clear();
        dataPorts.clear();
        eventPorts.clear();
        for (data.xmi.structure.Port port: baseXmiClass.getPorts()) {
            if (port.getPortType().equals(data.xmi.structure.Port.PortType.EVENT)) {
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

    private void initFlows() {
        flows.clear();
        for (data.xmi.structure.Flow flow: baseXmiClass.getFlows()) {
            flows.add(new Flow(flow));
        }
    }

    private void initConnections(){
        connections.clear();
        for (OwnedConnector ownedConnector: baseXmiClass.getConnectors()) {
            connections.add(new Connection(ownedConnector));
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

    private void initTransitions() {
        transitions.clear();
        StateMachine stateMachine = baseXmiClass.getStateMachine();
        if (stateMachine != null) {
            Region region = stateMachine.getRegion();
            if (region != null) {
                for (data.xmi.behavior.Transition transition : region.getTransitions()) {
                    //Get initial transition out of SLIM component
                    if (transition.getName().equals("initial")) continue;

                    transitions.add(new Transition(transition));
                }
            }
        }
    }

    public void finalizeTransitions() {
        for(Transition transition: transitions) {
            //Finalize source and target states
            transition.finalizeTargetAndSourceStates(states);

            //first finalize with own ports
            for(Port port: ports) {
                transition.finalizeTrigger(port, null);
            }

            //Second finalize with subcomponents
            for (Subcomponent subcomponent : subcomponents) {
                for (Port port: subcomponent.getReferenceComponent().getPorts()) {
                    transition.finalizeTrigger(port, subcomponent);
                }
            }
        }
    }

    public void finalizeConnections() {
        ArrayList<Port> allPorts = new ArrayList<>();
        allPorts.addAll(ports);
        for (Subcomponent subcomponent: subcomponents) {
            allPorts.addAll(subcomponent.getReferenceComponent().getPorts());
        }

        for (Connection connection: connections) {
            connection.finalizePortsAndSubcomponents(allPorts, subcomponents);
            connection.finalizeSubcomponentsForAccess(subcomponents);
        }

        for (Connection connection: connections) {
            connection.finalizeInModes(states);
        }
    }

    public void finalizeFlows() {
        ArrayList<Port> allPorts = new ArrayList<>();
        allPorts.addAll(ports);
        for (Subcomponent subcomponent: subcomponents) {
            allPorts.addAll(subcomponent.getReferenceComponent().getPorts());
        }

        for (Flow flow: flows) {
            flow.finalizePortsAndSubcomponents(allPorts, subcomponents);
        }

        for (Flow flow: flows) {
            flow.finalizeInModes(states);
        }
    }

    public ArrayList<DataPort> getDataPorts() {
        return dataPorts;
    }

    public ArrayList<EventPort> getEventPorts() {
        return eventPorts;
    }

    public ArrayList<data.slim.internal.structure.Port> getPorts() {
        return ports;
    }

    public String getName() {
        return name;
    }

    public boolean isAtomic() {
        return (subcomponents.size() == 0);
    }

    public boolean hasProperties() {
        return (fdirComponent || hasAccessProperties() || hasErrorProperties() || hasPatternProperties());
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

    private boolean hasPortConnections() {
        for (Connection connection: connections) {
            if (connection.isPortConnection()) return true;
        }
        return false;
    }

    private boolean hasAccessProperties() {
        for (Connection connection: connections) {
            if (connection.isAccessConnection()) return true;
        }
        return false;
    }

    private boolean hasPatternProperties() {
        return patternProperties.size() > 0;
    }

    private boolean hasErrorProperties() {
        return (errorEffects.size()>0);
    }

    private ArrayList<ArrayList<Connection>> getSortedAccessList(){
        ArrayList<ArrayList<Connection>> sortedConnections = new ArrayList<>();

        for (Connection connection: connections) {
            if (connection.isAccessConnection()) {
                boolean inList = false;
                for (ArrayList<Connection> connections: sortedConnections) {
                    if (connections.size() > 0 && connections.get(0).getSourceSubcomponentName().equals(connection.getSourceSubcomponentName())) {
                        connections.add(connection);
                        inList = true;
                    }
                }

                if (!inList) {
                    ArrayList<Connection> newConnections = new ArrayList<>();
                    newConnections.add(connection);
                    sortedConnections.add(newConnections);
                }
            }
        }

        return sortedConnections;
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
            for (data.slim.internal.structure.Port port : ports) {
                sb.append("\t\t" + port.toSlimString() + "\n");
            }
        }

        if (fdirComponent) sb.append("\tproperties\n\t\tFDIR => true;\n");

        //End tag
        sb.append("end ").append(name).append(";");
        return sb.toString();
    }

    @Override
    public String toSlimImplementationString() {
        StringBuilder sb = new StringBuilder();
        //Begin tag
        sb.append(slimComponentTypeName).append(" implementation ").append(getImplementationName()).append("\n");

        //Sub components
        if (subcomponents.size() > 0 || clockSubcomponents.size() > 0) {
            sb.append("\tsubcomponents\n");
        }

        if (clockSubcomponents.size() > 0 ){
            for (ClockSubcomponent clockSubcomponent: clockSubcomponents) {
                sb.append("\t\t").append(clockSubcomponent.toSlimString()).append("\n");
            }
        }

        if (subcomponents.size() > 0) {
            for (Subcomponent subcomponent : subcomponents) {
                sb.append("\t\t").append(subcomponent.toSlimString()).append("\n");
            }
        }

        //Connection and Flow header
        if (hasPortConnections() || flows.size() > 0) {
            sb.append("\tconnections\n");
        }
        //Connections
        if (hasPortConnections()) {
            for (Connection connection: connections) {
                if (connection.isPortConnection()) sb.append("\t\t").append(connection.toSlimString()).append("\n");
            }
        }
        //Flows
        if (flows.size() > 0) {
            for (Flow flow: flows) {
                sb.append("\t\t").append(flow.toSlimString()).append("\n");
            }
        }


        //States and Modes
        if (states.size() > 0) {
            if (isAtomic()) {
                sb.append("\tstates\n");
                for (State state: states) {
                    sb.append("\t\t").append(state.toSlimString()).append("\n");
                }
            } else {
                sb.append("\tmodes\n");
                for (State state: states) {
                    Mode mode = new Mode(state);
                    sb.append("\t\t").append(mode.toSlimString()).append("\n");
                }
            }


        }

        //Transitions
        if (transitions.size() > 0) {
            sb.append("\ttransitions\n");
            for (Transition transition: transitions) {
                sb.append("\t\t").append(transition.toSlimString()).append("\n");
            }
        }

        //Properties
        if (hasProperties()) sb.append("\tproperties\n");

        if (fdirComponent) sb.append("\t\tFDIR => true;\n");

        if (hasAccessProperties()) {
            for (ArrayList<Connection> connections: getSortedAccessList()) {
                if (connections.size() > 0) {
                    sb.append("\t\tAccesses => (reference(").append(connections.get(0).getSourceSubcomponentName()).append(")) applies to ");
                    for (Connection connection: connections) {
                        if (connections.indexOf(connection) != 0) sb.append(", ");
                        sb.append(connection.getTargetSubcomponentName());
                    }
                    sb.append(";\n");
                }
            }
        }

        if (hasErrorProperties()) {
            //First error effect error model is error model
            //TODO: Improve if more error models can be added to a single component
            sb.append("\t\tErrorModel => classifier(").append(errorEffects.get(0).getErrorModelName()).append(");\n");
            sb.append("\t\tFaultEffects => (");
            for (ErrorEffect errorEffect: errorEffects) {
                sb.append(errorEffect.toSlimString());
                if (errorEffects.indexOf(errorEffect) != (errorEffects.size()-1)) sb.append(",\n");
            }
            sb.append(");\n");
        }

        //Pattern properties
        if (hasPatternProperties()) {
            sb.append("\t\tPatterns => (\n");

            for (PatternProperty patternProperty: patternProperties) {
                sb.append("\t\t\t").append(patternProperty.toSlimString());
                if (patternProperties.indexOf(patternProperty) != (patternProperties.size()-1)) {
                    sb.append(",");
                }
                sb.append("\n");
            }

            sb.append("\t\t);\n");
        }

        //End tag
        sb.append("end ").append(getImplementationName()).append(";");
        return sb.toString();
    }
}
