/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.dom.library;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author prash
 */
public class SmallMoleculeComponent extends LibraryComponent {
    
    @Setter
    @Getter
    private double min;
    
    @Setter
    @Getter
    private double max;
    
    @Getter
    private List<Double> values;
    
    public SmallMoleculeComponent(String _name, String _displayId, URI _cd) {
        this.name = _name;
        this.displayId = _displayId;
        this.componentDefintion = _cd;
        
        this.values = new ArrayList<>();
    }
    
    
    
}
