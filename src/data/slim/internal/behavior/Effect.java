package data.slim.internal.behavior;

import data.slim.SlimObject;

/**
 * Created by Joost on 06-Mar-17.
 * Effect SLIM object
 */
public class Effect extends SlimObject{
    String name, type, expressionLanguage, expressionBody;

    public Effect(data.xmi.behavior.Effect xmiEffect) {
        this.name = xmiEffect.getName();
        this.type = xmiEffect.getType();
        this.expressionLanguage = xmiEffect.getExpressionLanguage();
        this.expressionBody = xmiEffect.getExpressionBody();
    }

    @Override
    public String toSlimString() {
        StringBuilder sb = new StringBuilder();
        sb.append("then ");
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
