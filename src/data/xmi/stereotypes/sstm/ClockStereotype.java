package data.xmi.stereotypes.sstm;

import org.w3c.dom.Element;

/**
 * Created by Joost on 15-Feb-17.
 */
public class ClockStereotype extends SLIMComponentStereotype{
    public static final String TAG_NAME = "SSTM.SSTM:Clock";
    public static final String ATTRIBUTE_TIME_UNIT = "TimeUnit";

    String timeUnit;

    public ClockStereotype(String id, String baseClassId) {
        super(id, baseClassId);
    }

    public ClockStereotype(Element clockStereotypeElement) {
        super(clockStereotypeElement);
        if (clockStereotypeElement.hasAttribute(ATTRIBUTE_TIME_UNIT)) this.timeUnit = clockStereotypeElement.getAttribute(ATTRIBUTE_TIME_UNIT);
    }

    public String getTimeUnit() {
        return timeUnit;
    }

    @Override
    public String toString() {
        return "Clock stereotype";
    }
}
