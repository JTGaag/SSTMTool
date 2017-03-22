package data.xmi.behavior;

import data.xmi.PackagedElement;
import data.xmi.stereotypes.sstm.SstmStateStereotype;
import org.w3c.dom.Element;

/**
 * Created by Joost on 20-Feb-17.
 * State class
 */
public class State extends Subvertex{
    public static final String TYPE_NAME = "uml:State";

    String name, whileExpression;
    boolean initial = false;
    SstmStateStereotype sstmStateStereotype;

    public State(Element stateElement) {
        super(stateElement.getAttribute(ATTRIBUTE_ID));
        this.name = stateElement.getAttribute(ATTRIBUTE_NAME);
    }

    public boolean addPossibleStereotype(SstmStateStereotype sstmStateStereotype) {
        if (sstmStateStereotype.getBaseStateId().equals(getId())) {
            this.sstmStateStereotype = sstmStateStereotype;
            whileExpression = sstmStateStereotype.getWhileExpression();
            return true;
        } else {
            return false;
        }
    }

    public void setInitial(boolean initial) {
        this.initial = initial;
    }

    public String getName() {
        return name;
    }

    public boolean isInitial() {
        return initial;
    }

    public String getWhileExpression() {
        return whileExpression;
    }

    @Override
    public String toString() {
        return "Package: " + name;
    }


}
