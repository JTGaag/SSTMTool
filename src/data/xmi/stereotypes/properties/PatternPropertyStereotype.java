package data.xmi.stereotypes.properties;

import data.xmi.stereotypes.Stereotype;
import org.w3c.dom.Element;

/**
 * Created by Joost on 04-Apr-17.
 */
public class PatternPropertyStereotype extends Stereotype {

    public static final String TAG_NAME = "SSTM.SSTM:PatternProperty";
    public static final String ATTRIBUTE_BASE_PROPERTY = "base_Property";

    String basePropertyId;

    public PatternPropertyStereotype(Element stereotypeElement) {
        super(stereotypeElement.getAttribute(ATTRIBUTE_ID));
        this.basePropertyId = stereotypeElement.getAttribute(ATTRIBUTE_BASE_PROPERTY);
    }

    public String getBasePropertyId() {
        return basePropertyId;
    }

    public String getPattern() {
        StringBuilder sb = new StringBuilder();
        sb.append("base expression <should not happen>");
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Pattern Property stereotype";
    }
}
