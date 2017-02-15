package data.stereotypes.sstm;

import data.stereotypes.sysml.BlockStereotype;

/**
 * Created by Joost on 15-Feb-17.
 */
public class DeviceStereotype extends BlockStereotype {
    public static final String TAG_NAME = "SSTM.SSTM:Device";

    public DeviceStereotype(String id, String baseClassId) {
        super(id, baseClassId);
    }

    @Override
    public String toString() {
        return "Device stereotype";
    }
}
