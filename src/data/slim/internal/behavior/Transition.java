package data.slim.internal.behavior;

import com.sun.istack.internal.Nullable;
import data.slim.SlimObject;
import data.slim.components.Component;
import data.slim.internal.behavior.State;
import data.slim.internal.structure.EventPort;
import data.slim.internal.structure.Port;
import data.slim.internal.structure.Subcomponent;

import java.util.ArrayList;

/**
 * Created by Joost on 20-Feb-17.
 */
public class Transition extends SlimObject{
    String name, sourceId, targetId;
    State sourceState, targetState;
    Trigger trigger;
    Guard guard;
    Effect effect;

    public Transition(data.xmi.behavior.Transition xmiTransition) {
        this.name = xmiTransition.getName();
        this.sourceId = xmiTransition.getSourceId();
        this.targetId = xmiTransition.getTargetId();
        if (xmiTransition.getTrigger() != null) this.trigger = new Trigger(xmiTransition.getTrigger());
        if (xmiTransition.getGuard() != null) this.guard = new Guard(xmiTransition.getGuard());
        if (xmiTransition.getEffect() != null) this.effect = new Effect(xmiTransition.getEffect());
    }

    public void finalizeTrigger(Port port, @Nullable Subcomponent subcomponent) {
        if (trigger != null) {
            trigger.finalize(port, subcomponent);
        }
    }

    public void finalizeTargetAndSourceStates(ArrayList<State> states) {
        for (State state: states) {
            if (state.getStateId().equals(sourceId)) sourceState = state;
            if (state.getStateId().equals(targetId)) targetState = state;
        }
    }

    @Override
    public String toSlimString() {
        //Null checker, ToDO: should not happen, probably with inital transition
        if (sourceState == null || targetState == null) return "";

        StringBuilder sb = new StringBuilder();
        //Begin
        sb.append(sourceState.getName());
        sb.append(" -[ ");
        //Event
        if (trigger != null) {
            sb.append(trigger.toSlimString());
        }

        //Guard
        if (guard != null) {
            sb.append(guard.toSlimString());
        }

        //Effect
        if (effect != null) {
            sb.append(effect.toSlimString());
        }

        //End
        sb.append("]-> ");
        sb.append(targetState.getName());
        sb.append(";");

        //Comment (name of transition)
        sb.append(" -- transition name: " + name);
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public State getSourceState() {
        return sourceState;
    }

    public State getTargetState() {
        return targetState;
    }

    public Trigger getTrigger() {
        return trigger;
    }

    public Guard getGuard() {
        return guard;
    }
}
