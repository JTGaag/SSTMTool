package data.slim.internal;


import data.slim.datatypes.DataType;

/**
 * Created by Joost on 16-Feb-17.
 * SLIM dataport java object
 *
 * TODO: add default options and FDIR options
 */
public class DataPort extends Port {
    DataType dataType;

    public DataPort(data.xmi.uml.Port xmiPort) {
        super(xmiPort);
        this.dataType = xmiPort.getDataType();
    }

    @Override
    public String toSlimString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append(": ");
        sb.append(direction);
        sb.append(" data port ");
        sb.append(dataType.toSlimString());
        sb.append(";");
        return sb.toString();
    }
}
