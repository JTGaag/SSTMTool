package data.xmi.stereotypes.sstm;

import org.w3c.dom.Element;

/**
 * Created by Joost on 15-Feb-17.
 */
public class ThreadStereotype extends SLIMComponentStereotype {
    public static final String TAG_NAME = "SSTM.SSTM:Thread";

    public ThreadStereotype(String id, String baseClassId) {
        super(id, baseClassId);
    }

    public ThreadStereotype(Element deviceStereotypeElement) {
        super(deviceStereotypeElement);
    }

    @Override
    public String toString() {
        return "Thread stereotype";
    }
}
