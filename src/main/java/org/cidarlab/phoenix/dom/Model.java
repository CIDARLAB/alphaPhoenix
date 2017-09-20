/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.dom;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author prash
 */
public class Model {
    
    @Getter
    @Setter
    private ModelType type;

    public Model() {
        this.type = ModelType.MichaelisMenten;
    }
    
    
    public enum ModelType {
        MichaelisMenten //Make this the default model.
    }
    
}
