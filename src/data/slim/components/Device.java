package data.slim.components;

import data.xmi.structure.Class;

/**
 * Created by Joost on 16-Feb-17.
 * Device component as java object
 */
public class Device extends Component{

    public Device(Class baseXmiClass) {
        super(baseXmiClass);
        this.baseXmiClass = baseXmiClass;
        this.slimComponentTypeName = "device";
    }

    @Override
    public String toString() {
        return "Device";
    }


}
