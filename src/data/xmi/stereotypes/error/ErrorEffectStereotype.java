package data.xmi.stereotypes.error;

import data.xmi.stereotypes.Stereotype;
import org.w3c.dom.Element;

/**
 * Created by Joost on 15-Feb-17.
 */
public class ErrorEffectStereotype extends Stereotype {

    public static final String TAG_NAME = "SSTM.SSTM:ErrorEffect";
    public static final String ATTRIBUTE_BASE_PROPERTY = "base_Property";
    public static final String ATTRIBUTE_ERROR_MODEL = "errorModel";
    public static final String ATTRIBUTE_ERROR_STATE = "errorState";
    public static final String ATTRIBUTE_ERROR_TARGET = "errorTarget";
    public static final String ATTRIBUTE_ERROR_EFFECT = "errorEffect";

    String basePropertyId, errorModelId, errorStateId, errorTargetId, errorEffect;

    public ErrorEffectStereotype(Element stereotypeElement) {
        super(stereotypeElement.getAttribute(ATTRIBUTE_ID));
        this.basePropertyId = stereotypeElement.getAttribute(ATTRIBUTE_BASE_PROPERTY);
        if (stereotypeElement.hasAttribute(ATTRIBUTE_ERROR_MODEL)) this.errorModelId = stereotypeElement.getAttribute(ATTRIBUTE_ERROR_MODEL);
        if (stereotypeElement.hasAttribute(ATTRIBUTE_ERROR_STATE)) this.errorStateId = stereotypeElement.getAttribute(ATTRIBUTE_ERROR_STATE);
        if (stereotypeElement.hasAttribute(ATTRIBUTE_ERROR_TARGET)) this.errorTargetId = stereotypeElement.getAttribute(ATTRIBUTE_ERROR_TARGET);
        if (stereotypeElement.hasAttribute(ATTRIBUTE_ERROR_EFFECT)) this.errorEffect = stereotypeElement.getAttribute(ATTRIBUTE_ERROR_EFFECT);
    }

    public String getBasePropertyId() {
        return basePropertyId;
    }

    public String getErrorModelId() {
        return errorModelId;
    }

    public String getErrorStateId() {
        return errorStateId;
    }

    public String getErrorTargetId() {
        return errorTargetId;
    }

    public String getErrorEffect() {
        return errorEffect;
    }

    @Override
    public String toString() {
        return "Error Effect stereotype";
    }
}
