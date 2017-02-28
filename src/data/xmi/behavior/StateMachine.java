package data.xmi.behavior;

import data.xmi.OwnedAttribute;
import data.xmi.OwnedBehavior;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Created by Joost on 20-Feb-17.
 */
public class StateMachine extends OwnedBehavior {
    public static final String TYPE_NAME = "uml:StateMachine";

    Region region;
    String name;

    public StateMachine(Element stateMachineElement) {
        super(stateMachineElement.getAttribute(ATTRIBUTE_ID));
        this.name = stateMachineElement.getAttribute(ATTRIBUTE_NAME);
        NodeList regionList = stateMachineElement.getElementsByTagName(Region.TAG_NAME);
        if (regionList.getLength()>0) {
            region = new Region((Element)regionList.item(0));
        }
    }

    public Region getRegion() {
        return region;
    }

    public String getName() {
        return name;
    }
}
