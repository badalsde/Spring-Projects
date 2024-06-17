package com.movie.exception;

import java.io.Serial;

public class MovieException extends Exception {
    @Serial
    private static final long serialVersionUID=1L;
    public MovieException(){}
    public MovieException(String message){
        super(message);
    }
}
