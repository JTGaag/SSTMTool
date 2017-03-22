package data.xmi.structure;

import data.xmi.PackagedElement;
import data.xmi.stereotypes.Stereotype;
import data.xmi.stereotypes.sysml.ValueTypeStereotype;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

/**
 * Created by Joost on 14-Feb-17.
 */
public class Enumeration extends PackagedElement {
    public static final String TYPE_NAME = "uml:Enumeration";

    protected String name;

    //OwnedLiterals
    private ArrayList<EnumerationLiteral> literals = new ArrayList<>();



    public Enumeration(Element enumerationElement) {
        super(enumerationElement.getAttribute(ATTRIBUTE_ID));
        this.name = enumerationElement.getAttribute(ATTRIBUTE_NAME);
        getOwnedLiterals(enumerationElement);
    }


    private void getOwnedLiterals(Element enumerationElement) {
        NodeList literalList = enumerationElement.getElementsByTagName(EnumerationLiteral.TAG_NAME);
        for (int i=0; i<literalList.getLength(); i++) {
            Element literalElement = (Element)literalList.item(i);
            if (literalElement.getAttribute(EnumerationLiteral.ATTRIBUTE_TYPE).equals(EnumerationLiteral.TYPE_NAME)) {
                literals.add(new EnumerationLiteral(literalElement));
            }
        }
    }

    public ArrayList<EnumerationLiteral> getLiterals() {
        return literals;
    }

    public String returnDefaultValue(String instanceId) {
        for (EnumerationLiteral literal: literals) {
            if (literal.getId().equals(instanceId)) return literal.getName();
        }
        return null;
    }

    @Override
    public String toString() {
        return "Enumeration: " + name + "; Nr of Literals: " + literals.size();
    }
}
