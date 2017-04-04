package data.controller;

import data.xmi.stereotypes.Stereotype;
import data.xmi.stereotypes.error.ErrorEffectStereotype;
import data.xmi.stereotypes.error.ErrorEventStereotype;
import data.xmi.stereotypes.error.ErrorModelStereotype;
import data.xmi.stereotypes.error.ErrorStateStereotype;
import data.xmi.stereotypes.fdir.AlarmStereotype;
import data.xmi.stereotypes.fdir.FdirComponentStereotype;
import data.xmi.stereotypes.fdir.ObservableStereotype;
import data.xmi.stereotypes.oosem.NodePhysicalStereotype;
import data.xmi.stereotypes.oosem.SystemOfInterestStereotype;
import data.xmi.stereotypes.properties.*;
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
        allStereotypes.addAll(getBusStereotypeInstances(doc));
        allStereotypes.addAll(getThreadStereotypeInstances(doc));
        allStereotypes.addAll(getMemoryStereotypeInstances(doc));
        allStereotypes.addAll(getSignalStereotypeInstances(doc));
        allStereotypes.addAll(getClockStereotypeInstances(doc));
        allStereotypes.addAll(getSstmStateStereotypeInstances(doc)); //SSTM State stereotype

        //FDIR
        allStereotypes.addAll(getFdirComponentStereotypeInstances(doc)); //FDIR Component
        allStereotypes.addAll(getAlarmStereotypeInstances(doc)); //FDIR Alarms
        allStereotypes.addAll(getObservableStereotypeInstances(doc)); //FDIR Observables

        //ERROR Shine
        allStereotypes.addAll(getErrorModelStereotypeInstances(doc)); //Error Model
        allStereotypes.addAll(getErrorEventStereotypeInstances(doc)); //Error Event
        allStereotypes.addAll(getErrorStateStereotypeInstances(doc)); //Error State
        allStereotypes.addAll(getErrorEffectStereotypeInstances(doc)); //Error Effect

        //Pattern properties
        allStereotypes.addAll(getPatternPropertyStereotypeInstances(doc)); //Pattern property parent stereotype
        allStereotypes.addAll(getUniversalityPatternStereotypeInstances(doc)); //Universality Pattern
        allStereotypes.addAll(getAbsencePatternStereotypeInstances(doc)); //Absence Pattern
        allStereotypes.addAll(getExistencePatternStereotypeInstances(doc)); //Existence Pattern
        allStereotypes.addAll(getRecurrencePatternStereotypeInstances(doc)); //Recurrence Pattern
        allStereotypes.addAll(getPrecedencePatternStereotypeInstances(doc)); //Precedence Pattern
        allStereotypes.addAll(getResponsePatternStereotypeInstances(doc)); //Response Pattern
        allStereotypes.addAll(getResponseInvariancePatternStereotypeInstances(doc)); //Response Invariance Pattern
        allStereotypes.addAll(getUntilPatternStereotypeInstances(doc)); //Until Pattern

        return allStereotypes;
    }


    ///////////////////////////////////////////////////
    //SysML
    ///////////////////////////////////////////////////

    private static ArrayList<BlockStereotype> getBlockStereotypeInstances(Document doc) {
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

    private static ArrayList<FlowPortStereotype> getFlowPortStereotypeInstances(Document doc) {
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

    private static ArrayList<ValueTypeStereotype> getValueTypeStereotypeInstances(Document doc) {
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

    private static ArrayList<NodePhysicalStereotype> getNodePhysicalStereotypeInstances(Document doc) {
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

    private static ArrayList<SystemOfInterestStereotype> getSystemOfInterestStereotypeInstances(Document doc) {
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

    private static ArrayList<DeviceStereotype> getDeviceStereotypeInstances(Document doc) {
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

    private static ArrayList<SystemStereotype> getSystemStereotypeInstances(Document doc) {
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

    private static ArrayList<ProcessorStereotype> getProcessorStereotypeInstances(Document doc) {
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

    private static ArrayList<BusStereotype> getBusStereotypeInstances(Document doc) {
        ArrayList<BusStereotype> busStereotypeInstances = new ArrayList<>();
        NodeList busStereotypesList = doc.getElementsByTagName(BusStereotype.TAG_NAME);
        for (int i=0; i<busStereotypesList.getLength(); i++) {
            if (busStereotypesList.item(i).getNodeType() == Node.ELEMENT_NODE) busStereotypeInstances.add(new BusStereotype((Element)busStereotypesList.item(i)));
        }
        return busStereotypeInstances;
    }

    private static ArrayList<ThreadStereotype> getThreadStereotypeInstances(Document doc) {
        ArrayList<ThreadStereotype> stereotypeInstances = new ArrayList<>();
        NodeList stereotypesList = doc.getElementsByTagName(ThreadStereotype.TAG_NAME);
        for (int i=0; i<stereotypesList.getLength(); i++) {
            if (stereotypesList.item(i).getNodeType() == Node.ELEMENT_NODE) stereotypeInstances.add(new ThreadStereotype((Element)stereotypesList.item(i)));
        }
        return stereotypeInstances;
    }

    private static ArrayList<MemoryStereotype> getMemoryStereotypeInstances(Document doc) {
        ArrayList<MemoryStereotype> stereotypeInstances = new ArrayList<>();
        NodeList stereotypesList = doc.getElementsByTagName(MemoryStereotype.TAG_NAME);
        for (int i=0; i<stereotypesList.getLength(); i++) {
            if (stereotypesList.item(i).getNodeType() == Node.ELEMENT_NODE) stereotypeInstances.add(new MemoryStereotype((Element)stereotypesList.item(i)));
        }
        return stereotypeInstances;
    }

    private static ArrayList<SignalStereotype> getSignalStereotypeInstances(Document doc) {
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

    private static ArrayList<ClockStereotype> getClockStereotypeInstances(Document doc) {
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

    private static ArrayList<SstmStateStereotype> getSstmStateStereotypeInstances(Document doc) {
        ArrayList<SstmStateStereotype> sstmStateStereotypeInstances = new ArrayList<>();
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

    ///////////////////////////////////////////////////
    //FDIR
    ///////////////////////////////////////////////////

    private static ArrayList<AlarmStereotype> getAlarmStereotypeInstances(Document doc) {
        ArrayList<AlarmStereotype> alarmStereotypeInstances = new ArrayList<>();
        NodeList alarmStereotypesList = doc.getElementsByTagName(AlarmStereotype.TAG_NAME);
        for (int i=0; i<alarmStereotypesList.getLength(); i++) {
            if (alarmStereotypesList.item(i).getNodeType() == Node.ELEMENT_NODE) alarmStereotypeInstances.add(new AlarmStereotype((Element)alarmStereotypesList.item(i)));
        }
        return alarmStereotypeInstances;
    }

    private static ArrayList<ObservableStereotype> getObservableStereotypeInstances(Document doc) {
        ArrayList<ObservableStereotype> observableStereotypeInstances = new ArrayList<>();
        NodeList observableStereotypesList = doc.getElementsByTagName(ObservableStereotype.TAG_NAME);
        for (int i=0; i<observableStereotypesList.getLength(); i++) {
            if (observableStereotypesList.item(i).getNodeType() == Node.ELEMENT_NODE) observableStereotypeInstances.add(new ObservableStereotype((Element)observableStereotypesList.item(i)));
        }
        return observableStereotypeInstances;
    }

    private static ArrayList<FdirComponentStereotype> getFdirComponentStereotypeInstances(Document doc) {
        ArrayList<FdirComponentStereotype> fdirComponentStereotypeInstances = new ArrayList<>();
        NodeList fdirComponentStereotypesList = doc.getElementsByTagName(FdirComponentStereotype.TAG_NAME);
        for (int i=0; i<fdirComponentStereotypesList.getLength(); i++) {
            if (fdirComponentStereotypesList.item(i).getNodeType() == Node.ELEMENT_NODE) fdirComponentStereotypeInstances.add(new FdirComponentStereotype((Element)fdirComponentStereotypesList.item(i)));
        }
        return fdirComponentStereotypeInstances;
    }

    ///////////////////////////////////////////////////
    //ERROR model Shine
    ///////////////////////////////////////////////////

    private static ArrayList<ErrorModelStereotype> getErrorModelStereotypeInstances(Document doc) {
        ArrayList<ErrorModelStereotype> errorModelStereotypeInstances = new ArrayList<>();
        NodeList errorModelStereotypesList = doc.getElementsByTagName(ErrorModelStereotype.TAG_NAME);
        for (int i=0; i<errorModelStereotypesList.getLength(); i++) {
            if (errorModelStereotypesList.item(i).getNodeType() == Node.ELEMENT_NODE) errorModelStereotypeInstances.add(new ErrorModelStereotype((Element)errorModelStereotypesList.item(i)));
        }
        return errorModelStereotypeInstances;
    }

    private static ArrayList<ErrorEventStereotype> getErrorEventStereotypeInstances(Document doc) {
        ArrayList<ErrorEventStereotype> errorEventStereotypeInstances = new ArrayList<>();
        NodeList errorEventStereotypesList = doc.getElementsByTagName(ErrorEventStereotype.TAG_NAME);
        for (int i=0; i<errorEventStereotypesList.getLength(); i++) {
            if (errorEventStereotypesList.item(i).getNodeType() == Node.ELEMENT_NODE) errorEventStereotypeInstances.add(new ErrorEventStereotype((Element)errorEventStereotypesList.item(i)));
        }
        return errorEventStereotypeInstances;
    }

    private static ArrayList<ErrorStateStereotype> getErrorStateStereotypeInstances(Document doc) {
        ArrayList<ErrorStateStereotype> errorStateStereotypeInstances = new ArrayList<>();
        NodeList errorStateStereotypesList = doc.getElementsByTagName(ErrorStateStereotype.TAG_NAME);
        for (int i=0; i<errorStateStereotypesList.getLength(); i++) {
            if (errorStateStereotypesList.item(i).getNodeType() == Node.ELEMENT_NODE) errorStateStereotypeInstances.add(new ErrorStateStereotype((Element)errorStateStereotypesList.item(i)));
        }
        return errorStateStereotypeInstances;
    }

    private static ArrayList<ErrorEffectStereotype> getErrorEffectStereotypeInstances(Document doc) {
        ArrayList<ErrorEffectStereotype> errorEffectStereotypeInstances = new ArrayList<>();
        NodeList errorEffectStereotypesList = doc.getElementsByTagName(ErrorEffectStereotype.TAG_NAME);
        for (int i=0; i<errorEffectStereotypesList.getLength(); i++) {
            if (errorEffectStereotypesList.item(i).getNodeType() == Node.ELEMENT_NODE) errorEffectStereotypeInstances.add(new ErrorEffectStereotype((Element)errorEffectStereotypesList.item(i)));
        }
        return errorEffectStereotypeInstances;
    }

    ///////////////////////////////////////////////////
    //ERROR model Shine
    ///////////////////////////////////////////////////

    private static ArrayList<PatternPropertyStereotype> getPatternPropertyStereotypeInstances(Document doc) {
        ArrayList<PatternPropertyStereotype> patternPropertyStereotypeInstances = new ArrayList<>();
        NodeList patternPropertyStereotypesList = doc.getElementsByTagName(PatternPropertyStereotype.TAG_NAME);
        for (int i=0; i<patternPropertyStereotypesList.getLength(); i++) {
            if (patternPropertyStereotypesList.item(i).getNodeType() == Node.ELEMENT_NODE) patternPropertyStereotypeInstances.add(new PatternPropertyStereotype((Element)patternPropertyStereotypesList.item(i)));
        }
        return patternPropertyStereotypeInstances;
    }

    private static ArrayList<UniversalityPatternStereotype> getUniversalityPatternStereotypeInstances(Document doc) {
        ArrayList<UniversalityPatternStereotype> universalityPatternStereotypeInstances = new ArrayList<>();
        NodeList universalityPatternStereotypesList = doc.getElementsByTagName(UniversalityPatternStereotype.TAG_NAME);
        for (int i=0; i<universalityPatternStereotypesList.getLength(); i++) {
            if (universalityPatternStereotypesList.item(i).getNodeType() == Node.ELEMENT_NODE) universalityPatternStereotypeInstances.add(new UniversalityPatternStereotype((Element)universalityPatternStereotypesList.item(i)));
        }
        return universalityPatternStereotypeInstances;
    }

    private static ArrayList<AbsencePatternStereotype> getAbsencePatternStereotypeInstances(Document doc) {
        ArrayList<AbsencePatternStereotype> absencePatternStereotypeInstances = new ArrayList<>();
        NodeList absencePatternStereotypesList = doc.getElementsByTagName(AbsencePatternStereotype.TAG_NAME);
        for (int i=0; i<absencePatternStereotypesList.getLength(); i++) {
            if (absencePatternStereotypesList.item(i).getNodeType() == Node.ELEMENT_NODE) absencePatternStereotypeInstances.add(new AbsencePatternStereotype((Element)absencePatternStereotypesList.item(i)));
        }
        return absencePatternStereotypeInstances;
    }

    private static ArrayList<ExistencePatternStereotype> getExistencePatternStereotypeInstances(Document doc) {
        ArrayList<ExistencePatternStereotype> existencePatternStereotypeInstances = new ArrayList<>();
        NodeList existencePatternStereotypesList = doc.getElementsByTagName(ExistencePatternStereotype.TAG_NAME);
        for (int i=0; i<existencePatternStereotypesList.getLength(); i++) {
            if (existencePatternStereotypesList.item(i).getNodeType() == Node.ELEMENT_NODE) existencePatternStereotypeInstances.add(new ExistencePatternStereotype((Element)existencePatternStereotypesList.item(i)));
        }
        return existencePatternStereotypeInstances;
    }

    private static ArrayList<RecurrencePatternStereotype> getRecurrencePatternStereotypeInstances(Document doc) {
        ArrayList<RecurrencePatternStereotype> patternStereotypeInstances = new ArrayList<>();
        NodeList patternStereotypesList = doc.getElementsByTagName(RecurrencePatternStereotype.TAG_NAME);
        for (int i=0; i<patternStereotypesList.getLength(); i++) {
            if (patternStereotypesList.item(i).getNodeType() == Node.ELEMENT_NODE) patternStereotypeInstances.add(new RecurrencePatternStereotype((Element)patternStereotypesList.item(i)));
        }
        return patternStereotypeInstances;
    }

    private static ArrayList<PrecedencePatternStereotype> getPrecedencePatternStereotypeInstances(Document doc) {
        ArrayList<PrecedencePatternStereotype> patternStereotypeInstances = new ArrayList<>();
        NodeList patternStereotypesList = doc.getElementsByTagName(PrecedencePatternStereotype.TAG_NAME);
        for (int i=0; i<patternStereotypesList.getLength(); i++) {
            if (patternStereotypesList.item(i).getNodeType() == Node.ELEMENT_NODE) patternStereotypeInstances.add(new PrecedencePatternStereotype((Element)patternStereotypesList.item(i)));
        }
        return patternStereotypeInstances;
    }

    private static ArrayList<ResponsePatternStereotype> getResponsePatternStereotypeInstances(Document doc) {
        ArrayList<ResponsePatternStereotype> responsePatternStereotypeInstances = new ArrayList<>();
        NodeList responsePatternStereotypesList = doc.getElementsByTagName(ResponsePatternStereotype.TAG_NAME);
        for (int i=0; i<responsePatternStereotypesList.getLength(); i++) {
            if (responsePatternStereotypesList.item(i).getNodeType() == Node.ELEMENT_NODE) responsePatternStereotypeInstances.add(new ResponsePatternStereotype((Element)responsePatternStereotypesList.item(i)));
        }
        return responsePatternStereotypeInstances;
    }

    private static ArrayList<ResponseInvariancePatternStereotype> getResponseInvariancePatternStereotypeInstances(Document doc) {
        ArrayList<ResponseInvariancePatternStereotype> patternStereotypeInstances = new ArrayList<>();
        NodeList patternStereotypesList = doc.getElementsByTagName(ResponseInvariancePatternStereotype.TAG_NAME);
        for (int i=0; i<patternStereotypesList.getLength(); i++) {
            if (patternStereotypesList.item(i).getNodeType() == Node.ELEMENT_NODE) patternStereotypeInstances.add(new ResponseInvariancePatternStereotype((Element)patternStereotypesList.item(i)));
        }
        return patternStereotypeInstances;
    }

    private static ArrayList<UntilPatternStereotype> getUntilPatternStereotypeInstances(Document doc) {
        ArrayList<UntilPatternStereotype> patternStereotypeInstances = new ArrayList<>();
        NodeList patternStereotypesList = doc.getElementsByTagName(UntilPatternStereotype.TAG_NAME);
        for (int i=0; i<patternStereotypesList.getLength(); i++) {
            if (patternStereotypesList.item(i).getNodeType() == Node.ELEMENT_NODE) patternStereotypeInstances.add(new UntilPatternStereotype((Element)patternStereotypesList.item(i)));
        }
        return patternStereotypeInstances;
    }

}
