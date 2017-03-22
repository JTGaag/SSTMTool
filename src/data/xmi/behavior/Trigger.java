package data.xmi.behavior;

import com.sun.istack.internal.Nullable;
import data.xmi.OwnedBehavior;
import data.xmi.XMIObject;
import data.xmi.structure.Class;
import data.xmi.structure.Port;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

/**
 * Created by Joost on 20-Feb-17.
 *
 * Basic element of SysML the tag xmi:type determines what kind of element it is.
 */
public class Trigger extends XMIObject{
    public static final String TAG_NAME = "trigger";
    public static final String ATTRIBUTE_TYPE = "xmi:type";
    public static final String ATTRIBUTE_NAME = "name";
    public static final String ATTRIBUTE_PORT = "port";

    public static final String COMMENT_TAG_NAME = "ownedComment";
    public static final String ATTRIBUTE_ANNOTATED_ELEMENT = "annotatedElement";

    protected String portId, name, subcomponentId;

    public Trigger(Element triggerElement) {
        super(triggerElement.getAttribute(ATTRIBUTE_ID));
        this.name = triggerElement.getAttribute(ATTRIBUTE_NAME);
        this.portId = triggerElement.getAttribute(ATTRIBUTE_PORT);

        getAssociatedSubcomponent(triggerElement);
    }

    private void getAssociatedSubcomponent(Element triggerElement) {
        NodeList ownedComment = triggerElement.getElementsByTagName(COMMENT_TAG_NAME);
        if (ownedComment.getLength() > 0) {
            this.subcomponentId = ((Element)ownedComment.item(0)).getAttribute(ATTRIBUTE_ANNOTATED_ELEMENT);
        }
    }

    public String getName() {
        return name;
    }

    public String getPortId() {
        return portId;
    }

    public String getSubcomponentId() {
        return subcomponentId;
    }
}
