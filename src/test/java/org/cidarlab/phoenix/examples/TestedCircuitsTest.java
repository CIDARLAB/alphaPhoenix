/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.examples;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLStreamException;
import org.cidarlab.phoenix.adaptors.SBMLAdaptor;
import org.cidarlab.phoenix.utils.Utilities;
import org.json.JSONArray;
import org.json.JSONObject;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBMLWriter;
import org.sbolstandard.core2.AccessType;
import org.sbolstandard.core2.ComponentDefinition;
import org.sbolstandard.core2.DirectionType;
import org.sbolstandard.core2.FunctionalComponent;
import org.sbolstandard.core2.Interaction;
import org.sbolstandard.core2.Model;
import org.sbolstandard.core2.ModuleDefinition;
import org.sbolstandard.core2.SBOLConversionException;
import org.sbolstandard.core2.SBOLDocument;
import org.sbolstandard.core2.SBOLValidationException;
import org.sbolstandard.core2.SBOLWriter;

/**
 *
 * @author prash
 */
public class TestedCircuitsTest {

    private static final String rootfp = Utilities.getFilepath() + Utilities.getSeparater() + "lib" + Utilities.getSeparater()  + "examples" + Utilities.getSeparater() + "tested_circuits" + Utilities.getSeparater();
    private static final String libfp = rootfp + "lib" + Utilities.getSeparater();
    private static final String paramsfp = rootfp + "params.json";
    
