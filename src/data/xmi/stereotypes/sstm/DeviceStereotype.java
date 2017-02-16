package data.xmi.stereotypes.sstm;

import data.xmi.stereotypes.sysml.BlockStereotype;
import org.w3c.dom.Element;

/**
 * Created by Joost on 15-Feb-17.
 */
public class DeviceStereotype extends SLIMComponentStereotype {
    public static final String TAG_NAME = "SSTM.SSTM:Device";

    public DeviceStereotype(String id, String baseClassId) {
        super(id, baseClassId);
    }

    public DeviceStereotype(Element deviceStereotypeElement) {
        super(deviceStereotypeElement);
    }

    @Override
    public String toString() {
        return "Device stereotype";
    }
}
