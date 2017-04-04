package data.slim.error;

import data.slim.SlimObject;
import data.slim.internal.behavior.Mode;
import data.slim.internal.behavior.State;
import data.slim.internal.behavior.Transition;
import data.slim.internal.structure.*;
import data.xmi.behavior.Region;
import data.xmi.behavior.StateMachine;
import data.xmi.structure.Class;
import data.xmi.structure.Property;

import java.util.ArrayList;

/**
 * Created by Joost on 24-Mar-17.
 */
public class ErrorComponent extends SlimObject {

    private Class baseXmiClass;
    private String name, id, errorStereotypeId;

    private ArrayList<ErrorEvent> errorEvents = new ArrayList<>();
    private ArrayList<State> states = new ArrayList<>();
    private ArrayList<Transition> transitions = new ArrayList<>();
    ArrayList<ClockSubcomponent> clockSubcomponents = new ArrayList<>();

    public ErrorComponent(Class baseXmiClass) {
        this.baseXmiClass = baseXmiClass;
        this.name = baseXmiClass.getName();
        this.id = baseXmiClass.getId();
        this.errorStereotypeId = baseXmiClass.getErrorStereotypeId();
        initErrorEvents();
        initStates();
        initTransitions();

        finalizeTransitions();
    }

    private void initErrorEvents() {
        errorEvents.clear();
        for (data.xmi.structure.Port port: baseXmiClass.getPorts()) {
            if (port.isErrorEvent()) {
                errorEvents.add(new ErrorEvent(port));
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
            for(ErrorEvent errorEvent: errorEvents) {
                transition.finalizeErrorTrigger(errorEvent);
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

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getErrorStereotypeId() {
        return errorStereotypeId;
    }

    public ArrayList<State> getStates() {
        return states;
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

    @Override
    public String toSlimTypeString() {
        StringBuilder sb = new StringBuilder();
        //Begin
        sb.append("error model ");
        sb.append(getName());
        sb.append("\n");

        //Features

        //End
        sb.append("end ");
        sb.append(getName());
        sb.append(";");
        return sb.toString();
    }

    @Override
    public String toSlimImplementationString() {
        StringBuilder sb = new StringBuilder();
        //Begin
        sb.append("error model implementation ").append(getImplementationName()).append("\n");

        //Events
        if (errorEvents.size() > 0) {
            sb.append("\tevents\n");
            for (ErrorEvent errorEvent: errorEvents) {
                sb.append("\t\t").append(errorEvent.toSlimString()).append("\n");
            }
        }

        //Clocks
        if (clockSubcomponents.size() > 0) {
            sb.append("\tclocks\n");
            for (ClockSubcomponent clockSubcomponent: clockSubcomponents) {
                sb.append("\t\t").append(clockSubcomponent.toSlimString()).append("\n");
            }
        }

        //States
        if (states.size() > 0) {
            sb.append("\tstates\n");
            for (State state: states) {
                sb.append("\t\t").append(state.toSlimString()).append("\n");
            }
        }

        //Transitions
        if (transitions.size() > 0) {
            sb.append("\ttransitions\n");
            for (Transition transition: transitions) {
                sb.append("\t\t").append(transition.toSlimString()).append("\n");
            }
        }


        //End
        sb.append("end ").append(getImplementationName()).append(";");
        return sb.toString();
    }
}
