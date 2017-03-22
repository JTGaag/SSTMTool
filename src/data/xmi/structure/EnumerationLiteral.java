package data.xmi.structure;

import data.xmi.PackagedElement;
import data.xmi.XMIObject;
import data.xmi.stereotypes.Stereotype;
import data.xmi.stereotypes.sysml.ValueTypeStereotype;
import org.w3c.dom.Element;

import java.util.ArrayList;

/**
 * Created by Joost on 14-Feb-17.
 */
public class EnumerationLiteral extends XMIObject {
    public static final String TYPE_NAME = "uml:EnumerationLiteral";
    public static final String TAG_NAME = "ownedLiteral";

    public static final String ATTRIBUTE_NAME = "name";
    public static final String ATTRIBUTE_TYPE = "xmi:type";

    protected String name;


    public EnumerationLiteral(Element enumerationLiteralElement) {
        super(enumerationLiteralElement.getAttribute(ATTRIBUTE_ID));
        this.name = enumerationLiteralElement.getAttribute(ATTRIBUTE_NAME);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "EnumerationLiteral: " + name;
    }
}
