package com.icwd.serviceregistry.scm20.helpers;

public class ResourceNotFoundException extends RuntimeException
{
    public ResourceNotFoundException(String message)
    {
        super(message);
    }
    public ResourceNotFoundException()
    {
        super("Resource not found!");
    }
}
