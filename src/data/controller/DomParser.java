package data.controller;

import data.stereotypes.Stereotype;
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

    ArrayList<Package> packages = new ArrayList<>();
    ArrayList<Class> classes = new ArrayList<>();
    ArrayList<Stereotype> stereotypeInstances = new ArrayList<>();

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

        NodeList packagedElementList = ((Element)model).getElementsByTagName(PackagedElement.TAG_NAME);
        System.out.println("Number of elements: " + packagedElementList.getLength());

        System.out.println("----------------------------");

        for (int temp = 0; temp < packagedElementList.getLength(); temp++) {

            Node nNode = packagedElementList.item(temp);


            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;
                if (eElement.getAttribute(PackagedElement.ATTRIBUTE_TYPE).equals(Package.TYPE_NAME)) {
                    packages.add(new Package(eElement.getAttribute(Package.ATTRIBUTE_ID), eElement.getAttribute(Package.ATTRIBUTE_NAME)));
                }else if (eElement.getAttribute(PackagedElement.ATTRIBUTE_TYPE).equals(Class.TYPE_NAME)) { //Is a class
                    classes.add(new Class(eElement.getAttribute(Package.ATTRIBUTE_ID), eElement.getAttribute(Class.ATTRIBUTE_NAME)));
                    System.out.println(classes.get(classes.size()-1).toString());
                    NodeList ownedAttributesList = eElement.getElementsByTagName("ownedAttribute");
                    for (int i=0; i<ownedAttributesList.getLength(); i++) {
                        Element attribute = (Element)ownedAttributesList.item(i);
                        System.out.println("    Attribute: name{ " + attribute.getAttribute("name") + " }, type{ " + attribute.getAttribute("xmi:type") + " }");
                    }
                }

            }


        }

        System.out.println("----------------------------");
        System.out.println("Stereotypes");
        extractStereotypeData(doc);


    }

    private void resetData() {
        packages.clear();
        classes.clear();
        stereotypeInstances.clear();
    }

    private void extractStereotypeData(Document doc) {
        //SysML
        stereotypeInstances.addAll(Stereotypes.getBlockStereotypeInstances(doc));
        stereotypeInstances.addAll(Stereotypes.getFlowPortStereotypeInstances(doc));
        stereotypeInstances.addAll(Stereotypes.getValueTypeStereotypeInstances(doc));
        //OOSEM
        stereotypeInstances.addAll(Stereotypes.getNodePhysicalStereotypeInstances(doc));
        stereotypeInstances.addAll(Stereotypes.getSystemOfInterestStereotypeInstances(doc));
        //SSTM
        stereotypeInstances.addAll(Stereotypes.getDeviceStereotypeInstances(doc));

        //DEBUG
        for (Stereotype stereotype: stereotypeInstances) {
            System.out.println(stereotype.toString());
        }
    }
}
