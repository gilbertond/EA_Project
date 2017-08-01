/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author gilberto
 */ 

//My Singleton class for connection to persistent unit
public class HibernateUtil {

    private static final EntityManagerFactory EMF;
    
    static {
        try {
            EMF = Persistence.createEntityManagerFactory("ea_project_PU");
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial Transaction creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static EntityManager getEntityManager() {
        return EMF.createEntityManager();
    }
}
