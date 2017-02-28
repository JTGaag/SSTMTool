package data.slim.internal;

import data.slim.SlimObject;

/**
 * Created by Joost on 20-Feb-17.
 */
public class Transition extends SlimObject{
    String name, sourceId, targetId;
    State sourceState, targetState;
    EventPort triggerEventPort;
    String guardString;

    public Transition(data.xmi.behavior.Transition xmiTransition) {
        this.name = xmiTransition.getName();
        this.sourceId = xmiTransition.getSourceId();
        this.targetId = xmiTransition.getTargetId();
    }

    @Override
    public String toSlimString() {
        StringBuilder sb = new StringBuilder();
        return sb.toString();
    }

    public String getName() {
        return name;
    }
}
