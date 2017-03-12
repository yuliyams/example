package com.app.dao;

import java.util.List;

/**
 * Generic interface for CRUD operations
 *
 */
public interface Dao<T> {

    /**
     * Creates a new entity object in a storage
     *
     * @param object
     *            - the object to be created
     * @return created object
     */
    T create(T object);

    /**
     * Updates the entity object
     *
     * @param object-
     *            the object to be updated
     * @return updated object
     */
    T update(T object);

    /**
     * Removes the entity object from the storage
     *
     * @param id
     *            - the id object to be removed
     * @return the removed object
     */
    T delete(long id);

    /**
     * Get the entity object by id
     *
     * @param id
     *            - object id
     * @return the object from the storage
     */
    T getById(long id);

    /**
     * Get all entity objects
     *
     * @return
     */
    List<T> getAll();
}
