/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.dao;

import java.util.List;

/**
 *
 * @author gilberto
 */
public interface DaoLayer {
    
    /**
     * Save an object
     * @param object 
     */
    public void saveRecord(Object object);
    
    public <T> void updateRecord(T object);
    
    public <T> T loadEntityObject(Class<T> entity, Integer pkid);
    
    /**
     * Delete entity record by id
     * @param <T>
     * @param entity 
     * @param pk
     * @param pkid
     * @return 
     */
    public <T> int deleteRecord(Class<T> entity, String pk, Integer pkid);
    
    /**
     * Update entity record
     * @param entity e.g. Person.class
     * @param fields these are the fields to be updated
     * @param fieldValues these are the field values
     * @param pk the primary key field of the entity
     * @param pkid the value of the primary key
     * @throws IllegalArgumentException 
     */
    public void updateRecord(Class entity, String[] fields, Object[] fieldValues, String pk, Integer pkid);
    
    /**
     * Fetch all records by the provided parameters
     * @param clazz the entity class to load into query
     * @param fields the fields to select
     * @param whereClause the where clause string if any
     * @param params the parametized variables in the where clause
     * @param paramsValues the values for the parameterized variables
     * @return the list of records as returned by the query 
     */
    public List<?> fetchRecord(Class clazz, String[] fields, String whereClause, String[] params, Object[] paramsValues);
}
