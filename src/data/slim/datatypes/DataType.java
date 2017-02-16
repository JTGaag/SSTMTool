package data.slim.datatypes;

import data.slim.SlimObject;

/**
 * Created by Joost on 16-Feb-17.
 * Base class for SLIM datatype
 */
public class DataType extends SlimObject{
    public static final String TAG_NAME = "type";
    public static final String ATTRIBUTE_HREF = "href";

    @Override
    public String toString() {
        return "DataType";
    }

    @Override
    public String toSlimString() {
        return "DataType asdasdas";
    }
}
