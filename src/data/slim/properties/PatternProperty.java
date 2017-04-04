package data.slim.properties;

import data.slim.SlimObject;
import data.xmi.stereotypes.properties.PatternPropertyStereotype;

/**
 * Created by Joost on 04-Apr-17.
 */
public class PatternProperty extends SlimObject {
    private PatternPropertyStereotype patternPropertyStereotype;
    private String name;

    public PatternProperty(data.xmi.properties.PatternProperty patternProperty) {
        this.patternPropertyStereotype = patternProperty.getPatternPropertyStereotype();
        this.name = patternProperty.getName();
    }

    @Override
    public String toSlimString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        //Naming part
        sb.append("Name => \"").append(name).append("\"; ");

        //Patern
        sb.append("Pattern => \"").append(patternPropertyStereotype.getPattern()).append("\"; ");

        sb.append("]");
        return sb.toString();
    }
}
