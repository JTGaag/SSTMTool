package data.xmi.uml;

import data.xmi.OwnedAttribute;
import org.w3c.dom.Element;

/**
 * Created by Joost on 14-Feb-17.
 */
public class Property extends OwnedAttribute{
    public static final String TYPE_NAME = "uml:Property";
    public static final String ATTRIBUTE_NAME = "name";
    public static final String ATTRIBUTE_TYPE = "type";
    public static final String ATTRIBUTE_AGGREGATION = "aggregation";
    public static final String ATTRIBUTE_ASSOCIATION = "association";

    String name, typeId, aggregation, associationId;

    public Property(String id, String name, String typeId, String aggregation, String associationId) {
        super(id);
        this.name = name;
        this.typeId = typeId;
        this.aggregation = aggregation;
        this.associationId = associationId;
    }

    public Property(Element propertyElement) {
        super(propertyElement.getAttribute(ATTRIBUTE_ID));
        this.name = propertyElement.getAttribute(ATTRIBUTE_NAME);
        this.typeId = propertyElement.getAttribute(ATTRIBUTE_TYPE);
        this.aggregation = propertyElement.getAttribute(ATTRIBUTE_AGGREGATION);
        this.associationId = propertyElement.getAttribute(ATTRIBUTE_ASSOCIATION);
    }


    @Override
    public String toString() {
        return "Package: " + name;
    }
}
