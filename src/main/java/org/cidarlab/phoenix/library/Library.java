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
import org.sbolstandard.core2.Model;
import org.sbolstandard.core2.ModuleDefinition;
import org.sbolstandard.core2.SBOLDocument;

/**
 *
 * @author prash
 */
public class Library {
    
    private final static String so = "http://identifiers.org/so/";
    private final static String sbo = "http://identifiers.org/biomodels.sbo/";
    
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
    private Map<URI,LibraryComponent> proteins = new HashMap<>();
    
    public Library(SBOLDocument doc){
        Map<URI,LibraryComponent> promoters = new HashMap<>();
        Map<URI,LibraryComponent> cds = new HashMap<>();
        for(ComponentDefinition cd: doc.getComponentDefinitions()){
            switch(getRole(cd)){
                case PROMOTER_CONSTITUTIVE:
                    constitutivePromoters.put(cd.getIdentity(),new LibraryComponent(cd.getName(),cd.getDisplayId(),cd.getIdentity()));
                    break;
                case PROMOTER:
                    promoters.put(cd.getIdentity(), new LibraryComponent(cd.getName(),cd.getDisplayId(),cd.getIdentity()));
                    break;
                case RBS:
                    rbs.put(cd.getIdentity(), new LibraryComponent(cd.getName(),cd.getDisplayId(),cd.getIdentity()));
                    break;
                case CDS:
                    cds.put(cd.getIdentity(), new LibraryComponent(cd.getName(),cd.getDisplayId(),cd.getIdentity()));
                    break;
                case TERMINATOR:
                    terminators.put(cd.getIdentity(),new LibraryComponent(cd.getName(),cd.getDisplayId(),cd.getIdentity()));
                    break;
                case PROTEIN: 
                    proteins.put(cd.getIdentity(),new LibraryComponent(cd.getName(),cd.getDisplayId(),cd.getIdentity()));
                    break;
            }
        }
        for(ModuleDefinition md:doc.getModuleDefinitions()){
            int fcsize = md.getFunctionalComponents().size();
            List<URI> modelList = getAllModels(md);
            //If this is for FP/output or Constitutive Promoter.
            if(fcsize == 1){
                for(FunctionalComponent fc:md.getFunctionalComponents()){
                    if(constitutivePromoters.containsKey(fc.getDefinitionURI())){
                        for(URI uri:modelList){
                            constitutivePromoters.get(fc.getDefinitionURI()).addModel(uri);
                        }
                    } else if(cds.containsKey(fc.getDefinitionURI())){
                        outputCDS.put(fc.getDefinitionURI(), cds.get(fc.getDefinitionURI()));
                        for(URI uri:modelList){
                            outputCDS.get(fc.getDefinitionURI()).addModel(uri);
                        }
                    }
                }
            } else if(fcsize == 2) {
                //Models for Activatible and Repressible Promoters and CDS. 
            }
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
            
            URI productionURI = new URI(productionSBO);
            URI inhibitionURI = new URI(inhibitionSO);
            URI stimulationURI = new URI(stimulationSO);
            
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
                } else if(uri.equals(proteinURI)){
                    return ComponentRole.PROTEIN;
                }
            }
        } catch (URISyntaxException ex) {
            Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ComponentRole.WILDCARD ;
    }
}
