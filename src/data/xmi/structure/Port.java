package data.xmi.structure;

import data.enums.PortDirection;
import data.slim.datatypes.*;
import data.slim.datatypes.Boolean;
import data.slim.datatypes.DataType;
import data.slim.datatypes.Integer;
import data.xmi.OwnedAttribute;
import data.xmi.stereotypes.Stereotype;
import data.xmi.stereotypes.sysml.FlowPortStereotype;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Joost on 14-Feb-17.
 */
public class Port extends OwnedAttribute{


    public enum PortType {
        EVENT, DATA
    }

    public static final String TYPE_NAME = "uml:Port";
    public static final String ATTRIBUTE_NAME = "name";
    public static final String ATTRIBUTE_TYPE = "type";
    public static final String ATTRIBUTE_AGGREGATION = "aggregation";

    public static final String DEFAULT_VALUE_TAG_NAME = "defaultValue";
    public static final String DEFAULT_VALUE_ATTRIBUTE_VALUE = "value";
    public static final String DEFAULT_VALUE_ATTRIBUTE_INSTANCE_ID = "instance";

    String name, typeId, aggregation;
    PortType portType = PortType.DATA; //Default is data port
    PortDirection direction = PortDirection.UNKNOWN;
    private String defaultValue, defaultValueId;


    //Possible stereotypes
    private ArrayList<Stereotype> stereotypes = new ArrayList<>();
    private data.slim.datatypes.DataType dataType = new DataType();

    public Port(String id, String name, String typeId, String aggregation) {
        super(id);
        this.name = name;
        this.typeId = typeId;
        this.aggregation = aggregation;
    }

    public Port(Element portElement) {
        super(portElement.getAttribute(ATTRIBUTE_ID));
        this.name = portElement.getAttribute(ATTRIBUTE_NAME);
        this.typeId = portElement.getAttribute(ATTRIBUTE_TYPE);
        this.aggregation = portElement.getAttribute(ATTRIBUTE_AGGREGATION);


        if (this.typeId == null || Objects.equals(this.typeId, "")) { //If no typeId (type is defined in element of port find it!
            addDataType(portElement);
        } else {
            //ValueType is added, now only add real as dataType.
            //TODO: connect valuetype to it
            //TODO: not true for signal case
            dataType = new Real();

            //Default value id
            if (portElement.getElementsByTagName(DEFAULT_VALUE_TAG_NAME).getLength() > 0) {
                if (((Element)portElement.getElementsByTagName(DEFAULT_VALUE_TAG_NAME).item(0)).hasAttribute(DEFAULT_VALUE_ATTRIBUTE_INSTANCE_ID)) {
                    defaultValueId = ((Element) portElement.getElementsByTagName(DEFAULT_VALUE_TAG_NAME).item(0)).getAttribute(DEFAULT_VALUE_ATTRIBUTE_INSTANCE_ID);
                }
            }
        }

    }

    public ArrayList<Stereotype> getStereotypes() {
        return stereotypes;
    }

    public DataType getDataType() {
        return dataType;
    }

    public PortType getPortType() {
        return portType;
    }

    public PortDirection getDirection() {
        return direction;
    }

    public String getName() {
        return name;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public boolean addPossibleStereotype(FlowPortStereotype stereotype) {
        if (stereotype.getBasePortId().equals(this.getId())) {
            this.stereotypes.add(stereotype);
            //Add direction if stereotype is flowPort (has direction parameter in stereotype)
            if (stereotype instanceof FlowPortStereotype) {
                this.direction = ((FlowPortStereotype) stereotype).getDirection();
            }

            return true;
        }
        return false;
    }

    /**
     * Check if type of the port is the the signal provide (using the id)
     * If it is it is an event port
     * @param signalId is of signal
     */
    public void setPossibleTypePort(String signalId) {
        if (this.typeId == null || Objects.equals(this.typeId, "")) return;
        if (this.typeId.equals(signalId)) {
            //this port is typed by a signal so it is an event port
            this.portType = PortType.EVENT;
        }
    }

    /**
     * Connect enums to port
     * @param enums
     */
    public void setPossibleEnums(ArrayList<Enumeration> enums) {
        for (Enumeration enumeration: enums) {
            if (this.typeId.equals(enumeration.getId())) {
                this.portType = PortType.DATA;
                this.dataType = new data.slim.datatypes.Enumeration(enumeration);

                //Do default value
                if (defaultValueId != null) {
                    defaultValue = enumeration.returnDefaultValue(defaultValueId);
                }
            }
        }
    }

    @Override
    public String toString() {
        return name + " : " + dataType.toString() + " (" + portType + ")";
    }

    private void addDataType(Element portElement) {
        NodeList typeList = portElement.getElementsByTagName(DataType.TAG_NAME);
        if (typeList.getLength() == 0) return;
        //Only get first type in
        Element type = (Element)typeList.item(0);
        switch (type.getAttribute(DataType.ATTRIBUTE_HREF)) {
            case Boolean.HREF_NAME:
                dataType = new Boolean();

                //Default value boolean
                if (portElement.getElementsByTagName(DEFAULT_VALUE_TAG_NAME).getLength() > 0) {
                    if (((Element)portElement.getElementsByTagName(DEFAULT_VALUE_TAG_NAME).item(0)).hasAttribute(DEFAULT_VALUE_ATTRIBUTE_VALUE)) {
                        defaultValue = ((Element) portElement.getElementsByTagName(DEFAULT_VALUE_TAG_NAME).item(0)).getAttribute(DEFAULT_VALUE_ATTRIBUTE_VALUE);
                    } else {
                        defaultValue = "false";
                    }
                }

                break;
            case Integer.HREF_NAME:
                dataType = new Integer();

                //Default value integer
                if (portElement.getElementsByTagName(DEFAULT_VALUE_TAG_NAME).getLength() > 0) {
                    if (((Element)portElement.getElementsByTagName(DEFAULT_VALUE_TAG_NAME).item(0)).hasAttribute(DEFAULT_VALUE_ATTRIBUTE_VALUE)) {
                        defaultValue = ((Element) portElement.getElementsByTagName(DEFAULT_VALUE_TAG_NAME).item(0)).getAttribute(DEFAULT_VALUE_ATTRIBUTE_VALUE);
                    } else {
                        defaultValue = "0";
                    }
                }

                break;
            case Real.HREF_NAME:
                dataType = new Real();

                //Default value real
                if (portElement.getElementsByTagName(DEFAULT_VALUE_TAG_NAME).getLength() > 0) {
                    if (((Element)portElement.getElementsByTagName(DEFAULT_VALUE_TAG_NAME).item(0)).hasAttribute(DEFAULT_VALUE_ATTRIBUTE_VALUE)) {
                        defaultValue = ((Element) portElement.getElementsByTagName(DEFAULT_VALUE_TAG_NAME).item(0)).getAttribute(DEFAULT_VALUE_ATTRIBUTE_VALUE);
                    } else {
                        defaultValue = "0.0";
                    }
                }

                break;
            default:
                break;
        }

    }
}
