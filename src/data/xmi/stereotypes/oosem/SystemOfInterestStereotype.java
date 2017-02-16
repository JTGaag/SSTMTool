package data.xmi.stereotypes.oosem;

import data.xmi.stereotypes.sysml.BlockStereotype;
import org.w3c.dom.Element;

/**
 * Created by Joost on 15-Feb-17.
 */
public class SystemOfInterestStereotype extends BlockStereotype {
    public static final String TAG_NAME = "SSTM.OOSEM:SystemOfInterest";

    public SystemOfInterestStereotype(String id, String baseClassId) {
        super(id, baseClassId);
    }

    public SystemOfInterestStereotype(Element systemOfInterestStereotypeElement) {
        super(systemOfInterestStereotypeElement);
    }

    @Override
    public String toString() {
        return "SystemOfInterest stereotype";
    }
}
