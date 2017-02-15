package data.controller;

import data.stereotypes.Stereotype;
import data.stereotypes.oosem.NodePhysicalStereotype;
import data.stereotypes.oosem.SystemOfInterestStereotype;
import data.stereotypes.sstm.DeviceStereotype;
import data.stereotypes.sysml.BlockStereotype;
import data.stereotypes.sysml.FlowPortStereotype;
import data.stereotypes.sysml.ValueTypeStereotype;
import data.xmi.PackagedElement;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

/**
 * Created by Joost on 15-Feb-17.
 */
public class Stereotypes {


    ///////////////////////////////////////////////////
    //General methods
    ///////////////////////////////////////////////////
    public static ArrayList<Stereotype> getAllsupportedStereotypes(Document doc) {
        ArrayList<Stereotype> allStereotypes = new ArrayList<>();
        //SysML
        allStereotypes.addAll(getBlockStereotypeInstances(doc));
        allStereotypes.addAll(getFlowPortStereotypeInstances(doc));
        allStereotypes.addAll(getValueTypeStereotypeInstances(doc));
        //OOSEM
        allStereotypes.addAll(getNodePhysicalStereotypeInstances(doc));
        allStereotypes.addAll(getSystemOfInterestStereotypeInstances(doc));
        //SSTM
        allStereotypes.addAll(getDeviceStereotypeInstances(doc));

        return allStereotypes;
    }


    ///////////////////////////////////////////////////
    //SysML
    ///////////////////////////////////////////////////

    public static ArrayList<BlockStereotype> getBlockStereotypeInstances(Document doc) {
        ArrayList<BlockStereotype> blockStereotypeInstances= new ArrayList<>();
        NodeList blockStereotypesList = doc.getElementsByTagName(BlockStereotype.TAG_NAME);
        for (int i=0; i<blockStereotypesList.getLength(); i++) {
            Node blockNode = blockStereotypesList.item(i);
            if (blockNode.getNodeType() == Node.ELEMENT_NODE) {
                Element blockElement = (Element)blockNode;
                blockStereotypeInstances.add(new BlockStereotype(blockElement.getAttribute(BlockStereotype.ATTRIBUTE_ID), blockElement.getAttribute(BlockStereotype.ATTRIBUTE_BASE_CLASS)));
            }
        }
        return blockStereotypeInstances;
    }

    public static ArrayList<FlowPortStereotype> getFlowPortStereotypeInstances(Document doc) {
        ArrayList<FlowPortStereotype> flowPortStereotypeInstances= new ArrayList<>();
        NodeList flowPortStereotypesList = doc.getElementsByTagName(FlowPortStereotype.TAG_NAME);
        for (int i=0; i<flowPortStereotypesList.getLength(); i++) {
            Node flowPortNode = flowPortStereotypesList.item(i);
            if (flowPortNode.getNodeType() == Node.ELEMENT_NODE) {
                Element flowPortElement = (Element)flowPortNode;
                flowPortStereotypeInstances.add(new FlowPortStereotype(flowPortElement.getAttribute(FlowPortStereotype.ATTRIBUTE_ID), flowPortElement.getAttribute(FlowPortStereotype.ATTRIBUTE_BASE_PORT), flowPortElement.getAttribute(FlowPortStereotype.ATTRIBUTE_DIRECTION)));
            }
        }
        return flowPortStereotypeInstances;
    }

    public static ArrayList<ValueTypeStereotype> getValueTypeStereotypeInstances(Document doc) {
        ArrayList<ValueTypeStereotype> valueTypeStereotypeInstances= new ArrayList<>();
        NodeList valueTypeStereotypesList = doc.getElementsByTagName(ValueTypeStereotype.TAG_NAME);
        for (int i=0; i<valueTypeStereotypesList.getLength(); i++) {
            Node valueTypeNode = valueTypeStereotypesList.item(i);
            if (valueTypeNode.getNodeType() == Node.ELEMENT_NODE) {
                Element valueTypeElement = (Element)valueTypeNode;
                valueTypeStereotypeInstances.add(new ValueTypeStereotype(valueTypeElement.getAttribute(ValueTypeStereotype.ATTRIBUTE_ID), valueTypeElement.getAttribute(ValueTypeStereotype.ATTRIBUTE_BASE_DATA_TYPE), valueTypeElement.getAttribute(ValueTypeStereotype.ATTRIBUTE_UNIT)));
            }
        }
        return valueTypeStereotypeInstances;
    }


    ///////////////////////////////////////////////////
    //OOSEM
    ///////////////////////////////////////////////////

    public static ArrayList<NodePhysicalStereotype> getNodePhysicalStereotypeInstances(Document doc) {
        ArrayList<NodePhysicalStereotype> nodePhysicalStereotypeInstances= new ArrayList<>();
        NodeList nodePhysicalStereotypesList = doc.getElementsByTagName(NodePhysicalStereotype.TAG_NAME);
        for (int i=0; i<nodePhysicalStereotypesList.getLength(); i++) {
            Node nodePhysicalNode = nodePhysicalStereotypesList.item(i);
            if (nodePhysicalNode.getNodeType() == Node.ELEMENT_NODE) {
                Element nodePhysicalElement = (Element)nodePhysicalNode;
                nodePhysicalStereotypeInstances.add(new NodePhysicalStereotype(nodePhysicalElement.getAttribute(NodePhysicalStereotype.ATTRIBUTE_ID), nodePhysicalElement.getAttribute(NodePhysicalStereotype.ATTRIBUTE_BASE_CLASS)));
            }
        }
        return nodePhysicalStereotypeInstances;
    }

    public static ArrayList<SystemOfInterestStereotype> getSystemOfInterestStereotypeInstances(Document doc) {
        ArrayList<SystemOfInterestStereotype> systemOfInterestStereotypeInstances= new ArrayList<>();
        NodeList systemOfInterestStereotypesList = doc.getElementsByTagName(SystemOfInterestStereotype.TAG_NAME);
        for (int i=0; i<systemOfInterestStereotypesList.getLength(); i++) {
            Node systemOfInterestNode = systemOfInterestStereotypesList.item(i);
            if (systemOfInterestNode.getNodeType() == Node.ELEMENT_NODE) {
                Element systemOfInterestElement = (Element)systemOfInterestNode;
                systemOfInterestStereotypeInstances.add(new SystemOfInterestStereotype(systemOfInterestElement.getAttribute(SystemOfInterestStereotype.ATTRIBUTE_ID), systemOfInterestElement.getAttribute(SystemOfInterestStereotype.ATTRIBUTE_BASE_CLASS)));
            }
        }
        return systemOfInterestStereotypeInstances;
    }


    ///////////////////////////////////////////////////
    //SSTM
    ///////////////////////////////////////////////////

    public static ArrayList<DeviceStereotype> getDeviceStereotypeInstances(Document doc) {
        ArrayList<DeviceStereotype> deviceStereotypeInstances= new ArrayList<>();
        NodeList deviceStereotypesList = doc.getElementsByTagName(DeviceStereotype.TAG_NAME);
        for (int i=0; i<deviceStereotypesList.getLength(); i++) {
            Node deviceNode = deviceStereotypesList.item(i);
            if (deviceNode.getNodeType() == Node.ELEMENT_NODE) {
                Element deviceElement = (Element)deviceNode;
                deviceStereotypeInstances.add(new DeviceStereotype(deviceElement.getAttribute(DeviceStereotype.ATTRIBUTE_ID), deviceElement.getAttribute(DeviceStereotype.ATTRIBUTE_BASE_CLASS)));
            }
        }
        return deviceStereotypeInstances;
    }

}
