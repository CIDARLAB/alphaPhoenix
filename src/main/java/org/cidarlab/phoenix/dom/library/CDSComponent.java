/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.dom.library;

import java.net.URI;
import java.util.ArrayList;
import lombok.Getter;

/**
 *
 * @author prash
 */
public class CDSComponent extends LibraryComponent {
    
    @Getter
    private URI protein;
    
    public CDSComponent(String _name, String _displayId, URI _cd, URI _protein) {
        this.name = _name;
        this.displayId = _displayId;
        this.componentDefintion = _cd;
        this.protein = protein;
    }
    
    
    
}
