package data.slim.components;

import data.slim.internal.Port;
import data.xmi.uml.Class;

/**
 * Created by Joost on 16-Feb-17.
 * Device component as java object
 */
public class Device extends Component{

    public Device(Class baseXmiClass) {
        super(baseXmiClass);
        this.baseXmiClass = baseXmiClass;
    }

    @Override
    public String toString() {
        return "Device";
    }

    @Override
    public String toSlimString() {
        StringBuilder sb = new StringBuilder();
        sb.append("device " + name + "\n");
        sb.append("\tfeatures\n");
        for (Port port: ports) {
            sb.append("\t\t" + port.toSlimString() + "\n");
        }
        sb.append("end " + name + ";");
        return sb.toString();
    }
}
