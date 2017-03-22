package data.xmi.behavior;

import data.xmi.OwnedBehavior;
import data.xmi.XMIObject;
import data.xmi.structure.Class;
import data.xmi.structure.Port;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

/**
 * Created by Joost on 20-Feb-17.
 */
public class Region extends XMIObject {
    public static final String TAG_NAME = "region";

    private ArrayList<State> states = new ArrayList<>();
    private ArrayList<Pseudostate> pseudostates = new ArrayList<>();
    private ArrayList<Transition> transitions = new ArrayList<>();


    public Region(Element regionElement) {
        super(regionElement.getAttribute(ATTRIBUTE_ID));

        createSubvertexes(regionElement);
        createTransitions(regionElement);
        addInitialToState();
    }

    private void createSubvertexes(Element regionElement) {
        NodeList subvertexList = regionElement.getElementsByTagName(Subvertex.TAG_NAME);
        for (int i=0; i<subvertexList.getLength(); i++) {
            Element subvertex = (Element)subvertexList.item(i);
            switch (subvertex.getAttribute(OwnedBehavior.ATTRIBUTE_TYPE)) {
                case State.TYPE_NAME:
                    states.add(new State(subvertex));
                    break;
                case Pseudostate.TYPE_NAME:
                    pseudostates.add(new Pseudostate(subvertex));
                    break;
                default:
                    break;
            }
        }
    }

    private void createTransitions(Element regionElement) {
        NodeList transitionList = regionElement.getElementsByTagName(Transition.TAG_NAME);
        for (int i=0; i<transitionList.getLength(); i++) {
            Element transition = (Element)transitionList.item(i);
            if (transition.getAttribute(Transition.ATTRIBUTE_TYPE).equals(Transition.TYPE_NAME)) {
                transitions.add(new Transition(transition));
            }
        }
    }

    private void addInitialToState() {
        String initialPseudoId = null, initialId = null;
        for (Pseudostate pseudostate: pseudostates) {
            if (pseudostate.getPseudostateType() == Pseudostate.PseudostateType.INITIAL) {
                initialPseudoId = pseudostate.getId();
            }
        }

        if (initialPseudoId != null) {
            for (Transition transition: transitions) {
                if (transition.getSourceId().equals(initialPseudoId)) initialId = transition.getTargetId();
            }
        }

        if (initialId != null) {
            for (State state: states) {
                if (state.getId().equals(initialId)) state.setInitial(true);
            }
        }
    }

    public ArrayList<State> getStates() {
        return states;
    }

    public ArrayList<Pseudostate> getPseudostates() {
        return pseudostates;
    }

    public ArrayList<Transition> getTransitions() {
        return transitions;
    }
}
