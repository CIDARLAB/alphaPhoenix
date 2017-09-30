/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.adaptors;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import javax.xml.stream.XMLStreamException;
import org.apache.commons.io.FileUtils;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBMLReader;
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
    
    
    
    public static void gunzip(String inputzip, String outputfile) {        
        FileOutputStream out = null;
        try {
            byte[] buffer = new byte[1024];
            try (GZIPInputStream gzis = new GZIPInputStream(new FileInputStream(inputzip))) {
                out = new FileOutputStream(outputfile);
                int len;
                while ((len = gzis.read(buffer)) > 0) {
                    out.write(buffer, 0, len);
                }
            }
            out.close();
            System.out.println("Unzip complete");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SynbiohubAdaptor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SynbiohubAdaptor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(SynbiohubAdaptor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static SBMLDocument getModel(URL modelurl, String tempfp){
        
        try {
            String zipfp = tempfp + "temp.gz";
            String output = tempfp + "temp.xml";
            FileUtils.copyURLToFile(modelurl, new File(zipfp));
            gunzip(zipfp,output);
            File zipfile = new File(zipfp);
            File outfile = new File(output);
            SBMLDocument sbml = SBMLReader.read(outfile);
            zipfile.delete();
            outfile.delete();
            return sbml;
        } catch (IOException | XMLStreamException ex) {
            Logger.getLogger(SynbiohubAdaptor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
