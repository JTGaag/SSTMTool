package data.slim.internal.structure;

import data.slim.components.Component;
import data.xmi.structure.Class;

/**
 * Created by Joost on 16-Feb-17.
 * Clock component as java object
 */
public class Clock extends Component{

    public Clock(Class baseXmiClass) {
        super(baseXmiClass);
        this.baseXmiClass = baseXmiClass;
        this.slimComponentTypeName = "clock";
    }

    @Override
    public String toString() {
        return "Clock";
    }


}
