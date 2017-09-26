/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.dom;

import java.io.File;
import java.util.List;
import org.sbml.jsbml.SBMLDocument;

/**
 *
 * @author prash
 */
public class ModelPart extends Model {
    
    public ModelPart(SBMLDocument _sbml){
        this.setSbml(_sbml);
        this.setType(ModelType.MichaelisMenten);
    }
    
    //Get from Phoenix
    public ModelPart(List<File> experiments){
        //Time Series Data, Model, etc
    }
    
    
}
