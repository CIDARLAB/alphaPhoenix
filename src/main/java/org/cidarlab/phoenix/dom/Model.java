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
public class Model {
    
    @Getter
    private SBMLDocument sbml;
    
    @Getter
    @Setter
    private boolean overriden;
    
    public Model(SBMLDocument _sbml){
        this.sbml = _sbml;
        this.overriden = false;
    }
}
