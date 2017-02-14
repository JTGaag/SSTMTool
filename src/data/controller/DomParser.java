package data.controller;

import data.uml.Class;
import data.uml.Package;
import data.xmi.PackagedElement;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import variables.XMLVariables;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Joost on 14-Feb-17.
 *
 * http://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
 *
 */
public class DomParser {
    File file;
    DocumentBuilderFactory dbFactory;
    DocumentBuilder dBuilder;
    Document doc;

    /**
     * Constructor setting everything up
     */
    public DomParser() {
        dbFactory = DocumentBuilderFactory.newInstance();
        try {
            dBuilder = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Function to read file, Note: File needs to be set first
     */
    public void readFile() {
        if (file == null) return;
        try {
            doc = dBuilder.parse(this.file);
            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();
            debugFile(doc);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setFile(File file) {
        this.file = file;
    }

    private void debugFile(Document doc) {
        System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

        //Get all models
//        NodeList nList = doc.getElementsByTagName(data.xmi.PackagedElement.TAG_NAME);
        NodeList modelList = doc.getElementsByTagName(XMLVariables.MODEL_TAG);
        System.out.println("Number of models: " + modelList.getLength());

        //Get first element (main model and get package elements
        Node model = modelList.item(0);
        NodeList nList = ((Element)model).getElementsByTagName(PackagedElement.TAG_NAME);
        System.out.println("Number of elements: " + nList.getLength());

        System.out.println("----------------------------");

        ArrayList<Package> packages = new ArrayList<>();
        ArrayList<Class> classes = new ArrayList<>();

        for (int temp = 0; temp < nList.getLength(); temp++) {

            Node nNode = nList.item(temp);

//            System.out.println("\nCurrent Element: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;
                if (eElement.getAttribute(PackagedElement.ATTRIBUTE_TYPE).equals(Package.TYPE_NAME)) {
                    packages.add(new Package(eElement.getAttribute(Package.ATTRIBUTE_NAME)));
                }else if (eElement.getAttribute(PackagedElement.ATTRIBUTE_TYPE).equals(Class.TYPE_NAME)) {
                    classes.add(new Class(eElement.getAttribute(Class.ATTRIBUTE_NAME)));
                }

//
//                System.out.println("Element Id : " + eElement.getAttribute(PackagedElement.ATTRIBUTE_ID));
//                System.out.println("Element Type : " + eElement.getAttribute(PackagedElement.ATTRIBUTE_TYPE));
//                System.out.println("Element Name : " + eElement.getAttribute(PackagedElement.ATTRIBUTE_NAME));
            }


        }

        for (Class classElement: classes) {
            System.out.println(classElement.toString());
        }

    }
}
