/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.core;

import hyness.stl.grammar.sharp.STLSharp;
import java.util.concurrent.ThreadLocalRandom;
import lombok.Getter;
import lombok.Setter;
import org.cidarlab.phoenix.dom.Module;
import org.cidarlab.phoenix.utils.Utilities;

/**
 *
 * @author prash
 */
public class PhoenixProject {
    
    @Getter
    private String resultsFolder;
    
    @Getter
    private String jobId;
    
    @Setter
    @Getter
    private Module structure;
    
    @Setter
    @Getter
    private STLSharp stl;
    
    public String getJobFolder(){
        return (resultsFolder + jobId);
    }
    
    public PhoenixProject(){
        this.resultsFolder = Utilities.getFilepath() + Utilities.getSeparater() + "results" + Utilities.getSeparater();
        if(!Utilities.isDirectory(this.resultsFolder)){
            Utilities.makeDirectory(this.resultsFolder);
        }
        createJob();
    }
    
    public PhoenixProject(String _resultsFolder){
        if(!Utilities.isDirectory(_resultsFolder)){
            System.err.println("Error! Directory " + _resultsFolder + " does not exist");
            System.exit(-1);
        }
        this.resultsFolder = _resultsFolder;
        createJob();
    }
    
    private void executeProject(){
        
    }
    
    private int getNextPositiveId(){
        int id = ThreadLocalRandom.current().nextInt();
        while(id <= 0){
            id = ThreadLocalRandom.current().nextInt();
        }
        return id;
    }
    
    private void createJob(){
        
        String id = "job" + getNextPositiveId();
        String jobFolder = (this.resultsFolder + id);
        while(Utilities.isDirectory(jobFolder)){
            id = "job" + getNextPositiveId();
            jobFolder = (this.resultsFolder + id);
        }
        Utilities.makeDirectory(jobFolder);
        this.jobId = id;
    }
}
