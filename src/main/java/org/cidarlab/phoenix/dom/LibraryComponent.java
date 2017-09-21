/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.dom;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.sbolstandard.core2.ComponentDefinition;
import org.sbolstandard.core2.ModuleDefinition;

/**
 *
 * @author prash
 */
public class LibraryComponent {
    
    //Does this cover everything?
    @Getter
    @Setter
    private ComponentDefinition componentDefintion;
    
    @Getter
    @Setter
    private List<ModuleDefinition> moduleDefinitions = new ArrayList<>();
    
    @Getter
    @Setter
    private Model model;
    
    public LibraryComponent(){
       
    }
           
}
