package data.slim.components;

import data.xmi.structure.Class;

/**
 * Created by Joost on 16-Feb-17.
 * Device component as java object
 */
public class Bus extends Component{

    public Bus(Class baseXmiClass) {
        super(baseXmiClass);
        this.baseXmiClass = baseXmiClass;
        this.slimComponentTypeName = "bus";
    }

    @Override
    public String toString() {
        return "Bus";
    }


}
