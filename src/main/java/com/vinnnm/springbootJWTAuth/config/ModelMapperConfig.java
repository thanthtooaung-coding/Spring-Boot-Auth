/*
 * @Author : Thant Htoo Aung
 * @Date : 6/12/2024
 * @Time : 9:00 PM
 * @Project_Name : Spring Boot Auth
 */

package com.vinnnm.springbootJWTAuth.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for ModelMapper bean.
 */
@Configuration
public class ModelMapperConfig {

    /**
     * Creates and returns a ModelMapper bean.
     *
     * ModelMapper is a library that helps in object mapping, useful for converting
     * DTOs to entities and vice versa.
     *
     * @return a configured ModelMapper instance.
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
