package data.xmi.behavior;

import org.w3c.dom.Element;

/**
 * Created by Joost on 20-Feb-17.
 *
 * PseudoState class
 * currently only used for initial pseudo state. It can only be typed so if the name of the pseudostate is "initial" else it will not be used
 */
public class Pseudostate extends Subvertex{
    public enum PseudostateType {
        INITIAL
    }

    public static final String TYPE_NAME = "uml:Pseudostate";

    String name;
    PseudostateType pseudostateType;

    public Pseudostate(Element packageElement) {
        super(packageElement.getAttribute(ATTRIBUTE_ID));
        this.name = packageElement.getAttribute(ATTRIBUTE_NAME);
        if (this.name.equals("initial")) {
            pseudostateType = PseudostateType.INITIAL;
        }
    }

    public String getName() {
        return name;
    }

    public PseudostateType getPseudostateType() {
        return pseudostateType;
    }

    @Override
    public String toString() {
        return "Package: " + name;
    }
}
