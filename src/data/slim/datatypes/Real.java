package data.slim.datatypes;

/**
 * Created by Joost on 16-Feb-17.
 * SLIM Real datatype
 */
public class Real extends DataType {
    public static final String HREF_NAME = "pathmap://UML_LIBRARIES/UMLPrimitiveTypes.library.structure#Real";

    @Override
    public String toString() {
        return "Real";
    }

    @Override
    public String toSlimString() {
        return "real";
    }
}
