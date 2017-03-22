package data.xmi;

/**
 * Created by Joost on 14-Feb-17.
 *
 * Basic element of SysML the tag xmi:type determines what kind of element it is.
 */
public class OwnedComment extends XMIObject{
    public static final String TAG_NAME = "ownedComment";
    public static final String ATTRIBUTE_TYPE = "xmi:type";
    public static final String ATTRIBUTE_NAME = "name";

    protected String type;

    public OwnedComment(String id) {
        super(id);
    }

    public String getType() {
        return type;
    }

}