    public static SBOLDocument createLibrary() throws FileNotFoundException, XMLStreamException, IOException, SBOLConversionException {
        SBOLDocument sbol = new SBOLDocument();

        String so = "http://identifiers.org/so/";
        String sbo = "http://identifiers.org/biomodels.sbo/";
        String baseurl = "http://www.phoenix.org/testedCircuits/contitutiveExpression/";
        String version = "0.2";
        String induciblePromSO = so + "SO:0002051";
        String constitutivePromSO = so + "SO:0002050";
        String rbsSO = so + "SO:0000139";
        String cdsSO = so + "SO:0000316";
        String terSO = so + "SO:0000141";

        String productionSBO = sbo + "SBO:0000589";
        String templateSBO = sbo + "SBO:0000645";
        String productSBO = sbo + "SBO:0000011";

        String inhibitionSO = sbo + "SBO:0000169";
        String inhibitorSO = sbo + "SBO:0000020";
        String inhibitedSO = sbo + "SBO:0000642";

        String stimulationSO = sbo + "SBO:0000170";
        String stimulatorSO = sbo + "SBO:0000459";
        String stimulatedSO = sbo + "SBO:0000643";

        String inducerSO = "http://www.biopax.org/release/biopax-level3.owl#SmallMolecule";

        try {

            URI complexURI = new URI("http://www.biopax.org/release/biopax-level3.owl#Complex");
            URI dnaRegionURI = new URI("http://www.biopax.org/release/biopax-level3.owl#DnaRegion");
            URI proteinURI = new URI("http://www.biopax.org/release/biopax-level3.owl#Protein");
            URI productionURI = new URI(productionSBO);
            URI templateURI = new URI(templateSBO);
            URI productURI = new URI(productSBO);

            URI induciblePromURI = new URI(induciblePromSO);
            URI constitutivePromURI = new URI(constitutivePromSO);
            URI rbsURI = new URI(rbsSO);
            URI cdsURI = new URI(cdsSO);
            URI terURI = new URI(terSO);

            URI inhibitionURI = new URI(inhibitionSO);
            URI inhibitorURI = new URI(inhibitorSO);
            URI inhibitedURI = new URI(inhibitedSO);

            URI stimulationURI = new URI(stimulationSO);
            URI stimulatorURI = new URI(stimulatorSO);
            URI stimulatedURI = new URI(stimulatedSO);

            URI sbmlURI = new URI("http://identifiers.org/edam/format_2585");
            URI frameworkURI = new URI("http://www.ebi.ac.uk/sbo/main/SBO:0000062");

            Map<String, String> modelMap = generateModels();

            ComponentDefinition P018U015 = sbol.createComponentDefinition(baseurl, "P018U015", version, dnaRegionURI);
            P018U015.addRole(constitutivePromURI);
            P018U015.setName("P018U015");

            ComponentDefinition P018U016 = sbol.createComponentDefinition(baseurl, "P018U016", version, dnaRegionURI);
            P018U016.addRole(constitutivePromURI);
            P018U016.setName("P018U016");

            ComponentDefinition P018U017 = sbol.createComponentDefinition(baseurl, "P018U017", version, dnaRegionURI);
            P018U017.addRole(constitutivePromURI);
            P018U017.setName("P018U017");

            ComponentDefinition P018U018 = sbol.createComponentDefinition(baseurl, "P018U018", version, dnaRegionURI);
            P018U018.addRole(constitutivePromURI);
            P018U018.setName("P018U018");

            ComponentDefinition pL2f1433 = sbol.createComponentDefinition(baseurl, "pL2f1433", version, dnaRegionURI);
            pL2f1433.addRole(new URI(induciblePromSO));
            pL2f1433.setName("pL2f1433");

            ComponentDefinition pL2f1434 = sbol.createComponentDefinition(baseurl, "pL2f1434", version, dnaRegionURI);
            pL2f1434.addRole(new URI(induciblePromSO));
            pL2f1434.setName("pL2f1434");

            ComponentDefinition pL2f1435 = sbol.createComponentDefinition(baseurl, "pL2f1435", version, dnaRegionURI);
            pL2f1435.addRole(new URI(induciblePromSO));
            pL2f1435.setName("pL2f1435");

            ComponentDefinition pL2f1436 = sbol.createComponentDefinition(baseurl, "pL2f1436", version, dnaRegionURI);
            pL2f1436.addRole(new URI(induciblePromSO));
            pL2f1436.setName("pL2f1436");

            ComponentDefinition pL2f1437 = sbol.createComponentDefinition(baseurl, "pL2f1437", version, dnaRegionURI);
            pL2f1437.addRole(new URI(induciblePromSO));
            pL2f1437.setName("pL2f1437");

            ComponentDefinition pL2f1438 = sbol.createComponentDefinition(baseurl, "pL2f1438", version, dnaRegionURI);
            pL2f1438.addRole(new URI(induciblePromSO));
            pL2f1438.setName("pL2f1438");

            ComponentDefinition pL2f1439 = sbol.createComponentDefinition(baseurl, "pL2f1439", version, dnaRegionURI);
            pL2f1439.addRole(new URI(induciblePromSO));
            pL2f1439.setName("pL2f1439");

            ComponentDefinition pL2f1440 = sbol.createComponentDefinition(baseurl, "pL2f1440", version, dnaRegionURI);
            pL2f1440.addRole(new URI(induciblePromSO));
            pL2f1440.setName("pL2f1440");

            ComponentDefinition GFP = sbol.createComponentDefinition(baseurl, "GFP", version, dnaRegionURI);
            GFP.addRole(new URI(cdsSO));
            GFP.setName("GFP");

            ComponentDefinition GFPProt = sbol.createComponentDefinition(baseurl, "GFPProt", version, proteinURI);
            GFPProt.setName("GFP Protein");

            ComponentDefinition LuxR = sbol.createComponentDefinition(baseurl, "LuxR", version, dnaRegionURI);
            LuxR.addRole(new URI(cdsSO));
            LuxR.setName("LuxR");

            ComponentDefinition LuxRProt = sbol.createComponentDefinition(baseurl, "LuxRProt", version, proteinURI);
            LuxRProt.setName("LuxR Protein");

            ComponentDefinition ter1 = sbol.createComponentDefinition(baseurl, "ter1", version, dnaRegionURI);
            ter1.addRole(new URI(terSO));
            ter1.setName("Terminator 1");

            ComponentDefinition rbs1 = sbol.createComponentDefinition(baseurl, "rbs1", version, dnaRegionURI);
            rbs1.addRole(new URI(rbsSO));
            rbs1.setName("RBS 1");

            Model P018U015Model = sbol.createModel(baseurl, "P018U015_Model", version, new URI(modelMap.get("P018U015")), sbmlURI, frameworkURI);
            Model P018U016Model = sbol.createModel(baseurl, "P018U016_Model", version, new URI(modelMap.get("P018U016")), sbmlURI, frameworkURI);
            Model P018U017Model = sbol.createModel(baseurl, "P018U017_Model", version, new URI(modelMap.get("P018U017")), sbmlURI, frameworkURI);
            Model P018U018Model = sbol.createModel(baseurl, "P018U018_Model", version, new URI(modelMap.get("P018U018")), sbmlURI, frameworkURI);

            Model pL2f1433Model = sbol.createModel(baseurl, "pL2f1433_Model", version, new URI(modelMap.get("pL2f1433")), sbmlURI, frameworkURI);
            Model pL2f1434Model = sbol.createModel(baseurl, "pL2f1434_Model", version, new URI(modelMap.get("pL2f1434")), sbmlURI, frameworkURI);
            Model pL2f1435Model = sbol.createModel(baseurl, "pL2f1435_Model", version, new URI(modelMap.get("pL2f1435")), sbmlURI, frameworkURI);
            Model pL2f1436Model = sbol.createModel(baseurl, "pL2f1436_Model", version, new URI(modelMap.get("pL2f1436")), sbmlURI, frameworkURI);
            Model pL2f1437Model = sbol.createModel(baseurl, "pL2f1437_Model", version, new URI(modelMap.get("pL2f1437")), sbmlURI, frameworkURI);
            Model pL2f1438Model = sbol.createModel(baseurl, "pL2f1438_Model", version, new URI(modelMap.get("pL2f1438")), sbmlURI, frameworkURI);
            Model pL2f1439Model = sbol.createModel(baseurl, "pL2f1439_Model", version, new URI(modelMap.get("pL2f1439")), sbmlURI, frameworkURI);
            Model pL2f1440Model = sbol.createModel(baseurl, "pL2f1440_Model", version, new URI(modelMap.get("pL2f1440")), sbmlURI, frameworkURI);

            Model GFPModel = sbol.createModel(baseurl, "GFP_Model", version, new URI(modelMap.get("GFP")), sbmlURI, frameworkURI);
            Model LuxRModel = sbol.createModel(baseurl, "LuxR_Model", version, new URI(modelMap.get("LuxR")), sbmlURI, frameworkURI);

            ModuleDefinition P018U015md = sbol.createModuleDefinition(baseurl, "P018U015md", version);
            FunctionalComponent P018U015fc = P018U015md.createFunctionalComponent("P018U015", AccessType.PRIVATE, P018U015.getIdentity(), DirectionType.IN);
            P018U015md.addModel(P018U015Model);

            ModuleDefinition P018U016md = sbol.createModuleDefinition(baseurl, "P018U016md", version);
            FunctionalComponent P018U016fc = P018U016md.createFunctionalComponent("P018U016", AccessType.PRIVATE, P018U016.getIdentity(), DirectionType.IN);
            P018U016md.addModel(P018U016Model);

            ModuleDefinition P018U017md = sbol.createModuleDefinition(baseurl, "P018U017md", version);
            FunctionalComponent P018U017fc = P018U017md.createFunctionalComponent("P018U017", AccessType.PRIVATE, P018U017.getIdentity(), DirectionType.IN);
            P018U017md.addModel(P018U017Model);

            ModuleDefinition P018U018md = sbol.createModuleDefinition(baseurl, "P018U018md", version);
            FunctionalComponent P018U018fc = P018U018md.createFunctionalComponent("P018U018", AccessType.PRIVATE, P018U018.getIdentity(), DirectionType.IN);
            P018U018md.addModel(P018U018Model);

            ModuleDefinition GFPmd = sbol.createModuleDefinition(baseurl, "GFPmd", version);
            FunctionalComponent GFPmdfc1 = GFPmd.createFunctionalComponent("GFP", AccessType.PRIVATE, GFP.getIdentity(), DirectionType.OUT);
            FunctionalComponent GFPmdfc2 = GFPmd.createFunctionalComponent("GFPPRot", AccessType.PRIVATE, GFPProt.getIdentity(), DirectionType.IN);
            Interaction GFPInt = GFPmd.createInteraction("GFP_production", productionURI);
            GFPInt.createParticipation("GFP_int", GFPmdfc1.getIdentity(), templateURI);
            GFPInt.createParticipation("GFP_prot_int", GFPmdfc2.getIdentity(), productURI);
            GFPmd.addModel(GFPModel);

            ModuleDefinition LuxRmd = sbol.createModuleDefinition(baseurl, "LuxRmd", version);
            FunctionalComponent LuxRmdfc1 = LuxRmd.createFunctionalComponent("LuxR", AccessType.PRIVATE, LuxR.getIdentity(), DirectionType.OUT);
            FunctionalComponent LuxRmdfc2 = LuxRmd.createFunctionalComponent("LuxRPRot", AccessType.PRIVATE, LuxRProt.getIdentity(), DirectionType.IN);
            Interaction LuxRInt = LuxRmd.createInteraction("LuxR_production", productionURI);
            LuxRInt.createParticipation("LuxR_int", LuxRmdfc1.getIdentity(), templateURI);
            LuxRInt.createParticipation("LuxR_prot_int", LuxRmdfc2.getIdentity(), productURI);
            LuxRmd.addModel(LuxRModel);

            ModuleDefinition pL2f1433md = sbol.createModuleDefinition(baseurl, "pL2f1433md", version);
            FunctionalComponent pL2f1433mdfc1 = pL2f1433md.createFunctionalComponent("LuxRProt_act", AccessType.PRIVATE, LuxRProt.getIdentity(), DirectionType.OUT);
            FunctionalComponent pL2f1433mdfc2 = pL2f1433md.createFunctionalComponent("pL2f1433", AccessType.PRIVATE, pL2f1433.getIdentity(), DirectionType.IN);
            Interaction pL2f1433Int = pL2f1433md.createInteraction("pL2f1433_activation", stimulationURI);
            pL2f1433Int.createParticipation("LuxRProt_act_int", pL2f1433mdfc1.getIdentity(), stimulatorURI);
            pL2f1433Int.createParticipation("pL2f1433_int", pL2f1433mdfc2.getIdentity(), stimulatedURI);
            pL2f1433md.addModel(pL2f1433Model);

            ModuleDefinition pL2f1434md = sbol.createModuleDefinition(baseurl, "pL2f1434md", version);
            FunctionalComponent pL2f1434mdfc1 = pL2f1434md.createFunctionalComponent("LuxRProt_act", AccessType.PRIVATE, LuxRProt.getIdentity(), DirectionType.OUT);
            FunctionalComponent pL2f1434mdfc2 = pL2f1434md.createFunctionalComponent("pL2f1434", AccessType.PRIVATE, pL2f1434.getIdentity(), DirectionType.IN);
            Interaction pL2f1434Int = pL2f1434md.createInteraction("pL2f1434_activation", stimulationURI);
            pL2f1434Int.createParticipation("LuxRProt_act_int", pL2f1434mdfc1.getIdentity(), stimulatorURI);
            pL2f1434Int.createParticipation("pL2f1434_int", pL2f1434mdfc2.getIdentity(), stimulatedURI);
            pL2f1434md.addModel(pL2f1434Model);

            ModuleDefinition pL2f1435md = sbol.createModuleDefinition(baseurl, "pL2f1435md", version);
            FunctionalComponent pL2f1435mdfc1 = pL2f1435md.createFunctionalComponent("LuxRProt_act", AccessType.PRIVATE, LuxRProt.getIdentity(), DirectionType.OUT);
            FunctionalComponent pL2f1435mdfc2 = pL2f1435md.createFunctionalComponent("pL2f1435", AccessType.PRIVATE, pL2f1435.getIdentity(), DirectionType.IN);
            Interaction pL2f1435Int = pL2f1435md.createInteraction("pL2f1435_activation", stimulationURI);
            pL2f1435Int.createParticipation("LuxRProt_act_int", pL2f1435mdfc1.getIdentity(), stimulatorURI);
            pL2f1435Int.createParticipation("pL2f1435_int", pL2f1435mdfc2.getIdentity(), stimulatedURI);
            pL2f1435md.addModel(pL2f1435Model);

            ModuleDefinition pL2f1436md = sbol.createModuleDefinition(baseurl, "pL2f1436md", version);
            FunctionalComponent pL2f1436mdfc1 = pL2f1436md.createFunctionalComponent("LuxRProt_act", AccessType.PRIVATE, LuxRProt.getIdentity(), DirectionType.OUT);
            FunctionalComponent pL2f1436mdfc2 = pL2f1436md.createFunctionalComponent("pL2f1436", AccessType.PRIVATE, pL2f1436.getIdentity(), DirectionType.IN);
            Interaction pL2f1436Int = pL2f1436md.createInteraction("pL2f1436_activation", stimulationURI);
            pL2f1436Int.createParticipation("LuxRProt_act_int", pL2f1436mdfc1.getIdentity(), stimulatorURI);
            pL2f1436Int.createParticipation("pL2f1436_int", pL2f1436mdfc2.getIdentity(), stimulatedURI);
            pL2f1436md.addModel(pL2f1436Model);

            ModuleDefinition pL2f1437md = sbol.createModuleDefinition(baseurl, "pL2f1437md", version);
            FunctionalComponent pL2f1437mdfc1 = pL2f1437md.createFunctionalComponent("LuxRProt_act", AccessType.PRIVATE, LuxRProt.getIdentity(), DirectionType.OUT);
            FunctionalComponent pL2f1437mdfc2 = pL2f1437md.createFunctionalComponent("pL2f1437", AccessType.PRIVATE, pL2f1437.getIdentity(), DirectionType.IN);
            Interaction pL2f1437Int = pL2f1437md.createInteraction("pL2f1437_activation", stimulationURI);
            pL2f1437Int.createParticipation("LuxRProt_act_int", pL2f1437mdfc1.getIdentity(), stimulatorURI);
            pL2f1437Int.createParticipation("pL2f1437_int", pL2f1437mdfc2.getIdentity(), stimulatedURI);
            pL2f1437md.addModel(pL2f1437Model);

            ModuleDefinition pL2f1438md = sbol.createModuleDefinition(baseurl, "pL2f1438md", version);
            FunctionalComponent pL2f1438mdfc1 = pL2f1438md.createFunctionalComponent("LuxRProt_act", AccessType.PRIVATE, LuxRProt.getIdentity(), DirectionType.OUT);
            FunctionalComponent pL2f1438mdfc2 = pL2f1438md.createFunctionalComponent("pL2f1438", AccessType.PRIVATE, pL2f1438.getIdentity(), DirectionType.IN);
            Interaction pL2f1438Int = pL2f1438md.createInteraction("pL2f1438_activation", stimulationURI);
            pL2f1438Int.createParticipation("LuxRProt_act_int", pL2f1438mdfc1.getIdentity(), stimulatorURI);
            pL2f1438Int.createParticipation("pL2f1438_int", pL2f1438mdfc2.getIdentity(), stimulatedURI);
            pL2f1438md.addModel(pL2f1438Model);

            ModuleDefinition pL2f1439md = sbol.createModuleDefinition(baseurl, "pL2f1439md", version);
            FunctionalComponent pL2f1439mdfc1 = pL2f1439md.createFunctionalComponent("LuxRProt_act", AccessType.PRIVATE, LuxRProt.getIdentity(), DirectionType.OUT);
            FunctionalComponent pL2f1439mdfc2 = pL2f1439md.createFunctionalComponent("pL2f1439", AccessType.PRIVATE, pL2f1439.getIdentity(), DirectionType.IN);
            Interaction pL2f1439Int = pL2f1439md.createInteraction("pL2f1439_activation", stimulationURI);
            pL2f1439Int.createParticipation("LuxRProt_act_int", pL2f1439mdfc1.getIdentity(), stimulatorURI);
            pL2f1439Int.createParticipation("pL2f1439_int", pL2f1439mdfc2.getIdentity(), stimulatedURI);
            pL2f1439md.addModel(pL2f1439Model);

            ModuleDefinition pL2f1440md = sbol.createModuleDefinition(baseurl, "pL2f1440md", version);
            FunctionalComponent pL2f1440mdfc1 = pL2f1440md.createFunctionalComponent("LuxRProt_act", AccessType.PRIVATE, LuxRProt.getIdentity(), DirectionType.OUT);
            FunctionalComponent pL2f1440mdfc2 = pL2f1440md.createFunctionalComponent("pL2f1440", AccessType.PRIVATE, pL2f1440.getIdentity(), DirectionType.IN);
            Interaction pL2f1440Int = pL2f1440md.createInteraction("pL2f1440_activation", stimulationURI);
            pL2f1440Int.createParticipation("LuxRProt_act_int", pL2f1440mdfc1.getIdentity(), stimulatorURI);
            pL2f1440Int.createParticipation("pL2f1440_int", pL2f1440mdfc2.getIdentity(), stimulatedURI);
            pL2f1440md.addModel(pL2f1440Model);
            
            ComponentDefinition testprom = sbol.createComponentDefinition(baseurl,"testProm", version, dnaRegionURI);
            testprom.addRole(new URI(constitutivePromSO));
            testprom.setName("Test Promoter");
            
            ComponentDefinition testrbs = sbol.createComponentDefinition(baseurl,"testRBS", version, dnaRegionURI);
            testrbs.addRole(new URI(rbsSO));
            testrbs.setName("Test RBS");
            
            ComponentDefinition testcds = sbol.createComponentDefinition(baseurl,"testCDS", version, dnaRegionURI);
            testcds.addRole(new URI(cdsSO));
            testcds.setName("Test CDS");
            
            ComponentDefinition testter = sbol.createComponentDefinition(baseurl,"testTer", version, dnaRegionURI);
            testter.addRole(new URI(terSO));
            testter.setName("Test Ter");
            
        } catch (URISyntaxException ex) {
            Logger.getLogger(TestedCircuitsTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SBOLValidationException ex) {
            Logger.getLogger(TestedCircuitsTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        SBOLWriter.write(sbol, libfp + "sbol.xml");
        return sbol;
    }

    public static Map<String, String> generateModels() throws FileNotFoundException, XMLStreamException {
        Map<String, String> modelMap = new HashMap<String, String>();
        JSONArray params = new JSONArray(Utilities.getFileContentAsString(paramsfp));
        for (Object obj : params) {
            JSONObject param = (JSONObject) obj;
            SBMLDocument model = null;
            switch (param.getString("part")) {
                case "cds":
                    switch (param.getString("type")) {
                        case "reporter":
                            model = SBMLAdaptor.createCDSModel("out");
                            SBMLAdaptor.setReactionParameterValue(model, "out_degradation", "k_loss", param.getDouble("k_loss"));
                            break;
                        case "connector":
                            model = SBMLAdaptor.createCDSModel("conn");
                            SBMLAdaptor.setReactionParameterValue(model, "conn_degradation", "k_loss", param.getDouble("k_loss"));
                            break;
                    }
                    break;
                case "promoter":
                    switch (param.getString("type")) {
                        case "constitutive":
                            model = SBMLAdaptor.createConstituativePromoterModel("out");
                            SBMLAdaptor.setReactionParameterValue(model, "out_expression", "k_express", param.getDouble("k_express"));
                            break;
                        case "activated":
                            model = SBMLAdaptor.createActivatedPromoterModel("conn", "out");
                            SBMLAdaptor.setReactionParameterValue(model, "out_expression", "K_d", param.getDouble("K_d"));
                            SBMLAdaptor.setReactionParameterValue(model, "out_expression", "basal_rate", param.getDouble("basal_rate"));
                            SBMLAdaptor.setReactionParameterValue(model, "out_expression", "max_rate", param.getDouble("max_rate"));
                            SBMLAdaptor.setReactionParameterValue(model, "out_expression", "n", param.getDouble("n"));
                            break;
                    }
                    break;
            }
            SBMLWriter writer = new SBMLWriter();
            writer.writeSBMLToFile(model, libfp + (param.getString("id")) + ".xml");
            modelMap.put((param.getString("id")), (param.getString("id")) + ".xml");
        }
        return modelMap;
    }
    

    public static void main(String[] args) {
        try {
            createLibrary();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TestedCircuitsTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XMLStreamException | SBOLConversionException | IOException ex) {
            Logger.getLogger(TestedCircuitsTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

}
