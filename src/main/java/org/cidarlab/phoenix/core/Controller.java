/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.core;

import java.util.ArrayList;
import java.util.List;
import org.cidarlab.phoenix.dom.Component;
import org.cidarlab.phoenix.dom.Component.ComponentRole;
import org.cidarlab.phoenix.dom.Model;
import org.cidarlab.phoenix.dom.Module;
import org.cidarlab.phoenix.dom.Module.ModuleRole;
import org.cidarlab.phoenix.dom.Orientation;

/**
 *
 * @author prash
 */
public class Controller {

    //<editor-fold desc="Decompose a Phoenix Module based on the mode">
    public static Module decompose(PhoenixMode mode, Module root) {
        boolean started = false;
        List<Component> components = null;
        switch (mode) {
            case BIOCPS:
                //Forward Strand
                started = false;
                int forInp = 0;
                int forMod = 0;
                int forOut = 0;

                ModuleRole role = ModuleRole.BIOCPS_INPUT;
                for (Component c : root.getComponents()) {
                    if (c.getOrientation().equals(Orientation.FORWARD)) {
                        if (!started) {
                            if (c.getRole().equals(ComponentRole.PROMOTER_INDUCIBLE)) {
                                role = ModuleRole.BIOCPS_INPUT;
                                started = true;
                                components = new ArrayList<>();
                                components.add(c);
                            } else if (isCDS(c)) {
                                if (isBioCPSModule(c)) {
                                    //Create a BioCPS Module
                                    role = ModuleRole.BIOCPS_MODULE;
                                    started = true;
                                    components = new ArrayList<>();
                                    components.add(c);
                                } else {
                                    //Create a BioCPS Output
                                    role = ModuleRole.BIOCPS_OUTPUT;
                                    started = true;
                                    components = new ArrayList<>();
                                    components.add(c);
                                }
                            } else {
                                //This means wrong grammar.
                                continue;
                            }

                        } else {
                            //In the middle of a module
                            components.add(c);
                            switch (role) {
                                case BIOCPS_INPUT:
                                    if (c.getRole().equals(ComponentRole.RBS)) {
                                        Module inp = new Module("In" + (forInp++));
                                        inp.setRole(role);
                                        inp.setComponents(components);
                                        inp.setRoot(false);
                                        root.addChild(inp);
                                        started = false;
                                    }
                                    break;
                                case BIOCPS_MODULE:
                                    if (c.getRole().equals(ComponentRole.RBS)) {
                                        Module mod = new Module("Mod" + (forMod++));
                                        mod.setRole(role);
                                        mod.setComponents(components);
                                        mod.setRoot(false);
                                        root.addChild(mod);
                                        started = false;
                                    }
                                    break;
                                case BIOCPS_OUTPUT:
                                    if (c.getRole().equals(ComponentRole.TERMINATOR)) {
                                        Module out = new Module("Out" + (forOut++));
                                        out.setRole(role);
                                        out.setComponents(components);
                                        out.setRoot(false);
                                        root.addChild(out);
                                        started = false;
                                    }
                                    break;
                                default:
                                    System.err.println("Unexpected module type");
                                    System.exit(-1);
                            }
                        }
                    } else {
                        //Do any of these make sense in the Forward TU units?
                        continue;
                    }
                }
                //Reverse Strand
                int revInp = 0;
                int revMod = 0;
                int revOut = 0;
                started = false;
                components = null;
                for (int i = (root.getComponents().size() - 1); i >= 0; i--) {
                    Component c = root.getComponents().get(i);
                    if (c.getOrientation().equals(Orientation.REVERSE)) {
                        if (!started) {
                            if (c.getRole().equals(ComponentRole.PROMOTER_INDUCIBLE)) {
                                role = ModuleRole.BIOCPS_INPUT;
                                started = true;
                                components = new ArrayList<>();
                                components.add(c);
                            } else if (isCDS(c)) {
                                if (isBioCPSModule(c)) {
                                    //Create a BioCPS Module
                                    role = ModuleRole.BIOCPS_MODULE;
                                    started = true;
                                    components = new ArrayList<>();
                                    components.add(c);
                                } else {
                                    //Create a BioCPS Output
                                    role = ModuleRole.BIOCPS_OUTPUT;
                                    started = true;
                                    components = new ArrayList<>();
                                    components.add(c);
                                }
                            } else {
                                //This means wrong grammar.
                                continue;
                            }

                        } else {
                            //In the middle of a module
                            components.add(c);
                            switch (role) {
                                case BIOCPS_INPUT:
                                    if (c.getRole().equals(ComponentRole.RBS)) {
                                        Module inp = new Module("In" + (forInp++));
                                        inp.setRole(role);
                                        inp.setComponents(components);
                                        inp.setRoot(false);
                                        root.addChild(inp);
                                        started = false;
                                    }
                                    break;
                                case BIOCPS_MODULE:
                                    if (c.getRole().equals(ComponentRole.RBS)) {
                                        Module mod = new Module("Mod" + (forMod++));
                                        mod.setRole(role);
                                        mod.setComponents(components);
                                        mod.setRoot(false);
                                        root.addChild(mod);
                                        started = false;
                                    }
                                    break;
                                case BIOCPS_OUTPUT:
                                    if (c.getRole().equals(ComponentRole.TERMINATOR)) {
                                        Module out = new Module("Out" + (forOut++));
                                        out.setRole(role);
                                        out.setComponents(components);
                                        out.setRoot(false);
                                        root.addChild(out);
                                        started = false;
                                    }
                                    break;
                                default:
                                    System.err.println("Unexpected module type");
                                    System.exit(-1);
                            }
                        }
                    } else {
                        //Do any of these make sense in the Forward TU units?
                        continue;
                    }
                }
                return root;
            case MM:
                //Forward Strand
                started = false;
                int forwardCount = 0;
                for (Component c : root.getComponents()) {
                    if (!started) {
                        if (c.getOrientation().equals(Orientation.FORWARD)) {
                            if (isPromoter(c)) {
                                started = true;
                                components = new ArrayList<>();
                                components.add(c);
                            } else {
                                //Throw an error?
                                continue;
                            }
                        } else {
                            //Reverse orientation. Wouldn't really make it a TU unless it is a part of the TU.
                            continue;
                        }
                    } //Started Forward strand
                    else {
                        if (c.getOrientation().equals(Orientation.FORWARD)) {
                            components.add(c);
                            if (isTerminator(c)) {
                                Module child = new Module("TU_F" + (forwardCount++));
                                child.setComponents(components);
                                child.setRole(ModuleRole.TRANSCRIPTIONAL_UNIT);
                                child.setRoot(false);
                                decomposeTU(child);
                                root.addChild(child);
                                started = false;
                            }
                        } else {
                            components.add(c); //Make this a wildcard?
                        }
                    }
                }
                //Reverse Strand
                int reverseCount = 0;
                started = false;
                components = null;
                for (int i = (root.getComponents().size() - 1); i >= 0; i--) {
                    Component c = root.getComponents().get(i);
                    if (!started) {
                        if (c.getOrientation().equals(Orientation.REVERSE)) {
                            if (isPromoter(c)) {
                                started = true;
                                components = new ArrayList<Component>();
                                components.add(c);
                            } else {
                                //Throw an error?
                                continue;
                            }
                        } else {
                            //Reverse orientation. Wouldn't really make it a TU unless it is a part of the TU.
                            continue;
                        }
                    } //Started Forward strand
                    else {
                        if (c.getOrientation().equals(Orientation.REVERSE)) {
                            components.add(c);
                            if (isTerminator(c)) {
                                Module child = new Module("TU_R" + (reverseCount++));
                                child.setComponents(components);
                                child.setRole(ModuleRole.TRANSCRIPTIONAL_UNIT);
                                child.setRoot(false);
                                decomposeTU(child);
                                root.addChild(child);
                                started = false;
                            }
                        } else {
                            components.add(c); //Make this a wildcard?
                        }
                    }
                }
                return root;
            default:
                return null;
        }
    }

