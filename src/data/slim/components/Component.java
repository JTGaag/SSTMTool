package data.slim.components;

import data.slim.SlimObject;
import data.slim.internal.DataPort;
import data.slim.internal.EventPort;
import data.xmi.uml.Class;
import data.xmi.uml.DataType;
import data.xmi.uml.Port;

import java.util.ArrayList;

/**
 * Created by Joost on 16-Feb-17.
 * Base class for SLIM components
 */
public class Component extends SlimObject{
    Class baseXmiClass;

    String name;

    ArrayList<DataPort> dataPorts = new ArrayList<>();
    ArrayList<EventPort> eventPorts = new ArrayList<>();
    ArrayList<data.slim.internal.Port> ports = new ArrayList<>();


    public Component(Class baseXmiClass) {
        this.baseXmiClass = baseXmiClass;
        this.name = baseXmiClass.getName();
        initPorts();
    }

    private void initPorts() {
        dataPorts.clear();
        eventPorts.clear();
        for (Port port: baseXmiClass.getPorts()) {
            if (port.getPortType().equals(Port.PortType.EVENT)) {
                EventPort eventPort = new EventPort(port);
                eventPorts.add(eventPort);
                ports.add(eventPort);
            } else {
                DataPort dataPort = new DataPort(port);
                dataPorts.add(dataPort);
                ports.add(dataPort);
            }
        }
    }


    public ArrayList<DataPort> getDataPorts() {
        return dataPorts;
    }

    public ArrayList<EventPort> getEventPorts() {
        return eventPorts;
    }

    public ArrayList<data.slim.internal.Port> getPorts() {
        return ports;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Component";
    }

    @Override
    public String toSlimString() {
        return "Component <Should not be shown!>";
    }
}
