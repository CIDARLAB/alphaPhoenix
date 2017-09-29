/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.adaptors;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.sbolstandard.core2.SBOLDocument;
import org.synbiohub.frontend.SynBioHubException;
import org.synbiohub.frontend.SynBioHubFrontend;

/**
 *
 * @author prash
 */
public class SynbiohubAdaptor {
    
    public static SBOLDocument getSBOL(String frontendurl, String collection){
        SynBioHubFrontend shub = new SynBioHubFrontend(frontendurl);
        URI u;
        try {
            u = new URI(collection);
            return shub.getSBOL(u);
    
        } catch (URISyntaxException | SynBioHubException ex) {
            Logger.getLogger(SynbiohubAdaptor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
}
