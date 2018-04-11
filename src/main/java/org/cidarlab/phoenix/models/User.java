/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.models;

import org.bson.types.ObjectId;
import org.cidarlab.phoenix.utils.Database;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 *
 * @author frascog
 */
@Entity("user")
public class User {
    
    @Id
    private ObjectId id;

    public User() {
        this.id = new ObjectId();
    }
    
    
    
    
    
}
