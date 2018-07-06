/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.dom.library;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author prash
 */
public class PrimitiveComponent extends LibraryComponent {
   
    
    public PrimitiveComponent(String _name, String _displayId, URI _cd) {
        this.name = _name;
        this.displayId = _displayId;
        this.componentDefintion = _cd;
    }
    
}
