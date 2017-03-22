package data.slim.internal.behavior;


/**
 * Created by Joost on 20-Feb-17.
 */
public class Mode extends State {

    public Mode(data.xmi.behavior.State xmiState) {
        super(xmiState);
    }

    public Mode(State state) {
        super(state);
    }

    @Override
    public String toSlimString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append(": ");
        if (initial) sb.append("initial ");
        sb.append("mode");
        sb.append(";");
        return sb.toString();
    }
}
