package data.xmi.uml;

import data.xmi.PackagedElement;
import org.w3c.dom.Element;

/**
 * Created by Joost on 14-Feb-17.
 */
public class Package extends PackagedElement{
    public static final String TYPE_NAME = "uml:Package";

    String name;

    public Package(String id, String name) {
        super(id);
        this.name = name;
    }

    public Package(Element packageElement) {
        super(packageElement.getAttribute(ATTRIBUTE_ID));
        this.name = packageElement.getAttribute(ATTRIBUTE_NAME);
    }

    @Override
    public String toString() {
        return "Package: " + name;
    }
}
