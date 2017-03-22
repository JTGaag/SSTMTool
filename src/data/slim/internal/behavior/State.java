package data.slim.internal.behavior;

import data.slim.SlimObject;

/**
 * Created by Joost on 20-Feb-17.
 */
public class State extends SlimObject{
    String name, stateId, whileExpression;
    boolean initial = false;

    public State(data.xmi.behavior.State xmiState) {
        this.name = xmiState.getName();
        this.initial = xmiState.isInitial();
        this.stateId = xmiState.getId();
        this.whileExpression = xmiState.getWhileExpression();
    }

    public State(State state) {
        this.name = state.getName();
        this.initial = state.isInitial();
        this.stateId = state.getStateId();
        this.whileExpression = state.getWhileExpression();
    }

    @Override
    public String toSlimString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append(": ");
        if (initial) sb.append("initial ");
        sb.append("state");

        if (whileExpression != null) {
            sb.append(" while (");
            sb.append(whileExpression);
            sb.append(")");
        }

        sb.append(";");
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public boolean isInitial() {
        return initial;
    }

    public String getStateId() {
        return stateId;
    }

    public String getWhileExpression() {
        return whileExpression;
    }
}
