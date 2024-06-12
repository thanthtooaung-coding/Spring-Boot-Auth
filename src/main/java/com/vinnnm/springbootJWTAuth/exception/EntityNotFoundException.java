/*
 * @Author : Thant Htoo Aung
 * @Date : 6/12/2024
 * @Time : 9:00 PM
 * @Project_Name : Spring Boot Auth
 */

package com.vinnnm.springbootJWTAuth.exception;

/**
 * Custom exception thrown when an entity is not found.
 */
public class EntityNotFoundException extends RuntimeException {

    /**
     * Constructs a new EntityNotFoundException with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception.
     */
    public EntityNotFoundException(String message) {
        super(message);
    }
}