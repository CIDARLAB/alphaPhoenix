/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.library;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;
import org.cidarlab.phoenix.dom.Component.ComponentRole;
import org.cidarlab.phoenix.dom.LibraryComponent;
import org.sbolstandard.core2.ComponentDefinition;
import org.sbolstandard.core2.FunctionalComponent;
import org.sbolstandard.core2.Interaction;
import org.sbolstandard.core2.Model;
import org.sbolstandard.core2.ModuleDefinition;
import org.sbolstandard.core2.SBOLDocument;

/**
 *
 * @author prash
 */
public class Library {
    
    
    @Getter
    private LibraryComponent promoterTest;
    
    @Getter
    private LibraryComponent rbsTest;
    
    @Getter
    private LibraryComponent cdsTest;
    
    @Getter
    private LibraryComponent terTest;
    
    
    private final static String so = "http://identifiers.org/so/";
    private final static String sbo = "http://identifiers.org/biomodels.sbo/";
    
    private final static String inducerSO = "http://www.biopax.org/release/biopax-level3.owl#SmallMolecule";
        
    private final static String induciblePromSO = so + "SO:0002051";
    private final static String constitutivePromSO = so + "SO:0002050";
    private final static String rbsSO = so + "SO:0000139";
    private final static String cdsSO = so + "SO:0000316";
    private final static String terSO = so + "SO:0000141";
    private final static String proteinSO = "http://www.biopax.org/release/biopax-level3.owl#Protein";
    
    private final static String productionSBO = sbo + "SBO:0000589";;
    private final static String inhibitionSO = sbo + "SBO:0000169";
    private final static String stimulationSO = sbo + "SBO:0000170";
    
    @Getter
    private Map<URI,LibraryComponent> constitutivePromoters = new HashMap<>();
    
    @Getter
    private Map<URI,LibraryComponent> repressiblePromoters = new HashMap<>();
    
    @Getter
    private Map<URI,LibraryComponent> activatiblePromoters = new HashMap<>();
    
    @Getter
    private Map<URI,LibraryComponent> inputActPromoters = new HashMap<>();
    
    @Getter
    private Map<URI,LibraryComponent> inputRepPromoters = new HashMap<>();
    
    
    @Getter
    private Map<URI,LibraryComponent> repressorCDS = new HashMap<>();
    
    @Getter 
    private Map<URI,LibraryComponent> activatorCDS = new HashMap<>();
    
    @Getter
    private Map<URI,LibraryComponent> outputCDS = new HashMap<>();
    
    @Getter
    private Map<URI,LibraryComponent> rbs = new HashMap<>();
    
    @Getter
    private Map<URI,LibraryComponent> terminators = new HashMap<>();
    
    @Getter
    private Map<URI,LibraryComponent> testers = new HashMap<>();
    
    @Getter
    private Map<URI,LibraryComponent> proteins = new HashMap<>();
    
    @Getter
    private Map<URI,LibraryComponent> inducers = new HashMap<>();
    
