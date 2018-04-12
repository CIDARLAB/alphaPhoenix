/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.schemas;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.cidarlab.phoenix.utils.Database;
import org.mindrot.jbcrypt.BCrypt;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 *
 * @author frascog
 */
@Entity("users")
public class User {
    
    @Id
    @Getter
    private ObjectId id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String institution;
    @Getter
    private final Date createdOn;
    @Getter
    private String password;

    public User() {
        this.createdOn = null;
    }
    
    public User(String name,String email,String password,String institution) {
        this.id = new ObjectId();
        this.createdOn = new Date();
        this.name = name;
        this.email = email;
        this.institution = institution;
        this.password = this.setPassword(password);
    }
    
    private String setPassword(String plainText) {
        return BCrypt.hashpw(plainText, BCrypt.gensalt());
    }
    
    public static User findByCredentials(String email, String plainTextPassword){
        User user = Database.getInstance().getDatastore().createQuery(User.class).filter("email", email).get();
        if(user != null) {
            if(BCrypt.checkpw(plainTextPassword, user.getPassword())) {
                return user;
            }  
        }
        return null;
    } 
    
    public static boolean userExists(String email) {
        return Database.getInstance().getDatastore().createQuery(User.class).filter("email", email).get() != null;
    }
}
