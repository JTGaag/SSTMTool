package data.slim.error;

import data.enums.PortDirection;
import data.slim.SlimObject;
import data.slim.datatypes.Real;

/**
 * Created by Joost on 16-Feb-17.
 * General parent for SLIM ports
 */
public class ErrorEvent extends SlimObject{
    data.xmi.structure.Port xmiPort;

    String name, timeUnit, probability, errorEventId;

    public ErrorEvent(data.xmi.structure.Port xmiPort) {
        this.xmiPort = xmiPort;
        this.name = xmiPort.getName();
        this.errorEventId = xmiPort.getId();
//        this.probability = Double.toString(Double.parseDouble(xmiPort.getErrorProbability())); //Do not use scientific notation
        this.probability = xmiPort.getErrorProbability();
        this.timeUnit = xmiPort.getErrorTimeUnit();
    }

    public String getName() {
        return name;
    }

    public String getErrorEventId() {
        return errorEventId;
    }

    @Override
    public String toSlimString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append(": error event");
        if (probability != null) {
            sb.append(" occurrence poisson ");
            sb.append(probability);
        }
        if (probability != null && timeUnit != null) {
            sb.append(" per ");
            sb.append(timeUnit);
        }
        sb.append(";");
        return sb.toString();
    }
}
