package data.xmi.structure;

import data.xmi.OwnedComment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import utils.NodeHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Joost on 14-Feb-17.
 */
public class Flow extends OwnedComment{

    public static final String ATTRIBUTE_TYPE = "type";
    public static final String ATTRIBUTE_ANNOTATED_ELEMENT_ID = "annotatedElement";

    public static final String SPECIFICATION_TAG_NAME = "specification";
    public static final String BODY_TAG_NAME = "body";

    public static final String OWNED_COMMEND_TAG = "ownedComment";
    public static final String COMMEND_BODY_TAG_NAME = "body";



    private String expression;

    private ArrayList<String> flowModeList = new ArrayList<>();
    private ArrayList<String> constraintElementIds = new ArrayList<>();

    public Flow(Element constraintElement) {
        super(constraintElement.getAttribute(ATTRIBUTE_ID));
        System.out.println("CREATE FLOW");
        extractConstraintElementIds(constraintElement);
        extractExpression(constraintElement);
        extractFlowModes(constraintElement);
    }

    private void extractConstraintElementIds(Element commentElement) {
        String constraintElementIdsString = commentElement.getAttribute(ATTRIBUTE_ANNOTATED_ELEMENT_ID);
        //Pattern to find data
        String[] ids = constraintElementIdsString.split(" ");
        constraintElementIds = new ArrayList<>(Arrays.asList(ids));
        for (String id: constraintElementIds) {
            System.out.println("ConnectedId: " + id);
        }
    }

    private void extractExpression(Element commentElement) {
        ArrayList<Element> bodyElement = NodeHelper.getChildrenByTagName(commentElement, BODY_TAG_NAME);

        this.expression = bodyElement.get(0).getTextContent();
        this.expression = this.expression.replace("self.", "");
    }

    private void extractFlowModes(Element commentElement) {
        NodeList ownedCommendList = commentElement.getElementsByTagName(OWNED_COMMEND_TAG);
        for (int i=0; i<ownedCommendList.getLength(); i++) {
            Element commendElement = (Element)ownedCommendList.item(i);
            String commendBody = commendElement.getElementsByTagName(COMMEND_BODY_TAG_NAME).item(0).getTextContent();

            //Pattern to find data
            Pattern pattern = Pattern.compile("\\{@link #(.*?)\\}");
            Matcher matcher = pattern.matcher(commendBody);
            while (matcher.find()) {
                System.out.println("Mode for flow: " + matcher.group(1));
                flowModeList.add(matcher.group(1));
            }
        }
    }

    public String getExpression() {
        return expression;
    }

    public ArrayList<String> getFlowModeList() {
        return flowModeList;
    }

    public ArrayList<String> getConstraintElementIds() {
        return constraintElementIds;
    }
}
