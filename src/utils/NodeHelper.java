package utils;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.ArrayList;

/**
 * Created by Joost on 21-Mar-17.
 */
public class NodeHelper {
    public static ArrayList<Element> getChildrenByTagName(Element parent, String name) {
        ArrayList<Element> nodeList = new ArrayList<Element>();
        for (Node child = parent.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child.getNodeType() == Node.ELEMENT_NODE && name.equals(child.getNodeName())) {
                nodeList.add((Element) child);
            }
        }

        return nodeList;
    }
}
