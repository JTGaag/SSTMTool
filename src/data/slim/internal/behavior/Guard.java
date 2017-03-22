package data.slim.internal.behavior;

import data.slim.SlimObject;

/**
 * Created by Joost on 20-Feb-17.
 */
public class Guard extends SlimObject{
    String name, type, expressionLanguage, expressionBody;

    public Guard(data.xmi.behavior.Guard xmiGuard) {
        this.name = xmiGuard.getName();
        this.type = xmiGuard.getType();
        this.expressionLanguage = xmiGuard.getExpressionLanguage();
        this.expressionBody = xmiGuard.getExpressionBody();
    }

    @Override
    public String toSlimString() {
        StringBuilder sb = new StringBuilder();
        sb.append("when ");
        sb.append(expressionBody);
        sb.append(" ");
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getExpressionLanguage() {
        return expressionLanguage;
    }

    public String getExpressionBody() {
        return expressionBody;
    }
}
