/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.dom.library;

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
import org.cidarlab.phoenix.dom.library.CompositeComponent.CompositeType;
import org.cidarlab.phoenix.utils.Args.Decomposition;
import org.sbolstandard.core2.Annotation;
import org.sbolstandard.core2.ComponentDefinition;
import org.sbolstandard.core2.FunctionalComponent;
import org.sbolstandard.core2.Interaction;
import org.sbolstandard.core2.Model;
import org.sbolstandard.core2.ModuleDefinition;
import org.sbolstandard.core2.Participation;
import org.sbolstandard.core2.SBOLDocument;
import org.sbolstandard.core2.SBOLValidationException;
import org.sbolstandard.core2.SequenceOntology;
import org.sbolstandard.core2.SystemsBiologyOntology;

/**
 *
 * @author prash
 */
public class Library {

    private final static String so = "http://identifiers.org/so/";
    private final static String sbo = "http://identifiers.org/biomodels.sbo/";

    private final static String inducerSO = "http://www.biopax.org/release/biopax-level3.owl#SmallMolecule";

    private final static String induciblePromSO = so + "SO:0002051";
    private final static String constitutivePromSO = so + "SO:0002050";
    private final static String rbsSO = so + "SO:0000139";
    private final static String cdsSO = so + "SO:0000316";
    
    
    @Getter
    private Map<URI, PromoterComponent> constitutivePromoters = new HashMap<>();

    @Getter
    private Map<URI, PromoterComponent> repressiblePromoters = new HashMap<>();

    @Getter
    private Map<URI, PromoterComponent> activatiblePromoters = new HashMap<>();

    @Getter
    private Map<URI, PromoterComponent> indActPromoters = new HashMap<>();

    @Getter
    private Map<URI, PromoterComponent> indRepPromoters = new HashMap<>();

    @Getter
    private Map<URI, CDSComponent> connectorCDS = new HashMap<>();

    @Getter
    private Map<URI, CDSComponent> outputCDS = new HashMap<>();

    @Getter
    private Map<URI, PrimitiveComponent> rbs = new HashMap<>();

    @Getter
    private Map<URI, PrimitiveComponent> terminators = new HashMap<>();

    @Getter
    private Map<URI, PrimitiveComponent> proteins = new HashMap<>();

    @Getter
    private Map<URI, SmallMoleculeComponent> smallMolecules = new HashMap<>();

    @Getter
    private Map<URI, CompositeComponent> pr = new HashMap<>();

    @Getter
    private Map<URI, CompositeComponent> rc = new HashMap<>();

    @Getter
    private SBOLDocument sbol;
    
    public Library(SBOLDocument doc, Decomposition decomposition) throws URISyntaxException, SBOLValidationException {
        
        this.sbol = doc;
        switch (decomposition) {
            case PR_C_T:
                pr_c_t(doc);
                break;
            case P_RC_T:
                break;
            case P_R_C_T:
                break;
            case PRCT:
                break;
        }
    }

