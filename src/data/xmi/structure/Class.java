package data.xmi.structure;

import com.sun.istack.internal.Nullable;
import data.xmi.OwnedAttribute;
import data.xmi.OwnedBehavior;
import data.xmi.OwnedConnector;
import data.xmi.PackagedElement;
import data.xmi.behavior.State;
import data.xmi.behavior.StateMachine;
import data.xmi.error.ErrorEffect;
import data.xmi.properties.PatternProperty;
import data.xmi.stereotypes.Stereotype;
import data.xmi.stereotypes.error.ErrorEffectStereotype;
import data.xmi.stereotypes.error.ErrorEventStereotype;
import data.xmi.stereotypes.error.ErrorModelStereotype;
import data.xmi.stereotypes.fdir.FdirComponentStereotype;
import data.xmi.stereotypes.fdir.FdirPortStereotype;
import data.xmi.stereotypes.properties.PatternPropertyStereotype;
import data.xmi.stereotypes.sstm.SLIMComponentStereotype;
import data.xmi.stereotypes.sstm.SignalStereotype;
import data.xmi.stereotypes.sstm.SstmStateStereotype;
import data.xmi.stereotypes.sysml.BlockStereotype;
import data.xmi.stereotypes.sysml.FlowPortStereotype;
import data.xmi.stereotypes.sysml.StateStereotype;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import utils.NodeHelper;

import java.util.ArrayList;

/**
 * Created by Joost on 14-Feb-17.
 */
public class Class extends PackagedElement {
    public static final String TYPE_NAME = "uml:Class";

    private String name, errorStereotypeId;
    private ArrayList<Property> properties = new ArrayList<>();
    private ArrayList<Port> ports = new ArrayList<>();
    private ArrayList<Flow> flows = new ArrayList<>();
    private ArrayList<OwnedConnector> connectors = new ArrayList<>();

    //Possible stereotypes
    private ArrayList<Stereotype> stereotypes = new ArrayList<>();
    private ArrayList<ErrorEffect> errorEffects = new ArrayList<>();
    private ArrayList<PatternProperty> patternProperties = new ArrayList<>();

    private StateMachine stateMachine;

    private boolean fdirComponent = false;

    /**
     * Constructor
     * @param classElement representing the class
     */
    public Class(Element classElement) {
        super(classElement.getAttribute(ATTRIBUTE_ID));
        this.name = classElement.getAttribute(ATTRIBUTE_NAME);

        //Attributes (ports and parts)
        createAttributes(classElement);

        //Flows using constraints linked to ports
        createFlows(classElement);

        //Connectors
        createConnectors(classElement);

        //State machine
        createStateMachine(classElement);


    }


    private void createAttributes(Element classElement) {
        NodeList ownedAttributesList = classElement.getElementsByTagName(OwnedAttribute.TAG_NAME);
        for (int i=0; i<ownedAttributesList.getLength(); i++) {
            Element attribute = (Element)ownedAttributesList.item(i);

            switch (attribute.getAttribute(OwnedAttribute.ATTRIBUTE_TYPE)) {
                case Property.TYPE_NAME:
                    properties.add(new Property(attribute));
                    break;
                case Port.TYPE_NAME:
                    ports.add(new Port(attribute));
                    break;
                default:

                    break;
            }
        }
    }

    /**
     * Create flows for
     * @param classElement
     */
    private void createFlows(Element classElement) {
        ArrayList<Element> commentElementList = NodeHelper.getChildrenByTagName(classElement, Flow.TAG_NAME);
        System.out.println("CLASS packagedElementList size: " + commentElementList.size());
        for (Element commentElement: commentElementList) {
            flows.add(new Flow(commentElement));
        }
    }

    private void createConnectors(Element classElement) {
        NodeList ownedConnectorsList = classElement.getElementsByTagName(OwnedConnector.TAG_NAME);
        for (int i=0; i<ownedConnectorsList.getLength(); i++) {
            connectors.add(new OwnedConnector((Element)ownedConnectorsList.item(i)));
        }
    }

    /**
     * Create stateMachine from class element
     * TODO: only one stateMachine can be made (will overwrite multiple and probably fuck up everything if more then one is created)
     * @param classElement
     */
    private void createStateMachine(Element classElement) {
        NodeList ownedBehaviorList = classElement.getElementsByTagName(OwnedBehavior.TAG_NAME);
        for (int i=0; i<ownedBehaviorList.getLength(); i++) {
            Element behavior = (Element)ownedBehaviorList.item(i);
            switch (behavior.getAttribute(OwnedBehavior.ATTRIBUTE_TYPE)) {
                case StateMachine.TYPE_NAME:
                    stateMachine = new StateMachine(behavior);
                    break;
                default:
                    break;
            }
        }


    }

    public String getName() {
        return name;
    }

    public ArrayList<Property> getProperties() {
        return properties;
    }

    public ArrayList<Port> getPorts() {
        return ports;
    }

    public ArrayList<Flow> getFlows() {
        return flows;
    }

