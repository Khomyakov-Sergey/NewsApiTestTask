package ru.clevertec.newsapi.exception;

/**
 * Exception class for entity, that already exist in DB.
 * @author Siarhei Khamiakou
 * @version 1.0
 */
public class EntityAlreadyExistException extends RuntimeException{

    private static final String ENTITY_ALREADY_EXIST = "Entity with such title ot text already exist";

    public EntityAlreadyExistException() {
        super(ENTITY_ALREADY_EXIST);
    }
}
