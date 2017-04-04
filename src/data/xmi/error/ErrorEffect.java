package data.xmi.error;

import data.xmi.stereotypes.error.ErrorEffectStereotype;

/**
 * Created by Joost on 27-Mar-17.
 */
public class ErrorEffect {
    String errorModelId, errorStateId, errorTargetId, errorEffect;

    public ErrorEffect(ErrorEffectStereotype errorEffectStereotype) {
        this.errorModelId = errorEffectStereotype.getErrorModelId();
        this.errorStateId = errorEffectStereotype.getErrorStateId();
        this.errorTargetId = errorEffectStereotype.getErrorTargetId();
        this.errorEffect = errorEffectStereotype.getErrorEffect();
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
}
