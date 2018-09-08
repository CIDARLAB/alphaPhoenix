/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.adaptors;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import org.cidarlab.phoenix.dom.CandidateComponent;
import org.cidarlab.phoenix.dom.Component;
import org.cidarlab.phoenix.dom.Module;
import org.cidarlab.phoenix.dom.Orientation;
import org.cidarlab.phoenix.utils.Utilities;
import org.json.JSONArray;
import org.json.JSONObject;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBMLWriter;
import org.sbolstandard.core2.AccessType;
import org.sbolstandard.core2.Activity;
import org.sbolstandard.core2.Attachment;
import org.sbolstandard.core2.ComponentDefinition;
import org.sbolstandard.core2.DirectionType;
import org.sbolstandard.core2.FunctionalComponent;
import org.sbolstandard.core2.Interaction;
import org.sbolstandard.core2.Model;
import org.sbolstandard.core2.ModuleDefinition;
import org.sbolstandard.core2.OrientationType;
import org.sbolstandard.core2.SBOLConversionException;
import org.sbolstandard.core2.SBOLDocument;
import org.sbolstandard.core2.SBOLValidationException;
import org.sbolstandard.core2.SBOLWriter;
import org.sbolstandard.core2.Sequence;
import org.sbolstandard.core2.SequenceAnnotation;
import org.sbolstandard.core2.SequenceOntology;
import org.sbolstandard.core2.SystemsBiologyOntology;

/**
 *
 * @author prash
 */
public class SBOLAdaptor {
        
    private static final String so = "http://identifiers.org/so/";
    private static final String sbo = "http://identifiers.org/biomodels.sbo/";
    private static final String baseurl = "http://www.phoenix.org/library/";
    private static final String version = "0.3";
    private static final String induciblePromSO = so + "SO:0002051";
    private static final String constitutivePromSO = so + "SO:0002050";
    private static final String rbsSO = so + "SO:0000139";
    private static final String engineeredRegionSO = so + "SO:0000804";
    
    private static final String sbmlO = "http://identifiers.org/edam/format_2585";
    private static final String frameworkSBO = "http://www.ebi.ac.uk/sbo/main/SBO:0000062";
    
    private static String provNS = "http://www.w3.org/ns/prov#";	
    private static String dcNS = "http://purl.org/dc/elements/1.1/";
    private static String dcTermsNS = "http://purl.org/dc/terms/";
    private static String celloNS = "http://cellocad.org/Terms/cello#";

    private static URI activityURI;
    private static String createdDate;

    
    
    public static void convertSBOLToGenbank(SBOLDocument sbol, String filepath) throws FileNotFoundException, SBOLConversionException, IOException {
        FileOutputStream out =  new FileOutputStream(new File(filepath));
        SBOLWriter.write(sbol, out, SBOLDocument.GENBANK);
        out.close();
    }
    
