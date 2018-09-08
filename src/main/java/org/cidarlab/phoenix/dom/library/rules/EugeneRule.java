/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.dom.library.rules;

import lombok.Getter;

/**
 *
 * @author prash
 */
public class EugeneRule extends EugeneRulesBaseListener{
    
    @Getter
    private String left;
    
    @Getter
    private String right;
    
    @Getter
    private EugeneRuleType erule;
    
    public enum EugeneRuleType{
        NOTWITH,
        AFTER,
        BEFORE
    }
    
    @Override 
    public void exitEugeneRule(EugeneRulesParser.EugeneRuleContext ctx) {
        //this.erule = ctx.erule.getText();
        this.left = ctx.left.getText();
        this.right = ctx.right.getText();
        switch(ctx.erule.getText()){
            case "notwith":
            case "NOTWITH":
                this.erule = EugeneRuleType.NOTWITH;
                break;
            case "before":
            case "BEFORE":
                this.erule = EugeneRuleType.BEFORE;
                break;
            case "after":
            case "AFTER":
                this.erule = EugeneRuleType.AFTER;
                break;
        }
    
    }	
    
    
}
