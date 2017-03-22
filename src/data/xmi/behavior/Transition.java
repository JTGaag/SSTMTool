package data.xmi.behavior;

import data.xmi.XMIObject;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Created by Joost on 20-Feb-17.
 *
 * Basic element of SysML the tag xmi:type determines what kind of element it is.
 */
public class Transition extends XMIObject{
    public static final String TAG_NAME = "transition";
    public static final String ATTRIBUTE_TYPE = "xmi:type";
    public static final String ATTRIBUTE_NAME = "name";
    public static final String ATTRIBUTE_SOURCE = "source";
    public static final String ATTRIBUTE_TARGET = "target";


    //Both attribute type and type name in same class because now only one type is identified, so useless to make extra object (this is however used to make sure only the correct one is added in case more are found or added)
    public static final String TYPE_NAME = "uml:Transition";

    String name, targetId, sourceId;

    Trigger trigger;
    Guard guard;
    Effect effect;

    public Transition(Element transitionElement) {
        super(transitionElement.getAttribute(ATTRIBUTE_ID));
        this.name = transitionElement.getAttribute(ATTRIBUTE_NAME);
        this.sourceId = transitionElement.getAttribute(ATTRIBUTE_SOURCE);
        this.targetId = transitionElement.getAttribute(ATTRIBUTE_TARGET);

        extractTrigger(transitionElement);
        extractGuard(transitionElement);
        extractEffect(transitionElement);
    }

    private void extractTrigger (Element transitionElement) {
        NodeList triggerList = transitionElement.getElementsByTagName(Trigger.TAG_NAME);
        if (triggerList.getLength() > 0) { //only if a trigger is there select one trigger.
            Element triggerElement = (Element)triggerList.item(0);
            trigger = new Trigger(triggerElement);
        }
    }

    private void extractGuard (Element transitionElement) {
        NodeList guardList = transitionElement.getElementsByTagName(Guard.TAG_NAME);
        if (guardList.getLength() > 0) { //only if a trigger is there select one trigger.
            Element guardElement = (Element)guardList.item(0);
            guard = new Guard(guardElement);
        }
    }

    private void extractEffect (Element transitionElement) {
        NodeList effectList = transitionElement.getElementsByTagName(Effect.TAG_NAME);
        if (effectList.getLength() > 0) { //only if a trigger is there select one trigger.
            Element effectElement = (Element)effectList.item(0);
            effect = new Effect(effectElement);
        }
    }

    public String getName() {
        return name;
    }

    public String getTargetId() {
        return targetId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public Trigger getTrigger() {
        return trigger;
    }

    public Guard getGuard() {
        return guard;
    }

    public Effect getEffect() {
        return effect;
    }
}
