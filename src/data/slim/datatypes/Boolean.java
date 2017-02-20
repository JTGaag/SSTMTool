package data.slim.datatypes;

/**
 * Created by Joost on 16-Feb-17.
 * SLIM Boolean data type
 */
public class Boolean extends DataType {
    public static final String HREF_NAME = "pathmap://UML_LIBRARIES/UMLPrimitiveTypes.library.structure#Boolean";

    @Override
    public String toString() {
        return "Boolean";
    }

    @Override
    public String toSlimString() {
        return "bool";
    }
}
