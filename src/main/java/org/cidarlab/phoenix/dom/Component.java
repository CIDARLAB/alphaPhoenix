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
    
    public static enum ComponentRole{
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
