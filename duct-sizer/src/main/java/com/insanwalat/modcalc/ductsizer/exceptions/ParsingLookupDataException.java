package com.insanwalat.modcalc.ductsizer.exceptions;

public class ParsingLookupDataException extends RuntimeException {

    public ParsingLookupDataException() {
    }

    public ParsingLookupDataException(String message) {
        super(message);
    }

    public ParsingLookupDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParsingLookupDataException(Throwable cause) {
        super(cause);
    }

    public ParsingLookupDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
