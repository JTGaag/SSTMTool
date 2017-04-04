package data.xmi.stereotypes.sstm;

import org.w3c.dom.Element;

/**
 * Created by Joost on 15-Feb-17.
 */
public class BusStereotype extends SLIMComponentStereotype {
    public static final String TAG_NAME = "SSTM.SSTM:Bus";

    public BusStereotype(String id, String baseClassId) {
        super(id, baseClassId);
    }

    public BusStereotype(Element deviceStereotypeElement) {
        super(deviceStereotypeElement);
    }

    @Override
    public String toString() {
        return "Bus stereotype";
    }
}
