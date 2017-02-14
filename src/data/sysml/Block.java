package data.sysml;

import data.uml.Class;

/**
 * Created by Joost on 14-Feb-17.
 */
public class Block extends Class {

    public Block(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Block: " + name;
    }
}
