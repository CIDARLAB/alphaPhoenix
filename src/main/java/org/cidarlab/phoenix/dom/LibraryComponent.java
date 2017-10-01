/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.dom;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author prash
 */
public class LibraryComponent {
    
    //Does this cover everything?
    
    @Getter
    private String name;
    
    @Getter
    private String displayId;
    
    @Getter
    @Setter
    private URI componentDefintion;
    
    @Getter
    @Setter
    private List<URI> moduleDefinitions = new ArrayList<>();
    
    @Getter
    @Setter
    private List<URI> models = new ArrayList<>();
    
    public void addModel(URI uri){
        this.models.add(uri);
    }
    
    public void addModuleDefinition(URI uri){
        this.moduleDefinitions.add(uri);
    }
    
    public LibraryComponent(String _name, String _displayId, URI _cd){
        this.name = _name;
        this.displayId = _displayId;
        this.componentDefintion = _cd;
    }
    
    @Override
    public boolean equals(Object o){
        if(o instanceof LibraryComponent){
            LibraryComponent lc = (LibraryComponent)o;
            if(lc.componentDefintion.equals(this.componentDefintion)){
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.componentDefintion);
        return hash;
    }
           
}
