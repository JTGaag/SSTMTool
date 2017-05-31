package data.controller;

import data.slim.components.Component;
import data.slim.error.ErrorComponent;
import data.slim.internal.structure.Clock;
import data.xmi.stereotypes.Stereotype;
import data.xmi.stereotypes.error.ErrorEffectStereotype;
import data.xmi.stereotypes.error.ErrorEventStereotype;
import data.xmi.stereotypes.error.ErrorStateStereotype;
import data.xmi.stereotypes.fdir.FdirPortStereotype;
import data.xmi.stereotypes.properties.PatternPropertyStereotype;
import data.xmi.stereotypes.sstm.SstmStateStereotype;
import data.xmi.stereotypes.sysml.BlockStereotype;
import data.xmi.stereotypes.sysml.FlowPortStereotype;
import data.xmi.stereotypes.sysml.StateStereotype;
import data.xmi.stereotypes.sysml.ValueTypeStereotype;
import data.xmi.structure.Class;
import data.xmi.structure.DataType;
import data.xmi.structure.Enumeration;
import data.xmi.structure.Package;
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

    public interface ModelControllerInterface {
        void writeToLog(String logString);
    }


    Element model;
    ArrayList<Stereotype> stereotypes = new ArrayList<>();

    NodeList packagedElementList;

    //UML / SysML
    ArrayList<Package> packages = new ArrayList<>();
    ArrayList<Class> classes = new ArrayList<>();
    ArrayList<Class> signals = new ArrayList<>();
    ArrayList<DataType> dataTypes = new ArrayList<>();
    ArrayList<Enumeration> enumerations = new ArrayList<>();

    //SLIM
    ArrayList<Component> components = new ArrayList<>();
    ArrayList<ErrorComponent> errorComponents = new ArrayList<>();
    ArrayList<Clock> clocks = new ArrayList<>();

    ModelControllerInterface modelControllerInterface;

    public ModelController(ModelControllerInterface modelControllerInterface) {
        this.modelControllerInterface = modelControllerInterface;
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

    public void setStereotypes(ArrayList<Stereotype> stereotypes) {
        this.stereotypes = stereotypes;
    }

    public void transformModel() {
        categorizePackagedElements();
        addStereotypesToElements();
        extractSignals();
        typePorts();
//        typeProperties();
        transformClassesToComponents();
        createSubcomponents();
        createClockSubcomponents();
        finalizeTransitions();
        finalizeConnections();
        finalizeFlows();
        finalizeErrorEffects();
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
    private void categorizePackagedElements() {
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
                    case Enumeration.TYPE_NAME:
                        enumerations.add(new Enumeration(element));
                        break;
                    default:
                        System.err.println("PackagedElement type not supported, Type: " + element.getAttribute(PackagedElement.ATTRIBUTE_TYPE));
                        break;
                }

            }


        }

        modelControllerInterface.writeToLog("Packaged elements categorized");
    }

    /**
     * Apply all the stereotypes to all elements
     */
    private void addStereotypesToElements() {
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
            } else if (stereotype instanceof SstmStateStereotype || stereotype instanceof ErrorStateStereotype) {
                for (Class classInstance: classes) {
                    if (classInstance.addPossibleStereotypeToStates((StateStereotype)stereotype)) break;
                }
            } else if (stereotype instanceof FdirPortStereotype) {
                for (Class classInstance: classes) {
                    if (classInstance.addPossibleFDIRStereotypeToPorts((FdirPortStereotype) stereotype)) break;
                }
            } else if (stereotype instanceof ErrorEventStereotype) {
                for (Class classInstance: classes) {
                    if (classInstance.isSlimErrorComponent() && classInstance.addPossibleErrorEventStereotypeToPorts((ErrorEventStereotype) stereotype)) break;
                }
            } else if (stereotype instanceof ErrorEffectStereotype) {
                for (Class classInstance: classes) {
                    if (classInstance.addPossibleErrorEffects((ErrorEffectStereotype) stereotype)) break;
                }
            } else if (stereotype instanceof PatternPropertyStereotype) {
                for (Class classInstance: classes) {
                    if (classInstance.addPossiblePatternProperties((PatternPropertyStereotype) stereotype)) break;
                }
            }
        }

        modelControllerInterface.writeToLog("Stereotypes added to elements");
    }

    private void extractSignals() {
        Iterator<Class> iterator = classes.iterator();
        while (iterator.hasNext()) {
            Class cls = iterator.next();
            if (cls.isSignal()) {
                signals.add(cls);
                iterator.remove();
            }
        }

        modelControllerInterface.writeToLog("Signals extracted");
    }

    //Type all ports
    private void typePorts() {
        //Loop over all signals
        for(Class signal: signals) {
            //Loop over all classes
            for(Class cls: classes) {
                cls.setPossibleTypePorts(signal.getId());
            }

        }

        //set enums
        for (Class cls: classes) {
            cls.setPossibleEnums(enumerations);
        }

        modelControllerInterface.writeToLog("Ports typed");
    }

    /**
     * Type all the properties in SLIM component classes
     */
    private void typeProperties() {
        //Loop over all classes to add types to properties
        for (Class cls: classes) {
            //Only for slim components
            if(cls.isSlimComponent()) {
                cls.setPropertiesType(classes);
            }
        }
    }

    private void transformClassesToComponents() {
        components.clear();
        clocks.clear();
        errorComponents.clear();
        for (Class cls: classes) {
            if (cls.isSlimComponent()) {
                Component component = SLIMComponentCreator.createComponent(cls);

                if (component != null) {
                    //put clocks in separate array
                    if (component instanceof Clock) {
                        clocks.add((Clock)component);
                    } else {
                        components.add(component);
                    }
                }
            }

            if (cls.isSlimErrorComponent()) {
                errorComponents.add(new ErrorComponent(cls));
            }
        }

        modelControllerInterface.writeToLog("Classes transformed to SLIM components");
    }

    private void createSubcomponents() {
        for (Component component: components) {
            component.createSubcomponents(components);
        }

        modelControllerInterface.writeToLog("Subcomponents created");
    }

    private void createClockSubcomponents() {
        for (Component component: components) {
            component.createClockSubcomponents(clocks);
        }
        for (ErrorComponent errorComponent: errorComponents) {
            errorComponent.createClockSubcomponents(clocks);
        }

        modelControllerInterface.writeToLog("Clock subcomponents created");
    }

    private void finalizeTransitions() {
        for (Component component: components) {
            component.finalizeTransitions();
        }

        modelControllerInterface.writeToLog("Transitions finalized");
    }

    private void finalizeConnections() {
        for (Component component: components) {
            component.finalizeConnections();
        }

        modelControllerInterface.writeToLog("Connections finalized");
    }

    private void finalizeFlows() {
        for (Component component: components) {
            component.finalizeFlows();
        }

        modelControllerInterface.writeToLog("Flows finalized");
    }


    private void finalizeErrorEffects() {
        for (Component component: components) {
            component.finalizeErrorEffects(errorComponents);
        }

        modelControllerInterface.writeToLog("Error effects finalized");
    }

    public String getSlimTextOrderd() {
        System.out.println("Get SLIM in model controller parser");
        StringBuilder sb = new StringBuilder();
        //Nominal Components
        for (Component component: components) {
            sb.append(component.toSlimTypeString());
            sb.append("\n\n");
            sb.append(component.toSlimImplementationString());
            sb.append("\n\n\n");
        }

        //Errors
        for (ErrorComponent errorComponent: errorComponents) {
            sb.append(errorComponent.toSlimTypeString()).append("\n\n");
            sb.append(errorComponent.toSlimImplementationString()).append("\n\n\n");
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    public String getSlimTextTypesFirst() {
        System.out.println("Get SLIM in model controller parser");
        StringBuilder sb = new StringBuilder();
        //Nominal components
        for (Component component: components) {
            sb.append(component.toSlimTypeString());
            sb.append("\n\n");
        }
        sb.append("\n\n");
        for (Component component: components) {
            sb.append(component.toSlimImplementationString());
            sb.append("\n\n");
        }

        //Errors
        for (ErrorComponent errorComponent: errorComponents) {
            sb.append(errorComponent.toSlimTypeString()).append("\n\n");
        }
        for (ErrorComponent errorComponent: errorComponents) {
            sb.append(errorComponent.toSlimImplementationString()).append("\n\n");
        }
        System.out.println(sb.toString());
        return sb.toString();
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

    public void listEnumerations() {
        System.out.println("-------------------------------------------");
        System.out.println("-----------------Enumerations-------------------");
        System.out.println("-------------------------------------------");
        for (Enumeration enumeration: enumerations) {
            System.out.println(enumeration.toString());
        }
    }

    public void listComponents() {
        System.out.println("-------------------------------------------");
        System.out.println("-----------------Components----------------");
        System.out.println("-------------------------------------------");
        for (Component component: components) {
            System.out.println(component.toSlimTypeString() + "\n\n" + component.toSlimImplementationString() + "\n\n\n");
        }
    }

    public void listErrorComponents() {
        System.out.println("-------------------------------------------");
        System.out.println("--------------Error Components-------------");
        System.out.println("-------------------------------------------");
        for (ErrorComponent errorComponent: errorComponents) {
            System.out.println(errorComponent.toSlimTypeString() + "\n\n" + errorComponent.toSlimImplementationString() + "\n\n\n");
        }
    }

    public void listClocks() {
        System.out.println("-------------------------------------------");
        System.out.println("-----------------Clocks----------------");
        System.out.println("-------------------------------------------");
        for (Clock clock: clocks) {
            System.out.println(clock.toSlimTypeString() + "\n\n" + clock.toSlimImplementationString() + "\n\n\n");
        }
    }

}
