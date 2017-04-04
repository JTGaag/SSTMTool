package data.xmi.properties;

import data.xmi.stereotypes.properties.PatternPropertyStereotype;
import data.xmi.structure.Property;

/**
 * Created by Joost on 04-Apr-17.
 */
public class PatternProperty {
    PatternPropertyStereotype patternPropertyStereotype;
    String name;

    public PatternProperty(Property property, PatternPropertyStereotype patternPropertyStereotype) {
        this.patternPropertyStereotype = patternPropertyStereotype;
        this.name = property.getName();
    }

    public PatternPropertyStereotype getPatternPropertyStereotype() {
        return patternPropertyStereotype;
    }

    public String getName() {
        return name;
    }
}
