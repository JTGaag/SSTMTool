package data.xmi.stereotypes.error;

import data.enums.PortDirection;
import data.xmi.stereotypes.Stereotype;
import org.w3c.dom.Element;

/**
 * Created by Joost on 15-Feb-17.
 */
public class ErrorEventStereotype extends Stereotype {

    public static final String TAG_NAME = "SSTM.SSTM:ErrorEvent";
    public static final String ATTRIBUTE_BASE_PORT = "base_Port";
    public static final String ATTRIBUTE_PROBABILITY = "probability";
    public static final String ATTRIBUTE_TIME_UNIT = "timeUnit";

    String basePortId, probability, timeUnit;

    public ErrorEventStereotype(Element stereotypeElement) {
        super(stereotypeElement.getAttribute(ATTRIBUTE_ID));
        this.basePortId = stereotypeElement.getAttribute(ATTRIBUTE_BASE_PORT);
        if (stereotypeElement.hasAttribute(ATTRIBUTE_PROBABILITY)) this.probability = stereotypeElement.getAttribute(ATTRIBUTE_PROBABILITY);
        if (stereotypeElement.hasAttribute(ATTRIBUTE_TIME_UNIT)) this.timeUnit = stereotypeElement.getAttribute(ATTRIBUTE_TIME_UNIT);
    }

    public String getBasePortId() {
        return basePortId;
    }

    public String getProbability() {
        return probability;
    }

    public String getTimeUnit() {
        return timeUnit;
    }

    @Override
    public String toString() {
        return "Error Event stereotype";
    }
}
