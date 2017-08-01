/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.dao;

import edu.mum.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author gilberto
 */ 
public class DaoLayerImpl implements DaoLayer {

    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public void saveRecord(Object object) {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(object);
            
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }finally{
            entityManager.close();
        }
    }
    
    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public <T> void updateRecord(T object) {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(object);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }finally{
            entityManager.close();
        }
    }
    
    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public <T> T loadEntityObject(Class<T> entity, Integer pkid) {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            T reObject = entityManager.find(entity, pkid);            
            return reObject;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }finally{
            entityManager.close();
        }
    }
        
    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public void updateRecord(Class entity, String[] fields, Object[] fieldValues, String pk, Integer pkid) throws IllegalArgumentException{
        if(fieldValues == null || fieldValues == null) {
            throw new IllegalArgumentException("Fields or values cannot be null...");
        }else if(fieldValues.length != fields.length){
            throw new IllegalArgumentException("Fields and values array size does not match...");
        }
                
        String where = "";
        if (fields.length > 0) {
            where = " " + fields[0].trim() + "=:" + fields[0] + "";
            for (int i = 1; i < fields.length; i++) {
                where += ", " + fields[i].trim() + "=:" + fields[i] + "";
            }
        }

        EntityManager entityManager = HibernateUtil.getEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            String hql = "UPDATE " + entity.getSimpleName() + " SET " + where
                    + " WHERE " + pk + "=:" + pk + "";
            
            Query query = entityManager.createQuery(hql);
            
            //Set field parameters
            query.setParameter(pk, pkid);
            for (int i = 0; i < fields.length; i++) {
                query.setParameter(fields[i], fieldValues[i]);
            }
            
            System.out.println("###############" + hql);
            
            int updateCount = query.executeUpdate();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }finally{
            entityManager.close();
        }
    }
    
    @Override
    @SuppressWarnings({"CallToThreadDumpStack", "CallToPrintStackTrace"})
    public <T> int deleteRecord(Class<T> entity, String pk, Integer pkid) {    
        EntityManager entityManager = HibernateUtil.getEntityManager();
        @SuppressWarnings("UnusedAssignment")
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            
            String hql = "DELETE FROM " + entity.getSimpleName() + " WHERE " + pk + " =:pkid ";
            Query query = entityManager.createQuery(hql);
            query.setParameter("pkid", pkid);
            
            query.executeUpdate();
            transaction.commit();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            entityManager.close();
        }
    }
    
    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public List<?> fetchRecord(Class clazz, String[] fields, String whereClause, String[] params, Object[] paramsValues) {
        if(params == null || paramsValues == null || fields == null) {
            throw new IllegalArgumentException("Fields or values cannot be null...");
        }else if(params.length != paramsValues.length){
            throw new IllegalArgumentException("Fields and values array size does not match...");
        }             

        EntityManager entityManager = HibernateUtil.getEntityManager();
        @SuppressWarnings("UnusedAssignment")
        EntityTransaction transaction = null;
        
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            String selectFields = "";
            if (fields.length > 0x0) {
                selectFields = "SELECT DISTINCT " + fields[0].trim();
                for (int i = 0x1; i < fields.length; i++) {
                    selectFields += ", " + fields[i].trim();
                }
            }

            String hql = selectFields + " FROM " + clazz.getName() + " r " + whereClause;
            Query query = entityManager.createQuery(hql);

            for (int i = 0; i < params.length; i++) {
                query.setParameter(params[i], paramsValues[i]);
            }

            List<Object[]> results = query.getResultList();
            transaction.commit();
            
            if (results.isEmpty()) {
                return new ArrayList<>();
            } else {
                return results;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            entityManager.close();
        }
    }
}