    private static void decomposeTU(Module module) {
        List<Component> components = new ArrayList<>();
        Module cds = null;
        for (Component c : module.getComponents()) {
            if (isCDS(c)) {
                cds = new Module("CDS");
                cds.addComponent(c);
                cds.setRole(ModuleRole.CDS);

                Component test = new Component();
                test.setOrientation(c.getOrientation());
                test.setRole(ComponentRole.TESTING);
                test.setName(c.getName());
                components.add(test);
            } else {
                components.add(c);
            }
        }
        Module prom = new Module("Prom");
        prom.setRole(ModuleRole.PROMOTER);
        prom.setComponents(components);

        module.addChild(prom);
        module.addChild(cds);
    }
    //</editor-fold>

    //<editor-fold desc="Identify the Component Type">
    private static boolean isBioCPSModule(Component c) {
        switch (c.getRole()) {
            case CDS_REPRESSOR:
            case CDS_ACTIVATOR:
            case CDS_REPRESSIBLE_REPRESSOR:
            case CDS_ACTIVATIBLE_ACTIVATOR:
                return true;
            case CDS_LINKER:
            case CDS_TAG:
            case CDS_RESISTANCE:
            case CDS_FLUORESCENT:
            case CDS_FLUORESCENT_FUSION:
            case CDS:
            case TERMINATOR:
            case PROMOTER:
            case PROMOTER_REPRESSIBLE:
            case PROMOTER_INDUCIBLE:
            case PROMOTER_CONSTITUTIVE:
            case RBS:
            case ORIGIN:
            case VECTOR:
            case TESTING:
            case MARKER:
            case WILDCARD:
                return false;
            default:
                return false;

        }
    }

