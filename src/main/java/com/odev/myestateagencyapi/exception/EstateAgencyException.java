package com.odev.myestateagencyapi.exception;

import lombok.Getter;

public class EstateAgencyException extends RuntimeException {
    @Getter
    private ErrorCodes errorCode;

    public EstateAgencyException(String exMessage, Exception exception, ErrorCodes errorCode) {
        super(exMessage, exception);
        this.errorCode = errorCode;
    }

    public EstateAgencyException(String exMessage) {
        super(exMessage);
    }
}
