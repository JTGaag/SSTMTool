package data.slim.internal.structure;

import data.slim.SlimObject;
import data.slim.components.Component;

/**
 * Created by Joost on 20-Feb-17.
 */
public class Subcomponent extends SlimObject{
    Component referenceComponent;
    String name, subcomponentId;

    public Subcomponent(String name, Component referenceComponent, String propertyId) {
        this.name = name;
        this.referenceComponent = referenceComponent;
        this.subcomponentId = propertyId;
    }

    public Component getReferenceComponent() {
        return referenceComponent;
    }

    public String getName() {
        return name;
    }

    public String getSubcomponentId() {
        return subcomponentId;
    }

    @Override
    public String toSlimString() {
        return name + ": " + referenceComponent.getSlimComponentTypeName() + " " + referenceComponent.getImplementationName() + ";";
    }
}
