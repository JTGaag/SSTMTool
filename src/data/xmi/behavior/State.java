package data.xmi.behavior;

import data.xmi.PackagedElement;
import org.w3c.dom.Element;

/**
 * Created by Joost on 20-Feb-17.
 * State class
 */
public class State extends Subvertex{
    public static final String TYPE_NAME = "uml:State";

    String name;
    boolean initial = false;

    public State(Element packageElement) {
        super(packageElement.getAttribute(ATTRIBUTE_ID));
        this.name = packageElement.getAttribute(ATTRIBUTE_NAME);
    }

    public void setInitial(boolean initial) {
        this.initial = initial;
    }

    public String getName() {
        return name;
    }

    public boolean isInitial() {
        return initial;
    }

    @Override
    public String toString() {
        return "Package: " + name;
    }
}
