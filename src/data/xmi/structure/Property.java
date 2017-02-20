package data.xmi.structure;

import data.xmi.OwnedAttribute;
import org.w3c.dom.Element;

import java.util.ArrayList;

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
    Class type;

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

    public void setType(ArrayList<Class> classes) {
        for (Class cls: classes) {
            if (cls.getId().equals(this.typeId)) {
                this.type = cls;
                return;
            }
        }
    }

    public String getTypeId() {
        return typeId;
    }

    public Class getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getAggregation() {
        return aggregation;
    }

    public String getAssociationId() {
        return associationId;
    }

    @Override
    public String toString() {
        return "Package: " + name;
    }
}
