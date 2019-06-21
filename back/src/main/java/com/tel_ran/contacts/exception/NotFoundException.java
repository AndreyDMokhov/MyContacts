package com.tel_ran.contacts.exception;


import org.springframework.http.HttpStatus;


public class NotFoundException extends BaseException{

    private String MESSAGE = "A contact is not exists";

    public NotFoundException(String s) {
        this.MESSAGE = s;
    }

    public NotFoundException() {
    }

    @Override
    public String getMessage() {
        return MESSAGE;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
