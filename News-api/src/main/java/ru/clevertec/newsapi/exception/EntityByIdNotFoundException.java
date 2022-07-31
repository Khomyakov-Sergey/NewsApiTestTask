package ru.clevertec.newsapi.exception;

import javax.persistence.EntityNotFoundException;

public class EntityByIdNotFoundException extends EntityNotFoundException {

    private static final String ENTITY_NOT_FOUND = "Entity with ID=%d not found, please check your request";

    public EntityByIdNotFoundException(Long id) {
        super(String.format(ENTITY_NOT_FOUND, id));
    }
}
