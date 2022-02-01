package com.example.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    private String resourceName;
    private String fieldName;
    private String valueName;

    public ResourceNotFoundException(String resourceName, String fieldName, String valueName)
    {
        super(String.format("%s not found with %s : %s", resourceName, fieldName, valueName)); //Post not found with id : 1
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.valueName = valueName;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }
}
