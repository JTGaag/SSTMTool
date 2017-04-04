package data.xmi.stereotypes.sstm;

import org.w3c.dom.Element;

/**
 * Created by Joost on 15-Feb-17.
 */
public class MemoryStereotype extends SLIMComponentStereotype {
    public static final String TAG_NAME = "SSTM.SSTM:Memory";

    public MemoryStereotype(String id, String baseClassId) {
        super(id, baseClassId);
    }

    public MemoryStereotype(Element deviceStereotypeElement) {
        super(deviceStereotypeElement);
    }

    @Override
    public String toString() {
        return "Memory stereotype";
    }
}
