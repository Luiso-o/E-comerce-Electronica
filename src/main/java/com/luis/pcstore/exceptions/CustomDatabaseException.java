package com.luis.pcstore.exceptions;

import org.springframework.dao.DataAccessException;

public class CustomDatabaseException extends DataAccessException {
    public CustomDatabaseException(String message, DataAccessException e){
        super(message, e);
    }
}
