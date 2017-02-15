package data.stereotypes.oosem;

import data.stereotypes.sysml.BlockStereotype;

/**
 * Created by Joost on 15-Feb-17.
 */
public class SystemOfInterestStereotype extends BlockStereotype {
    public static final String TAG_NAME = "SSTM.OOSEM:SystemOfInterest";

    public SystemOfInterestStereotype(String id, String baseClassId) {
        super(id, baseClassId);
    }

    @Override
    public String toString() {
        return "SystemOfInterest stereotype";
    }
}
