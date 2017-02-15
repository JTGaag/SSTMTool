package data.xmi;

/**
 * Created by Joost on 14-Feb-17.
 */
public class XMIObject {
    public static final String ATTRIBUTE_ID = "xmi:id";

    String id;

    public XMIObject(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
