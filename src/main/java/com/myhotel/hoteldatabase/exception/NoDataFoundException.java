package com.myhotel.hoteldatabase.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class NoDataFoundException extends Exception{
    public NoDataFoundException(String message){
        super(message);
    }
}
