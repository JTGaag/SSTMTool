package data.slim.error;

import data.slim.SlimObject;
import data.slim.internal.behavior.State;
import data.slim.internal.structure.Port;

import java.util.ArrayList;

/**
 * Created by Joost on 27-Mar-17.
 */
public class ErrorEffect extends SlimObject {
    String errorModelId, errorStateId, errorTargetId, errorEffect;
    ErrorComponent errorModel;
    State errorState;
    Port targetPort;


    public ErrorEffect(data.xmi.error.ErrorEffect xmiErrorEffect) {
        this.errorModelId = xmiErrorEffect.getErrorModelId();
        this.errorStateId = xmiErrorEffect.getErrorStateId();
        this.errorTargetId = xmiErrorEffect.getErrorTargetId();
        this.errorEffect = xmiErrorEffect.getErrorEffect();
    }

    public void finalize(ArrayList<ErrorComponent> errorModels, ArrayList<Port> ports) {
        System.out.println("Finaize Error Effect");
        for (ErrorComponent errorModel: errorModels) {
            if (errorModel.getErrorStereotypeId().equals(this.errorModelId)) {
                this.errorModel = errorModel;
                for (State state: errorModel.getStates()) {
                    if (state.getErrorStereotypeId().equals(errorStateId)) {
                        this.errorState = state;
                    }
                }
            }
        }

        for (Port port: ports) {
            if (port.getPortId().equals(errorTargetId)) {
                this.targetPort = port;
            }
        }
    }




    public String getErrorModelName() {
        if (errorModel == null) return null;
        return errorModel.getImplementationName();
    }


    @Override
    public String toSlimString() {
        if (errorModel == null || errorState == null || targetPort == null || errorEffect == null) return "<Error> Not enough information <Error>";
        StringBuilder sb = new StringBuilder();

        sb.append("[");

        sb.append("State=>\"").append(errorState.getName()).append("\"; ");
        sb.append("Target=>reference(").append(targetPort.getName()).append("); ");
        sb.append("Effect=>\"").append(errorEffect).append("\";");

        sb.append("]");

        return sb.toString();
    }
}
