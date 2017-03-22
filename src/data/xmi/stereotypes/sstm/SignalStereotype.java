package data.xmi.stereotypes.sstm;

import data.xmi.stereotypes.sysml.BlockStereotype;
import org.w3c.dom.Element;

/**
 * Created by Joost on 15-Feb-17.
 */
public class SignalStereotype extends BlockStereotype {
    public static final String TAG_NAME = "SSTM.SSTM:Signal";

    public SignalStereotype(String id, String baseClassId) {
        super(id, baseClassId);
    }

    public SignalStereotype(Element signalStereotypeElement) {
        super(signalStereotypeElement);
    }

    @Override
    public String toString() {
        return "Signal stereotype";
    }
}
