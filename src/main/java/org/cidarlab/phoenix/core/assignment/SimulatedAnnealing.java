/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.core.assignment;

import hyness.stl.TreeNode;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLStreamException;
import org.cidarlab.gridtli.dom.TLIException;
import org.cidarlab.phoenix.dom.Module;
import org.cidarlab.phoenix.dom.library.Library;
import org.cidarlab.phoenix.utils.Args;
import org.cidarlab.phoenix.utils.Utilities;

/**
 *
 * @author prash
 */
public class SimulatedAnnealing extends AbstractAssignment {

    private double coolingRate = 0.003;
    private double temperature = 1000;
    
    @Override
    public void solve(List<Module> modules, Library library, TreeNode stl, Args args) {
        for(Module m:modules){
            solve(m,library,stl,args);
        }
        for(int i=0;i<modules.size();i++){
            String simfp = args.getProjectFolder() + i + Utilities.getSeparater();
            Utilities.makeDirectory(simfp);
            Module m = modules.get(i);
            
            try {
                Simulation.run(modules.get(i),library,stl,args,simfp);
            } catch (URISyntaxException | MalformedURLException | XMLStreamException | FileNotFoundException ex) {
                Logger.getLogger(Exhaustive.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException | TLIException | InterruptedException ex) {
                Logger.getLogger(Exhaustive.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void solve(Module module, Library library, TreeNode stl, Args args){
        for(Module tu:module.getChildren()){
            assignTUchildCandidates(tu,library);
        }
        int count = 0;
        while(temperature > 1){
            System.out.println("Temperature :: " + temperature);
            temperature *= (1-coolingRate);
            count++;
        }
        
        System.out.println("Total :: " + count);
    }
    
    
    
    
    
}
