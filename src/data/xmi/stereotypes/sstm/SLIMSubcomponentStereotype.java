package data.xmi.stereotypes.sstm;

import data.xmi.stereotypes.sysml.BlockStereotype;
import org.w3c.dom.Element;

/**
 * Created by Joost on 08-Mar-17.
 */
public class SLIMSubcomponentStereotype extends BlockStereotype {
    public static final String TAG_NAME = "SSTM.SSTM:SlimSubcomponent";

    public SLIMSubcomponentStereotype(String id, String baseClassId) {
        super(id, baseClassId);
    }

    public SLIMSubcomponentStereotype(Element blockStereotypeElement) {
        super(blockStereotypeElement);
    }

    @Override
    public String toString() {
        return "SLIMSubcomponent stereotype";
    }
}
