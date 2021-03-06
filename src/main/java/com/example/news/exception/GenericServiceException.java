package com.example.news.exception;

public class GenericServiceException extends RuntimeException {

    public GenericServiceException(String message) {
        super(message);
    }

    public static class NotFound extends GenericServiceException {

        private static final String ENTITY_NOT_FOUND_WITH_ID = "Entity not found with id = %s";

        public NotFound(Long id) {
            super(String.format(ENTITY_NOT_FOUND_WITH_ID, id));
        }

    }

}
