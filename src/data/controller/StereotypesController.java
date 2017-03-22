package data.controller;

import data.xmi.stereotypes.Stereotype;
import data.xmi.stereotypes.oosem.NodePhysicalStereotype;
import data.xmi.stereotypes.oosem.SystemOfInterestStereotype;
import data.xmi.stereotypes.sstm.*;
import data.xmi.stereotypes.sysml.BlockStereotype;
import data.xmi.stereotypes.sysml.FlowPortStereotype;
import data.xmi.stereotypes.sysml.ValueTypeStereotype;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

/**
 * Created by Joost on 15-Feb-17.
 */
public class StereotypesController {


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
        allStereotypes.addAll(getSystemStereotypeInstances(doc));
        allStereotypes.addAll(getProcessorStereotypeInstances(doc));
        allStereotypes.addAll(getSignalStereotypeInstances(doc));
        allStereotypes.addAll(getClockStereotypeInstances(doc));
        allStereotypes.addAll(getSstmStateStereotypeInstances(doc)); //SSTM State stereotype

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
                blockStereotypeInstances.add(new BlockStereotype(blockElement));
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
                flowPortStereotypeInstances.add(new FlowPortStereotype(flowPortElement));
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
                valueTypeStereotypeInstances.add(new ValueTypeStereotype(valueTypeElement));
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
                nodePhysicalStereotypeInstances.add(new NodePhysicalStereotype(nodePhysicalElement));
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
                systemOfInterestStereotypeInstances.add(new SystemOfInterestStereotype(systemOfInterestElement));
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
                deviceStereotypeInstances.add(new DeviceStereotype(deviceElement));
            }
        }
        return deviceStereotypeInstances;
    }

    public static ArrayList<SystemStereotype> getSystemStereotypeInstances(Document doc) {
        ArrayList<SystemStereotype> systemStereotypeInstances= new ArrayList<>();
        NodeList systemStereotypesList = doc.getElementsByTagName(SystemStereotype.TAG_NAME);
        for (int i=0; i<systemStereotypesList.getLength(); i++) {
            Node systemNode = systemStereotypesList.item(i);
            if (systemNode.getNodeType() == Node.ELEMENT_NODE) {
                Element systemElement = (Element)systemNode;
                systemStereotypeInstances.add(new SystemStereotype(systemElement));
            }
        }
        return systemStereotypeInstances;
    }

    public static ArrayList<ProcessorStereotype> getProcessorStereotypeInstances(Document doc) {
        ArrayList<ProcessorStereotype> processorStereotypeInstances= new ArrayList<>();
        NodeList processorStereotypesList = doc.getElementsByTagName(ProcessorStereotype.TAG_NAME);
        for (int i=0; i<processorStereotypesList.getLength(); i++) {
            Node processorNode = processorStereotypesList.item(i);
            if (processorNode.getNodeType() == Node.ELEMENT_NODE) {
                Element processorElement = (Element)processorNode;
                processorStereotypeInstances.add(new ProcessorStereotype(processorElement));
            }
        }
        return processorStereotypeInstances;
    }

    public static ArrayList<SignalStereotype> getSignalStereotypeInstances(Document doc) {
        ArrayList<SignalStereotype> signalStereotypeInstances= new ArrayList<>();
        NodeList signalStereotypesList = doc.getElementsByTagName(SignalStereotype.TAG_NAME);
        for (int i=0; i<signalStereotypesList.getLength(); i++) {
            Node signalNode = signalStereotypesList.item(i);
            if (signalNode.getNodeType() == Node.ELEMENT_NODE) {
                Element signalElement = (Element)signalNode;
                signalStereotypeInstances.add(new SignalStereotype(signalElement));
            }
        }
        return signalStereotypeInstances;
    }

    public static ArrayList<ClockStereotype> getClockStereotypeInstances(Document doc) {
        ArrayList<ClockStereotype> clockStereotypeInstances= new ArrayList<>();
        NodeList clockStereotypesList = doc.getElementsByTagName(ClockStereotype.TAG_NAME);
        for (int i=0; i<clockStereotypesList.getLength(); i++) {
            Node clockNode = clockStereotypesList.item(i);
            if (clockNode.getNodeType() == Node.ELEMENT_NODE) {
                Element clockElement = (Element)clockNode;
                clockStereotypeInstances.add(new ClockStereotype(clockElement));
            }
        }
        return clockStereotypeInstances;
    }

    public static ArrayList<SstmStateStereotype> getSstmStateStereotypeInstances(Document doc) {
        ArrayList<SstmStateStereotype> sstmStateStereotypeInstances= new ArrayList<>();
        NodeList sstmStateStereotypesList = doc.getElementsByTagName(SstmStateStereotype.TAG_NAME);
        for (int i=0; i<sstmStateStereotypesList.getLength(); i++) {
            Node sstmStateNode = sstmStateStereotypesList.item(i);
            if (sstmStateNode.getNodeType() == Node.ELEMENT_NODE) {
                Element sstmStateElement = (Element)sstmStateNode;
                sstmStateStereotypeInstances.add(new SstmStateStereotype(sstmStateElement));
            }
        }
        return sstmStateStereotypeInstances;
    }

}
