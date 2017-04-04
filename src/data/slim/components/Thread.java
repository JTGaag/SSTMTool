package data.slim.components;

import data.xmi.structure.Class;

/**
 * Created by Joost on 16-Feb-17.
 * Memory component as java object
 */
public class Thread extends Component{

    public Thread(Class baseXmiClass) {
        super(baseXmiClass);
        this.baseXmiClass = baseXmiClass;
        this.slimComponentTypeName = "thread";
    }

    @Override
    public String toString() {
        return "Thread";
    }


}
