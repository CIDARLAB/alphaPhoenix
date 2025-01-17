/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.dom;

import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import org.cidarlab.phoenix.dom.Component.ComponentRole;

/**
 *
 * @author prash
 */
public class CandidateComponent {
    
    @Getter
    @Setter
    private Orientation orientation;
    
    @Getter
    @Setter
    private String name;
    
    @Getter
    @Setter
    private ComponentRole role;

    @Getter
    @Setter
    private LibraryComponent candidate;
    
    @Override
    public boolean equals(Object o){
        if(o instanceof CandidateComponent){
            CandidateComponent cd = (CandidateComponent)o;
            if(cd.candidate.getComponentDefintion().equals(this.candidate.getComponentDefintion())){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.candidate);
        return hash;
    }
    
    public CandidateComponent(Component c, LibraryComponent _candidate){
        this.orientation = c.getOrientation();
        this.name = c.getName();
        this.role = c.getRole();
        this.candidate = _candidate;     
    }
}
