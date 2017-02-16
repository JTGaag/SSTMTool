package data.xmi.uml;

import data.xmi.OwnedAttribute;
import data.xmi.PackagedElement;
import data.xmi.stereotypes.Stereotype;
import data.xmi.stereotypes.sysml.FlowPortStereotype;
import data.xmi.stereotypes.sysml.ValueTypeStereotype;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

/**
 * Created by Joost on 14-Feb-17.
 */
public class DataType extends PackagedElement {
    public static final String TYPE_NAME = "uml:DataType";

    protected String name;

    //Possible stereotypes
    private ArrayList<Stereotype> stereotypes = new ArrayList<>();

    public DataType(String id, String name) {
        super(id);
        this.name = name;
    }

    public DataType(Element classElement) {
        super(classElement.getAttribute(ATTRIBUTE_ID));
        this.name = classElement.getAttribute(ATTRIBUTE_NAME);
    }

    public ArrayList<Stereotype> getStereotypes() {
        return stereotypes;
    }

    public boolean addPossibleStereotype(ValueTypeStereotype stereotype) {
        if (stereotype.getBaseDataTypeId().equals(this.getId())) {
            this.stereotypes.add(stereotype);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "DataType: " + name + "; Nr of stereotypes: " + stereotypes.size();
    }
}
