/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.dom;

import lombok.Getter;
import lombok.Setter;
import org.sbml.jsbml.SBMLDocument;

/**
 *
 * @author prash
 */
public abstract class Model {
    
    @Getter
    @Setter
    private SBMLDocument sbml;
    
    @Getter
    @Setter
    private ModelType type;
    
    public enum ModelType {
        MichaelisMenten, //Make this the default model.
        BioCPS
    }
    
}
