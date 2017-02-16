package data.xmi.stereotypes.oosem;

import data.xmi.stereotypes.sysml.BlockStereotype;
import org.w3c.dom.Element;

/**
 * Created by Joost on 15-Feb-17.
 */
public class NodePhysicalStereotype extends BlockStereotype {
    public static final String TAG_NAME = "SSTM.OOSEM:NodePhysical";

    public NodePhysicalStereotype(String id, String baseClassId) {
        super(id, baseClassId);
    }

    public NodePhysicalStereotype(Element nodePhysicalElement) {
        super(nodePhysicalElement);
    }

    @Override
    public String toString() {
        return "NodePhysical stereotype";
    }
}
