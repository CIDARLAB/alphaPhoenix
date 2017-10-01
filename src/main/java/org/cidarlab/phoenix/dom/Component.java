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

/**
 *
 * @author prash
 */
public class Component {
    
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
    private List<Interaction> interactions = new ArrayList<>();
    
    @Getter
    @Setter
    private List<LibraryComponent> candidates = new ArrayList<>();
    
    public void addInteraction(Interaction _interaction){
        interactions.add(_interaction);
    }
    
    public static enum ComponentRole{
        PROTEIN,
        PROMOTER,
        PROMOTER_REPRESSIBLE,
        PROMOTER_INDUCIBLE,
        PROMOTER_CONSTITUTIVE,
        RBS,
        CDS,
        CDS_REPRESSOR,
        CDS_ACTIVATOR,
        CDS_REPRESSIBLE_REPRESSOR,
        CDS_ACTIVATIBLE_ACTIVATOR,
        CDS_LINKER,
        CDS_TAG,
        CDS_RESISTANCE,
        CDS_FLUORESCENT,
        CDS_FLUORESCENT_FUSION,
        TERMINATOR,
        ORIGIN,
        VECTOR,
        TESTING,
        MARKER,
        WILDCARD;
    }
}
