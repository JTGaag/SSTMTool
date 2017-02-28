package data.slim.internal;

import data.slim.SlimObject;
import data.slim.components.Component;

/**
 * Created by Joost on 20-Feb-17.
 */
public class Subcomponent extends SlimObject{
    Component referenceComponent;
    String name;

    public Subcomponent(String name, Component referenceComponent) {
        this.name = name;
        this.referenceComponent = referenceComponent;
    }

    public Component getReferenceComponent() {
        return referenceComponent;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toSlimString() {
        return name + ": " + referenceComponent.getSlimComponentTypeName() + " " + referenceComponent.getImplementationName() + ";";
    }
}
