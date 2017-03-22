package data.xmi;

import data.xmi.behavior.ConnectorEnd;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Joost on 14-Feb-17.
 *
 * Basic connector element connecting to OwnedAttributes
 */
public class OwnedConnector extends XMIObject {
    public static final String TAG_NAME = "ownedConnector";
    public static final String ATTRIBUTE_TYPE = "xmi:type";
    public static final String ATTRIBUTE_NAME = "xmi:name";

    public static final String OWNED_COMMEND_TAG = "ownedComment";
    public static final String COMMEND_BODY_TAG_NAME = "body";

    String name, type;

    ArrayList<ConnectorEnd> connectorEnds = new ArrayList<>();
    ArrayList<String> connectorModeList = new ArrayList<>();

    public OwnedConnector(Element connectorElement) {
        super(connectorElement.getAttribute(ATTRIBUTE_ID));
        this.name = connectorElement.getAttribute(ATTRIBUTE_NAME);
        this.type = connectorElement.getAttribute(ATTRIBUTE_TYPE);
        getEnds(connectorElement);
        getConnectorModes(connectorElement);
    }

    private void getConnectorModes(Element connectorElement) {
        NodeList ownedCommendList = connectorElement.getElementsByTagName(OWNED_COMMEND_TAG);
        for (int i=0; i<ownedCommendList.getLength(); i++) {
            Element commendElement = (Element)ownedCommendList.item(i);
            String commendBody = commendElement.getElementsByTagName(COMMEND_BODY_TAG_NAME).item(0).getTextContent();

            //Pattern to find data
            Pattern pattern = Pattern.compile("\\{@link #(.*?)\\}");
            Matcher matcher = pattern.matcher(commendBody);
            while (matcher.find()) {
                System.out.println("Mode: " + matcher.group(1));
                connectorModeList.add(matcher.group(1));
            }
        }
    }

    private void getEnds(Element connectorElement) {
        NodeList endList = connectorElement.getElementsByTagName(ConnectorEnd.TAG_NAME);
        for (int i=0; i< endList.getLength(); i++) {
            connectorEnds.add(new ConnectorEnd((Element)endList.item(i)));
        }
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public ArrayList<ConnectorEnd> getConnectorEnds() {
        return connectorEnds;
    }

    public ArrayList<String> getConnectorModeList() {
        return connectorModeList;
    }
}
