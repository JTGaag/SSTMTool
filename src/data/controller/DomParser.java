package data.controller;

import data.xmi.stereotypes.Stereotype;
import data.xmi.structure.Class;
import data.xmi.structure.Package;
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

    ModelController mainModelController = new ModelController();

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

        mainModelController.setModel((Element)model);
        //Add stereotypes
        mainModelController.setStereotypes(StereotypesController.getAllsupportedStereotypes(doc));

        mainModelController.transformModel();


        mainModelController.listPackages();
        mainModelController.listClasses();
        mainModelController.listDataTypes();
        mainModelController.listSignals();
        mainModelController.listComponents();

    }

    private void resetData() {
        packages.clear();
        classes.clear();
        stereotypeInstances.clear();
    }

    private void extractStereotypeData(Document doc) {
        stereotypeInstances = StereotypesController.getAllsupportedStereotypes(doc);
    }

    /**
     * Quick debug fuinction (should be better on high level)
     * @return
     */
    public String getSlimText() {
        System.out.println("Get SLIM in DOM parser");
        return mainModelController.getSlimText();
    }
}
