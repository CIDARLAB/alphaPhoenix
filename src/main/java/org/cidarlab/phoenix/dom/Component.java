/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.dom;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.cidarlab.minieugene.predicates.interaction.Interaction.InteractionType;
import org.cidarlab.phoenix.library.Library;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author prash
 */
public class Component {
    
    @Getter
    @Setter
    private Orientation orientation;
    
    @Getter
    @Setter
    private String name;
    
    @Getter
    @Setter
    private ComponentRole role;
    
    @Getter
    @Setter
    private String IOCname;
    
    @Getter
    @Setter
    private List<Interaction> interactions = new ArrayList<>();
    
    @Getter
    @Setter
    private List<LibraryComponent> candidates = new ArrayList<>();
    
    public void addInteraction(Interaction _interaction){
        interactions.add(_interaction);
    }
    
    public void addCandidate(LibraryComponent _c){
        candidates.add(_c);
    }
    
    public JSONObject toJSON(){
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("orientation", orientation.toString());
        json.put("role", role.toString());
        if(IOCname != null){
            json.put("ioc", IOCname);
        }
        JSONArray intArr = new JSONArray();
        for(Interaction i:this.interactions){
            intArr.put(i.toJSON());
        }
        json.put("interactions", intArr);
        JSONArray candArr = new JSONArray();
        for(LibraryComponent candidate:candidates){
            candArr.put(candidate.getComponentDefintion().toString());
        }
        json.put("candidates", candArr);
        return json;
    }
    
    public static Component fromJSON(JSONObject json, Library lib) throws URISyntaxException{
        Component c = new Component();
        
        c.setName(json.getString("name"));
        c.setOrientation(Orientation.valueOf(json.getString("orientation")));
        c.setRole(ComponentRole.valueOf(json.getString("role")));
        if(json.has("ioc")){
            c.setIOCname(json.getString("ioc"));
        }
        for(Object o:json.getJSONArray("candidates")){
            URI u = new URI((String)o);
            LibraryComponent lc = lib.fromURI(u);
            if(lc == null){
                
            } else {
                c.addCandidate(lib.fromURI(u)); //Check and see if this is NULL
            }
        }
        return c;
    }
    
    public void addInteractions(JSONObject json, Map<String,Component> componentMap){
        for(Object o:json.getJSONArray("interactions")){
            JSONObject intJSON = (JSONObject)o;
            this.addInteraction(new Interaction(componentMap.get(intJSON.getString("from")), componentMap.get(intJSON.getString("to")), InteractionType.valueOf(intJSON.getString("type"))));
        }
    }
    
    public static enum ComponentRole{
        PROTEIN,
        INDUCER,
        PROMOTER,
        PROMOTER_REPRESSIBLE,
        PROMOTER_INDUCIBLE,
        PROMOTER_CONSTITUTIVE,
        RBS,
        CDS,
        CDS_REPRESSOR,
        CDS_ACTIVATOR,
        CDS_REPRESSIBLE_REPRESSOR,
        CDS_ACTIVATIBLE_ACTIVATOR,
        CDS_LINKER,
        CDS_TAG,
        CDS_RESISTANCE,
        CDS_FLUORESCENT,
        CDS_FLUORESCENT_FUSION,
        TERMINATOR,
        ORIGIN,
        VECTOR,
        TESTING,
        MARKER,
        WILDCARD;
    }
}