    private static boolean isCDS(Component c) {
        switch (c.getRole()) {
            case CDS:
            case CDS_REPRESSOR:
            case CDS_ACTIVATOR:
            case CDS_REPRESSIBLE_REPRESSOR:
            case CDS_ACTIVATIBLE_ACTIVATOR:
            case CDS_LINKER:
            case CDS_TAG:
            case CDS_RESISTANCE:
            case CDS_FLUORESCENT:
            case CDS_FLUORESCENT_FUSION:
                return true;
            case TERMINATOR:
            case PROMOTER:
            case PROMOTER_REPRESSIBLE:
            case PROMOTER_INDUCIBLE:
            case PROMOTER_CONSTITUTIVE:
            case RBS:
            case ORIGIN:
            case VECTOR:
            case TESTING:
            case MARKER:
            case WILDCARD:
                return false;
            default:
                return false;

        }
    }

    private static boolean isTerminator(Component c) {
        switch (c.getRole()) {
            case TERMINATOR:
                return true;
            case PROMOTER:
            case PROMOTER_REPRESSIBLE:
            case PROMOTER_INDUCIBLE:
            case PROMOTER_CONSTITUTIVE:
            case RBS:
            case CDS:
            case CDS_REPRESSOR:
            case CDS_ACTIVATOR:
            case CDS_REPRESSIBLE_REPRESSOR:
            case CDS_ACTIVATIBLE_ACTIVATOR:
            case CDS_LINKER:
            case CDS_TAG:
            case CDS_RESISTANCE:
            case CDS_FLUORESCENT:
            case CDS_FLUORESCENT_FUSION:
            case ORIGIN:
            case VECTOR:
            case TESTING:
            case MARKER:
            case WILDCARD:
                return false;
            default:
                return false;

        }
    }

    private static boolean isPromoter(Component c) {
        switch (c.getRole()) {
            case PROMOTER:
            case PROMOTER_REPRESSIBLE:
            case PROMOTER_INDUCIBLE:
            case PROMOTER_CONSTITUTIVE:
                return true;
            case RBS:
            case CDS:
            case CDS_REPRESSOR:
            case CDS_ACTIVATOR:
            case CDS_REPRESSIBLE_REPRESSOR:
            case CDS_ACTIVATIBLE_ACTIVATOR:
            case CDS_LINKER:
            case CDS_TAG:
            case CDS_RESISTANCE:
            case CDS_FLUORESCENT:
            case CDS_FLUORESCENT_FUSION:
            case TERMINATOR:
            case ORIGIN:
            case VECTOR:
            case TESTING:
            case MARKER:
            case WILDCARD:
                return false;
            default:
                return false;

        }
    }
    //</editor-fold>

    public static void composeModels(PhoenixMode mode, Module root) {
        switch (mode) {
            case BIOCPS:
                break;
            case MM:
                composePartModels(root);      
                break;
            default:
                break;
        }
    }
    
    private static void composePartModels(Module root){
        
        if(!root.getModel().isOverriden()){
            List<Model> modelList = new ArrayList<>();
            for (Module child : root.getChildren()) {
                composePartModels(child);
                modelList.add(child.getModel());
            }
            //Do something with modelList?
            //Model composedModel;
            //root.setModel(composedModel);
        } 
    }

}
