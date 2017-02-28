package data.slim.internal;

import data.slim.SlimObject;

/**
 * Created by Joost on 20-Feb-17.
 */
public class State extends SlimObject{
    String name;
    boolean initial = false;

    public State(data.xmi.behavior.State xmiState) {
        this.name = xmiState.getName();
        this.initial = xmiState.isInitial();
    }

    public State(State state) {
        this.name = state.getName();
        this.initial = state.isInitial();
    }

    @Override
    public String toSlimString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append(": ");
        if (initial) sb.append("initial ");
        sb.append("state");
        sb.append(";");
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public boolean isInitial() {
        return initial;
    }
}
