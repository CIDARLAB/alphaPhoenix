/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.dom;

import java.util.List;
import java.util.Map;
import lombok.Getter;
import org.cidarlab.gridtli.dom.Signal;

/**
 *
 * @author prash
 */
public class SMC {
    
    @Getter
    private double satisfactionPercentage;
    
    @Getter
    private double error;
    
    @Getter
    private Map<String,List<Signal>> simulations;
    
    public SMC(double _satisfactionPercentage, double _error, Map<String,List<Signal>> _simulations){
        this.error = _error;
        this.satisfactionPercentage = _satisfactionPercentage;
        this.simulations = _simulations;
        
    }
    
}
