package com.insanwalat.modcalc.fanesp.exceptions;

public class InvalidFanEspCalcInputException extends RuntimeException {

    public InvalidFanEspCalcInputException() {
    }

    public InvalidFanEspCalcInputException(String message) {
        super(message);
    }

    public InvalidFanEspCalcInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidFanEspCalcInputException(Throwable cause) {
        super(cause);
    }

    public InvalidFanEspCalcInputException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