    public static void writeCircuitSBOL(Module m, Map<String,CandidateComponent> assignment, String filepath, String jobid, int assignmentindex){
        try {
            SBOLDocument sbol = new SBOLDocument();
                    
            String urlprefix = "https://phoenix.org/results/" + jobid + "/";
            String displayid = "circuit" + assignmentindex;
            String version = "0.1";
            URI engineeredRegionURI = new URI(engineeredRegionSO);
            ComponentDefinition cd = sbol.createComponentDefinition(urlprefix, displayid, version, engineeredRegionURI);
            int i=0;
            for(Component c:m.getComponents()){
                
                org.sbolstandard.core2.Component sbolcomponent = cd.createComponent(c.getName() + "_" + i, AccessType.PRIVATE, assignment.get(c.getName()).getCandidate().getComponentDefintion());
                String saId = "SequenceAnnotation" + i;
                String locationId = "Location" + i;
                if(c.getOrientation().equals(Orientation.FORWARD)){
                    SequenceAnnotation sa = cd.createSequenceAnnotation(saId, locationId, OrientationType.INLINE);
                    sa.setComponent(sbolcomponent.getIdentity());
                } else {
                    SequenceAnnotation sa = cd.createSequenceAnnotation(saId, locationId, OrientationType.REVERSECOMPLEMENT);
                    sa.setComponent(sbolcomponent.getIdentity());
                }
                i++;
            }
            
            String sbolfp = filepath + "sbol.xml";
            SBOLWriter.write(sbol, sbolfp);
            
        } catch (URISyntaxException | SBOLValidationException | IOException | SBOLConversionException ex) {
            Logger.getLogger(SBOLAdaptor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public static SBOLDocument convertUCFtoSBOL(JSONObject ucf, String outputfp) throws SBOLValidationException, URISyntaxException, IOException, SBOLConversionException, FileNotFoundException, XMLStreamException{
        
        SBOLDocument sbol = new SBOLDocument();
        Map<String,JSONObject> partMap = new HashMap<>();
        Map<String,URI> partURImap = new HashMap<>();
        
        Set<JSONObject> constitutiveProms = new HashSet<>();
        Set<JSONObject> reporterProteins = new HashSet<>();
        
        Activity genericTopLevel = sbol.createActivity(baseurl, "phoenix2sbol", version);
        genericTopLevel.setName("Phoenix UCF to SBOL conversion");
        genericTopLevel.setDescription("Conversion of the Phoenix UCF parts and metadata to SBOL2");
        genericTopLevel.createAnnotation(new QName(dcNS, "creator", "dc"), "Prashant Vaidyanathan");
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        df.setTimeZone(tz);
        createdDate = df.format(new Date());
        genericTopLevel.createAnnotation(new QName(provNS, "endedAtTime", "prov"), createdDate);
        activityURI = genericTopLevel.getIdentity();

        
        
        if(ucf.has("parts")){
            JSONArray partsArr = ucf.getJSONArray("parts");
            for(Object obj:partsArr){
                JSONObject part = (JSONObject)obj;
                partMap.put(part.getString("id"), part);
                partURImap.putAll(createPart(sbol,part));
                
                if(part.getString("part").equals("promoter")){
                    if(part.getString("type").equals("constitutive")){
                        if(part.has("k_express")){
                            constitutiveProms.add(part);
                        }
                    }
                }
                if(part.getString("part").equals("cds")){
                    if(part.getString("type").equals("reporter")){
                        reporterProteins.add(part);
                    }
                }
                
            }
        }
        
        if(ucf.has("composite-parts")){
            JSONArray cpartsArr = ucf.getJSONArray("composite-parts");
            for(Object obj:cpartsArr){
                JSONObject cpart = (JSONObject)obj;
                partMap.put(cpart.getString("id"), cpart);
                partURImap.put(cpart.getString("id"), createCompositePart(sbol,cpart,partURImap));
                
                if(cpart.getString("type").equals("promoter-rbs")){
                    String promId = (String)cpart.getJSONArray("parts").get(0);
                    if (partMap.get(promId).getString("part").equals("promoter")) {
                        if (partMap.get(promId).getString("type").equals("constitutive")) {
                            constitutiveProms.add(cpart);
                        }
                    }
                    
                }
                
            }
        }
        
        if(ucf.has("eugene-rules")){
            JSONArray erules = ucf.getJSONArray("eugene-rules");
            Utilities.writeToFile(outputfp + "eugeneRules.json",erules.toString(2));
            Attachment erAttachment = sbol.createAttachment(baseurl, "EugeneRules", version, new URI("file:eugeneRules.json"));
            erAttachment.addWasGeneratedBy(activityURI);
            
        }
        
        
        if(ucf.has("modules")){
            JSONArray modulesArr = ucf.getJSONArray("modules");
            for(Object obj:modulesArr){
                JSONObject module = (JSONObject)obj;
                createModuleDefinition(sbol,module,partURImap,partMap,outputfp);
            }
        }
        
        for(JSONObject part:constitutiveProms){
            ComponentDefinition prom = sbol.getComponentDefinition(partURImap.get(part.getString("id")));
            createConstitutivePromoterModels(sbol,part,prom,partMap,outputfp);
        }
        
        for(JSONObject part:reporterProteins){
            String id = part.getString("id");
            ComponentDefinition cds = sbol.getComponentDefinition(partURImap.get(id));
            ComponentDefinition prot = sbol.getComponentDefinition(partURImap.get(id + "_Protein"));
            createProduction(sbol, part, cds, prot, outputfp);
        }
        
        SBOLWriter.write(sbol, outputfp + "sbol.xml");
       
        
        return sbol;
    }
    
    private static URI createCompositePart(SBOLDocument sbol, JSONObject part, Map<String,URI> partURImap) throws SBOLValidationException, URISyntaxException{
        switch(part.getString("type")){
            case "promoter-rbs":
                return createPromoterRBSPart(sbol,part,partURImap);
            default: 
                return null;
        }
    }
    
    private static URI createPromoterRBSPart(SBOLDocument sbol, JSONObject part, Map<String,URI> partURImap) throws SBOLValidationException, URISyntaxException{
        ComponentDefinition promrbs = sbol.createComponentDefinition(baseurl, part.getString("id"), version, ComponentDefinition.DNA);
        promrbs.addWasGeneratedBy(activityURI);
        if(part.has("name")){
            promrbs.setName(part.getString("name"));
        } else {
            promrbs.setName(part.getString("id"));
        }
        promrbs.addType(new URI(engineeredRegionSO));
        int cindex = 0;
        int startIndex = 1;
        for(Object p:part.getJSONArray("parts")){
            String pid = (String)p;
            org.sbolstandard.core2.Component sbolcomponent = promrbs.createComponent(pid + "_" + cindex, AccessType.PRIVATE, partURImap.get(pid));
            sbolcomponent.addWasGeneratedBy(activityURI);
            String locationId = "Location" + cindex;
            String saId = "SequenceAnnotation" + cindex;
            String rangeId = "Range" + cindex;
            SequenceAnnotation sa = promrbs.createSequenceAnnotation(saId, locationId);
            sa.setComponent(sbolcomponent.getIdentity());
            sa.addWasGeneratedBy(activityURI);
            int seqLen = 0;
            for(Sequence s:sbol.getComponentDefinition(partURImap.get(pid)).getSequences()){
                seqLen = s.getElements().length();
                break;
            }
            if(seqLen == 0){
                seqLen = 1;
            } 
            sa.addRange(rangeId, startIndex, startIndex + seqLen-1);    
            startIndex += seqLen;
            cindex++;
        }
        return promrbs.getIdentity();
    }
    
    private static void createConstitutivePromoterModels(SBOLDocument sbol, JSONObject part, ComponentDefinition prom, Map<String, JSONObject> partMap, String outputfp) throws SBOLValidationException, FileNotFoundException, XMLStreamException, URISyntaxException{
        String promId = part.getString("id");
        ModuleDefinition md = sbol.createModuleDefinition(baseurl, promId + "_ModuleDefinition", version);
        md.addWasGeneratedBy(activityURI);
        md.createFunctionalComponent(promId, AccessType.PRIVATE, prom.getIdentity(), DirectionType.OUT);
        createPromModel(part,partMap,outputfp);
        Model model = sbol.createModel(baseurl, promId + "_Model",version, new URI("file:" + promId + ".xml") , new URI(sbmlO), new URI(frameworkSBO));
        model.addWasGeneratedBy(activityURI);
        md.addModel(model);
    }
    
    private static void createModuleDefinition(SBOLDocument sbol, JSONObject module, Map<String,URI> partURImap, Map<String,JSONObject> partMap, String outputfp) throws URISyntaxException, SBOLValidationException, FileNotFoundException, XMLStreamException{
        
        String promId = "";
        String cdsId = "";
        Set<String> smIds = new HashSet<>();
        for(Object obj:module.getJSONArray("parts")){
            String id = (String)obj;
            if(partMap.get(id).has("part")){
                switch (partMap.get(id).getString("part")) {
                    case "promoter":
                        promId = id;
                        break;
                    case "cds":
                        cdsId = id;
                        break;
                    case "smallMolecule":
                        smIds.add(id);
                        break;
                }
            } else {
                switch(partMap.get(id).getString("type")){
                    case "promoter-rbs":
                        promId = id;
                        break;
                }
            }
            
        }
        
        String protMD = baseurl + cdsId + "_protein_production/" + version;
        if(sbol.getModuleDefinition(new URI(protMD)) == null){
            ComponentDefinition cds = sbol.getComponentDefinition(partURImap.get(cdsId));
            ComponentDefinition prot = sbol.getComponentDefinition(partURImap.get(cdsId + "_Protein"));
            createProduction(sbol, partMap.get(cdsId), cds, prot, outputfp);
        }
        
        if(smIds.isEmpty()){
            String interaction = module.getString("interaction");
            String promMD = baseurl + cdsId + "_Protein_" + promId + "_" + interaction + "/" + version;
            if(sbol.getModuleDefinition(new URI(promMD)) == null){
                ComponentDefinition prot = sbol.getComponentDefinition(partURImap.get(cdsId + "_Protein"));
                ComponentDefinition prom = sbol.getComponentDefinition(partURImap.get(promId));
                createInduction(sbol, partMap.get(promId), interaction, cdsId + "_Protein", prot, promId, prom, partMap, outputfp);
            }
        } else {
            String interaction = module.getString("interaction");
            List<ComponentDefinition> smCDs = new ArrayList<>();
            List<String> smIdList = new ArrayList<>();
            
            String complex = "";
            for (String s : new HashSet<>(smIds)) {
                smCDs.add(sbol.getComponentDefinition(partURImap.get(s)));
                smIdList.add(s);
                complex += s + "_";
            }
            complex += cdsId + "_Protein";
            String complexMD = baseurl + complex + "_complex_ModuleDefinition" + "/" + version;
            if(sbol.getModuleDefinition(new URI(complexMD)) == null){
                ComponentDefinition prot = sbol.getComponentDefinition(partURImap.get(cdsId + "_Protein"));
                createComplex(sbol, smIdList, smCDs, cdsId + "_Protein", prot, partURImap);
            }
            
            String promMD = baseurl + complex + "_Complex_" + promId + "_" + interaction + "/" + version;
            if(sbol.getModuleDefinition(new URI(promMD)) == null){
                String complexURL = baseurl + complex + "_Complex/" + version;
                ComponentDefinition complexCD = sbol.getComponentDefinition(new URI(complexURL));
                ComponentDefinition prom = sbol.getComponentDefinition(partURImap.get(promId));
                createInduction(sbol, partMap.get(promId), interaction, complex + "_Compelx", complexCD, promId, prom, partMap, outputfp);
            }
        }
    }
    
    private static void createInduction(SBOLDocument sbol, JSONObject part, String interaction, String inducerId, ComponentDefinition inducer, String promId, ComponentDefinition prom, Map<String,JSONObject> partMap, String outputfp) throws SBOLValidationException, FileNotFoundException, XMLStreamException, URISyntaxException{
        switch(interaction){
            case "activation":
                createActivation(sbol, part, inducerId, inducer, promId, prom, partMap, outputfp);
                break;
            case "repression":
                createRepression(sbol, part, inducerId, inducer, promId, prom, partMap, outputfp);
                break;
        }
    }
    
    private static void createActivation(SBOLDocument sbol, JSONObject part, String inducerId, ComponentDefinition inducer, String promId, ComponentDefinition prom, Map<String,JSONObject> partMap, String outputfp) throws SBOLValidationException, FileNotFoundException, XMLStreamException, URISyntaxException{
        ModuleDefinition md = sbol.createModuleDefinition(baseurl, inducerId + "_" +  promId + "_ModuleDefinition", version);
        md.addWasGeneratedBy(activityURI);
        FunctionalComponent inducerFC = md.createFunctionalComponent(inducerId, AccessType.PRIVATE, inducer.getIdentity(), DirectionType.OUT);
        inducerFC.addWasGeneratedBy(activityURI);
        FunctionalComponent promoterFC = md.createFunctionalComponent(promId, AccessType.PRIVATE, prom.getIdentity(), DirectionType.IN);
        promoterFC.addWasGeneratedBy(activityURI);
        Interaction interaction = md.createInteraction(inducerId + "_" + promId + "_Stimulation", SystemsBiologyOntology.STIMULATION);
        interaction.addWasGeneratedBy(activityURI);
        interaction.createParticipation(inducerId + "_stimulator", inducerFC.getIdentity(), SystemsBiologyOntology.STIMULATOR);
        interaction.createParticipation(promId + "_stimulated", promoterFC.getIdentity(), SystemsBiologyOntology.STIMULATED);
        
        String modelURL = baseurl + promId + "_Model/" + version;
        if(sbol.getModel(new URI(modelURL)) == null){
            createPromModel(part, partMap, outputfp);
            Model model = sbol.createModel(baseurl, promId + "_Model",version, new URI("file:" + promId + ".xml") , new URI(sbmlO), new URI(frameworkSBO));
            model.addWasGeneratedBy(activityURI);
            md.addModel(model);
        } else {
            md.addModel(sbol.getModel(new URI(modelURL)));
        }
    }
    
    private static void createRepression(SBOLDocument sbol, JSONObject part, String inducerId, ComponentDefinition inducer, String promId, ComponentDefinition prom, Map<String,JSONObject> partMap, String outputfp) throws SBOLValidationException, URISyntaxException, FileNotFoundException, XMLStreamException{
        ModuleDefinition md = sbol.createModuleDefinition(baseurl, inducerId + "_" +  promId + "_ModuleDefinition", version);
        md.addWasGeneratedBy(activityURI);
        FunctionalComponent inducerFC = md.createFunctionalComponent(inducerId, AccessType.PRIVATE, inducer.getIdentity(), DirectionType.OUT);
        inducerFC.addWasGeneratedBy(activityURI);
        FunctionalComponent promoterFC = md.createFunctionalComponent(promId, AccessType.PRIVATE, prom.getIdentity(), DirectionType.IN);
        promoterFC.addWasGeneratedBy(activityURI);
        Interaction interaction = md.createInteraction(inducerId + "_" + promId + "_Inhibition", SystemsBiologyOntology.INHIBITION);
        interaction.addWasGeneratedBy(activityURI);
        interaction.createParticipation(inducerId + "_inhibitor", inducerFC.getIdentity(), SystemsBiologyOntology.INHIBITOR);
        interaction.createParticipation(promId + "_inhibited", promoterFC.getIdentity(), SystemsBiologyOntology.INHIBITED);
        
        String modelURL = baseurl + promId + "_Model/" + version;
        if(sbol.getModel(new URI(modelURL)) == null){
            createPromModel(part, partMap, outputfp);
            Model model = sbol.createModel(baseurl, promId + "_Model",version, new URI("file:" + promId + ".xml") , new URI(sbmlO), new URI(frameworkSBO));
            model.addWasGeneratedBy(activityURI);
            md.addModel(model);
        } else {
            md.addModel(sbol.getModel(new URI(modelURL)));
        }
    }
    
    private static void createPromModel(JSONObject part, Map<String,JSONObject> partMap, String outputfp) throws FileNotFoundException, XMLStreamException{
        String activationType = "";
        if(part.getString("type").equals("promoter-rbs")){
            String promId = (String)part.getJSONArray("parts").get(0);
            activationType = partMap.get(promId).getString("type");
        } else {
            activationType = part.getString("type");
        }
        SBMLDocument model = null;
        switch (activationType) {
            case "constitutive":
                model = SBMLAdaptor.createConstituativePromoterModel("out");
                SBMLAdaptor.setReactionParameterValue(model, "out_expression", "k_express", part.getDouble("k_express"));
                break;
            case "activated":
                model = SBMLAdaptor.createActivatedPromoterModel("conn", "out");
                
                SBMLAdaptor.setReactionParameterValue(model, "out_expression", "K_d", part.getDouble("K_d"));
                SBMLAdaptor.setReactionParameterValue(model, "out_expression", "basal", part.getDouble("basal"));
                SBMLAdaptor.setReactionParameterValue(model, "out_expression", "max", part.getDouble("max"));
                SBMLAdaptor.setReactionParameterValue(model, "out_expression", "n", part.getDouble("n"));
                break;
            case "repressed":
                model = SBMLAdaptor.createRepressedPromoterModel("conn", "out");
                SBMLAdaptor.setReactionParameterValue(model, "out_expression", "K_d", part.getDouble("K_d"));
                SBMLAdaptor.setReactionParameterValue(model, "out_expression", "basal", part.getDouble("basal"));
                SBMLAdaptor.setReactionParameterValue(model, "out_expression", "max", part.getDouble("max"));
                SBMLAdaptor.setReactionParameterValue(model, "out_expression", "n", part.getDouble("n"));
                break;
            case "activated-induced":
                model = SBMLAdaptor.createInducibleActivatedPromoterModel("conn", "ind", "out");
                SBMLAdaptor.setReactionParameterValue(model, "out_expression", "K_d", part.getDouble("K_d"));
                SBMLAdaptor.setReactionParameterValue(model, "out_expression", "basal", part.getDouble("basal"));
                SBMLAdaptor.setReactionParameterValue(model, "out_expression", "max", part.getDouble("max"));
                SBMLAdaptor.setReactionParameterValue(model, "out_expression", "n", part.getDouble("n"));
                
                SBMLAdaptor.setReactionParameterValue(model, "out_expression", "max_TF", part.getDouble("max_TF"));
                SBMLAdaptor.setReactionParameterValue(model, "out_expression", "K_d_TF", part.getDouble("K_d_TF"));
                SBMLAdaptor.setReactionParameterValue(model, "out_expression", "n_TF", part.getDouble("n_TF"));
                
                //SBMLAdaptor.setReactionParameterValue(model, "out_expression", "max_SM", part.getDouble("max_SM"));
                SBMLAdaptor.setReactionParameterValue(model, "out_expression", "K_d_SM", part.getDouble("K_d_SM"));
                SBMLAdaptor.setReactionParameterValue(model, "out_expression", "n_SM", part.getDouble("n_SM"));
                break;
            case "repressed-induced":
                model = SBMLAdaptor.createInducibleActivatedPromoterModel("conn", "ind", "out"); //THIS IS WRONG AND NEEDS TO CHANGE
                SBMLAdaptor.setReactionParameterValue(model, "out_expression", "K_d", part.getDouble("K_d"));
                SBMLAdaptor.setReactionParameterValue(model, "out_expression", "basal", part.getDouble("basal"));
                SBMLAdaptor.setReactionParameterValue(model, "out_expression", "max", part.getDouble("max"));
                SBMLAdaptor.setReactionParameterValue(model, "out_expression", "n", part.getDouble("n"));
                
                SBMLAdaptor.setReactionParameterValue(model, "out_expression", "max_TF", part.getDouble("max_TF"));
                SBMLAdaptor.setReactionParameterValue(model, "out_expression", "K_d_TF", part.getDouble("K_d_TF"));
                SBMLAdaptor.setReactionParameterValue(model, "out_expression", "n_TF", part.getDouble("n_TF"));
                
                //SBMLAdaptor.setReactionParameterValue(model, "out_expression", "max_SM", part.getDouble("max_SM"));
                SBMLAdaptor.setReactionParameterValue(model, "out_expression", "K_d_SM", part.getDouble("K_d_SM"));
                SBMLAdaptor.setReactionParameterValue(model, "out_expression", "n_SM", part.getDouble("n_SM"));
                break;
        }
        SBMLWriter writer = new SBMLWriter();
        writer.writeSBMLToFile(model, outputfp + part.getString("id") + ".xml");
    }
    
    private static void createComplex(SBOLDocument sbol, List<String> smIds, List<ComponentDefinition> smCDS, String protId, ComponentDefinition prot, Map<String,URI> partURImap) throws SBOLValidationException{
        String complex = "";
        for(String s: smIds){
            complex += s + "_";
        }
        complex += protId ;
        ComponentDefinition cmplx = sbol.createComponentDefinition(baseurl, complex + "_Complex", version, ComponentDefinition.COMPLEX);
        cmplx.setName(complex + " Complex");
        cmplx.addWasGeneratedBy(activityURI);
        QName qname = new QName(baseurl,"complexType");
            
        
        for(String s:smIds){
            org.sbolstandard.core2.Component smcomp = cmplx.createComponent(s, AccessType.PRIVATE, partURImap.get(s));
            smcomp.addWasGeneratedBy(activityURI);
            smcomp.createAnnotation(qname, "SmallMolecule");
        }
        org.sbolstandard.core2.Component protComplex = cmplx.createComponent(protId, AccessType.PRIVATE, prot.getIdentity());
        protComplex.addWasGeneratedBy(activityURI);
        protComplex.createAnnotation(qname, "Protein");
        
        ModuleDefinition md = sbol.createModuleDefinition(baseurl, complex + "_complex_ModuleDefinition", version);
        md.addWasGeneratedBy(activityURI);
        List<FunctionalComponent> smFCList = new ArrayList<>();
        for(int i=0;i<smIds.size();i++){
            smFCList.add(md.createFunctionalComponent(smIds.get(i), AccessType.PRIVATE, smCDS.get(i).getIdentity(), DirectionType.NONE));
        }
        FunctionalComponent protFC = md.createFunctionalComponent(protId, AccessType.PRIVATE, prot.getIdentity(), DirectionType.NONE);
        protFC.addWasGeneratedBy(activityURI);
        FunctionalComponent complexFC = md.createFunctionalComponent(complex + "_Complex", AccessType.PRIVATE, cmplx.getIdentity(), DirectionType.NONE);
        complexFC.addWasGeneratedBy(activityURI);
        
        Interaction interaction = md.createInteraction(complex + "_complex_formation", SystemsBiologyOntology.NON_COVALENT_BINDING);
        interaction.addWasGeneratedBy(activityURI);
        for(int i=0;i<smIds.size();i++){
            interaction.createParticipation(smIds.get(i), smFCList.get(i).getIdentity(), SystemsBiologyOntology.REACTANT);
        }
        interaction.createParticipation(protId, protFC.getIdentity(), SystemsBiologyOntology.REACTANT);
        interaction.createParticipation(complex + "_Complex", complexFC.getIdentity(), SystemsBiologyOntology.PRODUCT);
    }
    
    private static void createProduction(SBOLDocument sbol, JSONObject part, ComponentDefinition cds, ComponentDefinition prot, String outputfp) throws SBOLValidationException, URISyntaxException, FileNotFoundException, XMLStreamException{
        String cdsId = part.getString("id");
        
        ModuleDefinition md = sbol.createModuleDefinition(baseurl, cdsId + "_protein_production", version);
        md.addWasGeneratedBy(activityURI);
        md.setName(cdsId + " Protein Production");
        FunctionalComponent cdsFC = md.createFunctionalComponent(cdsId, AccessType.PRIVATE, cds.getIdentity(), DirectionType.OUT);
        cdsFC.addWasGeneratedBy(activityURI);
        FunctionalComponent protFC = md.createFunctionalComponent(cdsId + "_Protein", AccessType.PRIVATE, prot.getIdentity(), DirectionType.IN);
        protFC.addWasGeneratedBy(activityURI);
        Interaction interaction = md.createInteraction(cdsId + "_production", SystemsBiologyOntology.PRODUCTION);
        interaction.addWasGeneratedBy(activityURI);
        interaction.createParticipation(cdsId + "_template", cdsFC.getIdentity(), SystemsBiologyOntology.TEMPLATE);
        interaction.createParticipation(cdsId + "_product", protFC.getIdentity(), SystemsBiologyOntology.PRODUCT);
        createCDSModel(part,outputfp);
        
        Model cdsModel = sbol.createModel(baseurl, cdsId + "_Model", version, new URI("file:" + cdsId + ".xml"), new URI(sbmlO), new URI(frameworkSBO));
        cdsModel.addWasGeneratedBy(activityURI);
        md.addModel(cdsModel);
        
    }
    
    private static void createCDSModel(JSONObject part, String outputfp) throws FileNotFoundException, XMLStreamException{
        SBMLDocument model = null;
        switch(part.getString("type")){
            case "reporter":
                model = SBMLAdaptor.createCDSModel("out");
                SBMLAdaptor.setReactionParameterValue(model, "out_degradation", "k_loss", part.getDouble("k_loss"));
                break;
            case "connector":
                model = SBMLAdaptor.createCDSModel("conn");
                SBMLAdaptor.setReactionParameterValue(model, "conn_degradation", "k_loss", part.getDouble("k_loss"));
                break;
        }
        SBMLWriter writer = new SBMLWriter();
        writer.writeSBMLToFile(model, outputfp + part.getString("id") + ".xml");
        
    }
    
    //<editor-fold desc="Create Component Definitions">
    
    private static Map<String,URI> createPart(SBOLDocument sbol, JSONObject part) throws SBOLValidationException, URISyntaxException{
        Map<String,URI> idMap = new HashMap<String,URI>();
        switch(part.getString("part")){
            case "promoter":
                idMap.put(part.getString("id"), createPromoter(sbol,part));
                break;
            case "rbs":
                idMap.put(part.getString("id"), createRBS(sbol,part));
                break;
            case "cds":
                idMap.putAll(createCDS(sbol,part));
                break;
            case "smallMolecule":
                idMap.put(part.getString("id"), createSmallMolecule(sbol,part));
                break;
            case "terminator":
                idMap.put(part.getString("id"), createTerminator(sbol,part));
                break;
        }
        return idMap;
    }
    
    private static URI createPromoter(SBOLDocument sbol, JSONObject part) throws SBOLValidationException, URISyntaxException{
        ComponentDefinition prom = sbol.createComponentDefinition(baseurl, part.getString("id"), version, ComponentDefinition.DNA);
        prom.addWasGeneratedBy(activityURI);
        if(part.has("name")){
            prom.setName(part.getString("name"));
        } else {
            prom.setName(part.getString("id"));
        }
        
        if(part.has("sequence")){
            Sequence seq = sbol.createSequence(baseurl, part.getString("id") + "_Sequence", version, part.getString("sequence"), Sequence.IUPAC_DNA);
            seq.addWasGeneratedBy(activityURI);
            prom.addSequence(seq);
        }
        
        switch(part.getString("type")){
            case "constitutive":
                prom.addRole(new URI(constitutivePromSO));
                break;
            case "repressed":
            case "activated-induced":
            case "activated":
            case "repressed-induced":
                prom.addRole(new URI(induciblePromSO));
                break;
        }
        return prom.getIdentity();
    }
    
    private static URI createRBS(SBOLDocument sbol, JSONObject part) throws SBOLValidationException, URISyntaxException{
        ComponentDefinition rbs = sbol.createComponentDefinition(baseurl, part.getString("id"), version, ComponentDefinition.DNA);
        rbs.addWasGeneratedBy(activityURI);
        if(part.has("name")){
            rbs.setName(part.getString("name"));
        } else {
            rbs.setName(part.getString("id"));
        }
        
        if(part.has("sequence")){
            Sequence seq = sbol.createSequence(baseurl, part.getString("id") + "_Sequence", version, part.getString("sequence"), Sequence.IUPAC_DNA);
            seq.addWasGeneratedBy(activityURI);
            rbs.addSequence(seq);
        }
        
        rbs.addRole(new URI(rbsSO));
        return rbs.getIdentity();
    }
    
    private static Map<String,URI> createCDS(SBOLDocument sbol, JSONObject part) throws SBOLValidationException {
        Map<String,URI> uriMaps = new HashMap<>();
        ComponentDefinition cds = sbol.createComponentDefinition(baseurl, part.getString("id"), version, ComponentDefinition.DNA);
        cds.addRole(SequenceOntology.CDS);
        cds.addWasGeneratedBy(activityURI);
        ComponentDefinition prot = sbol.createComponentDefinition(baseurl, part.getString("id") + "_Protein", version, ComponentDefinition.PROTEIN);
        prot.addWasGeneratedBy(activityURI);
        if(part.has("name")){
            cds.setName(part.getString("name"));
            prot.setName(part.getString("name") + " Protein");
        } else {
            cds.setName(part.getString("id"));
            prot.setName(part.getString("id") + " Protein");
        }
        
        if(part.has("sequence")){
            Sequence seq = sbol.createSequence(baseurl, part.getString("id") + "_Sequence", version, part.getString("sequence"), Sequence.IUPAC_DNA);
            seq.addWasGeneratedBy(activityURI);
            cds.addSequence(seq);
        }
        
        uriMaps.put(part.getString("id"), cds.getIdentity());
        uriMaps.put(part.getString("id") + "_Protein", prot.getIdentity());
        return uriMaps;
    }
    
    private static URI createSmallMolecule(SBOLDocument sbol, JSONObject part) throws SBOLValidationException{
        ComponentDefinition sm = sbol.createComponentDefinition(baseurl, part.getString("id"), version, ComponentDefinition.SMALL_MOLECULE);
        sm.addWasGeneratedBy(activityURI);
        if(part.has("name")){
            sm.setName(part.getString("name"));
        } else {
            sm.setName(part.getString("id"));
        }
        if(part.has("max")){
            QName qnmax = new QName(baseurl,"max");
            sm.createAnnotation(qnmax, part.getDouble("max"));
        }
        if(part.has("min")){
            QName qnmin = new QName(baseurl,"min");
            sm.createAnnotation(qnmin, part.getDouble("min"));
        }
        return sm.getIdentity();
    }
    
    private static URI createTerminator(SBOLDocument sbol, JSONObject part) throws SBOLValidationException {
        ComponentDefinition terminator = sbol.createComponentDefinition(baseurl, part.getString("id"), version, ComponentDefinition.DNA);
        terminator.addWasGeneratedBy(activityURI);
        if(part.has("name")){
            terminator.setName(part.getString("name"));
        } else {
            terminator.setName(part.getString("id"));
        }
        
        if(part.has("sequence")){
            Sequence seq = sbol.createSequence(baseurl, part.getString("id") + "_Sequence", version, part.getString("sequence"), Sequence.IUPAC_DNA);
            seq.addWasGeneratedBy(activityURI);
            terminator.addSequence(seq);
        }
        
        terminator.addRole(SequenceOntology.TERMINATOR);
        return terminator.getIdentity();
    }
    
    //</editor-fold>
    
}
