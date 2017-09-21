/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.dom;

import lombok.Getter;
import lombok.Setter;
import org.cidarlab.minieugene.predicates.interaction.Interaction.InteractionType;

/**
 *
 * @author prash
 */
public class Interaction {
    
    @Getter
    private final Component from;
    
    @Getter
    private final Component to;
    
    @Getter
    private final InteractionType type;
    
    @Getter
    @Setter
    private Model model;
    
    public Interaction(Component _from, Component _to, InteractionType _type){
        from = _from;
        to = _to;
        type = _type;
    }
    
    
    
}
