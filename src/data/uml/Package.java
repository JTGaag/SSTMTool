package data.uml;

import data.xmi.PackagedElement;

/**
 * Created by Joost on 14-Feb-17.
 */
public class Package extends PackagedElement{
    public static final String TYPE_NAME = "uml:Package";

    String name;

    public Package(String id, String name) {
        super(id);
        this.name = name;
    }

    @Override
    public String toString() {
        return "Package: " + name;
    }
}
