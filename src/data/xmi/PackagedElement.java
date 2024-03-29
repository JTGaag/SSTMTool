package data.xmi;

import data.xmi.stereotypes.Stereotype;

import java.util.ArrayList;

/**
 * Created by Joost on 14-Feb-17.
 *
 * Basic element of SysML the tag xmi:type determines what kind of element it is.
 */
public class PackagedElement extends XMIObject{
    public static final String TAG_NAME = "packagedElement";
    public static final String ATTRIBUTE_TYPE = "xmi:type";
    public static final String ATTRIBUTE_NAME = "name";

    protected String type;

    public PackagedElement(String id) {
        super(id);
    }

    public String getType() {
        return type;
    }

}
