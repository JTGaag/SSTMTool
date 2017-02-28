package data.slim.components;

import data.xmi.structure.Class;

/**
 * Created by Joost on 16-Feb-17.
 * Device component as java object
 */
public class Processor extends Component{

    public Processor(Class baseXmiClass) {
        super(baseXmiClass);
        this.baseXmiClass = baseXmiClass;
        this.slimComponentTypeName = "processor";
    }

    @Override
    public String toString() {
        return "Processor";
    }


}
