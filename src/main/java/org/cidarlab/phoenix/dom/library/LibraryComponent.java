/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.dom.library;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author prash
 */
public abstract class LibraryComponent {
    
    //Does this cover everything?
    
    @Getter
    protected String name;
    
    @Getter
    protected String displayId;
    
    @Getter
    @Setter
    protected URI componentDefintion;
    
    @Getter
    @Setter
    protected List<URI> moduleDefinitions = new ArrayList<>();
    
    @Getter
    @Setter
    protected List<URI> models = new ArrayList<>();
    
    @Getter
    @Setter
    protected Set<URI> notWith = new HashSet<>();
    
    @Getter
    @Setter
    protected Set<URI> before = new HashSet<>();
    
    @Getter
    @Setter
    protected Set<URI> after = new HashSet<>();
    
    
    public void addAfter(URI uri){
        this.after.add(uri);
    }
    
    public void addBefore(URI uri){
        this.before.add(uri);
    }
    
    public void addNothWith(URI uri){
        this.notWith.add(uri);
    }
    
    public void addModel(URI uri){
        this.models.add(uri);
    }
    
    public void addModuleDefinition(URI uri){
        this.moduleDefinitions.add(uri);
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