    public ArrayList<OwnedConnector> getConnectors() {
        return connectors;
    }

    public ArrayList<Stereotype> getStereotypes() {
        return stereotypes;
    }

    public StateMachine getStateMachine() {
        return stateMachine;
    }

    public boolean isFdirComponent() {
        return fdirComponent;
    }

    public String getErrorStereotypeId() {
        return errorStereotypeId;
    }

    public ArrayList<ErrorEffect> getErrorEffects() {
        return errorEffects;
    }

    public ArrayList<PatternProperty> getPatternProperties() {
        return patternProperties;
    }

    public boolean addPossibleStereotype(BlockStereotype stereotype) {
        if (stereotype.getBaseClassId().equals(this.getId())) {
            this.stereotypes.add(stereotype);

            if (stereotype instanceof FdirComponentStereotype) fdirComponent = true;
            if (stereotype instanceof ErrorModelStereotype) errorStereotypeId = stereotype.getId();

            return true;
        }
        return false;
    }

    public boolean addPossibleStereotypeToPorts(FlowPortStereotype stereotype) {
        for (Port port: ports) {
            if(port.addPossibleStereotype(stereotype)) return true;
        }
        return false;
    }

    public boolean addPossibleFDIRStereotypeToPorts(FdirPortStereotype stereotype) {
        for (Port port: ports) {
            if(port.addPossibleFDIRStereotypes(stereotype)) return true;
        }
        return false;
    }

    public boolean addPossibleErrorEventStereotypeToPorts(ErrorEventStereotype errorEventStereotype) {
        for (Port port: ports) {
            if(port.addPossibleErrorEventStereotypes(errorEventStereotype)) return true;
        }
        return false;
    }

    /**
     * Add stereotypes to states
     * @param stateStereotype
     * @return
     */
    public boolean addPossibleStereotypeToStates(StateStereotype stateStereotype) {
        if (stateMachine == null || stateMachine.getRegion() == null) return false;
        for (State state: stateMachine.getRegion().getStates()) {
            if (state.addPossibleStereotype(stateStereotype)) return true;
        }
        return false;
    }

    public void setPossibleTypePorts(String signalId) {
        for (Port port: ports) {
            port.setPossibleTypePort(signalId);
        }
    }

    public void setPossibleEnums(ArrayList<Enumeration> enums) {
        for (Port port: ports) {
            port.setPossibleEnums(enums);
        }
    }

    public void setPropertiesType(ArrayList<Class> classes) {
        for (Property property: properties) {
            property.setType(classes);
        }
    }

    public boolean addPossibleErrorEffects(ErrorEffectStereotype errorEffectStereotype) {
        for (Property property: properties) {
            if (property.getId().equals(errorEffectStereotype.getBasePropertyId())) { //property is error effect
                errorEffects.add(new ErrorEffect(errorEffectStereotype));
                return true;
            }
        }
        return false;
    }

    public boolean addPossiblePatternProperties(PatternPropertyStereotype patternPropertyStereotype) {
        for (Property property: properties) {
            if (property.getId().equals(patternPropertyStereotype.getBasePropertyId())) { //property is error effect
                patternProperties.add(new PatternProperty(property, patternPropertyStereotype));
                return true;
            }
        }
        return false;
    }



    /**
     * Function to derermine if class is a signal block
     * @return
     */
    public boolean isSignal() {

        for (Stereotype stereotype: stereotypes) {
            if (stereotype instanceof SignalStereotype) {
                return true;
            }
        }
        return false;
    }

    /**
     * Function to determine if class is a SLIM component
     * @return if class is (and can be transformed to) SLIM component
     */
    public boolean isSlimComponent() {
        for (Stereotype stereotype: stereotypes) {
            if (stereotype instanceof SLIMComponentStereotype) return true;
        }
        return false;
    }

    /**
     * Function to determine if class is a SLIM ERROR component
     * @return if class is (and can be transformed to) SLIM component
     */
    public boolean isSlimErrorComponent() {
        for (Stereotype stereotype: stereotypes) {
            if (stereotype instanceof ErrorModelStereotype) return true;
        }
        return false;
    }

    /**
     * Get the SLIM Component Stereotype
     * @return
     */
    @Nullable
    public SLIMComponentStereotype getSLIMComponentStereotype() {
        for (Stereotype stereotype: stereotypes) {
            if (stereotype instanceof SLIMComponentStereotype) return (SLIMComponentStereotype)stereotype;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Port port: ports) {
            stringBuilder.append("      ");
            stringBuilder.append(port.toString());
            stringBuilder.append("\n");
        }
        return "Class: " + name +
                "\n     Nr of Properties: " + properties.size() +
                "\n     Nr of Ports: " + ports.size() +
                "\n     Signal?: " + isSignal() +
                "\n     SLIM Component?: " + isSlimComponent() +
                "\n     SLIM Error?: " + isSlimErrorComponent() +
                "\n     Ports: \n" + stringBuilder.toString();
    }

}
