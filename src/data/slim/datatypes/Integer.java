package data.slim.datatypes;

/**
 * Created by Joost on 16-Feb-17.
 * SLIM Integer datatype
 */
public class Integer extends DataType {
    public static final String HREF_NAME = "pathmap://UML_LIBRARIES/UMLPrimitiveTypes.library.structure#Integer";

    @Override
    public String toString() {
        return "Integer";
    }

    @Override
    public String toSlimString() {
        return "int";
    }
}
