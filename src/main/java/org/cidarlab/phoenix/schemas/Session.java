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
import org.mongodb.morphia.query.Query;

/**
 *
 * @author frascog
 */
@Entity("sessions")
public class Session {
    
    @Id
    @Getter
    private ObjectId id;
    @Getter
    private final ObjectId userId;
    @Getter
    private final Date createdOn;
    @Getter
    private String token;

    public Session() {
        this.userId = null;
        this.createdOn = null;
    }

    public Session(User user,ObjectId key) {
        this.userId = user.getId();
        this.createdOn = new Date();
        this.token = BCrypt.hashpw(key.toString(), BCrypt.gensalt());
    }
    
    private static boolean validateSession(Session session,String key) {
        return BCrypt.checkpw(key, session.getToken());
    }
    
    public static Session findByCredentials(String id,String key) {
        
        Session session = Database.getInstance().getDatastore().get(Session.class,new ObjectId(id));
        if(session != null) {
           if(Session.validateSession(session, key)) {
            return session;
            } 
        }
        return null;
    }
    
    public static User getUser(Session session) {
        return Database.getInstance().getDatastore().get(User.class,session.getUserId());
    }
    
    public static void deleteSessionForUser(User user) {
        Query<Session> query = Database.getInstance().getDatastore().createQuery(Session.class).filter("userId", user.getId());
        Database.getInstance().getDatastore().findAndDelete(query);
    }
}
