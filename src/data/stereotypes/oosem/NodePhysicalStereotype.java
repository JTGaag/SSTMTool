package data.stereotypes.oosem;

import data.stereotypes.sysml.BlockStereotype;

/**
 * Created by Joost on 15-Feb-17.
 */
public class NodePhysicalStereotype extends BlockStereotype {
    public static final String TAG_NAME = "SSTM.OOSEM:NodePhysical";

    public NodePhysicalStereotype(String id, String baseClassId) {
        super(id, baseClassId);
    }

    @Override
    public String toString() {
        return "NodePhysical stereotype";
    }
}
