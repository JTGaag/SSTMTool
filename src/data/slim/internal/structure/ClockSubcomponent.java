package data.slim.internal.structure;

import data.slim.SlimObject;
import data.slim.components.Component;

/**
 * Created by Joost on 20-Feb-17.
 */
public class ClockSubcomponent extends SlimObject{
    Clock referenceClock;
    String name, clockId;

    public ClockSubcomponent(String name, Clock referenceClock, String propertyId) {
        this.name = name;
        this.referenceClock = referenceClock;
        this.clockId = propertyId;
    }

    public Component getReferenceClock() {
        return referenceClock;
    }

    public String getName() {
        return name;
    }

    public String getClockId() {
        return clockId;
    }

    @Override
    public String toSlimString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(": data clock");
        //Time Unit
        if (referenceClock.getTimeUnit() != null) {
            sb.append(" { TimeUnit => \"").append(referenceClock.getTimeUnit()).append("\"; }");
        }

        sb.append(";");
        return sb.toString();
    }
}
