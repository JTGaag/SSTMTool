package data.slim.internal.structure;

import data.slim.components.Component;
import data.xmi.structure.Class;

/**
 * Created by Joost on 16-Feb-17.
 * Clock component as java object
 */
public class Clock extends Component{

    String timeUnit;

    public Clock(Class baseXmiClass, String timeUnit) {
        super(baseXmiClass);
        this.baseXmiClass = baseXmiClass;
        this.slimComponentTypeName = "clock";
        this.timeUnit = timeUnit;
    }

    public String getTimeUnit() {
        return timeUnit;
    }

    @Override
    public String toString() {
        return "Clock";
    }


}
