/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.utils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.sbolstandard.core2.SBOLValidationException;
import org.synbiohub.frontend.SynBioHubException;

/**
 *
 * @author prash
 */
public class SAPlots {
    public static void main(String[] args) throws URISyntaxException, SynBioHubException, SBOLValidationException, MalformedURLException, IOException, InterruptedException {
        
        
        String caseStudyfp = Utilities.getLibFilepath() + "caseStudy" + Utilities.getSeparater();
        Utilities.makeDirectory(caseStudyfp);
        
        String safp = caseStudyfp + "dissertation" + Utilities.getSeparater();
        Utilities.makeDirectory(safp);
        
        String sbolfp = safp + "sbol" + Utilities.getSeparater();
        Utilities.makeDirectory(sbolfp);
        
        String simfp = safp + "sim" + Utilities.getSeparater();
        Utilities.makeDirectory(simfp);
        
        String progfp = safp + "progress" + Utilities.getSeparater();
        Utilities.makeDirectory(progfp);
        
       
        
        String resultfp = caseStudyfp + "simulatedAnnealing/twoTU/low_350.0_450.0/high_5000.0_10000.0/iteration0/saResults" + Utilities.getSeparater();
        
        for(int i=0;i<102;i++){
            String fp = resultfp + i + Utilities.getSeparater();
            List<String> lines = Utilities.getFileContentAsStringList(fp + "sbol.py");
            
            List<String> newScript = new ArrayList<>();
            for(String s:lines){
                if(s.startsWith("fig = plt.figure(figsize=")){
                    newScript.add("fig = plt.figure(figsize=(1.6,0.7))");
                } else if(s.startsWith("ax_dna.set_ylim([")){
                    newScript.add("ax_dna.set_ylim([-30.0,30.0])");
                } else {
                    newScript.add(s);
                }
            }
            Utilities.writeToFile(fp + "newscript.py", newScript);
            Utilities.runPythonScript(fp + "newscript.py");
        }
        
        
        for(int i=0;i<102;i++){
            String fp = resultfp + i + Utilities.getSeparater();
            FileUtils.copyFile(new File(fp + "sbol.png"), new File(sbolfp + i + ".png"));
            FileUtils.copyFile(new File(fp + "maxScore.png"), new File(progfp + i + ".png"));
            FileUtils.copyFile(new File(fp + "out0.png"), new File(simfp + i + ".png"));
        }
        
        

    }
}
