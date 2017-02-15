package data.uml;

import data.xmi.PackagedElement;

/**
 * Created by Joost on 14-Feb-17.
 */
public class Class extends PackagedElement {
    public static final String TYPE_NAME = "uml:Class";

    protected String name;

    public Class(String id, String name) {
        super(id);
        this.name = name;
    }

    @Override
    public String toString() {
        return "Class: " + name;
    }
}
