package data.controller;

import com.sun.istack.internal.Nullable;
import data.slim.components.*;
import data.slim.components.System;
import data.slim.components.Thread;
import data.slim.internal.structure.Clock;
import data.xmi.stereotypes.sstm.*;
import data.xmi.structure.Class;
import sun.misc.ConditionLock;

/**
 * Created by Joost on 16-Feb-17.
 * Creator class to house all transformation methods
 */
public class SLIMComponentCreator {
    private enum ComponentType {
        DATA, THREAD, BUS, DEVICE, MEMORY, PROCESSOR, SYSTEM, UNSPECIFIED, CLOCK
    }

    /**
     * CreateComponent from xmiClass
     *
     * Can return null if Class has no SLIM component stereotype (cannot define component)
     * @param xmiClass Class to create component from
     * @return Component
     */
    @Nullable
    public static Component createComponent(Class xmiClass) {
        Component component = null;
        //Check if Class is SLIM component, if not return null
        if (!xmiClass.isSlimComponent()) return null;
        switch (getComponentType(xmiClass)) {
            case UNSPECIFIED:
                break;
            case DEVICE:
                component = new Device(xmiClass);
                break;
            case SYSTEM:
                component = new System(xmiClass);
                break;
            case PROCESSOR:
                component = new Processor(xmiClass);
                break;
            case CLOCK:
                component = new Clock(xmiClass, ((ClockStereotype)xmiClass.getSLIMComponentStereotype()).getTimeUnit());
                break;
            case BUS:
                component = new Bus(xmiClass);
                break;
            case THREAD:
                component = new Thread(xmiClass);
                break;
            case MEMORY:
                component = new Memory(xmiClass);
                break;
            default:
                break;
        }
        return component;
    }

    private static ComponentType getComponentType(Class xmiClass) {
        SLIMComponentStereotype stereotype = xmiClass.getSLIMComponentStereotype();
        if (stereotype instanceof DeviceStereotype) return ComponentType.DEVICE;
        if (stereotype instanceof SystemStereotype) return ComponentType.SYSTEM;
        if (stereotype instanceof ProcessorStereotype) return ComponentType.PROCESSOR;
        if (stereotype instanceof ClockStereotype) return ComponentType.CLOCK;
        if (stereotype instanceof BusStereotype) return ComponentType.BUS;
        if (stereotype instanceof ThreadStereotype) return ComponentType.THREAD;
        if (stereotype instanceof MemoryStereotype) return ComponentType.MEMORY;


        return ComponentType.UNSPECIFIED;
    }
}
