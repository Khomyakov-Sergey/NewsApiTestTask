package ru.clevertec.newsapi.exception;

import javax.persistence.EntityNotFoundException;

/**
 * Exception class for entity, that can`t be founded in DB.
 * @author Siarhei Khamiakou
 * @version 1.0
 */
public class EntityByIdNotFoundException extends EntityNotFoundException {

    private static final String ENTITY_NOT_FOUND = "Entity with ID=%d not found, please check your request";

    public EntityByIdNotFoundException(Long id) {
        super(String.format(ENTITY_NOT_FOUND, id));
    }
}
