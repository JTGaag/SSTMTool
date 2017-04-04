package data.slim.components;

import data.xmi.structure.Class;

/**
 * Created by Joost on 16-Feb-17.
 * Memory component as java object
 */
public class Memory extends Component{

    public Memory(Class baseXmiClass) {
        super(baseXmiClass);
        this.baseXmiClass = baseXmiClass;
        this.slimComponentTypeName = "memory";
    }

    @Override
    public String toString() {
        return "Memory";
    }


}
