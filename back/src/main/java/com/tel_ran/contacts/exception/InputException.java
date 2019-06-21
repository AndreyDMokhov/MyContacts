package com.tel_ran.contacts.exception;

import org.springframework.http.HttpStatus;

public class InputException extends BaseException {


    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }


}
