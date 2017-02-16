package data.xmi.uml;

import data.xmi.OwnedAttribute;
import data.xmi.PackagedElement;
import data.xmi.stereotypes.Stereotype;
import data.xmi.stereotypes.sstm.SignalStereotype;
import data.xmi.stereotypes.sysml.BlockStereotype;
import data.xmi.stereotypes.sysml.FlowPortStereotype;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

/**
 * Created by Joost on 14-Feb-17.
 */
public class Class extends PackagedElement {
    public static final String TYPE_NAME = "uml:Class";

    protected String name;
    private ArrayList<Property> properties = new ArrayList<>();
    private ArrayList<Port> ports = new ArrayList<>();

    //Possible stereotypes
    private ArrayList<Stereotype> stereotypes = new ArrayList<>();

    public Class(String id, String name) {
        super(id);
        this.name = name;
    }

    public Class(Element classElement) {
        super(classElement.getAttribute(ATTRIBUTE_ID));
        this.name = classElement.getAttribute(ATTRIBUTE_NAME);

        createAttributes(classElement);
    }

    private void createAttributes(Element classElement) {
        NodeList ownedAttributesList = classElement.getElementsByTagName(OwnedAttribute.TAG_NAME);
        for (int i=0; i<ownedAttributesList.getLength(); i++) {
            Element attribute = (Element)ownedAttributesList.item(i);

            switch (attribute.getAttribute(OwnedAttribute.ATTRIBUTE_TYPE)) {
                case Property.TYPE_NAME:
                    properties.add(new Property(attribute));
                    break;
                case Port.TYPE_NAME:
                    ports.add(new Port(attribute));
                    break;
                default:

                    break;
            }
        }
    }

    public ArrayList<Property> getProperties() {
        return properties;
    }

    public ArrayList<Port> getPorts() {
        return ports;
    }

    public ArrayList<Stereotype> getStereotypes() {
        return stereotypes;
    }

    public boolean addPossibleStereotype(BlockStereotype stereotype) {
        if (stereotype.getBaseClassId().equals(this.getId())) {
            this.stereotypes.add(stereotype);
            return true;
        }
        return false;
    }

    public boolean addPossibleStereotypeToPorts(FlowPortStereotype stereotype) {
        for (Port port: ports) {
            if(port.addPossibleStereotype(stereotype)) return true;
        }
        return false;
    }

    public void setPossibleTypePorts(String signalId) {
        for (Port port: ports) {
            port.setPossibleTypePort(signalId);
        }
    }

    /**
     * Function to derermine if class is a signal block
     * @return
     */
    public boolean isSignal() {

        for (Stereotype stereotype: stereotypes) {
            if (stereotype instanceof SignalStereotype) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Port port: ports) {
            stringBuilder.append("      ");
            stringBuilder.append(port.toString());
            stringBuilder.append("\n");
        }
        return "Class: " + name +
                "\n     Nr of Properties: " + properties.size() +
                "\n     Nr of Ports: " + ports.size() +
                "\n     Signal: " + isSignal() +
                "\n     Ports: \n" + stringBuilder.toString();
    }
}
