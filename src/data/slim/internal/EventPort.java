package data.slim.internal;

/**
 * Created by Joost on 16-Feb-17.
 * SLIM event port java object
 */
public class EventPort extends Port {
    public EventPort(data.xmi.uml.Port xmiPort) {
        super(xmiPort);
    }

    @Override
    public String toSlimString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append(": ");
        sb.append(direction);
        sb.append(" even port;");
        return sb.toString();
    }
}
