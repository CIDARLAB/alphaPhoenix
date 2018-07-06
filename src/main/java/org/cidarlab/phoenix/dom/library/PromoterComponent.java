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

/**
 *
 * @author prash
 */
public class PromoterComponent extends LibraryComponent{
    
    @Getter
    List<LibraryComponent> transcriptionFactors;
    
    public PromoterComponent(String _name, String _displayId, URI _cd) {
        this.name = _name;
        this.displayId = _displayId;
        this.componentDefintion = _cd;
        this.transcriptionFactors = new ArrayList<>();
    }
    
    public void addComplex(ComplexComponent complex){
        this.transcriptionFactors.add(complex);
    }
    
    public void addProtein(PrimitiveComponent protein){
        this.transcriptionFactors.add(protein);
    }
    
    public URI getComplexProtein(URI smallMolecule){
        for(LibraryComponent lc:this.transcriptionFactors){
            if(lc instanceof ComplexComponent){
                ComplexComponent c = (ComplexComponent)lc;
                if (c.getSmallMolecule().equals(smallMolecule)) {
                    return c.getProtein();
                }
            }
            
        }
        return null;
    }
    
    public URI getComplexSmallMolecule(URI protein){
        for(LibraryComponent lc:this.transcriptionFactors){
            if(lc instanceof ComplexComponent){
                ComplexComponent c = (ComplexComponent)lc;
                if (c.getProtein().equals(protein)) {
                    return c.getSmallMolecule();
                }
            }
        }
        return null;
    }
    
}
