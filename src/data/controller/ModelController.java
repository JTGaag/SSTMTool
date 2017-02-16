package data.controller;

import data.slim.components.Component;
import data.xmi.stereotypes.Stereotype;
import data.xmi.stereotypes.sysml.BlockStereotype;
import data.xmi.stereotypes.sysml.FlowPortStereotype;
import data.xmi.stereotypes.sysml.ValueTypeStereotype;
import data.xmi.uml.Class;
import data.xmi.uml.DataType;
import data.xmi.uml.Package;
import data.xmi.PackagedElement;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Joost on 15-Feb-17.
 * Model controller to handle model data
 *
 * Model data:
 * -packagedElements
 * -packageImport
 * -profileApplication
 *
 */
public class ModelController {
    Element model;
    NodeList packagedElementList;

    ArrayList<Package> packages = new ArrayList<>();
    ArrayList<Class> classes = new ArrayList<>();
    ArrayList<Class> signals = new ArrayList<>();
    ArrayList<DataType> dataTypes = new ArrayList<>();

    ArrayList<Component> components = new ArrayList<>();

    public ModelController() {
    }

    public Element getModel() {
        return model;
    }

    public void setModel(Element model) {
        this.model = model;
        packagedElementList = model.getElementsByTagName(PackagedElement.TAG_NAME);

        //DEBUG
        System.out.println("Number of elements: " + packagedElementList.getLength());

        System.out.println("----------------------------");
    }

    public NodeList getPackagedElementList() {
        return packagedElementList;
    }

    public ArrayList<Component> getComponents() {
        return components;
    }

    /**
     * Categorize all the packagedELements in the right category
     *
     * Supported:
     * -Package
     * -Class
     * -DataType
     */
    public void categorizePackagedElements() {
        if (packagedElementList == null) return;

        //Clear all arraylists
        packages.clear();
        classes.clear();
        dataTypes.clear();


        for (int temp = 0; temp < packagedElementList.getLength(); temp++) {

            Node nNode = packagedElementList.item(temp);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element element = (Element) nNode;
                //Switch over all possible types of the packagedElement
                switch (element.getAttribute(PackagedElement.ATTRIBUTE_TYPE)) {
                    case Package.TYPE_NAME:
                        packages.add(new Package(element));
                        break;
                    case Class.TYPE_NAME:
                        classes.add(new Class(element));
                        break;
                    case DataType.TYPE_NAME:
                        dataTypes.add(new DataType(element));
                        break;
                    default:
                        System.err.println("PackagedElement type not supported, Type: " + element.getAttribute(PackagedElement.ATTRIBUTE_TYPE));
                        break;
                }

            }


        }
    }

    /**
     * Apply all the stereotypes to all elements
     * @param stereotypes
     */
    public void addStereotypesToElements(ArrayList<Stereotype> stereotypes) {
        //Loop over all stereotypes and try to add them to Class or Port
        for (Stereotype stereotype: stereotypes) {
            if (stereotype instanceof ValueTypeStereotype) {
                System.out.println("Value Stereotype, id: " + stereotype.getId());
                for (DataType dataType: dataTypes) {
                    if (dataType.addPossibleStereotype((ValueTypeStereotype)stereotype)) break;
                }
            } else if (stereotype instanceof BlockStereotype) {
                for (Class classInstance: classes) {
                    if (classInstance.addPossibleStereotype((BlockStereotype)stereotype)) break;
                }
            } else if (stereotype instanceof FlowPortStereotype) {
                for (Class classInstance: classes) {
                    if (classInstance.addPossibleStereotypeToPorts((FlowPortStereotype)stereotype)) break;
                }
            }
        }
    }

    public void extractSignals() {
        Iterator<Class> iterator = classes.iterator();
        while (iterator.hasNext()) {
            Class cls = iterator.next();
            if (cls.isSignal()) {
                signals.add(cls);
                iterator.remove();
            }
        }
    }

    //Type all ports
    public void typePorts() {
        //Loop over all signals
        for(Class signal: signals) {
            //Loop over all classes
            for(Class cls: classes) {
                cls.setPossibleTypePorts(signal.getId());
            }

        }
    }

    public void transformClassesToComponents() {
        for (Class cls: classes) {
            if (cls.isSlimComponent()) {
                Component component = SLIMComponentCreator.createComponent(cls);
                if (component != null) {
                    components.add(component);
                }
            }
        }
    }

    public void listPackages() {
        System.out.println("-------------------------------------------");
        System.out.println("-----------------Packages------------------");
        System.out.println("-------------------------------------------");
        for (Package pg: packages) {
            System.out.println(pg.toString());
        }
    }

    public void listClasses() {
        System.out.println("-------------------------------------------");
        System.out.println("-----------------Classes-------------------");
        System.out.println("-------------------------------------------");
        for (Class cls: classes) {
            System.out.println(cls.toString());
        }
    }

    public void listDataTypes() {
        System.out.println("-------------------------------------------");
        System.out.println("-----------------DataTypes------------------");
        System.out.println("-------------------------------------------");
        for (DataType dataType: dataTypes) {
            System.out.println(dataType.toString());
        }
    }

    public void listSignals() {
        System.out.println("-------------------------------------------");
        System.out.println("-----------------Signals-------------------");
        System.out.println("-------------------------------------------");
        for (Class cls: signals) {
            System.out.println(cls.toString());
        }
    }

    public void listComponents() {
        System.out.println("-------------------------------------------");
        System.out.println("-----------------Components----------------");
        System.out.println("-------------------------------------------");
        for (Component component: components) {
            System.out.println(component.toSlimString() + "\n\n");
        }
    }

}
