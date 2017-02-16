package data.xmi.stereotypes.sstm;

import data.xmi.stereotypes.sysml.BlockStereotype;
import org.w3c.dom.Element;

/**
 * Created by Joost on 16-Feb-17.
 */
public class SLIMComponentStereotype extends BlockStereotype {
    public static final String TAG_NAME = "SSTM.SSTM:SlimComponent";

    public SLIMComponentStereotype(String id, String baseClassId) {
        super(id, baseClassId);
    }

    public SLIMComponentStereotype(Element blockStereotypeElement) {
        super(blockStereotypeElement);
    }

    @Override
    public String toString() {
        return "SLIMComponent stereotype";
    }
}
