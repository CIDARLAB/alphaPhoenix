/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.dom;

import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import org.cidarlab.minieugene.predicates.interaction.Interaction.InteractionType;
import org.json.JSONObject;

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
    
    public JSONObject toJSON(){
        JSONObject json = new JSONObject();
        json.put("from", from.getName());
        json.put("to", to.getName());
        json.put("type", type.toString());
        return json;
    }
    
    @Override
    public String toString(){
        String str = "";
        str += from.getName() + "|" + type.toString() + "|" + to.getName();
        return str;
    }
    
    @Override
    public boolean equals(Object o){
        if(o instanceof Interaction){
            Interaction i = (Interaction)o;
            if(i.toString().equals(this.toString())){
                return true;
            } 
        } 
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.toString());
        return hash;
    }
    
}
