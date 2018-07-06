/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.utils;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author prash
 */
public class Args {
    
    public enum Decomposition{
        PR_C_T,
        P_RC_T,
        P_R_C_T,
        PRCT
    }
    
    
    public enum Simulation{
        STOCHASTIC,
        DETERMINISTIC
    }
    
    public enum Assignment{
        EXHAUSTIVE,
        SIMULATED_ANNEALING
    }
    
    @Getter
    private int runCount;
    
    @Getter
    private double confidence;
    
    @Getter
    private double threshold;
    
    @Getter
    private Decomposition decomposition;
    
    @Getter
    private Simulation simulation;
    
    @Getter
    private Assignment assignment;
    
    @Setter
    @Getter
    private String projectFolder;
    
    public Args(){
        this.assignment = Assignment.SIMULATED_ANNEALING;
        this.simulation = Simulation.STOCHASTIC;
        this.decomposition = Decomposition.PR_C_T;
    }
    
    public Args(Decomposition _decomposition, Simulation _simulation, int _runCount, double _confidence, double _threshold, Assignment _assignment){
        this.decomposition = _decomposition;
        this.simulation = _simulation;
        this.runCount = _runCount;
        this.confidence = _confidence;
        this.threshold = _threshold;
        this.assignment = _assignment;
    }
    
}
