package data.xmi.stereotypes.fdir;

import org.w3c.dom.Element;

/**
 * Created by Joost on 23-Mar-17.
 */
public class AlarmStereotype extends FdirPortStereotype {
    public static final String TAG_NAME = "SSTM.FDIR:Alarm";

    public AlarmStereotype(Element flowPortStereotypeElement) {
        super(flowPortStereotypeElement);
    }
}
