package data.controller;

import com.sun.istack.internal.Nullable;
import data.slim.components.Component;
import data.slim.components.Device;
import data.slim.components.Processor;
import data.slim.components.System;
import data.xmi.stereotypes.sstm.DeviceStereotype;
import data.xmi.stereotypes.sstm.ProcessorStereotype;
import data.xmi.stereotypes.sstm.SLIMComponentStereotype;
import data.xmi.stereotypes.sstm.SystemStereotype;
import data.xmi.structure.Class;

/**
 * Created by Joost on 16-Feb-17.
 * Creator class to house all transformation methods
 */
public class SLIMComponentCreator {
    private enum ComponentType {
        DATA, THREAD, BUS, DEVICE, MEMORY, PROCESSOR, SYSTEM, UNSPECIFIED
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

        return ComponentType.UNSPECIFIED;
    }
}
