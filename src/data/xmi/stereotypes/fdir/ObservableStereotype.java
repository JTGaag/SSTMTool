package data.xmi.stereotypes.fdir;

import org.w3c.dom.Element;

/**
 * Created by Joost on 23-Mar-17.
 */
public class ObservableStereotype extends FdirPortStereotype {
    public static final String TAG_NAME = "SSTM.FDIR:Observable";

    public ObservableStereotype(Element flowPortStereotypeElement) {
        super(flowPortStereotypeElement);
    }
}
