package com.laarc.hoamanagerserver.api.crud;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BaseCrudService<T, ID> {

    /**
     * Get entity by ID
     * @param id Entity identifier
     * @return The entity with specified ID
     */
    T getById(ID id);

    /**
     * Retrieve a list of all entities
     * @return A list of all entities
     */
    List<T> getAll();

    /**
     * Save the entity (Create and update)
     * @param entity The entity to save
     * @return The saved entity
     */
    T save(T entity);

    /**
     * Finds existence of entity with ID
     * @param id Entity identifier
     * @return true if the entity exists; otherwise false
     */
    boolean existsById(ID id);

    /**
     * Deletes an entity with ID
     * @param id Entity identifier
     */
    void deleteById(ID id);

}
