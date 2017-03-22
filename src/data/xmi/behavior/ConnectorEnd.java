package data.xmi.behavior;

import data.xmi.XMIObject;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Created by Joost on 20-Feb-17.
 *
 * Basic element of SysML the tag xmi:type determines what kind of element it is.
 */
public class ConnectorEnd extends XMIObject{
    public static final String TAG_NAME = "end";
    public static final String ATTRIBUTE_TYPE = "xmi:type";
    public static final String ATTRIBUTE_NAME = "name";

    public static final String ATTRIBUTE_ROLE = "role";
    public static final String ATTRIBUTE_PART_WITH_PORT = "partWithPort";


    protected String type, name;
    protected String roleId, partId;

    public ConnectorEnd(Element endElement) {
        super(endElement.getAttribute(ATTRIBUTE_ID));
        this.name = endElement.getAttribute(ATTRIBUTE_NAME);
        this.type = endElement.getAttribute(ATTRIBUTE_TYPE);
        this.roleId = endElement.getAttribute(ATTRIBUTE_ROLE);
        if (endElement.hasAttribute(ATTRIBUTE_PART_WITH_PORT)) this.partId = endElement.getAttribute(ATTRIBUTE_PART_WITH_PORT);
    }


    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getRoleId() {
        return roleId;
    }

    public String getPartId() {
        return partId;
    }
}
