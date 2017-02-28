package data.xmi.stereotypes.sstm;

import org.w3c.dom.Element;

/**
 * Created by Joost on 15-Feb-17.
 */
public class ProcessorStereotype extends SLIMComponentStereotype {
    public static final String TAG_NAME = "SSTM.SSTM:Processor";

    public ProcessorStereotype(String id, String baseClassId) {
        super(id, baseClassId);
    }

    public ProcessorStereotype(Element deviceStereotypeElement) {
        super(deviceStereotypeElement);
    }

    @Override
    public String toString() {
        return "Process stereotype";
    }
}
