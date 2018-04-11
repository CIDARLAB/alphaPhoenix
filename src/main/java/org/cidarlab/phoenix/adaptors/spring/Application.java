/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.adaptors.spring;

import org.cidarlab.phoenix.models.User;
import org.cidarlab.phoenix.utils.Database;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author prash
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        
        Database.init();
        Database.getInstance().save(new User());
        SpringApplication.run(Application.class, args);
    }
}
