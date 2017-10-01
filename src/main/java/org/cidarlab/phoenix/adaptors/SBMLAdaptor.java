/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.adaptors;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLStreamException;

import org.sbml.jsbml.ASTNode;
import org.sbml.jsbml.Compartment;
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
import org.sbml.jsbml.SimpleSpeciesReference;
import org.sbml.jsbml.Species;
import org.sbml.jsbml.SpeciesReference;
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
            
	/*private static void testAdaptor() {
		SBMLDocument degradationDoc = createDegradationModel("GFP");

		SBMLDocument expressionDoc = createExpressionModel("GFP");

		SBMLDocument repressionDoc = createRepressionModel("LacI", "GFP");

		SBMLDocument activationDoc = createActivationModel("GAL4VP16", "GFP");

		SBMLDocument inductionRepressionDoc = createInductionRepressionModel("IPTG", "LacI", "GFP");

		SBMLDocument inductionActivationDoc = createInductionActivationModel("Arabinose", "AraC", "GFP");

		SBMLDocument selfRepressionDoc = createRepressionModel("LacI", "GFP");
		HashMap<String, String> substitutions = new HashMap<String, String>();
		substitutions.put("GFP", "LacI");
		substituteSpecies(substitutions, selfRepressionDoc.getModel());

		SBMLDocument inductionInverterDoc1 = createInductionRepressionModel("IPTG", "LacI", "TetR");
		SBMLDocument inductionInverterDoc2 = createInductionRepressionModel("aTc", "TetR", "LacI");
		SBMLDocument toggleDoc = SBMLAdaptor.composeModels(inductionInverterDoc1.getModel(), inductionInverterDoc2.getModel());

		List<Model> inverterMods = new LinkedList<Model>();
		inverterMods.add(createRepressionModel("LacI", "TetR").getModel());
		inverterMods.add(createRepressionModel("TetR", "cI").getModel());
		inverterMods.add(createRepressionModel("cI", "LacI").getModel());
		SBMLDocument repressilatorDoc = SBMLAdaptor.composeModels(inverterMods);
	}*/
	       
        
	/*
     * Methods for composing SBML models of gene expression
     */  
	
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
				if (composedRxn.getSBOTermID().equals(SBOTerm.EXPRESSION.getID())) {
					versionSBase(composedRxn, composedMod);
					composedMod.addReaction(composedRxn);
				} else if (composedRxn.getSBOTermID().equals(SBOTerm.DEGRADATION.getID()) && !composedMod.containsReaction(composedRxn.getId())) {
					composedMod.addReaction(composedRxn);
				}
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
    	return composeModels(createRepressedPromoterModel(activatorID, expressedID, activatorName, expressedName).getModel(), createCDSModel(expressedID, expressedName).getModel());
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
        LocalParameter degradationRate = degradationLaw.createLocalParameter("k_d");
    	degradationRate.setName("k_d");
    	degradationRate.setValue(1.0);
    	degradationLaw.setMath(parseFormula(degradationRate.getId() + "*" + degraded.getId()));
    	return degradation;
    }
    
    private static Reaction createExpressionReaction(Species expressed, Model mod) {
    	Reaction expression = createRatelessExpressionReaction(expressed, mod);
    	KineticLaw expressionLaw = expression.createKineticLaw();
		LocalParameter expressionRate = expressionLaw.createLocalParameter("k_EXE");
		expressionRate.setName("k_EXE");
		expressionRate.setValue(1.0);
                LocalParameter activePromoterFraction = expressionLaw.createLocalParameter("y");
		activePromoterFraction.setName("y");
		activePromoterFraction.setValue(1.0);
		expressionLaw.setMath(parseFormula(expressionRate.getId() + "*" + activePromoterFraction.getId()));
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
		LocalParameter maxExpressionRate = repressibleExpressionLaw.createLocalParameter("k_EXR");
		maxExpressionRate.setName("k_EXR");
		maxExpressionRate.setValue(1.0);
		LocalParameter dissociation = repressibleExpressionLaw.createLocalParameter("d");
		dissociation.setName("d");
		dissociation.setValue(1.0);
		LocalParameter coop = repressibleExpressionLaw.createLocalParameter("h");
		coop.setName("h");
		coop.setValue(2.0);
                LocalParameter activePromoterFraction = repressibleExpressionLaw.createLocalParameter("y");
		activePromoterFraction.setName("y");
		activePromoterFraction.setValue(1.0);
		repressibleExpressionLaw.setMath(parseFormula(maxExpressionRate.getId()
                        + "*((1-" + activePromoterFraction.getId() + ")*(1/(1+("+ repressor.getId() + "/"
                        + dissociation.getId() + ")^" + coop.getId() + "))+" + activePromoterFraction.getId() + ")"));
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
		LocalParameter maxExpressionRate = activatableExpressionLaw.createLocalParameter("k_EXA");
		maxExpressionRate.setName("k_EXA");
		maxExpressionRate.setValue(1.0);
		LocalParameter dissociation = activatableExpressionLaw.createLocalParameter("d");
		dissociation.setName("d");
		dissociation.setValue(1.0);
		LocalParameter coop = activatableExpressionLaw.createLocalParameter("h");
		coop.setName("h");
		coop.setValue(2.0);
                LocalParameter activePromoterFraction = activatableExpressionLaw.createLocalParameter("y");
		activePromoterFraction.setName("y");
		activePromoterFraction.setValue(1.0);
                activatableExpressionLaw.setMath(parseFormula(maxExpressionRate.getId()
                        + "*((1-" + activePromoterFraction.getId() + ")*(("+ activator.getId() + "/"
                        + dissociation.getId() + ")^" + coop.getId() + "/(1+("+ activator.getId() + "/"
                        + dissociation.getId() + ")^" + coop.getId() + "))+" + activePromoterFraction.getId() + ")"));
    	return activatableExpression;
    }
    
    private static Reaction createRatelessActivatableExpressionReaction(Species activator, Species expressed, Model mod) {
    	Reaction activatableExpression = createRatelessExpressionReaction(expressed, mod);
    	ModifierSpeciesReference modifier = activatableExpression.createModifier(activator);
    	modifier.setSBOTerm(SBOTerm.ACTIVATOR.getID());
    	return activatableExpression;
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
