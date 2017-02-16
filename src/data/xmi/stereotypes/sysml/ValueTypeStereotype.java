package data.xmi.stereotypes.sysml;

import data.xmi.stereotypes.Stereotype;
import org.w3c.dom.Element;

/**
 * Created by Joost on 15-Feb-17.
 */
public class ValueTypeStereotype extends Stereotype {
    public static final String TAG_NAME = "Blocks:ValueType";
    public static final String ATTRIBUTE_BASE_DATA_TYPE = "base_DataType";
    public static final String ATTRIBUTE_UNIT = "unit";

    String baseDataTypeId, unitId;

    public ValueTypeStereotype(String id, String baseDataTypeId, String unitId) {
        super(id);
        this.baseDataTypeId = baseDataTypeId;
        this.unitId = unitId;
    }

    public ValueTypeStereotype(Element valueTypeStereotypeElement) {
        super(valueTypeStereotypeElement.getAttribute(ATTRIBUTE_ID));
        this.baseDataTypeId = valueTypeStereotypeElement.getAttribute(ATTRIBUTE_BASE_DATA_TYPE);
        this.unitId = valueTypeStereotypeElement.getAttribute(ATTRIBUTE_UNIT);
    }

    public String getBaseDataTypeId() {
        return baseDataTypeId;
    }

    public String getUnitId() {
        return unitId;
    }

    @Override
    public String toString() {
        return "ValueType stereotype";
    }
}
