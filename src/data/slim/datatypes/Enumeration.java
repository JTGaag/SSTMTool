package data.slim.datatypes;

import data.xmi.structure.EnumerationLiteral;

/**
 * Created by Joost on 16-Feb-17.
 * SLIM Real datatype
 */
public class Enumeration extends DataType {
    data.xmi.structure.Enumeration xmiEnumeration;

    public Enumeration(data.xmi.structure.Enumeration enumeration) {
        this.xmiEnumeration = enumeration;
    }

    @Override
    public String toString() {
        return "Real";
    }

    @Override
    public String toSlimString() {
        StringBuilder sb = new StringBuilder();
        sb.append("enum(");
        for (EnumerationLiteral enumerationLiteral: xmiEnumeration.getLiterals()) {
            if (xmiEnumeration.getLiterals().indexOf(enumerationLiteral) != 0) {
                sb.append(", ");
            }
            sb.append(enumerationLiteral.getName());
        }
        sb.append(")");
        return sb.toString();
    }
}
