/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.adaptors;

import static edu.utah.ece.async.ibiosim.dataModels.biomodel.util.SBMLutilities.updateVarId;
import edu.utah.ece.async.ibiosim.dataModels.util.exceptions.BioSimException;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLStreamException;

import org.sbml.jsbml.ASTNode;
import org.sbml.jsbml.Compartment;
import org.sbml.jsbml.Event;
import org.sbml.jsbml.KineticLaw;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.LocalParameter;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.ModifierSpeciesReference;
import org.sbml.jsbml.NamedSBase;
import org.sbml.jsbml.Parameter;
import org.sbml.jsbml.Reaction;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBMLException;
import org.sbml.jsbml.SBMLReader;
import org.sbml.jsbml.SBMLWriter;
import org.sbml.jsbml.SimpleSpeciesReference;
import org.sbml.jsbml.Species;
import org.sbml.jsbml.SpeciesReference;
import org.sbml.jsbml.Trigger;
import org.sbml.jsbml.text.parser.FormulaParserLL3;
import org.sbml.jsbml.text.parser.IFormulaParser;
import org.sbml.jsbml.util.compilers.FormulaCompiler;
import org.sbml.jsbml.util.compilers.FormulaCompilerLibSBML;
import org.sbml.jsbml.text.parser.ParseException;

/**
 *
 * @author nicholasroehner
 * @author evanappleton
 * @author prash
 */
public class SBMLAdaptor {
            
        public static SBMLDocument convertParamsLocalToGlobal(SBMLDocument doc){
            Model model = doc.getModel();
            ListOf<Reaction> reactions = model.getListOfReactions();
            for (Reaction reaction : reactions) {
                KineticLaw kineticLaw = reaction.getKineticLaw();
                ListOf<LocalParameter> localParameters = kineticLaw.getListOfLocalParameters();

                for (LocalParameter localParameter : localParameters) {
                    String newId = reaction.getId() + "_" + localParameter.getId();
                    Parameter newParam = new Parameter(localParameter);
                    newParam.setId(newId);
                    model.addParameter(newParam);
                    replaceNameASTNode(kineticLaw.getMath(), localParameter.getId(), newParam.getId());
                }

                int size = kineticLaw.getLocalParameterCount();

                for (int i = size - 1; i >= 0; i--) {
                    kineticLaw.removeLocalParameter(i);
                }

            }

            return doc;
        }
        
    
    
