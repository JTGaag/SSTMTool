package data.xmi;

/**
 * Created by Joost on 14-Feb-17.
 *
 * Basic property of object. Kind of property is typed by xmi:type
 */
public class OwnedBehavior extends XMIObject {
    public static final String TAG_NAME = "ownedAttribute";
    public static final String ATTRIBUTE_TYPE = "xmi:type";

    public OwnedBehavior(String id) {
        super(id);
    }
}
