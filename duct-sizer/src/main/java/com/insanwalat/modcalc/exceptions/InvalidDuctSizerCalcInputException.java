package com.insanwalat.modcalc.exceptions;

public class InvalidDuctSizerCalcInputException extends RuntimeException {

    public InvalidDuctSizerCalcInputException() {
    }

    public InvalidDuctSizerCalcInputException(String message) {
        super(message);
    }

    public InvalidDuctSizerCalcInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidDuctSizerCalcInputException(Throwable cause) {
        super(cause);
    }

    public InvalidDuctSizerCalcInputException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
