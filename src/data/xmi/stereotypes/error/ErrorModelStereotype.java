package data.xmi.stereotypes.error;

import data.xmi.stereotypes.sstm.SLIMComponentStereotype;
import data.xmi.stereotypes.sysml.BlockStereotype;
import org.w3c.dom.Element;

/**
 * Created by Joost on 15-Feb-17.
 */
public class ErrorModelStereotype extends BlockStereotype {
    public static final String TAG_NAME = "SSTM.SSTM:ErrorModel";

    public ErrorModelStereotype(String id, String baseClassId) {
        super(id, baseClassId);
    }

    public ErrorModelStereotype(Element deviceStereotypeElement) {
        super(deviceStereotypeElement);
    }

    @Override
    public String toString() {
        return "Error model stereotype";
    }
}
