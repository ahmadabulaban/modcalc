package com.insanwalat.modcalc.fanesp.exceptions;

public class InvalidFanEspSaveInputException extends RuntimeException {

    public InvalidFanEspSaveInputException() {
    }

    public InvalidFanEspSaveInputException(String message) {
        super(message);
    }

    public InvalidFanEspSaveInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidFanEspSaveInputException(Throwable cause) {
        super(cause);
    }

    public InvalidFanEspSaveInputException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
