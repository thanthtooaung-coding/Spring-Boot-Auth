/*
 * @Author : Thant Htoo Aung
 * @Date : 6/12/2024
 * @Time : 9:00 PM
 * @Project_Name : Spring Boot Auth
 */

package com.vinnnm.springbootJWTAuth.exception;

/**
 * Custom exception thrown when there is an error during the creation of an entity.
 */
public class EntityCreationException extends RuntimeException {

    /**
     * Constructs a new EntityCreationException with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception.
     */
    public EntityCreationException(String message) {
        super(message);
    }
}