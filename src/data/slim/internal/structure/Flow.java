package data.slim.internal.structure;

import data.enums.PortDirection;
import data.slim.SlimObject;
import data.slim.internal.behavior.State;

import java.util.ArrayList;

/**
 * Created by Joost on 16-Feb-17.
 * General parent for SLIM ports
 */
public class Flow extends SlimObject{
    data.xmi.structure.Flow xmiFlow;

    String expression;
    ArrayList<String> flowModeList = new ArrayList<>();
    ArrayList<String> constraintElementIds = new ArrayList<>();
    Port targetPort;
    Subcomponent targetSubcomponent;
    ArrayList<State> inModes = new ArrayList<>();

    public Flow(data.xmi.structure.Flow xmiFlow) {
        this.xmiFlow = xmiFlow;
        this.constraintElementIds = xmiFlow.getConstraintElementIds();
        this.expression = xmiFlow.getExpression();
        this.flowModeList = xmiFlow.getFlowModeList();
    }

    public void finalizePortsAndSubcomponents(ArrayList<Port> ports, ArrayList<Subcomponent> subcomponents) {
        for (Subcomponent subcomponent: subcomponents) {
            if (constraintElementIds.contains(subcomponent.getSubcomponentId())) this.targetSubcomponent = subcomponent;
        }

        for (Port port: ports) {
            if (constraintElementIds.contains(port.getPortId())) this.targetPort = port;
        }
    }

    public void finalizeInModes(ArrayList<State> allStates) {
        for (String id: flowModeList) {
            for (State state: allStates) {
                if (state.getStateId().equals(id)) inModes.add(state);
            }
        }
    }

    @Override
    public String toSlimString() {
        StringBuilder sb = new StringBuilder();
        sb.append("flow ");
        sb.append(expression);
        sb.append(" -> ");
        if (targetSubcomponent != null) sb.append(targetSubcomponent.getName() + ".");
        if (targetPort != null) sb.append(targetPort.getName());
        //in Modes here
        if (inModes.size() > 0) {
            sb.append(" in modes (");
            for (State mode: inModes) {
                if (inModes.indexOf(mode) > 0 ) sb.append(", ");
                sb.append(mode.getName());
            }
            sb.append(")");
        }

        sb.append(";");

        return sb.toString();
    }
}
