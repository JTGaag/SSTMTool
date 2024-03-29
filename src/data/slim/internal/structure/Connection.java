package data.slim.internal.structure;

import data.slim.SlimObject;
import data.slim.internal.behavior.State;
import data.xmi.OwnedConnector;

import java.util.ArrayList;

/**
 * Created by Joost on 16-Feb-17.
 * General parent for SLIM ports
 */
public class Connection extends SlimObject{

    String name, sourceElementId, targetElementId, sourceSubcomponentId, targetSubcomponentId;
    Port sourcePort, targetPort;
    Subcomponent sourceSubcomponent, targetSubcomponent;

    ArrayList<String> modesList = new ArrayList<>();
    ArrayList<State> inModes = new ArrayList<>();

    public Connection(OwnedConnector xmiConnector) {
        this.name = xmiConnector.getName();
        connectorEndsIds(xmiConnector);
        modesList = xmiConnector.getConnectorModeList();
    }

    private void connectorEndsIds(OwnedConnector xmiConnector) {
        if (xmiConnector.getConnectorEnds().size()<2) return;
        sourceElementId = xmiConnector.getConnectorEnds().get(0).getRoleId();
        targetElementId = xmiConnector.getConnectorEnds().get(1).getRoleId();
        if (xmiConnector.getConnectorEnds().get(0).getPartId() != null) sourceSubcomponentId = xmiConnector.getConnectorEnds().get(0).getPartId();
        if (xmiConnector.getConnectorEnds().get(1).getPartId() != null) targetSubcomponentId = xmiConnector.getConnectorEnds().get(1).getPartId();
    }

    public void finalizePortsAndSubcomponents(ArrayList<Port> ports, ArrayList<Subcomponent> subcomponents) {
        for (Subcomponent subcomponent: subcomponents) {
            if (subcomponent.getSubcomponentId().equals(sourceSubcomponentId)) this.sourceSubcomponent = subcomponent;
            if (subcomponent.getSubcomponentId().equals(targetSubcomponentId)) this.targetSubcomponent = subcomponent;
        }

        for (Port port: ports) {
            if (port.getPortId().equals(sourceElementId)) this.sourcePort = port;
            if (port.getPortId().equals(targetElementId)) this.targetPort = port;
        }
    }

    public void finalizeSubcomponentsForAccess(ArrayList<Subcomponent> subcomponents) {
        for (Subcomponent subcomponent: subcomponents) {
            if (subcomponent.getSubcomponentId().equals(sourceElementId)) this.sourceSubcomponent = subcomponent;
            if (subcomponent.getSubcomponentId().equals(targetElementId)) this.targetSubcomponent = subcomponent;
        }
    }

    public void finalizeInModes(ArrayList<State> allStates) {
        for (String id: modesList) {
            for (State state: allStates) {
                if (state.getStateId().equals(id)) inModes.add(state);
            }
        }
    }

    public String getName() {
        return name;
    }

    public boolean isPortConnection() {
        return (sourcePort != null && targetPort != null);
    }

    public boolean isAccessConnection() {
        return (!isPortConnection() && sourceSubcomponent != null && targetSubcomponent != null);
    }

    public String getSourceSubcomponentName() {
        return sourceSubcomponent.getName();
    }

    public String getTargetSubcomponentName() {
        return targetSubcomponent.getName();
    }

    @Override
    public String toSlimString() {
        if (isPortConnection()) {
            StringBuilder sb = new StringBuilder();
            if (sourcePort.getClass().equals(targetPort.getClass())) { //Ports need to be the same
                sb.append("port ");
                if (sourceSubcomponent != null) sb.append(sourceSubcomponent.getName() + ".");
                sb.append(sourcePort.getName());

                sb.append(" -> ");

                if (targetSubcomponent != null) sb.append(targetSubcomponent.getName() + ".");
                sb.append(targetPort.getName());

                //DONE: do the "in modes" thingy
                if (inModes.size() > 0) {
                    sb.append(" in modes (");
                    for (State mode : inModes) {
                        if (inModes.indexOf(mode) > 0) sb.append(", ");
                        sb.append(mode.getName());
                    }
                    sb.append(")");
                }

                sb.append(";");
            } else {
                sb.append("[ERROR] Ports are not the same ERROR");
            }

            return sb.toString();
        } else if (isAccessConnection()) {
            StringBuilder sb = new StringBuilder();

            return sb.toString();
        } else {
            return "";
        }
    }
}
