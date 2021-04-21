package com.arthur.delivery.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(Object id){
        super("id nao encontrado " + id);
    }

}
