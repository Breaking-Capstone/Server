package com.capstone.newtral.config.exception;

import lombok.Getter;

public class LoginErrorException extends RuntimeException{

    public LoginErrorException(String m){
        super(m);
    }
}
