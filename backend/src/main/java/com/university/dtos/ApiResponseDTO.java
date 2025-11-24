package com.university.dtos; // Le package doit être adapté à votre projet

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data // Génère Getters, Setters, toString(), equals(), et hashCode()
@NoArgsConstructor // Génère un constructeur sans argument (par défaut)
public class ApiResponseDTO {

    private int status;
    private Object data; 
    private String error;
    private Object meta; 

    public ApiResponseDTO(int status, Object data, String error, Object meta) {
        this.status = status;
        this.data = data;
        this.error = error;
        this.meta = meta;
    }
}