    public Library(SBOLDocument doc){
        try {
            URI productionURI = new URI(productionSBO);
            URI inhibitionURI = new URI(inhibitionSO);
            URI stimulationURI = new URI(stimulationSO);
            Map<URI,LibraryComponent> promoters = new HashMap<>();
            Map<URI,LibraryComponent> cds = new HashMap<>();
            for(ComponentDefinition cd: doc.getComponentDefinitions()){
                switch(getRole(cd)){
                    case PROMOTER_CONSTITUTIVE:
                        //System.out.println("Found Constitutive Promoter");
                        if(cd.getDisplayId().startsWith("test")){
                            this.promoterTest = new LibraryComponent(cd.getName(),cd.getDisplayId(),cd.getIdentity());
                        } else {
                            constitutivePromoters.put(cd.getIdentity(),new LibraryComponent(cd.getName(),cd.getDisplayId(),cd.getIdentity()));
                        }
                        break;
                    case PROMOTER:
                        //System.out.println("Found Promoter");
                        if(cd.getDisplayId().startsWith("test")){
                            this.promoterTest = new LibraryComponent(cd.getName(),cd.getDisplayId(),cd.getIdentity());
                        } else {
                            promoters.put(cd.getIdentity(), new LibraryComponent(cd.getName(),cd.getDisplayId(),cd.getIdentity()));
                        }
                        break;
                    case RBS:
                        //System.out.println("Found RBS");
                        if(cd.getDisplayId().startsWith("test")){
                            this.rbsTest = new LibraryComponent(cd.getName(),cd.getDisplayId(),cd.getIdentity());
                        } else{
                            rbs.put(cd.getIdentity(), new LibraryComponent(cd.getName(),cd.getDisplayId(),cd.getIdentity()));
                        }
                        break;
                    case CDS:
                        //System.out.println("Found CDS");
                        if(cd.getDisplayId().startsWith("test")){
                            this.cdsTest = new LibraryComponent(cd.getName(),cd.getDisplayId(),cd.getIdentity());
                        } else {
                            cds.put(cd.getIdentity(), new LibraryComponent(cd.getName(),cd.getDisplayId(),cd.getIdentity()));
                        }
                        break;
                    case TERMINATOR:
                        //System.out.println("Found Terminator");
                        if(cd.getDisplayId().startsWith("test")){
                            this.terTest = new LibraryComponent(cd.getName(),cd.getDisplayId(),cd.getIdentity());
                        } else {
                            terminators.put(cd.getIdentity(),new LibraryComponent(cd.getName(),cd.getDisplayId(),cd.getIdentity()));
                        }
                        break;
                    case PROTEIN:
                        //System.out.println("Found a Protein!");
                        proteins.put(cd.getIdentity(),new LibraryComponent(cd.getName(),cd.getDisplayId(),cd.getIdentity()));
                        break;
                    case INDUCER:
                        inducers.put(cd.getIdentity(),new LibraryComponent(cd.getName(),cd.getDisplayId(),cd.getIdentity()));
                        break;
                }
            }
            Set<URI> activatingProteins = new HashSet<URI>();
            Set<URI> repressingProteins = new HashSet<URI>();
            
            for(ModuleDefinition md:doc.getModuleDefinitions()){
                int fcsize = md.getFunctionalComponents().size();
                
                //If this is for FP/output or Constitutive Promoter.
                if(fcsize == 1){
                    List<URI> modelList = getAllModels(md);
                    for(FunctionalComponent fc:md.getFunctionalComponents()){
                        if(constitutivePromoters.containsKey(fc.getDefinitionURI())){
                            for(URI uri:modelList){
                                constitutivePromoters.get(fc.getDefinitionURI()).addModel(uri);
                            }
                            constitutivePromoters.get(fc.getDefinitionURI()).addModuleDefinition(md.getIdentity());
                        } else if(cds.containsKey(fc.getDefinitionURI())){
                            outputCDS.put(fc.getDefinitionURI(), cds.get(fc.getDefinitionURI()));
                            for(URI uri:modelList){
                                outputCDS.get(fc.getDefinitionURI()).addModel(uri);
                            }
                            outputCDS.get(fc.getDefinitionURI()).addModuleDefinition(md.getIdentity());
                        } 
                    }
                } else if(fcsize == 2) {
                    //Models for Activatible and Repressible Promoters and CDS.
                    for(FunctionalComponent fc:md.getFunctionalComponents()){
                        if(proteins.containsKey(fc.getDefinitionURI())){
                            proteins.get(fc.getDefinitionURI()).addModuleDefinition(md.getIdentity());
                            for(Interaction interaction:md.getInteractions()){
                                for(URI type:interaction.getTypes()){
                                    if(type.equals(inhibitionURI)){
                                        repressingProteins.add(fc.getDefinitionURI());
                                    } else if(type.equals(stimulationURI)){
                                        activatingProteins.add(fc.getDefinitionURI());
                                    } 
                                }
                            }
                        } else if(promoters.containsKey(fc.getDefinitionURI())){
                            List<URI> modelList = getAllModels(md);
                            for(Interaction interaction:md.getInteractions()){
                                for(URI type:interaction.getTypes()){
                                    if(type.equals(inhibitionURI)){
                                        boolean inputProm = false;
                                        for(FunctionalComponent fcin:md.getFunctionalComponents()){
                                            if(inducers.containsKey(fcin.getDefinitionURI())){
                                                inputProm = true;
                                                break;
                                            }
                                        }
                                        if(inputProm){
                                            inputRepPromoters.put(fc.getDefinitionURI(), promoters.get(fc.getDefinitionURI()));
                                            for (URI uri : modelList) {
                                                inputRepPromoters.get(fc.getDefinitionURI()).addModel(uri);
                                            }
                                            inputRepPromoters.get(fc.getDefinitionURI()).addModuleDefinition(md.getIdentity());
                                        } else {
                                            repressiblePromoters.put(fc.getDefinitionURI(), promoters.get(fc.getDefinitionURI()));
                                            for (URI uri : modelList) {
                                                repressiblePromoters.get(fc.getDefinitionURI()).addModel(uri);
                                            }
                                            repressiblePromoters.get(fc.getDefinitionURI()).addModuleDefinition(md.getIdentity());
                                        }
                                        
                                    } else if(type.equals(stimulationURI)){
                                        boolean inputProm = false;
                                        for(FunctionalComponent fcin:md.getFunctionalComponents()){
                                            if(inducers.containsKey(fcin.getDefinitionURI())){
                                                inputProm = true;
                                                break;
                                            }
                                        }
                                        if(inputProm){
                                            inputActPromoters.put(fc.getDefinitionURI(), promoters.get(fc.getDefinitionURI()));
                                            for (URI uri : modelList) {
                                                inputActPromoters.get(fc.getDefinitionURI()).addModel(uri);
                                            }
                                            inputActPromoters.get(fc.getDefinitionURI()).addModuleDefinition(md.getIdentity());
                                        } else {
                                            activatiblePromoters.put(fc.getDefinitionURI(), promoters.get(fc.getDefinitionURI()));
                                            for (URI uri : modelList) {
                                                activatiblePromoters.get(fc.getDefinitionURI()).addModel(uri);
                                            }
                                            activatiblePromoters.get(fc.getDefinitionURI()).addModuleDefinition(md.getIdentity());
                                        }
                                    }
                                }
                            }
                        } else if(cds.containsKey(fc.getDefinitionURI())){
                            //Do nothing for now. 
                        } else if(inducers.containsKey(fc.getDefinitionURI())){
                            
                        }
                        else {
                            //System.out.println("MD :: " + md.getIdentity());
                            //System.out.println("FC :: " + fc.getDefinitionURI());
                            System.out.println("Unknown ModuleDefinition");
                        }
                    }
                }
            }
            
            for(ModuleDefinition md:doc.getModuleDefinitions()){
                int fcsize = md.getFunctionalComponents().size();
                if(fcsize == 2){
                    URI protURI = null;
                    URI cdsURI = null;
                    boolean production = false;
                    for(FunctionalComponent fc:md.getFunctionalComponents()){
                        if(proteins.containsKey(fc.getDefinitionURI())){
                            protURI = fc.getDefinitionURI();
                        }
                        if(cds.containsKey(fc.getDefinitionURI())){
                            cdsURI = fc.getDefinitionURI();
                            production = true;
                        }
                    }
                    if(production){
                        List<URI> modelList = getAllModels(md);
                        if(repressingProteins.contains(protURI)){
                            repressorCDS.put(cdsURI, cds.get(cdsURI));
                            for (URI uri : modelList) {
                                repressorCDS.get(cdsURI).addModel(uri);
                            }
                            repressorCDS.get(cdsURI).addModuleDefinition(md.getIdentity());
                        }
                        else if(activatingProteins.contains(protURI)){
                            activatorCDS.put(cdsURI, cds.get(cdsURI));
                            for (URI uri : modelList) {
                                activatorCDS.get(cdsURI).addModel(uri);
                            }
                            activatorCDS.get(cdsURI).addModuleDefinition(md.getIdentity());
                        } else {
                            outputCDS.put(cdsURI, cds.get(cdsURI));
                            outputCDS.get(cdsURI).addModuleDefinition(md.getIdentity());
                        }
                    }
                }
            }
            
        } catch (URISyntaxException ex) {
            Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static List<URI> getAllModels(ModuleDefinition md){
        List<URI> uris = new ArrayList<>();
        for(Model model: md.getModels()){
            uris.add(model.getSource());
        }
        return uris;
    }
    
    public static ComponentRole getRole(ComponentDefinition cd) {
        try {
            URI induciblePromURI = new URI(induciblePromSO);
            URI constitutivePromURI = new URI(constitutivePromSO);
            URI rbsURI = new URI(rbsSO);
            URI cdsURI = new URI(cdsSO);
            URI terURI = new URI(terSO);
            URI proteinURI = new URI(proteinSO);
            
            URI inducerURI = new URI(inducerSO);
            
            for(URI uri:cd.getTypes()){
                if(uri.equals(proteinURI)){
                    return ComponentRole.PROTEIN;
                }
            }
            
            Set<URI> roles = cd.getRoles();
            for(URI uri: roles){
                if(uri.equals(terURI)){
                    return ComponentRole.TERMINATOR;
                } else if(uri.equals(rbsURI)){
                    return ComponentRole.RBS;
                } else if(uri.equals(constitutivePromURI)){
                    return ComponentRole.PROMOTER_CONSTITUTIVE;
                } else if(uri.equals(induciblePromURI)){
                    return ComponentRole.PROMOTER;
                } else if(uri.equals(cdsURI)){
                    return ComponentRole.CDS;
                } else if(uri.equals(inducerURI)){
                    return ComponentRole.INDUCER;
                }
            }
        } catch (URISyntaxException ex) {
            Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ComponentRole.WILDCARD ;
    }
}