    private void pr_c_t(SBOLDocument doc) throws SBOLValidationException {
        Map<URI, PrimitiveComponent> promoters = new HashMap<>();
        Map<URI, PrimitiveComponent> cds = new HashMap<>();
        Map<URI, ComplexComponent> complexes = new HashMap<>();
        for (ComponentDefinition cd : doc.getComponentDefinitions()) {

            switch (getRole(cd)) {
                case PROMOTER_CONSTITUTIVE:
                    constitutivePromoters.put(cd.getIdentity(), new PromoterComponent(cd.getName(), cd.getDisplayId(), cd.getIdentity()));
                    break;
                case PROMOTER_INDUCIBLE:
                    promoters.put(cd.getIdentity(), new PrimitiveComponent(cd.getName(), cd.getDisplayId(), cd.getIdentity()));
                    break;
                case RBS:
                    rbs.put(cd.getIdentity(), new PrimitiveComponent(cd.getName(), cd.getDisplayId(), cd.getIdentity()));
                    break;
                case CDS:
                    cds.put(cd.getIdentity(), new PrimitiveComponent(cd.getName(), cd.getDisplayId(), cd.getIdentity()));
                    break;
                case TERMINATOR:
                    terminators.put(cd.getIdentity(), new PrimitiveComponent(cd.getName(), cd.getDisplayId(), cd.getIdentity()));
                    break;
                case PROTEIN:
                    proteins.put(cd.getIdentity(), new PrimitiveComponent(cd.getName(), cd.getDisplayId(), cd.getIdentity()));
                    break;
                case SMALL_MOLECULE:
                    SmallMoleculeComponent smcomponent = new SmallMoleculeComponent(cd.getName(), cd.getDisplayId(), cd.getIdentity());
                    if (cd.hasAnnotations()) {
                        for (Annotation a : cd.getAnnotations()) {
                            String localPart = a.getQName().getLocalPart();
                            if (localPart.equals("min")) {
                                smcomponent.setMin(Double.valueOf(a.getStringValue()));
                            } else if (localPart.equals("max")) {
                                smcomponent.setMax(Double.valueOf(a.getStringValue()));
                            }
                        }
                    }
                    smallMolecules.put(cd.getIdentity(), smcomponent);
                    break;
                case ENGINEERED_REGION:
                    List<URI> compositeList = new ArrayList<>();
                    for (org.sbolstandard.core2.Component c : cd.getSortedComponents()) {
                        compositeList.add(c.getDefinitionIdentity());
                    }
                    pr.put(cd.getIdentity(), new CompositeComponent(cd.getName(), cd.getDisplayId(), cd.getIdentity(), CompositeType.PR, compositeList));
                    break;
                case COMPLEX:
                    URI protURI = null;
                    URI smURI = null;
                    for(org.sbolstandard.core2.Component c:cd.getComponents()){
                        for(Annotation a:c.getAnnotations()){
                            if(a.getQName().getLocalPart().equals("complexType")){
                                if(a.getStringValue().equals("Protein")){
                                    protURI = c.getDefinitionIdentity();
                                } else if(a.getStringValue().equals("SmallMolecule")){
                                    smURI = c.getDefinitionIdentity();
                                }
                            }
                        }
                    }
                    complexes.put(cd.getIdentity(), new ComplexComponent(cd.getName(),cd.getDisplayId(),cd.getIdentity(),protURI, smURI));
                    break;
            }
        }
        
        Map<URI,URI> protCDSMap = new HashMap<>();
        Map<URI,URI> protMDMap = new HashMap<>();
        for(ModuleDefinition md:doc.getModuleDefinitions()){
            for (Interaction i : md.getInteractions()) {
                for (URI type : i.getTypes()) {
                    if (type.equals(SystemsBiologyOntology.PRODUCTION)) {
                        URI intProt = null;
                        URI intCDS = null;
                        for(Participation p:i.getParticipations()){
                            for(URI role:p.getRoles()){
                                if(role.equals(SystemsBiologyOntology.TEMPLATE)){
                                    intCDS = p.getParticipantDefinition().getIdentity();
                                } else if(role.equals(SystemsBiologyOntology.PRODUCT)){
                                    intProt = p.getParticipantDefinition().getIdentity();
                                }
                            }
                        }
                        protCDSMap.put(intProt, intCDS);
                        protMDMap.put(intProt, md.getIdentity());
                    } 
                }
            }
        }
        
        Set<URI> connectorProtein = new HashSet<URI>();
        
        //Maybe identify Complex here?
        for(ModuleDefinition md:doc.getModuleDefinitions()){
            for (Interaction i : md.getInteractions()) {
                for (URI type : i.getTypes()) {
                    if (type.equals(SystemsBiologyOntology.STIMULATION)) {
                        URI stimulated = null;
                        URI stimulator = null;
                        for(Participation p:i.getParticipations()){
                            for(URI role:p.getRoles()){
                                if(role.equals(SystemsBiologyOntology.STIMULATED)){
                                    stimulated = p.getParticipantDefinition().getIdentity();
                                } else if(role.equals(SystemsBiologyOntology.STIMULATOR)){
                                    stimulator = p.getParticipantDefinition().getIdentity();
                                }
                            }
                        }
                        
                        if(complexes.containsKey(stimulator)){
                            PrimitiveComponent primProm = null;
                            
                            if(promoters.containsKey(stimulated)){
                                primProm = promoters.get(stimulated);
                                
                            } else if(pr.containsKey(stimulated)){
                                primProm = promoters.get(pr.get(stimulated).getChildren().get(0));
                                pr.get(stimulated).addModuleDefinition(md.getIdentity());
                                for(Model model:md.getModels()){
                                    pr.get(stimulated).addModel(model.getIdentity());
                                }
                            } else {
                                System.out.println("Something wrong");
                            }
                            
                            PromoterComponent ipc = new PromoterComponent(primProm.name, primProm.displayId, primProm.componentDefintion);
                            ipc.addComplex(complexes.get(stimulator));
                            
                            indActPromoters.put(primProm.componentDefintion, ipc);
                            ComplexComponent complex = (ComplexComponent) complexes.get(stimulator);
                            connectorProtein.add(complex.getProtein());
                            
                        } else if(proteins.containsKey(stimulator)){
                            PrimitiveComponent primProm = null;
                            
                            if(promoters.containsKey(stimulated)){
                                primProm = promoters.get(stimulated);
                            } else if(pr.containsKey(stimulated)){
                                primProm = promoters.get(pr.get(stimulated).getChildren().get(0));
                                pr.get(stimulated).addModuleDefinition(md.getIdentity());
                                for(Model model:md.getModels()){
                                    pr.get(stimulated).addModel(model.getIdentity());
                                }
                            } else {
                                System.out.println("Something wrong");
                            }
                            
                            PromoterComponent ipc = new PromoterComponent(primProm.name, primProm.displayId, primProm.componentDefintion);
                            ipc.addProtein(proteins.get(stimulator));
                            
                            activatiblePromoters.put(primProm.componentDefintion, ipc);
                            connectorProtein.add(stimulator);
                            
                        } else {
                            System.out.println("Something went wrong");
                        }
                        
                    } else if (type.equals(SystemsBiologyOntology.INHIBITION)) {
                        URI inhibited = null;
                        URI inhibitor = null;
                        for(Participation p:i.getParticipations()){
                            for(URI role:p.getRoles()){
                                if(role.equals(SystemsBiologyOntology.INHIBITED)){
                                    inhibited = p.getParticipantDefinition().getIdentity();
                                } else if(role.equals(SystemsBiologyOntology.INHIBITOR)){
                                    inhibitor = p.getParticipantDefinition().getIdentity();
                                }
                            }
                        }
                        if(complexes.containsKey(inhibitor)){
                            
                            PrimitiveComponent primProm = null;
                            
                            if(promoters.containsKey(inhibited)){
                                primProm = promoters.get(inhibited);
                            } else if(pr.containsKey(inhibited)){
                                primProm = promoters.get(pr.get(inhibited).getChildren().get(0));
                                pr.get(inhibited).addModuleDefinition(md.getIdentity());
                                for(Model model:md.getModels()){
                                    pr.get(inhibited).addModel(model.getIdentity());
                                }
                            } else {
                                System.out.println("Something wrong");
                            }
                            
                            PromoterComponent ipc = new PromoterComponent(primProm.name, primProm.displayId, primProm.componentDefintion);
                            ipc.addComplex(complexes.get(inhibitor));
                            
                            indRepPromoters.put(primProm.componentDefintion, ipc);
                            ComplexComponent complex = (ComplexComponent) complexes.get(inhibitor);
                            connectorProtein.add(complex.getProtein());
                            
                        } else if(proteins.containsKey(inhibitor)){
                            PrimitiveComponent primProm = null;
                            
                            if(promoters.containsKey(inhibited)){
                                primProm = promoters.get(inhibited);
                            } else if(pr.containsKey(inhibited)){
                                primProm = promoters.get(pr.get(inhibited).getChildren().get(0));
                                pr.get(inhibited).addModuleDefinition(md.getIdentity());
                                for(Model model:md.getModels()){
                                    pr.get(inhibited).addModel(model.getIdentity());
                                }
                            } else {
                                System.out.println("Something wrong");
                            }
                            PromoterComponent ipc = new PromoterComponent(primProm.name, primProm.displayId, primProm.componentDefintion);
                            ipc.addProtein(proteins.get(inhibitor));
                            
                            repressiblePromoters.put(primProm.componentDefintion, ipc);
                            connectorProtein.add(inhibitor);
                            
                        } else {
                            System.out.println("Something went wrong");
                        }
                    }
                }
            }
        }
        
        for(ModuleDefinition md:doc.getModuleDefinitions()){
            for(FunctionalComponent fc:md.getFunctionalComponents()){
                if(pr.containsKey(fc.getDefinitionIdentity())){
                    CompositeComponent compcomp = pr.get(fc.getDefinitionIdentity());
                    if (this.constitutivePromoters.containsKey(compcomp.getChildren().get(0))) {
                        pr.get(fc.getDefinitionIdentity()).addModuleDefinition(md.getIdentity());
                        for (Model model : md.getModels()) {
                            pr.get(fc.getDefinitionIdentity()).addModel(model.getIdentity());
                        }
                    }
                }
            }
        }
        
        for(URI uri:protCDSMap.keySet()){
            if(connectorProtein.contains(uri)){
                PrimitiveComponent primCDS = cds.get(protCDSMap.get(uri));
                CDSComponent cdsComp = new CDSComponent(primCDS.name, primCDS.displayId, primCDS.componentDefintion, uri);
                cdsComp.addModuleDefinition(protMDMap.get(uri));
                for(Model model:doc.getModuleDefinition(protMDMap.get(uri)).getModels()){
                    cdsComp.addModel(model.getIdentity());
                }
                
                connectorCDS.put(protCDSMap.get(uri), cdsComp);
                
                
            } else {
                PrimitiveComponent primCDS = cds.get(protCDSMap.get(uri));
                CDSComponent cdsComp = new CDSComponent(primCDS.name, primCDS.displayId, primCDS.componentDefintion, uri);
                cdsComp.addModuleDefinition(protMDMap.get(uri));
                for(Model model:doc.getModuleDefinition(protMDMap.get(uri)).getModels()){
                    cdsComp.addModel(model.getIdentity());
                }
                
                outputCDS.put(protCDSMap.get(uri), cdsComp);
                
            }
        }
        
    }
    
    
    public Map<URI,CompositeComponent> getPR(ComponentRole promRole){
        Map<URI,CompositeComponent> lcmap = new HashMap<>();
        for(URI uri:this.pr.keySet()){
            CompositeComponent prcomponent = pr.get(uri);
            URI prPromURI = prcomponent.getChildren().get(0);
            switch(promRole){
                case PROMOTER_CONSTITUTIVE:
                    if (this.constitutivePromoters.containsKey(prPromURI)) {
                        lcmap.put(uri, pr.get(uri));
                    }
                    break;
                case PROMOTER_ACTIVATABLE:
                    if (this.activatiblePromoters.containsKey(prPromURI)) {
                        lcmap.put(uri, pr.get(uri));
                    }
                    break;
                case PROMOTER_REPRESSIBLE:
                    if (this.repressiblePromoters.containsKey(prPromURI)) {
                        lcmap.put(uri, pr.get(uri));
                    }
                    break;
            }
        }
        return lcmap;
    }
    
    
    
