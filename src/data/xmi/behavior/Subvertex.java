package data.xmi.behavior;

import data.xmi.XMIObject;

/**
 * Created by Joost on 20-Feb-17.
 *
 * Basic element of SysML the tag xmi:type determines what kind of element it is.
 */
public class Subvertex extends XMIObject{
    public static final String TAG_NAME = "subvertex";
    public static final String ATTRIBUTE_TYPE = "xmi:type";
    public static final String ATTRIBUTE_NAME = "name";

    protected String type;

    public Subvertex(String id) {
        super(id);
    }

    public String getType() {
        return type;
    }

}
