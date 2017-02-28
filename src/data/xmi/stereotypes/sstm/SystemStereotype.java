package data.xmi.stereotypes.sstm;

import org.w3c.dom.Element;

/**
 * Created by Joost on 15-Feb-17.
 */
public class SystemStereotype extends SLIMComponentStereotype {
    public static final String TAG_NAME = "SSTM.SSTM:System";

    public SystemStereotype(String id, String baseClassId) {
        super(id, baseClassId);
    }

    public SystemStereotype(Element deviceStereotypeElement) {
        super(deviceStereotypeElement);
    }

    @Override
    public String toString() {
        return "System stereotype";
    }
}