    private static List<URI> getAllModels(ModuleDefinition md) {
        List<URI> uris = new ArrayList<>();
        for (Model model : md.getModels()) {
            uris.add(model.getSource());
        }
        return uris;
    }

    public static ComponentRole getRole(ComponentDefinition cd) {
        try {
            URI induciblePromURI = new URI(induciblePromSO);
            URI constitutivePromURI = new URI(constitutivePromSO);
            URI rbsURI = new URI(rbsSO);
            URI inducerURI = new URI(inducerSO);

            for (URI uri : cd.getTypes()) {
                if (uri.equals(ComponentDefinition.PROTEIN)) {
                    return ComponentRole.PROTEIN;
                } else if (uri.equals(ComponentDefinition.SMALL_MOLECULE)) {
                    return ComponentRole.SMALL_MOLECULE;
                } else if (uri.equals(SequenceOntology.ENGINEERED_REGION)) {
                    return ComponentRole.ENGINEERED_REGION;
                } else if(uri.equals(ComponentDefinition.COMPLEX)){
                    return ComponentRole.COMPLEX;
                }
            }

            Set<URI> roles = cd.getRoles();
            for (URI uri : roles) {
                if (uri.equals(SequenceOntology.TERMINATOR)) {
                    return ComponentRole.TERMINATOR;
                } else if (uri.equals(rbsURI)) {
                    return ComponentRole.RBS;
                } else if (uri.equals(constitutivePromURI)) {
                    return ComponentRole.PROMOTER_CONSTITUTIVE;
                } else if (uri.equals(induciblePromURI)) {
                    return ComponentRole.PROMOTER_INDUCIBLE;
                } else if (uri.equals(SequenceOntology.CDS)) {
                    return ComponentRole.CDS;
                }
            }
        } catch (URISyntaxException ex) {
            Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ComponentRole.WILDCARD;
    }
    
    public Map<URI, PromoterComponent> getAllPromoters(){
        Map<URI, PromoterComponent> all = new HashMap<>();
        all.putAll(constitutivePromoters);
        all.putAll(repressiblePromoters);
        all.putAll(activatiblePromoters);
        all.putAll(indActPromoters);
        all.putAll(indRepPromoters);
        return all;
    }
    
    public Map<URI, PromoterComponent> getAllInduciblePromoters(){
        Map<URI, PromoterComponent> all = new HashMap<>();
        all.putAll(repressiblePromoters);
        all.putAll(activatiblePromoters);
        all.putAll(indActPromoters);
        all.putAll(indRepPromoters);
        return all;
    }
    
    public Map<URI, LibraryComponent> getAllLibraryComponents() {
        Map<URI, LibraryComponent> all = new HashMap<>();
        all.putAll(constitutivePromoters);
        all.putAll(repressiblePromoters);
        all.putAll(activatiblePromoters);
        all.putAll(indActPromoters);
        all.putAll(indRepPromoters);
        all.putAll(connectorCDS);
        all.putAll(outputCDS);
        all.putAll(rbs);
        all.putAll(terminators);
        all.putAll(proteins);
        all.putAll(smallMolecules);
        all.putAll(pr);
        all.putAll(rc);
        return all;
    }

    public LibraryComponent fromURI(URI uri) {
        Map<URI, LibraryComponent> all = getAllLibraryComponents();
        if (all.containsKey(uri)) {
            return all.get(uri);
        }
        return null;
    }

}
