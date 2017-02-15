package data.stereotypes.sysml;

import data.stereotypes.Stereotype;

/**
 * Created by Joost on 15-Feb-17.
 */
public class BlockStereotype extends Stereotype {
    public static final String TAG_NAME = "Blocks:Block";
    public static final String ATTRIBUTE_BASE_CLASS = "base_Class";

    String baseClassId;

    public BlockStereotype(String id, String baseClassId) {
        super(id);
        this.baseClassId = baseClassId;
    }

    public String getBaseClassId() {
        return baseClassId;
    }

    @Override
    public String toString() {
        return "Block stereotype";
    }
}
