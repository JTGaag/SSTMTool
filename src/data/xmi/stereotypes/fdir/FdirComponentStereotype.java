package data.xmi.stereotypes.fdir;

import data.xmi.stereotypes.sysml.BlockStereotype;
import org.w3c.dom.Element;

/**
 * Created by Joost on 23-Mar-17.
 */
public class FdirComponentStereotype extends BlockStereotype {
    public static final String TAG_NAME = "SSTM.FDIR:FdirComponent";

    public FdirComponentStereotype(Element flowPortStereotypeElement) {
        super(flowPortStereotypeElement);
    }

    @Override
    public String toString() {
        return "FDIR component stereotype";
    }
}