        public static SBMLDocument convertParamsLocalToGlobal(String filepath){
            SBMLDocument doc = null; 
            try {
                doc = SBMLReader.read(new File(filepath));
            } catch (XMLStreamException | IOException ex) {
                Logger.getLogger(SBMLAdaptor.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (doc != null){
                return convertParamsLocalToGlobal(doc);
            }
            else {
                return null;
            }
        }
        
        private static void replaceNameASTNode(ASTNode node,String oldName, String newName){
            
            if(node.isVariable()){
                if(node.getName().equals(oldName)){
                    node.setName(newName);
                }
            }
            for(ASTNode child:node.getChildren()){
                replaceNameASTNode(child,oldName,newName);
            }
        }
        
	public static SBMLDocument composeModels(Model mod1, Model mod2) {
		return SBMLAdaptor.composeModels(mod1, mod2, new HashMap<String, String>());
	}
	
	public static SBMLDocument composeModels(Model mod1, Model mod2, HashMap<String, String> substitutions) {
		List<Model> mods = new LinkedList<Model>();
		mods.add(mod1);
		mods.add(mod2);
		return composeModels(mods, substitutions);
	}
	
	public static SBMLDocument composeModels(List<Model> mods) {
		return composeModels(mods, new HashMap<String, String>());
	}
	
	public static SBMLDocument composeModels(List<Model> mods, HashMap<String, String> substitutions) {
		SBMLDocument composedDoc = createCompartmentModel("cell", "cell");
		Model composedMod = composedDoc.getModel();
		for (Model mod : mods) {
			for (Species spec : mod.getListOfSpecies()) {
				Species composedSpec = spec.clone();
				if (versionSBase(composedSpec, composedMod)) {
					substitutions.put(composedSpec.getId(), spec.getId());
				}
				composedSpec.setCompartment(composedMod.getCompartment(0));
				composedMod.addSpecies(composedSpec);
			}
			for (Reaction rxn : mod.getListOfReactions()) {
				Reaction composedRxn = rxn.clone();
//				if (composedRxn.getSBOTermID().equals(SBOTerm.EXPRESSION.getID())) {
				versionSBase(composedRxn, composedMod);
				composedMod.addReaction(composedRxn);
//				} else if (composedRxn.getSBOTermID().equals(SBOTerm.DEGRADATION.getID()) && !composedMod.containsReaction(composedRxn.getId())) {
//					composedMod.addReaction(composedRxn);
//				}
			}
		}
		if (substitutions.isEmpty()) {
			for (int i = 1; i < mods.size(); i++) {
				substitutions.putAll(inferIOSubstitutions(mods.get(i - 1), mods.get(i)));
			}
		}
		if (!substitutions.isEmpty()) {
			substituteSpecies(substitutions, composedMod);
		}
		return composedDoc;
	}
        
        public static void addEvent(SBMLDocument doc, String id, double time, double value) {
            int counter = 0;
            while (doc.getModel().containsUniqueNamedSBase("event" + counter)) {
                counter ++;
            }
            Event event = doc.getModel().createEvent("event" + counter);
            event.createDelay(new ASTNode(time));
            Trigger t = event.createTrigger(new ASTNode(ASTNode.Type.CONSTANT_TRUE));
            t.setInitialValue(false);
            t.setPersistent(true);
            event.createEventAssignment(id, new ASTNode(value));
        }
	
	private static boolean versionSBase(NamedSBase sb, Model mod) {
		String versionedID = sb.getId();
		int version = 0;
		while (mod.containsUniqueNamedSBase(versionedID)) {
			version++;
			versionedID = sb.getId() + version;
		}
		if (version > 0) {
			sb.setId(versionedID);
			 return true;
		} else {
			return false;
		}
	}
        
        public static void renameSpecies(SBMLDocument doc, String oldID, String newID) {
            try {
                if (doc.getModel().getSpecies(newID) != null) {
                    doc.getModel().removeSpecies(newID);
                }
                updateVarId(doc, true, oldID, newID);
                Species spec = doc.getModel().getSpecies(oldID);
                if (spec != null) {
                    spec.setId(newID);
                    spec.setName(newID);
                }
            } catch (BioSimException ex) {
                Logger.getLogger(SBMLAdaptor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        public static void setValue(SBMLDocument doc, String id, double value) {
            if (doc.getModel().containsSpecies(id)) {
                doc.getModel().getSpecies(id).setInitialAmount(value);
            }
            else if (doc.getModel().containsCompartment(id)) {
                doc.getModel().getCompartment(id).setValue(value);
            }
            else if (doc.getModel().containsParameter(id)) {
                doc.getModel().getParameter(id).setValue(value);
            }
        }
        
        public static void setReactionParameterValue(SBMLDocument doc, String reactionID, String parameterID, double value) {
            if (doc.getModel().containsReaction(reactionID)) {
                LocalParameter p = doc.getModel().getReaction(reactionID).getKineticLaw().getLocalParameter(parameterID);
                if (p != null) {
                    p.setValue(value);
                }
            }
        }
	
	public static void substituteSpecies(HashMap<String, String> substitutions, Model mod) {
		for (String substitutedID : substitutions.keySet()) {
			Species substitutedSpec = mod.getSpecies(substitutedID);
			Species substituteSpec = mod.getSpecies(substitutions.get(substitutedID));
			if (substituteSpec.isSetSBOTerm()) {
				if (substituteSpec.getSBOTermID().equals(SBOTerm.INPUT.getID())) {
					if (substitutedSpec.isSetSBOTerm()) {
						if (substitutedSpec.getSBOTermID().equals(SBOTerm.INPUT.getID())) {
							mod.removeSpecies(substitutedSpec);
						} else if (substitutedSpec.getSBOTermID().equals(SBOTerm.OUTPUT.getID())) {
							mod.removeSpecies(substituteSpec);
							substitutedSpec.setId(substituteSpec.getId());
							substitutedSpec.unsetSBOTerm();
						} else {
							mod.removeSpecies(substituteSpec);
							substitutedSpec.setId(substituteSpec.getId());
						}
					} else {
						mod.removeSpecies(substituteSpec);
						substitutedSpec.setId(substituteSpec.getId());
					}
				} else if (substituteSpec.getSBOTermID().equals(SBOTerm.OUTPUT.getID())) {
					mod.removeSpecies(substitutedSpec);
					if (substitutedSpec.isSetSBOTerm()) {
						if (substitutedSpec.getSBOTermID().equals(SBOTerm.INPUT.getID())) {
							substituteSpec.unsetSBOTerm();
						} else if (!substitutedSpec.getSBOTermID().equals(SBOTerm.OUTPUT.getID())) {
							substituteSpec.setSBOTerm(substitutedSpec.getSBOTerm());
						}
					}
				} else if (substitutedSpec.isSetSBOTerm() && substitutedSpec.getSBOTermID().equals(SBOTerm.OUTPUT.getID())) {
					mod.removeSpecies(substituteSpec);
					substitutedSpec.setId(substituteSpec.getId());
					substitutedSpec.setSBOTerm(substituteSpec.getSBOTerm());
				} else {
					mod.removeSpecies(substitutedSpec);
				}
			} else if (substitutedSpec.isSetSBOTerm()) {
				if (substitutedSpec.getSBOTermID().equals(SBOTerm.INPUT.getID())) {
					mod.removeSpecies(substitutedSpec);
				} else if (substitutedSpec.getSBOTermID().equals(SBOTerm.OUTPUT.getID())) {
					mod.removeSpecies(substituteSpec);
					substitutedSpec.setId(substituteSpec.getId());
					substitutedSpec.unsetSBOTerm();
				} else {
					mod.removeSpecies(substitutedSpec);
					substituteSpec.setSBOTerm(substitutedSpec.getSBOTerm());
				}
			} else {
				mod.removeSpecies(substitutedSpec);
			}
		}
		for (Reaction rxn : mod.getListOfReactions()) {
			substituteSpeciesReferences(substitutions, rxn);
		}
	}
	
	private static void substituteSpeciesReferences(HashMap<String, String> substitutions, Reaction rxn) {
		String formula = compileFormula(rxn.getKineticLaw().getMath());
		Set<SimpleSpeciesReference> specRefs = new HashSet<SimpleSpeciesReference>();
		specRefs.addAll(rxn.getListOfModifiers());
		specRefs.addAll(rxn.getListOfReactants());
		specRefs.addAll(rxn.getListOfProducts());
		for (SimpleSpeciesReference specRef : specRefs) {
			if (substitutions.containsKey(specRef.getSpecies())) {
				formula.replaceAll(specRef.getSpecies(), substitutions.get(specRef.getSpecies()));
				specRef.setSpecies(substitutions.get(specRef.getSpecies()));
			}
		}
		rxn.getKineticLaw().setMath(parseFormula(formula));
	}
	
	private static HashMap<String, String> inferIOSubstitutions(Model mod1, Model mod2) {
		Set<Species> outputs = getOutputs(mod1);
		Set<Species> inputs = getInputs(mod2);
		HashMap<String, String> substitutions = new HashMap<String, String>();
		if (!inputs.isEmpty() && !outputs.isEmpty()) {
			substitutions.put(inputs.iterator().next().getId(), outputs.iterator().next().getId());
		}
		return substitutions;
	}
        
        
        
    public static String convertModelToLaTeXEquations(SBMLDocument doc){
        String result = "";
        Map<String, ASTNode> map = new HashMap<String, ASTNode>();
        Model m = doc.getModel();
        for (Reaction r : m.getListOfReactions()) {
            for (SpeciesReference prod : r.getListOfProducts()) {
                ASTNode node = r.getKineticLaw().getMath();
                if (prod.getStoichiometry() != 1) {
                    ASTNode newNode = new ASTNode(ASTNode.Type.TIMES);
                    newNode.addChild(new ASTNode(prod.getStoichiometry()));
                    newNode.addChild(node);
                    node = newNode;
                }
                if (map.containsKey(prod.getSpecies())) {
                    ASTNode newNode = new ASTNode(ASTNode.Type.PLUS);
                    newNode.addChild(map.get(prod.getSpecies()));
                    newNode.addChild(node);
                    node = newNode;
                }
                map.put(prod.getSpecies(), node);
            }
            for (SpeciesReference reac : r.getListOfReactants()) {
                ASTNode node = r.getKineticLaw().getMath();
                if (reac.getStoichiometry() != 1) {
                    ASTNode newNode = new ASTNode(ASTNode.Type.TIMES);
                    newNode.addChild(new ASTNode(reac.getStoichiometry()));
                    newNode.addChild(node);
                    node = newNode;
                }
                if (map.containsKey(reac.getSpecies())) {
                    ASTNode newNode = new ASTNode(ASTNode.Type.MINUS);
                    newNode.addChild(map.get(reac.getSpecies()));
                    newNode.addChild(node);
                    node = newNode;
                }
                map.put(reac.getSpecies(), node);
            }
        }
        for (String species : map.keySet()) {
            result += "\\frac{d" + species + "}{dt} = " + myFormulaToLaTeXString(map.get(species)) + "\n";
        }
        return result;
    }
	
	/*
     * Methods for creating SBML models of gene expression
     */
	
    public static SBMLDocument createCompartmentModel(String compID) {
        return createCompartmentModel(compID, compID);
    }

    public static SBMLDocument createCompartmentModel(String compID, String compName) {
        SBMLDocument compartmentDoc = new SBMLDocument(3, 1);
        Model compartmentMod = compartmentDoc.createModel();
        Compartment cell = compartmentMod.createCompartment(compID);
        cell.setName(compName);
        cell.setConstant(false);
        cell.setSize(1);
        return compartmentDoc;
    }
    
    public static SBMLDocument createCDSModel(String proteinID) {
        return createCDSModel(proteinID, proteinID);
    }
    
    public static SBMLDocument createCDSModel(String proteinID, String proteinName) {
    	SBMLDocument cdsDoc = createCompartmentModel("cell", "cell");
    	Species degraded = createSpecies(proteinID, proteinName, cdsDoc.getModel());
    	degraded.setSBOTerm(SBOTerm.OUTPUT.getID());
    	createDegradationReaction(degraded, cdsDoc.getModel());
    	return cdsDoc;
    }
    
    public static SBMLDocument createConstituativePromoterModel(String expressedID) {
    	return createConstituativePromoterModel(expressedID, expressedID);
    }
    
    public static SBMLDocument createConstituativePromoterModel(String expressedID, String expressedName) {
    	SBMLDocument expressionDoc = createCompartmentModel("cell", "cell");
    	Species expressed = createSpecies(expressedID, expressedName, expressionDoc.getModel());
    	expressed.setSBOTerm(SBOTerm.OUTPUT.getID());
    	createExpressionReaction(expressionDoc.getModel().getSpecies(expressedID), expressionDoc.getModel());
    	return expressionDoc;
    }
    
    public static SBMLDocument createRepressedPromoterModel(String repressorID, String expressedID) {
    	return createRepressedPromoterModel(repressorID, expressedID, repressorID, expressedID);
    }
    
    public static SBMLDocument createRepressedPromoterModel(String repressorID, String expressedID, String repressorName, String expressedName) {
    	SBMLDocument repressionDoc = createCompartmentModel("cell", "cell");
    	Species expressed = createSpecies(expressedID, expressedName, repressionDoc.getModel());
    	expressed.setSBOTerm(SBOTerm.OUTPUT.getID());
    	Species repressor = createSpecies(repressorID, repressorName, repressionDoc.getModel());
    	repressor.setSBOTerm(SBOTerm.INPUT.getID());
    	createRepressibleExpressionReaction(repressor, repressionDoc.getModel().getSpecies(expressedID), repressionDoc.getModel());
    	return repressionDoc;
    }
    
    public static SBMLDocument createActivatedPromoterModel(String activatorID, String expressedID) {
    	return createActivatedPromoterModel(activatorID, expressedID, activatorID, expressedID);
    }
    
    public static SBMLDocument createActivatedPromoterModel(String activatorID, String expressedID, String activatorName, String expressedName) {
    	SBMLDocument activationDoc = createCompartmentModel("cell", "cell");
    	Species expressed = createSpecies(expressedID, expressedName, activationDoc.getModel());
    	expressed.setSBOTerm(SBOTerm.OUTPUT.getID());
    	Species activator = createSpecies(activatorID, activatorName, activationDoc.getModel());
    	activator.setSBOTerm(SBOTerm.INPUT.getID());
    	createActivatableExpressionReaction(activator, activationDoc.getModel().getSpecies(expressedID), activationDoc.getModel());
    	return activationDoc;
    }
    
    public static SBMLDocument createInducibleActivatedPromoterModel(String activatorID, String inducerID, String expressedID) {
    	return createInducibleActivatedPromoterModel(activatorID, inducerID, expressedID, activatorID, inducerID, expressedID);
    }
    
    public static SBMLDocument createInducibleActivatedPromoterModel(String activatorID, String inducerID, String expressedID, String activatorName, String inducerName, String expressedName) {
    	SBMLDocument inducedActivationDoc = createCompartmentModel("cell", "cell");
    	Species expressed = createSpecies(expressedID, expressedName, inducedActivationDoc.getModel());
    	expressed.setSBOTerm(SBOTerm.OUTPUT.getID());
    	Species activator = createSpecies(activatorID, activatorName, inducedActivationDoc.getModel());
    	activator.setSBOTerm(SBOTerm.INPUT.getID());
        Species inducer = createSpecies(inducerID, inducerName, inducedActivationDoc.getModel());
    	inducer.setSBOTerm(SBOTerm.INPUT.getID());
    	createInducibleActivatableExpressionReaction(activator, inducer, inducedActivationDoc.getModel().getSpecies(expressedID), inducedActivationDoc.getModel());
    	return inducedActivationDoc;
    }
    
    public static SBMLDocument createRepressedModuleModel(String repressorID, String expressedID) {
    	return createRepressedModuleModel(repressorID, expressedID, repressorID, expressedID);
    }
    
    public static SBMLDocument createRepressedModuleModel(String repressorID, String expressedID, String repressorName, String expressedName) {
    	return composeModels(createRepressedPromoterModel(repressorID, expressedID, repressorName, expressedName).getModel(), createCDSModel(expressedID, expressedName).getModel());
    }
    
    public static SBMLDocument createActivatedModuleModel(String activatorID, String expressedID) {
    	return createActivatedModuleModel(activatorID, expressedID, activatorID, expressedID);
    }
    
    public static SBMLDocument createActivatedModuleModel(String activatorID, String expressedID, String activatorName, String expressedName) {
    	return composeModels(createActivatedPromoterModel(activatorID, expressedID, activatorName, expressedName).getModel(), createCDSModel(expressedID, expressedName).getModel());
    }
    
    public static SBMLDocument createInducibleActivatedModuleModel(String activatorID, String inducerID, String expressedID) {
    	return createInducibleActivatedModuleModel(activatorID, inducerID, expressedID, activatorID, inducerID, expressedID);
    }
    
    public static SBMLDocument createInducibleActivatedModuleModel(String activatorID, String inducerID, String expressedID, String activatorName, String inducerName, String expressedName) {
    	return composeModels(createInducibleActivatedPromoterModel(activatorID, inducerID, expressedID, activatorName, inducerName, expressedName).getModel(), createCDSModel(expressedID, expressedName).getModel());
    }
    
    public static Species createSpecies(String specID, Model mod) {
    	return createSpecies(specID, specID, mod);
    }
    
    public static Species createSpecies(String specID, String specName, Model mod) {
    	return createSpecies(specID, specName, mod, 0.0);
    }
    
    public static Species createSpecies(String specID, String specName, Model mod, double initialAmount) {
    	Species spec;
    	if (mod.getNumCompartments() > 0) {
    		spec = mod.createSpecies(specID, mod.getCompartment(0));
    	} else {
    		spec = mod.createSpecies(specID);
    	}
    	spec.setName(specName);
        spec.setInitialAmount(initialAmount);
    	spec.setHasOnlySubstanceUnits(true);
    	spec.setBoundaryCondition(false);
    	spec.setConstant(false);
    	return spec;
    }
    
    public static Parameter createParameter(String paramID, Model mod) {
    	return createParameter(paramID, paramID, mod);
    }
    
    public static Parameter createParameter(String paramID, String paramName, Model mod) {
    	return createParameter(paramID, paramName, mod, 1.0);
    }
    
    public static Parameter createParameter(String paramID, String paramName, Model mod, double value) {
    	Parameter p = mod.createParameter(paramID);
    	p.setName(paramName);
        p.setValue(value);
        p.setConstant(false);
    	return p;
    }
    
    private static Reaction createDegradationReaction(Species degraded, Model mod) {
    	Reaction degradation = mod.createReaction(degraded.getId() + "_degradation");
    	degradation.setName(degraded.getName() + "_degradation");
    	degradation.setFast(false);
    	degradation.setReversible(false);
    	degradation.setSBOTerm(SBOTerm.DEGRADATION.getID());
    	SpeciesReference reactant = degradation.createReactant(degraded);
    	reactant.setStoichiometry(1.0);
    	reactant.setConstant(true);
    	KineticLaw degradationLaw = degradation.createKineticLaw();
        LocalParameter degradationRate = degradationLaw.createLocalParameter("k_loss");
    	degradationRate.setName("k_loss");
    	degradationRate.setValue(1.0);
    	degradationLaw.setMath(parseFormula(degradationRate.getId() + "*" + degraded.getId()));
    	return degradation;
    }
    
    private static Reaction createExpressionReaction(Species expressed, Model mod) {
    	Reaction expression = createRatelessExpressionReaction(expressed, mod);
    	KineticLaw expressionLaw = expression.createKineticLaw();
		LocalParameter expressionRate = expressionLaw.createLocalParameter("k_express");
		expressionRate.setName("k_express");
		expressionRate.setValue(1.0);
		expressionLaw.setMath(parseFormula(expressionRate.getId()));
    	return expression;
    }
    
    private static Reaction createRatelessExpressionReaction(Species expressed, Model mod) {
    	Reaction expression = mod.createReaction(expressed.getId() + "_expression");
    	expression.setName(expressed.getName() + "_expression");
    	expression.setFast(false);
    	expression.setReversible(false);
    	expression.setSBOTerm(SBOTerm.EXPRESSION.getID());
    	SpeciesReference product = expression.createProduct(expressed);
    	product.setStoichiometry(1.0);
    	product.setConstant(true);
    	return expression;
    }
    
    private static Reaction createRepressibleExpressionReaction(Species repressor, Species expressed, Model mod) {
    	Reaction repressibleExpression = createRatelessRepressibleExpressionReaction(repressor, expressed, mod);
        KineticLaw repressibleExpressionLaw = repressibleExpression.createKineticLaw();
        LocalParameter maxExpressionRate = repressibleExpressionLaw.createLocalParameter("max");
        maxExpressionRate.setName("max");
        maxExpressionRate.setValue(1.0);
        LocalParameter basalExpressionRate = repressibleExpressionLaw.createLocalParameter("basal");
        basalExpressionRate.setName("basal");
        basalExpressionRate.setValue(1.0);
        LocalParameter kd = repressibleExpressionLaw.createLocalParameter("K_d");
        kd.setName("K_d");
        kd.setValue(2.0);
        LocalParameter hillCoef = repressibleExpressionLaw.createLocalParameter("n");
        hillCoef.setName("n");
        hillCoef.setValue(1.0);
        repressibleExpressionLaw.setMath(parseFormula(basalExpressionRate.getId()
                + "+(" + maxExpressionRate.getId() + "/(1+(" + repressor.getId() + "/" + kd.getId()
                + ")^" + hillCoef.getId() + "))"));                
    	return repressibleExpression;
    }
    
    private static Reaction createRatelessRepressibleExpressionReaction(Species repressor, Species expressed, Model mod) {
    	Reaction repressibleExpression = createRatelessExpressionReaction(expressed, mod);
    	ModifierSpeciesReference modifier = repressibleExpression.createModifier(repressor);
    	modifier.setSBOTerm(SBOTerm.REPRESSOR.getID());
    	return repressibleExpression;
    }
    
    private static Reaction createActivatableExpressionReaction(Species activator, Species expressed, Model mod) {
        Reaction activatableExpression = createRatelessActivatableExpressionReaction(activator, expressed, mod);
        KineticLaw activatableExpressionLaw = activatableExpression.createKineticLaw();
        LocalParameter maxExpressionRate = activatableExpressionLaw.createLocalParameter("max");
        maxExpressionRate.setName("max");
        maxExpressionRate.setValue(1.0);
        LocalParameter basalExpressionRate = activatableExpressionLaw.createLocalParameter("basal");
        basalExpressionRate.setName("basal");
        basalExpressionRate.setValue(1.0);
        LocalParameter kd = activatableExpressionLaw.createLocalParameter("K_d");
        kd.setName("K_d");
        kd.setValue(2.0);
        LocalParameter hillCoef = activatableExpressionLaw.createLocalParameter("n");
        hillCoef.setName("n");
        hillCoef.setValue(1.0);
        activatableExpressionLaw.setMath(parseFormula(basalExpressionRate.getId()
                + "+(" + maxExpressionRate.getId() + "*(" + activator.getId() + "^" + hillCoef.getId() + "/(" + kd.getId() + "+" + activator.getId()
                + "^" + hillCoef.getId() + ")))"));
        return activatableExpression;
    }
    
    private static Reaction createRatelessActivatableExpressionReaction(Species activator, Species expressed, Model mod) {
    	Reaction activatableExpression = createRatelessExpressionReaction(expressed, mod);
    	ModifierSpeciesReference modifier = activatableExpression.createModifier(activator);
    	modifier.setSBOTerm(SBOTerm.ACTIVATOR.getID());
    	return activatableExpression;
    }
    
    private static Reaction createInducibleActivatableExpressionReaction(Species activator, Species inducer, Species expressed, Model mod) {
        Reaction inducibleActivatableExpression = createRatelessInducibleActivatableExpressionReaction(activator, inducer, expressed, mod);
        KineticLaw inducibleActivatableExpressionLaw = inducibleActivatableExpression.createKineticLaw();
        LocalParameter basalExpressionRate = inducibleActivatableExpressionLaw.createLocalParameter("basal");
        basalExpressionRate.setName("basal");
        basalExpressionRate.setValue(1.0);
        LocalParameter maxBasalExpressionRate = inducibleActivatableExpressionLaw.createLocalParameter("max");
        maxBasalExpressionRate.setName("max");
        maxBasalExpressionRate.setValue(1.0);
        LocalParameter kdBasal = inducibleActivatableExpressionLaw.createLocalParameter("K_d");
        kdBasal.setName("K_d");
        kdBasal.setValue(1.0);
        LocalParameter hillCoefBasal = inducibleActivatableExpressionLaw.createLocalParameter("n");
        hillCoefBasal.setName("n");
        hillCoefBasal.setValue(2.0);
        LocalParameter maxTFExpressionRate = inducibleActivatableExpressionLaw.createLocalParameter("max_TF");
        maxTFExpressionRate.setName("max_TF");
        maxTFExpressionRate.setValue(1.0);
        LocalParameter kdTF = inducibleActivatableExpressionLaw.createLocalParameter("K_d_TF");
        kdTF.setName("K_d_TF");
        kdTF.setValue(1.0);
        LocalParameter hillCoefTF = inducibleActivatableExpressionLaw.createLocalParameter("n_TF");
        hillCoefTF.setName("n_TF");
        hillCoefTF.setValue(2.0);
        LocalParameter kdSM = inducibleActivatableExpressionLaw.createLocalParameter("K_d_SM");
        kdSM.setName("K_d_SM");
        kdSM.setValue(1.0);
        LocalParameter hillCoefSM = inducibleActivatableExpressionLaw.createLocalParameter("n_SM");
        hillCoefSM.setName("n_SM");
        hillCoefSM.setValue(2.0);
        String boundTF = createActivationHillWithoutBasal(inducer.getId(), activator.getId(), kdSM.getId(), hillCoefSM.getId());
        String unboundTF = "(" + activator.getId() + "-" + boundTF + ")";
        String basal = basalExpressionRate.getId() + "+" + createActivationHillWithoutBasal(unboundTF, maxBasalExpressionRate.getId(), kdBasal.getId(), hillCoefBasal.getId());
        String induced = createActivationHillWithoutBasal(boundTF, maxTFExpressionRate.getId(), kdTF.getId(), hillCoefTF.getId());
        inducibleActivatableExpressionLaw.setMath(parseFormula(basal + "+" + induced));
        return inducibleActivatableExpression;
    }
    
    private static String createActivationHillWithoutBasal(String x, String max, String Kd, String n) {
        return "(" + max + "*((" + x + "^" + n + ")/((" + Kd + "^" + n + ")+(" + x + "^" + n + "))))";
    }
    
    private static Reaction createRatelessInducibleActivatableExpressionReaction(Species activator, Species inducer, Species expressed, Model mod) {
    	Reaction inducibleActivatableExpression = createRatelessExpressionReaction(expressed, mod);
    	ModifierSpeciesReference modifier1 = inducibleActivatableExpression.createModifier(activator);
        ModifierSpeciesReference modifier2 = inducibleActivatableExpression.createModifier(inducer);
    	modifier1.setSBOTerm(SBOTerm.ACTIVATOR.getID());
        modifier2.setSBOTerm(SBOTerm.ACTIVATOR.getID());
    	return inducibleActivatableExpression;
    }
	
	/*
     * Methods for parsing and compiling mathematical formulas
     */
    
    //Parse a string fomula to make an ASTNode for a KineticLaw object
    //String syntax at: http://sbml.org/Special/Software/JSBML/latest-stable/build/apidocs/org/sbml/jsbml/text/parser/FormulaParserLL3.html
    private static ASTNode parseFormula(String formula) {
        ASTNode tree = null;
        try {
            IFormulaParser parser = new FormulaParserLL3(new StringReader(""));
            tree = ASTNode.parseFormula(formula, parser);
        } catch (ParseException e) {
            return null;
        }
        return tree;
    }
    
    private static String myFormulaToLaTeXString(ASTNode math) {
        if (math.getType() == ASTNode.Type.CONSTANT_E) {
            return "exponentiale";
        } else if (math.getType() == ASTNode.Type.CONSTANT_FALSE) {
            return "false";
        } else if (math.getType() == ASTNode.Type.CONSTANT_PI) {
            return "pi";
        } else if (math.getType() == ASTNode.Type.CONSTANT_TRUE) {
            return "true";
        } else if (math.getType() == ASTNode.Type.DIVIDE) {
            String leftStr = myFormulaToLaTeXString(math.getLeftChild());
            String rightStr = myFormulaToLaTeXString(math.getRightChild());
            return "\\frac{" + leftStr + ", " + rightStr + "}";
        } else if (math.getType() == ASTNode.Type.FUNCTION) {
            String result = math.getName() + "(";
                for (int i = 0; i < math.getChildCount(); i++) {
                    String child = myFormulaToLaTeXString(math.getChild(i));
                    result += child;
                    if (i + 1 < math.getChildCount()) {
                        result += ", ";
                    }
                }
                result += ")";
            return result;
        } else if (math.getType() == ASTNode.Type.FUNCTION_ABS) {
            return "abs(" + myFormulaToLaTeXString(math.getChild(0)) + ")";
        } else if (math.getType() == ASTNode.Type.FUNCTION_ARCCOS) {
            return "acos(" + myFormulaToLaTeXString(math.getChild(0)) + ")";
        } else if (math.getType() == ASTNode.Type.FUNCTION_ARCCOSH) {
            return "acosh(" + myFormulaToLaTeXString(math.getChild(0)) + ")";
        } else if (math.getType() == ASTNode.Type.FUNCTION_ARCCOT) {
            return "acot(" + myFormulaToLaTeXString(math.getChild(0)) + ")";
        } else if (math.getType() == ASTNode.Type.FUNCTION_ARCCOTH) {
            return "acoth(" + myFormulaToLaTeXString(math.getChild(0)) + ")";
        } else if (math.getType() == ASTNode.Type.FUNCTION_ARCCSC) {
            return "acsc(" + myFormulaToLaTeXString(math.getChild(0)) + ")";
        } else if (math.getType() == ASTNode.Type.FUNCTION_ARCCSCH) {
            return "acsch(" + myFormulaToLaTeXString(math.getChild(0)) + ")";
        } else if (math.getType() == ASTNode.Type.FUNCTION_ARCSEC) {
            return "asec(" + myFormulaToLaTeXString(math.getChild(0)) + ")";
        } else if (math.getType() == ASTNode.Type.FUNCTION_ARCSECH) {
            return "asech(" + myFormulaToLaTeXString(math.getChild(0)) + ")";
        } else if (math.getType() == ASTNode.Type.FUNCTION_ARCSIN) {
            return "asin(" + myFormulaToLaTeXString(math.getChild(0)) + ")";
        } else if (math.getType() == ASTNode.Type.FUNCTION_ARCSINH) {
            return "asinh(" + myFormulaToLaTeXString(math.getChild(0)) + ")";
        } else if (math.getType() == ASTNode.Type.FUNCTION_ARCTAN) {
            return "atan(" + myFormulaToLaTeXString(math.getChild(0)) + ")";
        } else if (math.getType() == ASTNode.Type.FUNCTION_ARCTANH) {
            return "atanh(" + myFormulaToLaTeXString(math.getChild(0)) + ")";
        } else if (math.getType() == ASTNode.Type.FUNCTION_CEILING) {
            return "ceil(" + myFormulaToLaTeXString(math.getChild(0)) + ")";
        } else if (math.getType() == ASTNode.Type.FUNCTION_COS) {
            return "cos(" + myFormulaToLaTeXString(math.getChild(0)) + ")";
        } else if (math.getType() == ASTNode.Type.FUNCTION_COSH) {
            return "cosh(" + myFormulaToLaTeXString(math.getChild(0)) + ")";
        } else if (math.getType() == ASTNode.Type.FUNCTION_COT) {
            return "cot(" + myFormulaToLaTeXString(math.getChild(0)) + ")";
        } else if (math.getType() == ASTNode.Type.FUNCTION_COTH) {
            return "coth(" + myFormulaToLaTeXString(math.getChild(0)) + ")";
        } else if (math.getType() == ASTNode.Type.FUNCTION_CSC) {
            return "csc(" + myFormulaToLaTeXString(math.getChild(0)) + ")";
        } else if (math.getType() == ASTNode.Type.FUNCTION_CSCH) {
            return "csch(" + myFormulaToLaTeXString(math.getChild(0)) + ")";
        } else if (math.getType() == ASTNode.Type.FUNCTION_DELAY) {
            String leftStr = myFormulaToLaTeXString(math.getLeftChild());
            String rightStr = myFormulaToLaTeXString(math.getRightChild());
            return "delay(" + leftStr + ", " + rightStr + ")";
        } else if (math.getType() == ASTNode.Type.FUNCTION_EXP) {
            return "exp(" + myFormulaToLaTeXString(math.getChild(0)) + ")";
        } else if (math.getType() == ASTNode.Type.FUNCTION_FACTORIAL) {
            return "factorial(" + myFormulaToLaTeXString(math.getChild(0)) + ")";
        } else if (math.getType() == ASTNode.Type.FUNCTION_FLOOR) {
            return "floor(" + myFormulaToLaTeXString(math.getChild(0)) + ")";
        } else if (math.getType() == ASTNode.Type.FUNCTION_LN) {
            return "ln(" + myFormulaToLaTeXString(math.getChild(0)) + ")";
        } else if (math.getType() == ASTNode.Type.FUNCTION_LOG) {
            String result = "log(";
            for (int i = 0; i < math.getChildCount(); i++) {
                String child = myFormulaToLaTeXString(math.getChild(i));
                result += child;
                if (i + 1 < math.getChildCount()) {
                    result += ", ";
                }
            }
            result += ")";
            return result;
        } else if (math.getType() == ASTNode.Type.FUNCTION_PIECEWISE) {
            String result = "piecewise(";
            for (int i = 0; i < math.getChildCount(); i++) {
                String child = myFormulaToLaTeXString(math.getChild(i));
                result += child;
                if (i + 1 < math.getChildCount()) {
                    result += ", ";
                }
            }
            result += ")";
            return result;
        } else if (math.getType() == ASTNode.Type.FUNCTION_POWER) {
            String leftStr = myFormulaToLaTeXString(math.getLeftChild());
            String rightStr = myFormulaToLaTeXString(math.getRightChild());
            return leftStr + "^{" + rightStr + "}";
        } else if (math.getType() == ASTNode.Type.FUNCTION_ROOT) {
            String leftStr = myFormulaToLaTeXString(math.getLeftChild());
            String rightStr = myFormulaToLaTeXString(math.getRightChild());
            return "\\sqrt[" + rightStr + "]{" + leftStr + "}";
        } else if (math.getType() == ASTNode.Type.FUNCTION_SEC) {
            return "sec(" + myFormulaToLaTeXString(math.getChild(0)) + ")";
        } else if (math.getType() == ASTNode.Type.FUNCTION_SECH) {
            return "sech(" + myFormulaToLaTeXString(math.getChild(0)) + ")";
        } else if (math.getType() == ASTNode.Type.FUNCTION_SIN) {
            return "sin(" + myFormulaToLaTeXString(math.getChild(0)) + ")";
        } else if (math.getType() == ASTNode.Type.FUNCTION_SINH) {
            return "sinh(" + myFormulaToLaTeXString(math.getChild(0)) + ")";
        } else if (math.getType() == ASTNode.Type.FUNCTION_TAN) {
            return "tan(" + myFormulaToLaTeXString(math.getChild(0)) + ")";
        } else if (math.getType() == ASTNode.Type.FUNCTION_TANH) {
            return "tanh(" + myFormulaToLaTeXString(math.getChild(0)) + ")";
        } else if (math.getType() == ASTNode.Type.INTEGER) {
            if (math.hasUnits()) {
                return "" + math.getInteger() + " \\text{" + math.getUnits() + "}";
            }
            return "" + math.getInteger();
        } else if (math.getType() == ASTNode.Type.LOGICAL_AND) {
            if (math.getChildCount() == 0) {
                return "";
            }
            String result = "(";
            for (int i = 0; i < math.getChildCount(); i++) {
                String child = myFormulaToLaTeXString(math.getChild(i));
                result += child;
                if (i + 1 < math.getChildCount()) {
                    result += " \\land ";
                }
            }
            result += ")";
            return result;
        } else if (math.getType() == ASTNode.Type.LOGICAL_NOT) {
            if (math.getChildCount() == 0) {
                return "";
            }
            String result = "\\lnot(";
            String child = myFormulaToLaTeXString(math.getChild(0));
            result += child;
            result += ")";
            return result;
        } else if (math.getType() == ASTNode.Type.LOGICAL_OR) {
            if (math.getChildCount() == 0) {
                return "";
            }
            String result = "(";
            for (int i = 0; i < math.getChildCount(); i++) {
                String child = myFormulaToLaTeXString(math.getChild(i));
                result += child;
                if (i + 1 < math.getChildCount()) {
                    result += " \\lor( ";
                }
            }
            result += ")";
            return result;
        } else if (math.getType() == ASTNode.Type.LOGICAL_XOR) {
            if (math.getChildCount() == 0) {
                return "";
            }
            String result = "(";
            for (int i = 0; i < math.getChildCount(); i++) {
                String child = myFormulaToLaTeXString(math.getChild(i));
                result += child;
                if (i + 1 < math.getChildCount()) {
                    result += " \\oplus ";
                }
            }
            result += ")";
            return result;
        } else if (math.getType() == ASTNode.Type.MINUS) {
            if (math.getChildCount() == 1) {
                return "-" + myFormulaToLaTeXString(math.getChild(0));
            }
            String leftStr = myFormulaToLaTeXString(math.getLeftChild());
            String rightStr = myFormulaToLaTeXString(math.getRightChild());
            return "(" + leftStr + " - " + rightStr + ")";
        } else if (math.getType() == ASTNode.Type.NAME) {
            return math.getName();
        } else if (math.getType() == ASTNode.Type.NAME_AVOGADRO) {
            return "avogadro";
        } else if (math.getType() == ASTNode.Type.NAME_TIME) {
            return "t";
        } else if (math.getType() == ASTNode.Type.PLUS) {
            String returnVal = "(";
            boolean first = true;
            for (int i = 0; i < math.getChildCount(); i++) {
                if (first) {
                    first = false;
                } else {
                    returnVal += " + ";
                }
                returnVal += myFormulaToLaTeXString(math.getChild(i));
            }
            returnVal += ")";
            return returnVal;
        } else if (math.getType() == ASTNode.Type.POWER) {
            String leftStr = myFormulaToLaTeXString(math.getLeftChild());
            String rightStr = myFormulaToLaTeXString(math.getRightChild());
            return "(" + leftStr + "^{" + rightStr + "})";
        } else if (math.getType() == ASTNode.Type.RATIONAL) {
            if (math.hasUnits()) {
                return "\\frac{" + math.getNumerator() + "}{" + math.getDenominator() + "} \\text{" + math.getUnits() + "}";
            }
            return "\\frac{" + math.getNumerator() + "}{" + math.getDenominator() + "}";
        } else if (math.getType() == ASTNode.Type.REAL) {
            if (math.hasUnits()) {
                return "" + math.getReal() + " \\text{" + math.getUnits() + "}";
            }
            return "" + math.getReal();
        } else if (math.getType() == ASTNode.Type.REAL_E) {
            if (math.hasUnits()) {
                return math.getMantissa() + "E" + math.getExponent() + " \\text{" + math.getUnits() + "}";
            }
            return math.getMantissa() + "E" + math.getExponent();
        } else if (math.getType() == ASTNode.Type.RELATIONAL_EQ) {
            String leftStr = myFormulaToLaTeXString(math.getLeftChild());
            String rightStr = myFormulaToLaTeXString(math.getRightChild());
            return "(" + leftStr + " = " + rightStr + ")";
        } else if (math.getType() == ASTNode.Type.RELATIONAL_GEQ) {
            String leftStr = myFormulaToLaTeXString(math.getLeftChild());
            String rightStr = myFormulaToLaTeXString(math.getRightChild());
            return "(" + leftStr + " \\geq " + rightStr + ")";
        } else if (math.getType() == ASTNode.Type.RELATIONAL_GT) {
            String leftStr = myFormulaToLaTeXString(math.getLeftChild());
            String rightStr = myFormulaToLaTeXString(math.getRightChild());
            return "(" + leftStr + " > " + rightStr + ")";
        } else if (math.getType() == ASTNode.Type.RELATIONAL_LEQ) {
            String leftStr = myFormulaToLaTeXString(math.getLeftChild());
            String rightStr = myFormulaToLaTeXString(math.getRightChild());
            return "(" + leftStr + " \\leq " + rightStr + ")";
        } else if (math.getType() == ASTNode.Type.RELATIONAL_LT) {
            String leftStr = myFormulaToLaTeXString(math.getLeftChild());
            String rightStr = myFormulaToLaTeXString(math.getRightChild());
            return "(" + leftStr + " < " + rightStr + ")";
        } else if (math.getType() == ASTNode.Type.RELATIONAL_NEQ) {
            String leftStr = myFormulaToLaTeXString(math.getLeftChild());
            String rightStr = myFormulaToLaTeXString(math.getRightChild());
            return "(" + leftStr + " \\neq " + rightStr + ")";
        } else if (math.getType() == ASTNode.Type.TIMES) {
            String returnVal = "(";
            boolean first = true;
            for (int i = 0; i < math.getChildCount(); i++) {
                if (first) {
                    first = false;
                } else {
                    returnVal += " \\times ";
                }
                returnVal += myFormulaToLaTeXString(math.getChild(i));
            }
            returnVal += ")";
            return returnVal;
        } else if (math.getType() == ASTNode.Type.FUNCTION_SELECTOR) {
            String returnVal = myFormulaToLaTeXString(math.getChild(0));
            for (int i = 1; i < math.getChildCount(); i++) {
                returnVal += "[" + myFormulaToLaTeXString(math.getChild(i)) + "]";
            }
            return returnVal;
        } else if (math.getType() == ASTNode.Type.VECTOR) {
            String returnVal = "[";
            boolean first = true;
            for (int i = 0; i < math.getChildCount(); i++) {
                if (first) {
                    first = false;
                } else {
                    returnVal += ", ";
                }
                returnVal += myFormulaToLaTeXString(math.getChild(i));
            }
            returnVal += "]";
            return returnVal;
        } else if (math.isOperator()) {
            System.err.println("Operator " + math.getName() + " is not currently supported.");
        } else {
            System.err.println(math.getName() + " is not currently supported.");
        }
        return "";
    }
    
    private static String compileFormula(ASTNode tree) {
    	String formula = null;
        try {
        	FormulaCompiler compiler = new FormulaCompilerLibSBML();
            formula = ASTNode.formulaToString(tree, compiler);
        } catch (SBMLException e) {
            return null;
        } 
        return formula;
    }
    
    /*
     * Methods for getting SBML elements annotated with SBO terms
     */
	
    private static Set<Species> getInputs(Model mod) {
		Set<Species> inputs = new HashSet<Species>();
		for (Species spec : mod.getListOfSpecies()) {
			if (spec.isSetSBOTerm() && spec.getSBOTermID().equals(SBOTerm.INPUT.getID())) {
				inputs.add(spec);
			}
		}
		return inputs;
	}
	
	private static Set<Species> getOutputs(Model mod) {
		Set<Species> outputs = new HashSet<Species>();
		for (Species spec : mod.getListOfSpecies()) {
			if (spec.isSetSBOTerm() && spec.getSBOTermID().equals(SBOTerm.OUTPUT.getID())) {
				outputs.add(spec);
			}
		}
		return outputs;
	}
	
	public enum SBOTerm {
    	EXPRESSION("SBO:0000589"),
    	DEGRADATION("SBO:0000179"),
    	INPUT("SBO:0000600"),
    	OUTPUT("SBO:0000601"),
    	ACTIVATOR("SBO:0000459"),
    	REPRESSOR("SBO:0000020");
    	
    	private final String id;
    	
    	SBOTerm(String id) {
    		this.id = id;
    	}
    	
    	public String getID() {
    		return id;
    	}
    }
    
}
