package data.xmi.behavior;

import data.xmi.PackagedElement;
import data.xmi.stereotypes.error.ErrorStateStereotype;
import data.xmi.stereotypes.sstm.SstmStateStereotype;
import data.xmi.stereotypes.sysml.StateStereotype;
import org.w3c.dom.Element;

/**
 * Created by Joost on 20-Feb-17.
 * State class
 */
public class State extends Subvertex{
    public static final String TYPE_NAME = "uml:State";

    String name, whileExpression, errorStereotypeId;
    boolean initial = false;
    SstmStateStereotype sstmStateStereotype;
    ErrorStateStereotype errorStateStereotype;

    public State(Element stateElement) {
        super(stateElement.getAttribute(ATTRIBUTE_ID));
        this.name = stateElement.getAttribute(ATTRIBUTE_NAME);
    }

    public boolean addPossibleStereotype(StateStereotype stateStereotype) {
        if (stateStereotype.getBaseStateId().equals(getId())) {
            if (stateStereotype instanceof SstmStateStereotype) {
                this.sstmStateStereotype = (SstmStateStereotype)stateStereotype;
                whileExpression = ((SstmStateStereotype)stateStereotype).getWhileExpression();
            } else if (stateStereotype instanceof ErrorStateStereotype) {
                this.errorStateStereotype = (ErrorStateStereotype)stateStereotype;
                errorStereotypeId = stateStereotype.getId();
            }
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

    public String getErrorStereotypeId() {
        return errorStereotypeId;
    }

    public String getWhileExpression() {
        return whileExpression;
    }


    @Override
    public String toString() {
        return "State: " + name;
    }


}
