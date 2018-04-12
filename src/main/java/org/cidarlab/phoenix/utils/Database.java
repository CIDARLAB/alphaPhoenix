package org.cidarlab.phoenix.utils;

import com.mongodb.MongoClient;
import lombok.Getter;
import org.cidarlab.phoenix.schemas.Entities;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

/**
 *
 * @author frascog
 */
public class Database {
    
    private static Database instance;
    
    private final String uri = this.setValue(System.getenv("database_uri"), "localhost");
    private final int port = new Integer(this.setValue(System.getenv("database_port"), "27017"));
    private final String name = this.setValue(System.getenv("database_name"), "phoenix");
    
    
    private MongoClient mongoClient;
    private Morphia morphia;
    @Getter
    private Datastore datastore;

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public Database() {
        this.mongoClient = new MongoClient();
        this.morphia = new Morphia();
        this.morphia.map(Entities.class);
        this.datastore = this.morphia.createDatastore(mongoClient, this.name);
        this.datastore.ensureIndexes();
    }
    
    public static void init() {
        Database.getInstance();
    }
    
    private String setValue(String value, String defaultValue) {
        return isNotNullOrEmpty(value) ? value : defaultValue;
    }
    
    private static boolean isNotNullOrEmpty(String str){
        return (str != null && !str.isEmpty());
    }
    
    public void save(Object object) {
        this.datastore.save(object);
    }
    
}


