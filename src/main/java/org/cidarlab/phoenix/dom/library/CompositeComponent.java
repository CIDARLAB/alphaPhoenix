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
import org.sbolstandard.core2.SBOLDocument;

/**
 *
 * @author prash
 */
public class CompositeComponent extends LibraryComponent {
    
    @Getter
    private List<URI> children = new ArrayList<>();
    
    @Getter
    private CompositeType type;
    
    
    public CompositeComponent(String _name, String _displayId, URI _cd, CompositeType _type, List<URI> _children) {
        this.name = _name;
        this.displayId = _displayId;
        this.children = _children;
        this.type = _type;
        this.componentDefintion = _cd;
    }
    
    public enum CompositeType{
        PR,
        RC
    }
    
}
